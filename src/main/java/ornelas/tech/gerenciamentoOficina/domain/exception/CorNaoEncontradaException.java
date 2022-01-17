package ornelas.tech.gerenciamentoOficina.domain.exception;

public class CorNaoEncontradaException extends EntidadeNaoEncontradaException {

    public CorNaoEncontradaException(Long idCor) {
        super(String.format("A cor com o id '%d não existe'", idCor));
    }

    public CorNaoEncontradaException(Long idCor, Throwable e) {
        super(String.format("A cor com o id '%d' não existe'", idCor));
    }
}
