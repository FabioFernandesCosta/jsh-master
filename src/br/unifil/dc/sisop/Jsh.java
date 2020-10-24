package br.unifil.dc.sisop;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public final class Jsh {

    public static String userName;
    public static String userDir;
    public static int    userUID;

    public static void promptTerminal() {

        while (true) {
    		exibirPrompt();
    		ComandoPrompt comandoEntrado = lerComando();
    		executarComando(comandoEntrado);
    	}
    }

    public static void exibirPrompt() {

        userName  = System.getProperty("user.name");
        userDir = System.getProperty("user.dir");

        try {
            String command = "id -u "+userName;
            Process child = Runtime.getRuntime().exec(command);

            InputStream in = child.getInputStream();
            byte[] arrSaida = in.readAllBytes();

            userUID = arrSaida[0];
        }catch (IOException e){
        }
        System.out.print(userName + "#" + userUID + ":" + userDir + "% ");

    }

    public static ComandoPrompt lerComando() {

        Scanner sc1 = new Scanner(System.in);
        String cm = sc1.nextLine();

        ComandoPrompt cmdPrompt = new ComandoPrompt(cm);
        return cmdPrompt;
    }


    public static void executarComando(ComandoPrompt comando) {
        switch (comando.getNome()){
            case "encerrar":
                System.exit(0);
            case "relogio":
                ComandosInternos.exibirRelogio();
                break;
            case "la":
                ComandosInternos.escreverListaArquivos(java.util.Optional.ofNullable(userDir));
                break;
            case "cd":
                ComandosInternos.criarNovoDiretorio((String)comando.getArgumentos().get(0));
                break;
            case "ad":
                ComandosInternos.apagarDiretorio((String)comando.getArgumentos().get(0));
                break;
            case "mdt":
                ComandosInternos.mudarDiretorioTrabalho((String)comando.getArgumentos().get(0));
                break;
            default:
                executarPrograma(comando);
                break;
        }
    }

    public static int executarPrograma(ComandoPrompt comando) {
        Runtime runTime = Runtime.getRuntime();
        File curDir = new File(".");
        File[] filesList = curDir.listFiles();
        for(File f : filesList){
            if(f.isFile()){
                if ((String)comando.getArgumentos().get(0) == f.getName()){
                    try {
                        String executablePath = f.getName();
                        Process process = runTime.exec(executablePath);
                    }catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return 1;
    }

    public static void main(String[] args) {

        promptTerminal();
    }
    private Jsh() {}
}