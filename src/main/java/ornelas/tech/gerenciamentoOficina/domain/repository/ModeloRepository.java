package ornelas.tech.gerenciamentoOficina.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ornelas.tech.gerenciamentoOficina.domain.model.Modelo;

import java.util.List;


@Repository
public interface ModeloRepository extends JpaRepository<Modelo, Long> {

    List<Modelo> findAllByModeloAparelhoContainingIgnoringCase(String modelo);
    boolean existsByModeloAparelhoEqualsIgnoreCase(String modelo);
}
