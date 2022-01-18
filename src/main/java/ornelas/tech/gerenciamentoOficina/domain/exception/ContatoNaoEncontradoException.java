package ornelas.tech.gerenciamentoOficina.domain.exception;

public class ContatoNaoEncontradoException extends EntidadeNaoEncontradaException {
    public ContatoNaoEncontradoException(Long idContato) {
        super(String.format("Não foi encontrado um contato com o id %d", idContato));
    }
}
