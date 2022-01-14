package ornelas.tech.gerenciamentoOficina.infrastructure.repository;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ornelas.tech.gerenciamentoOficina.domain.model.Aparelho;
import ornelas.tech.gerenciamentoOficina.domain.model.Cor;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class AparelhoRepositoryImpl implements AparelhosRepositoryQueries {


    private final EntityManagerFactory entityManagerFactory;

    @Override
    public Aparelho saveAparelho(Aparelho aparelho) {
        EntityManager manager = entityManagerFactory.createEntityManager();
        var tx = manager.getTransaction();
        var session = manager.unwrap(Session.class);
        List<Long> coresIds = aparelho.getCores().stream()
                .map(Cor::getIdCor).collect(Collectors.toList());
        aparelho.getCores().clear();
        tx.begin();
        List<Cor> cores = session.byMultipleIds(Cor.class).multiLoad(coresIds);
        aparelho.getCores().addAll(cores);
        manager.persist(aparelho);
        manager.flush();
        cores.forEach(cor -> {
            cor.getAparelhos().add(aparelho);
            cor = manager.merge(cor);
        });
        tx.commit();
         manager.detach(aparelho);
        return manager.find(Aparelho.class, aparelho.getIdAparelho());
    }

    @Override
    public Aparelho updateAparelho(Aparelho aparelho) {
        EntityManager manager = entityManagerFactory.createEntityManager();
        var tx = manager.getTransaction();
        var session = manager.unwrap(Session.class);
        List<Long> coresIds = aparelho.getCores().stream()
                .map(Cor::getIdCor).collect(Collectors.toList());
        tx.begin();
        aparelho.getCores().clear();
        manager.flush();
        List<Cor> cores = session.byMultipleIds(Cor.class).multiLoad(coresIds);
        aparelho.getCores().addAll(cores);
        manager.persist(aparelho);
        manager.flush();
        cores.forEach(cor -> {
            cor.getAparelhos().add(aparelho);
            cor = manager.merge(cor);
        });
        tx.commit();
        manager.detach(aparelho);
        return manager.find(Aparelho.class, aparelho.getIdAparelho());
    }
}
