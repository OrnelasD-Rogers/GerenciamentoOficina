package ornelas.tech.gerenciamentoOficina.domain.exception;

public class TelefoneNaoEncontradoException extends EntidadeNaoEncontradaException{

    public TelefoneNaoEncontradoException(Long id) {
        super(String.format("Não foi encontrado um telefone com o id %d", id));
    }
}
