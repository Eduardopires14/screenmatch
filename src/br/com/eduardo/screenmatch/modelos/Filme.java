package br.com.eduardo.screenmatch.modelos;

import br.com.eduardo.screenmatch.calculos.Classificavel;

public class Filme extends Titulo implements Classificavel {
    private String diretor;

    public Filme(String nome, int anoLancamento) {
        super(nome, anoLancamento);
    }

    public Filme(String nome, int anoLancamento, String diretor) {
        super(nome, anoLancamento);
        this.diretor = diretor;
    }

    //Esse construtor permite criar sem inserir necessáriamente inserir um atributo
    /*public Filme (){

    }*/

    public String getDiretor() {
        return diretor;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    @Override
    public int getClassificacao() {
        return (int) pegaMedia()/2;
    }

    @Override
    public String toString() {
        return"Filme: " + this.getNome() + " (" + this.getAnoLancamento() + ")" ;
    }
}
