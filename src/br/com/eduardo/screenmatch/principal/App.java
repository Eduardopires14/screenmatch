package br.com.eduardo.screenmatch.principal;

import br.com.eduardo.screenmatch.calculos.CalculadoraDeTempo;
import br.com.eduardo.screenmatch.calculos.FiltroDeRecomendacao;
import br.com.eduardo.screenmatch.modelos.Episodio;
import br.com.eduardo.screenmatch.modelos.Filme;
import br.com.eduardo.screenmatch.modelos.Serie;

import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        Filme meuFilme = new Filme("O poderoso chefão",1970, "Roberval");

        //Foi criado um método construtor;
        //meuFilme.setNome("O poderoso chefão");
        //meuFilme.setAnoLancamento(1970);
        meuFilme.setDuracaoEmMinutos(180);

        meuFilme.exibeFichaTecnica();
        meuFilme.avalia(8);
        meuFilme.avalia(5);
        meuFilme.avalia(9);
        System.out.println("Total de avaliações: " + meuFilme.getTotalDeAvaliacoes());
        System.out.println(String.format("Média: %.2f", meuFilme.pegaMedia()));

        Serie lost = new Serie("Lost", 2000);

        //Foi criado um método construtor;
        //lost.setNome("Lost");
        //lost.setAnoLancamento(2000);

        lost.setTemporadas(10);
        lost.setEpisodiosPorTemporada(10);
        lost.setMinutosPorEpisodio(50);
        lost.exibeFichaTecnica();

        Filme outroFilme = new Filme("Avatar", 2023);

        //Foi criado um método construtor;
        //outroFilme.setNome("Avatar");
        //outroFilme.setAnoLancamento(2023);
        outroFilme.setDuracaoEmMinutos(200);

        CalculadoraDeTempo calculadora = new CalculadoraDeTempo();
        calculadora.inclui(meuFilme);
        calculadora.inclui(outroFilme);
        calculadora.inclui(lost);
        System.out.println(calculadora.getTempoTotal());

        FiltroDeRecomendacao filtro = new FiltroDeRecomendacao();
        filtro.filtra(meuFilme);

        Episodio episodio = new Episodio();
        episodio.setNumero(1);
        episodio.setSerie(lost);
        episodio.setTotalVisualizações(300);
        filtro.filtra(episodio);

        Filme filmeDoPaulo = new Filme("Dogville",2003);

        //Foi criado um método construtor;
        //filmeDoPaulo.setNome("Dogville");
        //filmeDoPaulo.setAnoLancamento(2003);
        filmeDoPaulo.setDuracaoEmMinutos(200);
        filmeDoPaulo.avalia(10);

        ArrayList<Filme> listaDeFilmes = new ArrayList<>();
        listaDeFilmes.add(filmeDoPaulo);
        listaDeFilmes.add(meuFilme);
        listaDeFilmes.add(outroFilme);
        System.out.println("Tamanho da lista: " + listaDeFilmes.size());
        System.out.println("Primeiro filme: " + listaDeFilmes.get(0).getNome());

        System.out.println(listaDeFilmes);






    }
}
