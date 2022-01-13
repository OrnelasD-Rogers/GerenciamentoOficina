package ornelas.tech.gerenciamentoOficina.domain.exception;

public class MarcaExistenteException extends EntidadeExistenteException {

    public MarcaExistenteException(String marca) {
        super(String.format("A marca '%s' já existe", marca));
    }
}
