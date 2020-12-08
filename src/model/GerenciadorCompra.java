package model;

import exceptions.FecharComprarException;
import exceptions.ProcessarFaturaException;
import helper.Utils;

public class GerenciadorCompra {
    double valorFatura;

    public static Carrinho fecharCompra(Carrinho carrinho){
        try{
            System.out.println("Por favor, aguarde enquanto fechamos seu pedido...");
            Utils.pausar(2);
            GerenciadorCompra.mostrarProdutosDoCarrinho(carrinho);
            double valorFatura = calcularFatura(carrinho);
            System.out.println("\n" + "Sua fatura é " + Utils.doubleParaString(valorFatura));
            carrinho.limparCarrinho();
            System.out.println("Obrigado pela preferência");
            Utils.pausar(5);
            return carrinho;
        } catch(Exception e){
            throw new FecharComprarException("ERRO AO FECHAR COMPRA");
        }

    }
    

    public static double calcularFatura(Carrinho carrinho) {
        double valorFatura = 0.0;
        try{
            for (Produto produto : carrinho.getProdutos()) {
                valorFatura = valorFatura + produto.valorTotal();
            }
            return valorFatura;
        } catch(Exception e){
            throw new ProcessarFaturaException("ERRO AO CALCULAR FATURA");
        }

    }

    private static void mostrarProdutosDoCarrinho(Carrinho carrinho) {
        System.out.println("Produtos do Carrinho\n");
        for (Produto produto : carrinho.getProdutos()) {
            produto.toString();
        }
    }
}
