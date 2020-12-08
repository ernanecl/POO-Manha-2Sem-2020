package exceptions;

public class CadastrarProdutoException extends  RuntimeException{
    public CadastrarProdutoException(String message) {
        super(message);
    }
}
