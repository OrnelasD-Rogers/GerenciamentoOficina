package ornelas.tech.gerenciamentoOficina.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ornelas.tech.gerenciamentoOficina.domain.model.Pagamento;

import java.util.List;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {

    List<Pagamento> findByAparelhoIdAparelho(Long idAparelho);
}
