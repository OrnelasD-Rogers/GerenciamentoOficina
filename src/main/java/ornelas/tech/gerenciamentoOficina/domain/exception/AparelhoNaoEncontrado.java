package ornelas.tech.gerenciamentoOficina.domain.exception;


public class AparelhoNaoEncontrado extends EntidadeNaoEncontradaException {

    public AparelhoNaoEncontrado(Long idAparelho) {
        super(String.format("O aparelho com o id %d não foi encontrado", idAparelho));
    }
}
