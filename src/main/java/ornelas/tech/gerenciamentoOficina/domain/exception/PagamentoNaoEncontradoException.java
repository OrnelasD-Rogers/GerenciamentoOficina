package ornelas.tech.gerenciamentoOficina.domain.exception;

public class PagamentoNaoEncontradoException extends EntidadeNaoEncontradaException {
    public PagamentoNaoEncontradoException(Long idPagamento) {
        super(String.format("Não foi encotnrado um pagamento com o id %d", idPagamento));
    }
}
