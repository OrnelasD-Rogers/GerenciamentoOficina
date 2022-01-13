package ornelas.tech.gerenciamentoOficina.domain.exception;

public class ModeloExistenteException extends EntidadeExistenteException {

    public ModeloExistenteException(String nomeModelo) {
        super(String.format("O modelo com o nome '%s' já existe",nomeModelo));
    }
}
