package br.com.eduardo.screenmatch.calculos;

import br.com.eduardo.screenmatch.modelos.Filme;
import br.com.eduardo.screenmatch.modelos.Serie;
import br.com.eduardo.screenmatch.modelos.Titulo;

public class CalculadoraDeTempo {
    private int tempoTotal;

    public int getTempoTotal() {
        return this.tempoTotal;
    }

//    public void inclui(Filme f){
//        this.tempoTotal += f.getDuracaoEmMinutos();
//    }
//
//    public void inclui(Serie s){
//        this.tempoTotal += s.getDuracaoEmMinutos();
//    }

    //A fim de evitar duplicação de código se faz o método usando a classe pai
    //isso é polimorfismo
    public void inclui(Titulo t){
        System.out.println("Adicionando duração em minutos de " + t);
        this.tempoTotal += t.getDuracaoEmMinutos();
    }


}
