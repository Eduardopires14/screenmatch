package br.com.eduardo.screenmatch.principal;

import br.com.eduardo.screenmatch.excecao.ErroDeConversaoDeAnoException;
import br.com.eduardo.screenmatch.modelos.Titulo;
import br.com.eduardo.screenmatch.modelos.TituloOmdb;
import br.com.eduardo.screenmatch.modelos.OmdbService;
import br.com.eduardo.screenmatch.modelos.FileUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrincipalComBuscaOrganizada {
    public static void main(String[] args) {
        Scanner leitura = new Scanner(System.in);
        String busca = "";
        List<Titulo> listaTitulos = new ArrayList<>();
        OmdbService omdbService = new OmdbService();

        while (!busca.equalsIgnoreCase("sair")) {
            System.out.println("Digite um filme para busca");
            busca = leitura.nextLine();

            if (busca.equalsIgnoreCase("sair")) break;

            try {
                TituloOmdb meuTituloOmdb = omdbService.buscarTitulo(busca);
                System.out.println(meuTituloOmdb);

                Titulo meuTitulo = new Titulo(meuTituloOmdb);
                System.out.println("Título já convertido: " + meuTitulo);

                listaTitulos.add(meuTitulo);

            } catch (NumberFormatException e) {
                System.out.println("Erro de conversão numérica: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Erro de argumento na busca, verifique o endereço.");
            } catch (ErroDeConversaoDeAnoException e) {
                System.out.println(e.getMessage());
            } catch (IOException | InterruptedException e) {
                System.out.println("Erro de comunicação com a API: " + e.getMessage());
            } finally {
                System.out.println("Iteração finalizada.");
            }
        }

        try {
            FileUtil.salvarJson("filmes.json", listaTitulos, omdbService.getGson());
            System.out.println("Lista salva em filmes.json");
        } catch (IOException e) {
            System.out.println("Erro ao salvar arquivo: " + e.getMessage());
        }
    }
}
