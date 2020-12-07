package view;

import helper.Utils;
import jdk.jshell.execution.Util;
import model.Produto;

import java.util.*;

public class Mercado {

    private  static Scanner teclado = new Scanner(System.in);
    private static ArrayList<Produto>produtos;
    private static Map<Produto, Integer> carrinho;

    public static void main(String[] args) {

        produtos = new ArrayList<Produto>();
        carrinho = new HashMap<Produto, Integer>();
        Mercado.menu();
    }

    private static void menu() {
        System.out.println("============ Bem-vinda(o) ============");
        System.out.println("============   POO Shop   ============");
        System.out.println("\nSelecione uma opção");
        System.out.println("1 - Cadastrar produto");
        System.out.println("2 - Listar produtos");
        System.out.println("3 - Comprar produto");
        System.out.println("4 - Consultar carrinho");
        System.out.println("5 - Sair");

        int opcao = 0;

        try{
            opcao = Integer.parseInt(Mercado.teclado.nextLine());
        }catch(InputMismatchException e) {
            Mercado.menu();
        }catch(NumberFormatException f) {
            Mercado.menu();
        }

        switch (opcao) {
            case 1:
                Mercado.cadastrarProduto();
                break;

            case 2:
                Mercado.listarProdutos();
                break;

            case 3:
                Mercado.comprarProduto();
                break;

            case 4:
                Mercado.consultarCarrinho();
                break;

            case 5:
                System.out.println("Volte sempre!");
                Utils.pausar(2);
                System.exit(0);
            default:
                System.out.println("Opção inválida.");
                Utils.pausar(2);
                Mercado.menu();
                break;
        }
    }

    private static void cadastrarProduto() {
        System.out.println("Cadastro de produto\n");

        System.out.println("Informe o nome do produto: ");
        String nome = Mercado.teclado.nextLine();

        System.out.println("Informe o preço do produto: ");
        Double preco = Mercado.teclado.nextDouble();

        Produto produto = new Produto(nome, preco);

        Mercado.produtos.add(produto);

        System.out.println("O produto " + produto.getNome() + " foi cadastro com sucesso.");
        Utils.pausar(2);
        Mercado.menu();
    }

    private static void listarProdutos() {
        if (Mercado.produtos.size() > 0) {
            System.out.println("Listagen de produtos\n");

            for (Produto p : Mercado.produtos) {
                System.out.println(p + "\n");
            }
        }else {
            System.out.println("Ainda não existem produtos cadastrados.");
        }

        Utils.pausar(2);
        Mercado.menu();
    }

    private static void consultarCarrinho() {
        if (Mercado.carrinho.size() > 0) {
            System.out.println("Produtos no carrinho: \n");

            for (Produto p : Mercado.carrinho.keySet()) {
                System.out.println("Produto: " + p + "\nQuantidade: " + Mercado.carrinho.get(p));
            }

        }else {
            System.out.println("Ainda nçao existem produtos no carrinho.");
        }

        Utils.pausar(2);
        Mercado.menu();
    }

    private static void comprarProduto() {
        if (Mercado.produtos.size() > 0){
            System.out.println("Informe o código do produto que desejar comprar: \n");

            System.out.println("===== Produtos disponíveis =====");
            for (Produto p : Mercado.produtos){
                System.out.println(p + "\n");
            }
            int codigo = Integer.parseInt(Mercado.teclado.nextLine());
            boolean tem = false;

            for(Produto p : Mercado.produtos) {
                if (p.getCodigo() == codigo) {
                    int quant = 0;
                    try {
                        quant = Mercado.carrinho.get(p);
                        //já existe este produto no carrinho, atualiza quantidade
                        Mercado.carrinho.put(p, quant + 1);
                    }catch (NullPointerException e) {
                        //primeiro produto no carrinho
                        Mercado.carrinho.put(p, 1);
                    }

                    System.out.println("O produto " + p.getNome() + " foi adicionado ao carrinho.");
                    tem = true;
                }
                if(tem) {
                    System.out.println("Deseja adicionar outros produtos ao carrinho?");
                    System.out.println("Informe 1 para sim ou 0 para não");
                    int op = Integer.parseInt(Mercado.teclado.nextLine());

                    if (op == 1) {
                        Mercado.comprarProduto();
                    }else {
                        System.out.println("Por favor, aguarde enquanto fechamos seu pedido...");
                        Utils.pausar(2);
                        Mercado.fecharProduto();
                    }
                }else {
                    System.out.println("Não foi encontrado o produto com o código " + codigo);
                    Utils.pausar(2);
                    Mercado.menu();
                }
            }
        }else {
            System.out.println("Ainda não existe produto cadastrado no mercado.");
            Utils.pausar(2);
            Mercado.menu();
        }
    }

    private static void fecharProduto() {
        Double valorTotal = 0.0;
        System.out.println("Produtos do Carrinho\n");
        for (Produto p : Mercado.carrinho.keySet()) {
            int quant = Mercado.carrinho.get(p);
            valorTotal += p.getPreco() * quant;
            System.out.println(p);
            System.out.println("Qauntidade: " + quant + "\n");
        }
        System.out.println("Sua fatura é " + Utils.doubleParaString(valorTotal));
        Mercado.carrinho.clear();
        System.out.println("Obrigado pela preferência");
        Utils.pausar(5);
        Mercado.menu();

    }
}
