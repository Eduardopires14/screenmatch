package br.com.eduardo.screenmatch.modelos;

import br.com.eduardo.screenmatch.modelos.TituloOmdb;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class OmdbService {
    private final String apiKey = "a3137558";
    private final HttpClient client;
    private final Gson gson;

    public OmdbService() {
        this.client = HttpClient.newHttpClient();
        this.gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .create();
    }

    public TituloOmdb buscarTitulo(String nomeFilme) throws IOException, InterruptedException {
        String endereco = "https://www.omdbapi.com/?t=" + nomeFilme.replace(" ", "+") + "&apiKey=" + apiKey;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return gson.fromJson(response.body(), TituloOmdb.class);
    }

    public Gson getGson() {
        return gson;
    }
}