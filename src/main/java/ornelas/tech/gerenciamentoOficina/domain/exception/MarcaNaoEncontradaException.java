package ornelas.tech.gerenciamentoOficina.domain.exception;

public class MarcaNaoEncontradaException extends EntidadeNaoEncontradaException {

    public MarcaNaoEncontradaException(Long idMarca) {
        super(String.format("A marca com o id %d não foi encontrada", idMarca));
    }
}
