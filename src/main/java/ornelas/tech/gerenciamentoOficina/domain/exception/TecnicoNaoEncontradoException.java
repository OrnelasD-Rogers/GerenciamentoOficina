package ornelas.tech.gerenciamentoOficina.domain.exception;

public class TecnicoNaoEncontradoException extends EntidadeNaoEncontradaException {
    public TecnicoNaoEncontradoException(Long idTecnico) {
        super(String.format("Não foi encontrado um técnico com o id %d", idTecnico));
    }

}
