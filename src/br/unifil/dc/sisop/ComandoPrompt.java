package br.unifil.dc.sisop;

import java.util.List;
import java.util.Arrays;
import java.util.Collections;

public class ComandoPrompt {

    public ComandoPrompt(String comando) {
        if(comando.contains(" ")){
            nome = comando.substring(0, comando.indexOf(" "));
            argumentos = (comando.substring(comando.indexOf(" ")+1)).split(" ");
        }else {
            nome = comando;
            argumentos = new String[0];
        }
    }

    public String getNome() {
        return nome;
    }

    public List<String> getArgumentos() {
        return Collections.unmodifiableList(Arrays.asList(this.argumentos));
    }
    private final String nome;
    private final String[] argumentos;
}
