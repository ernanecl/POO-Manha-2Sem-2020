package model;

import helper.Utils;

import java.util.Random;

public class Produto {

    private static int contador = 1;

    private int codigo;
    private String nome;
    private Double preco;
    private int quantidade = 1;

    public Produto() {
    }


    public Produto(String nome, Double preco) {
        this.codigo = gerarValorRandomico();
        this.nome = nome;
        this.preco = preco;
    }

    private int gerarValorRandomico() {
        Random gerador = new Random();
        return gerador.nextInt(7000);
    }


    public static int getContador() {
        return contador;
    }

    public double valorTotal(){
        return this.preco * this.quantidade;
    }

    public static void setContador(int contador) {
        Produto.contador = contador;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String toString() {
        int codigo = this.codigo;
        String nome = this.nome;
        double preco = this.preco;
        int quantidade = this.quantidade;
        double valorTotal = this.valorTotal();
        String texto = "\n" + "CODIGO: "+ codigo + "\n" +
                " NOME: " + nome + "\n" +
                " QUANTIDADE: " + quantidade + "\n" +
                " PRECO: " + preco + "\n" +
                " VALOR TOTAL " + valorTotal;
        System.out.println(texto);
        return texto;
    }

    public void adicionarQuantidade() {
        this.quantidade = this.quantidade + 1;
    }

}
