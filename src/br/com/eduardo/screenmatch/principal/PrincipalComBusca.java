package br.com.eduardo.screenmatch.principal;

import br.com.eduardo.screenmatch.excecao.ErroDeConversaoDeAnoException;
import br.com.eduardo.screenmatch.modelos.Titulo;
import br.com.eduardo.screenmatch.modelos.TituloOmdb;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrincipalComBusca {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner leitura = new Scanner(System.in);
        String busca = "";
        List<Titulo> listaTitulos = new ArrayList<>();

        //Essa linha cria uma instância da classe Gson e a atribui à variável gson.
        // A classe Gson é a principal classe da biblioteca GSON, responsável por realizar a conversão entre objetos Java e JSON.
        //Cria uma instância do Gson configurada para converter automaticamente os nomes dos campos JSON do formato "Upper Camel Case" para o formato "lowerCamelCase" durante a deserialização.
        // Isso permite que o código da aula mapeie corretamente os dados JSON da API OMDb para os campos correspondentes no objeto Java,
        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).setPrettyPrinting().create();

        while (!busca.equalsIgnoreCase("sair")) {

            System.out.println("Digite um filme para busca");
            busca = leitura.nextLine();

            if (busca.equalsIgnoreCase("sair")){
                break;
            }

            //O replace substitui o espaço em branco por um sinal de + para poder fazer a busca correta na URL que não aceita espaços
            String endereco = "https://www.omdbapi.com/?t=" + busca.replace(" ", "+") + "&apiKey=a3137558";

            try {

                //Cria o cliente HTTP, responsável por enviar as requisições
                HttpClient client = HttpClient.newHttpClient();
                //Criação da requisição HTTP
                //HttpRequest.newBuilder()
                //Inicia a construção de novo objeto de requisição no padrão builder
                //Builder é um padrão que ajuda a criar objetos complexos passo a passo
                //.uri(URI.create("https://www.omdbapi.com/?t=matrix&apiKey=a3137558"))
                //Endereço para onde a requisição será enviada
                //Contém a API e a apiKey para autenticação
                //.build()
                //finaliza a construção da requisição HTTP, criando um objeto HttpRequest completo e pronto para ser enviado.
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(endereco))
                        .build();
                //Esta linha envia a requisição HTTP que foi construída e recebe a resposta do servidor.
                //client.send(request, HttpResponse.BodyHandlers.ofString()) envia a requisição (request) usando o cliente HTTP (client).
                //O segundo argumento, HttpResponse.BodyHandlers.ofString(), especifica que o corpo da resposta deve ser tratado como uma String.
                //HttpResponse<String> response armazena a resposta do servidor em um objeto HttpResponse.
                HttpResponse<String> response = client
                        .send(request, HttpResponse.BodyHandlers.ofString());
                //Finalmente, esta linha imprime o corpo da resposta no console.

                // declara uma variável chamada json do tipo String.
                // Ela recebe o valor retornado pelo método response.body()
                //response.body() retorna uma String contendo os dados em formato JSON da resposta de uma requisição a uma API.
                String json = response.body();
                //Essa linha imprime no console o conteúdo da variável json
                // ela mostra o JSON bruto que foi recebido da API.
                System.out.println(json);



                //utiliza o método fromJson() do objeto gson para converter a String JSON armazenada na variável json em um objeto da classe Titulo.
                // O resultado da conversão é armazenado na variável meuTitulo.
                //Titulo meuTitulo = gson.fromJson(json, Titulo.class); COMENTEI PARA TESTAR O USO DE RECORD PARA IDENTIFICAR OS ATRIBUTOS DO JSON

                //USO DE RECORD PARA IDENTIFICAR OS ATRIBUTOS DO JSON
                TituloOmdb meuTituloOmdb = gson.fromJson(json, TituloOmdb.class);
                //Essa linha imprime no console o objeto meuTitulo.
                // Para que a impressão seja útil, a classe Titulo deve ter o método toString() implementado, retornando uma representação textual do objeto.
                System.out.println(meuTituloOmdb);


                Titulo meuTitulo = new Titulo(meuTituloOmdb);
                System.out.println("Título já convertido");
                System.out.println(meuTitulo);

                //ERA SÓ UM EXEMPLO DE COMO USAR O FileWriter
                    //FileWriter escrita = new FileWriter("filmes.txt");
                    //escrita.write(meuTitulo.toString());
                    // escrita.close();

                listaTitulos.add(meuTitulo);

            } catch (NumberFormatException e) {
                System.out.println("Aconteceu um erro!");
                System.out.println(e.getMessage());

            } catch (IllegalArgumentException e) {
                System.out.println("Algum erro de argumento na busca, verifique o endereço");
            } catch (ErroDeConversaoDeAnoException e) {
                System.out.println(e.getMessage());
            } finally {
                System.out.println("O programa finalizou corretamente");

            }

        }

        System.out.println(listaTitulos);
        FileWriter escrita = new FileWriter("filmes.json");
        escrita.write(gson.toJson(listaTitulos));
        escrita.close();

    }
}
