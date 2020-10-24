package br.unifil.dc.sisop;

import java.io.File;
import java.util.Optional;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;


public final class ComandosInternos {
    
    public static int exibirRelogio() {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));
        return 1;
    }
    
    public static int escreverListaArquivos(Optional<String> nomeDir) {
        File curDir = new File(".");
        File[] filesList = curDir.listFiles();
        for(File f : filesList){

            if(f.isDirectory())
                System.out.println(f.getName());
            if(f.isFile()){
                System.out.println(f.getName());
            }
        }
        return 1;
    }
    
    public static int criarNovoDiretorio(String nomeDir) {
        File file = new File(Jsh.userDir + "\\" + nomeDir);
        if (file.mkdirs()) {
            System.out.println("Directorio criado");
        } else {
            System.out.println("falha para criar diretorio");
        }
        return 1;
    }
    
    public static int apagarDiretorio(String nomeDir) {
        File file = new File(nomeDir);
        if(file.delete()){
            System.out.println(nomeDir + " deletado");
        }else System.out.println(nomeDir + " não existe neste diretorio");
        return 1;
    }
    
    public static int mudarDiretorioTrabalho(String nomeDir){
        String separador = System.getProperty("file.separator");
        String acPath = System.getProperty("user.dir");
        int x = 0;
        File dir;

        if (nomeDir.startsWith("..")){
            for (int i = 0; (i < nomeDir.length()) && (nomeDir.charAt(i) == '.'); i++){
                x++;
            }
            for (int i = 0; i < x - 1; i++){
                acPath = acPath.substring(0, acPath.lastIndexOf(separador));
            }
            dir = new File(acPath);

        }else  if (acPath.contains(nomeDir)){
            dir = new File(acPath.substring(0, acPath.indexOf(nomeDir))+ nomeDir);
            acPath = dir.getPath();

        }else {
            acPath = acPath + separador + nomeDir;
            dir = new File(acPath);
        }

        if (dir.exists() && dir.isDirectory()){
            System.setProperty("user.dir", acPath);

        }else {
            System.out.println("caminho inexistente");

        }
        return 1;

    }
    private ComandosInternos() {}
}
