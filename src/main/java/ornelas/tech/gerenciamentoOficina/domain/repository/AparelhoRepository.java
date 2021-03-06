package ornelas.tech.gerenciamentoOficina.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ornelas.tech.gerenciamentoOficina.domain.model.Aparelho;
import ornelas.tech.gerenciamentoOficina.domain.model.SituacaoAparelhoEnum;

import java.time.OffsetDateTime;
import java.util.List;

@Repository
public interface AparelhoRepository extends JpaRepository<Aparelho, Long>, AparelhosRepositoryQueries {

    List<Aparelho> findByClienteNomeClienteContainingIgnoringCase(String nomeCliente);
    List<Aparelho> findByTipoTipoAparelhoContainingIgnoringCase(String tipo);
    List<Aparelho> findByMarcaMarcaAparelhoContainingIgnoringCase(String marca);
    List<Aparelho> findByModeloModeloAparelhoContainingIgnoringCase(String modelo);
    List<Aparelho> findBySituacaoAparelhoEquals(SituacaoAparelhoEnum situacao);
    List<Aparelho> findByDataEntradaBetween(OffsetDateTime inicio, OffsetDateTime limite);
    List<Aparelho> findByDataSaidaBetween(OffsetDateTime inicio, OffsetDateTime limite);

}
