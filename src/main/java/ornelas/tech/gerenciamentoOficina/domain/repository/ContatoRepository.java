package ornelas.tech.gerenciamentoOficina.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ornelas.tech.gerenciamentoOficina.domain.model.Contato;

import java.util.List;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long> {

    List<Contato> findByAparelhoIdAparelho(Long idAparelho);
}
