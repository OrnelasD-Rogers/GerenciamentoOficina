package ornelas.tech.gerenciamentoOficina.domain.exception;

public class TipoNaoEncontradoExeption extends EntidadeNaoEncontradaException {

    public TipoNaoEncontradoExeption(Long idTipo) {
        super(String.format("Não foi encontrado um tipo de aparelho com o id %d", idTipo));
    }
}
