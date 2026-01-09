package br.com.eduardo.screenmatch.modelos;

import br.com.eduardo.screenmatch.calculos.Classificavel;

public class Episodio implements Classificavel {
    private int numero;
    private String nome;
    private Serie serie;
    private int totalVisualizações;

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    public int getTotalVisualizações() {
        return totalVisualizações;
    }

    public void setTotalVisualizações(int totalVisualizações) {
        this.totalVisualizações = totalVisualizações;
    }

    @Override
    public int getClassificacao() {
        if(totalVisualizações > 100){
            return 4;
        } else {
            return 2;
        }
    }
}
