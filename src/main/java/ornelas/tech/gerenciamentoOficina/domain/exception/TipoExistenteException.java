package ornelas.tech.gerenciamentoOficina.domain.exception;

public class TipoExistenteException extends EntidadeExistenteException {

    public TipoExistenteException(String tipoAparelho) {
        super(String.format("O tipo de aparelho '%s' já existe",tipoAparelho) );
    }
}
