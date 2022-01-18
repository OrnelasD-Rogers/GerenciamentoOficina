package ornelas.tech.gerenciamentoOficina.domain.repository;

import ornelas.tech.gerenciamentoOficina.domain.model.Aparelho;
import ornelas.tech.gerenciamentoOficina.domain.model.SituacaoAparelhoEnum;

import java.time.OffsetDateTime;
import java.util.List;

public interface AparelhosRepositoryQueries {

    Aparelho saveAparelho(Aparelho aparelho);

    /**
     * Faz join para as consultas criando uma query mais otimizada para trazer os dados do banco
     * @return Lista de aparelhos
     */
    List<Aparelho> findAllJoining();

    List<Aparelho> criteriaSearch(String nomeTipo, String nomeMarca, String nomeModelo, SituacaoAparelhoEnum situacao,
                                  OffsetDateTime dataEntradaIni, OffsetDateTime dataEntradaLimite,
                                  OffsetDateTime dataSaidaIni,OffsetDateTime dataSaidaLimite);

}
