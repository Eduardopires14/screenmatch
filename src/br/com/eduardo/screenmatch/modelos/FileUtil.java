package br.com.eduardo.screenmatch.modelos;

import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileUtil {

    public static <T> void salvarJson(String nomeArquivo, List<T> lista, Gson gson) throws IOException {
        try (FileWriter escrita = new FileWriter(nomeArquivo)) {
            escrita.write(gson.toJson(lista));
            escrita.close();
        }
    }
}
