package ornelas.tech.gerenciamentoOficina.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ornelas.tech.gerenciamentoOficina.domain.model.Cor;

import java.util.List;

@Repository
public interface CorRepository extends JpaRepository<Cor, Long> {

    List<Cor> findByNomeCorContainingIgnoringCase(String nomeCor);
    boolean existsByNomeCorEqualsIgnoreCase(String nomeCor);
}
