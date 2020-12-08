package model;

import exceptions.CadastrarProdutoException;
import exceptions.CarrinhoException;
import exceptions.CompraException;
import exceptions.ProdutoException;
import helper.Utils;
import jdk.jshell.execution.Util;
import model.Carrinho;
import model.GerenciadorCompra;
import model.Produto;

import java.util.*;

public class Mercado {

    private Scanner teclado = new Scanner(System.in);
    private Carrinho carrinho =  new Carrinho();
    private ArrayList<Produto>produtos = new ArrayList<Produto>();

    public void abrirMenu() {
        System.out.println("============ Bem-vinda(o) ============");
        System.out.println("============   POO Shop   ============");
        System.out.println("\nSelecione uma opção");
        System.out.println("1 - Cadastrar produto");
        System.out.println("2 - Listar produtos");
        System.out.println("3 - Comprar produto");
        System.out.println("4 - Consultar carrinho");
        System.out.println("5 - Sair");

        try{
            Integer opcao = 0;
            opcao = this.teclado.nextInt();
            clearBuffer(teclado);

            switch (opcao) {
                case 1:
                    cadastrarProduto();
                    abrirMenu();
                    break;

                case 2:
                    mostrarProdutosDisponveis();
                    abrirMenu();
                    break;

                case 3:
                    processoCompra();
                    break;

                case 4:
                    consultarCarrinho();
                    abrirMenu();
                    break;

                case 5:
                    System.out.println("Volte sempre!");
                    Utils.pausar(2);
                    System.exit(0);
                default:
                    System.out.println("Opção inválida.");
                    Utils.pausar(2);
                    abrirMenu();
                    break;
            }
        } catch(Exception e){
            throw e;
        }

    }

    private void cadastrarProduto() {
        try{
            System.out.println("Cadastro de produto\n");

            System.out.println("Informe o nome do produto: ");
            String nome = teclado.nextLine();

            System.out.println("Informe o preço do produto: ");
            Double preco = Double.parseDouble(teclado.nextLine());

            Produto produto = new Produto(nome, preco);

            produtos.add(produto);

            System.out.println("O produto " + produto.getNome() + " foi cadastro com sucesso.");
            Utils.pausar(2);
        } catch(Exception e){
            throw new CadastrarProdutoException("ERRO AO CADASTRAR PRODUTO");
        }

    }

    private void mostrarProdutosDisponveis() {
        try{
            if (produtos.size() > 0) {
                System.out.println("Listagem de produtos\n");

                for (Produto produto : produtos) {
                    String texto = "\n" + "CODIGO: "+ produto.getCodigo() + "\n"
                            + "NOME: " + produto.getNome() + "\n"
                            + "VALOR: " + produto.getPreco() + "\n";

                    System.out.println(texto);
                }
            }else {
                System.out.println("Ainda não existem produtos cadastrados.");
            }

            Utils.pausar(2);
        } catch(Exception e){
            throw new ProdutoException("ERRO AO MOSTRAR PRODUTOS");
        }

    }

    private void consultarCarrinho() {
        try{
            if (carrinho.isNotEmpty()) {
                System.out.println("Produtos no carrinho: \n");

                for (Produto produto : carrinho.getProdutos()) {
                    produto.toString();
                }

            }else {
                System.out.println("Ainda nao existem produtos no carrinho.");
            }

            Utils.pausar(2);
        } catch(Exception e){
            throw new CarrinhoException("ERRO AO CONSULTAR CARRINHO");
        }

    }

    private void processoCompra() {
        try{
            if (listaDeProdutoIsNotEmpty()) {
                mostrarProdutosDisponveis();
                int codigo = solicitarCodigo();
                if(verificarSeExisteProdutoByCodigo(codigo)){
                    processoAdicionarItemCarrinho(codigo);
                    verificarNecessidadeDeAdicionarMaisItens();
                } else{
                    System.out.println("Codigo do produto: "+ codigo + " não existe no mercado");
                }
            }else {
                System.out.println("Ainda não existe produto cadastrado no mercado.");
                Utils.pausar(2);
                abrirMenu();
            }
        } catch(Exception e){
            throw new CompraException("ERRO AO REALIZAR PROCESSO DE COMPRA");
        }
    }

    public void verificarNecessidadeDeAdicionarMaisItens(){
        System.out.println("Deseja adicionar outros produtos ao carrinho?");
        System.out.println("Informe 1 para sim ou 0 para não");

        int opcao = Integer.parseInt(teclado.nextLine());

        if(opcao == 1){
            processoCompra();
        }else{
            fecharPedido();
        }
    }

    private void fecharPedido() {
        GerenciadorCompra.fecharCompra(carrinho);
    }


    private void processoAdicionarItemCarrinho(int codigo) {
        for(Produto produto : produtos) {
            if (produto.getCodigo() == codigo) {
                adicionarItemNoCarrinho(produto);
            }
        }
    }

    private void adicionarItemNoCarrinho(Produto produto) {
        boolean existeProdutoCarrinho = false;

        for (Produto produtoCarrinho : carrinho.getProdutos()) { //procura produto no carrinho
            if(produtoCarrinho.equals(produto)){
                produtoCarrinho.adicionarQuantidade();
                existeProdutoCarrinho = true;
            }
        }

        if(!existeProdutoCarrinho){
            carrinho.addProduto(produto);
        }
    }

    private boolean listaDeProdutoIsNotEmpty() {
        return produtos.size() > 0;
    }

    private boolean verificarSeExisteProdutoByCodigo(int codigo) {
        for(Produto produto : produtos) {
            if (produto.getCodigo() == codigo) {
                return true;
            }
        }
        return false;
    }

    private int solicitarCodigo() {
        System.out.println("Informe o código do produto que desejar adicionar ao carrinho: \n");
        int codigo = Integer.parseInt(teclado.nextLine());
        return codigo;
    }

    private static void clearBuffer(Scanner scanner) {
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
    }

}
