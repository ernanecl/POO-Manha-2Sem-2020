package model;

import java.util.ArrayList;

public class Carrinho {
    private ArrayList<Produto> produtos = new ArrayList<>();


    public Carrinho() {
    }

    public Carrinho(ArrayList<Produto> produtos) {
        this.produtos = produtos;
    }

    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public void addProduto(Produto produto) {
        this.produtos.add(produto);
    }

    //retornar TRUE quando não estiver vazio
    //retornar FALSE quando estiver vazio
    public boolean isNotEmpty() {
        return !(this.produtos.size() <= 0);
    }

    public void limparCarrinho() {
        this.produtos.clear();
    }
}
