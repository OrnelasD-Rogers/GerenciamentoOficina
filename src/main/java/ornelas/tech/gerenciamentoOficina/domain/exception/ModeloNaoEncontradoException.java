package ornelas.tech.gerenciamentoOficina.domain.exception;

public class ModeloNaoEncontradoException extends EntidadeNaoEncontradaException {
    public ModeloNaoEncontradoException(Long idModelo) {
        super(String.format("O modelo com o id %d não foi encontrado", idModelo));
    }
}
