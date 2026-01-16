package br.com.eduardo.screenmatch.modelos;

import br.com.eduardo.screenmatch.excecao.ErroDeConversaoDeAnoException;
import com.google.gson.annotations.SerializedName;

public class Titulo implements Comparable<Titulo> {
    //FOI APAGADO POIS FOI IMPLEMENTADO O RECORD
    //@SerializedName("Title") //Usado para identificar no JSON qual texto entre parentese se refere a qual na classe do java
    private String nome;
    //@SerializedName("Year")
    private int anoLancamento;
    private boolean incluidoNoPlano;
    private double somaDasAvaliacoes;
    private int totalDeAvaliacoes;
    private int duracaoEmMinutos;

    public Titulo(String nome, int anoLancamento) {
        this.nome = nome;
        this.anoLancamento = anoLancamento;
    }

    public Titulo(TituloOmdb meuTituloOmdb) {
        this.nome = meuTituloOmdb.title();

        if (meuTituloOmdb.year().length() > 4){
            throw  new ErroDeConversaoDeAnoException("Não consegui converter o ano pois o conteúdo informado possui mais que 4 caracteres");

        }
        // O Integer.valueOf(meuTituloOmdb.year()); converte o conteúdo recebido para um valor
        this.anoLancamento = Integer.valueOf(meuTituloOmdb.year());
        //.substring(0,2) É usado para resgatar apenas uma parte da String passada pelo JSON. Neste caso os caracteres 0 até o 2.
        this.duracaoEmMinutos = Integer.valueOf((meuTituloOmdb.runtime().substring(0,2)));
    }


    public int getTotalDeAvaliacoes(){
        return totalDeAvaliacoes;
    }

    public String getNome() {
        return nome;
    }

    public int getAnoLancamento() {
        return anoLancamento;
    }

    public boolean isIncluidoNoPlano() {
        return incluidoNoPlano;
    }

    public int getDuracaoEmMinutos() {
        return duracaoEmMinutos;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIncluidoNoPlano(boolean incluidoNoPlano) {
        this.incluidoNoPlano = incluidoNoPlano;
    }

    public void setAnoLancamento(int anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public void setDuracaoEmMinutos(int duracaoEmMinutos) {
        this.duracaoEmMinutos = duracaoEmMinutos;
    }

    public void exibeFichaTecnica(){
        System.out.println("Nome do filme: " + nome);
        System.out.println("Ano de lançamento: " + anoLancamento);
        System.out.println("Duração em minutos: " + duracaoEmMinutos);
    }

    public void avalia(double nota){
        somaDasAvaliacoes += nota;
        totalDeAvaliacoes++;
    }

    public double pegaMedia(){
        return somaDasAvaliacoes/totalDeAvaliacoes;
    }

    @Override
    public int compareTo(Titulo outroTitulo) {
        return this.getNome().compareTo(outroTitulo.getNome());
    }

    @Override
    public String toString() {
        return "(anoLancamento = " + anoLancamento +
                ", nome = '" + nome +
                ", Duração= " + duracaoEmMinutos + ")";
    }
}
