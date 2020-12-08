package exceptions;

public class ProcessarFaturaException extends RuntimeException{
    public ProcessarFaturaException(String message) {
        super(message);
    }
}
