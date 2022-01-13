package ornelas.tech.gerenciamentoOficina.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ornelas.tech.gerenciamentoOficina.domain.model.Marca;

import java.util.List;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Long> {

    List<Marca> findAllByMarcaAparelhoContainingIgnoringCase(String marca);

    boolean existsByMarcaAparelhoEqualsIgnoreCase(String marca);
}
