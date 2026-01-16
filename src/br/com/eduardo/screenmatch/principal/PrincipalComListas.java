package br.com.eduardo.screenmatch.principal;

import br.com.eduardo.screenmatch.modelos.Filme;
import br.com.eduardo.screenmatch.modelos.Serie;
import br.com.eduardo.screenmatch.modelos.Titulo;

import java.util.*;

public class PrincipalComListas {
    public static void main(String[] args) {
        Filme meuFilme = new Filme("O Poderoso Chefão", 1970);
        meuFilme.avalia(9);
        Filme outroFilme = new Filme("Avatar", 2023);
        outroFilme.avalia(6);
        var filmeDoPaulo = new Filme("Dogville", 2003);
        filmeDoPaulo.avalia(10);
        Serie lost = new Serie("Lost", 2000);

        //Para mesclar filmes e séries basta parametrizar a lista como titulo pois é superclass de filme e serie
        ArrayList<Titulo> lista = new ArrayList<>();
        lista.add(filmeDoPaulo);
        lista.add(meuFilme);
        lista.add(outroFilme);
        lista.add(lost);

        for (int i = 0; i < lista.size(); i++) {
            System.out.println(lista.get(i).toString());
        }

        //Outra maneira de iterar: é o for each. Aparece com a palavra reservada iter
        //Para cada item do tipo Titulo da lista faça... Essa é a interpretação
        for (Titulo item : lista) {
            System.out.println(item.getNome());
            if(item instanceof Filme filme) { //Neste caso o cast já está sendo feito na parametrização do if, então a forma de fazer cast da linha a seguir não é necessária
                //Isso é um cast que diz que o que virá a seguir é tipo filme e pode tratar desse modo
                //Tendo em vista que parametrizamos Titulo que pode ser filme ou serie
               // Filme filme = (Filme) item;
                System.out.println("Classificação: " + filme.getClassificacao());
            }

        }

        List<String> buscaPorArtista = new ArrayList<>();
        buscaPorArtista.add("Adam Sandler");
        buscaPorArtista.add("Paulo");
        buscaPorArtista.add("Jaqueline");
        System.out.println(buscaPorArtista);

        Collections.sort(buscaPorArtista);
        System.out.println(buscaPorArtista);

        Collections.sort(lista);
        System.out.println(lista);

        lista.sort(Comparator.comparing(Titulo::getAnoLancamento));
        System.out.println(lista);



    }
}
