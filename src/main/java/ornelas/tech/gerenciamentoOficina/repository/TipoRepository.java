package ornelas.tech.gerenciamentoOficina.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ornelas.tech.gerenciamentoOficina.domain.model.Tipo;

import java.util.List;

@Repository
public interface TipoRepository extends JpaRepository<Tipo, Long> {

    List<Tipo> findAllByTipoAparelhoContainingIgnoreCase(String tipo);

    boolean existsByTipoAparelhoEqualsIgnoreCase(String tipo);

}
