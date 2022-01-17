package ornelas.tech.gerenciamentoOficina.domain.exception;

public class CorExistenteException extends EntidadeExistenteException {

    public CorExistenteException(String nomeCor) {
        super(String.format("A cor com o nome '%s' já existe",nomeCor));

    }
}
