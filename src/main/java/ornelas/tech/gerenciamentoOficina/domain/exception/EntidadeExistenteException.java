package ornelas.tech.gerenciamentoOficina.domain.exception;

public class EntidadeExistenteException extends RuntimeException{

    public EntidadeExistenteException(String message) {
        super(message);
    }
}
