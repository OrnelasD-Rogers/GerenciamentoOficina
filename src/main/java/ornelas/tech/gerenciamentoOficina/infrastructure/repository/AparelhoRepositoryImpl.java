package ornelas.tech.gerenciamentoOficina.infrastructure.repository;

import org.hibernate.jpa.QueryHints;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ornelas.tech.gerenciamentoOficina.domain.exception.CorNaoEncontradaException;
import ornelas.tech.gerenciamentoOficina.domain.model.*;
import ornelas.tech.gerenciamentoOficina.domain.repository.AparelhosRepositoryQueries;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class AparelhoRepositoryImpl implements AparelhosRepositoryQueries {

    @PersistenceContext
    private EntityManager manager;

    @Override
    @Transactional
    public Aparelho saveAparelho(Aparelho aparelho) {
        List<Long> coresIds = aparelho.getCores().stream()
                .map(Cor::getIdCor).collect(Collectors.toList());
        aparelho.getCores().clear();
        manager.persist(aparelho);
        manager.flush();
        manager.detach(aparelho);

        String deleteAparelhoCorQuery = "DELETE FROM aparelho_cor WHERE id_aparelho = ?";
        manager.createNativeQuery(deleteAparelhoCorQuery)
                .setParameter(1, aparelho.getIdAparelho())
                .executeUpdate();

        String insertAparelhoQuery =  "INSERT INTO aparelho_cor (id_aparelho, id_cor) VALUES (?, ?)";
        for (Long idCor: coresIds) {
            if(manager.find(Cor.class, idCor) == null){
                throw new CorNaoEncontradaException(idCor);
            }
            manager.createNativeQuery(insertAparelhoQuery)
                    .setParameter(1, aparelho.getIdAparelho())
                    .setParameter(2, idCor)
                    .executeUpdate();

        }
        return manager.find(Aparelho.class, aparelho.getIdAparelho());
    }


    @Override
    public List<Aparelho> findAllJoining() {
        List<Aparelho> aparelhos = new ArrayList<>();

        String queryGeneralizada = "select a from Aparelho a join fetch a.cliente join fetch a.tipo " +
                "join fetch a.marca join fetch a.modelo join fetch a.tecnico";
        aparelhos = manager.createQuery(queryGeneralizada, Aparelho.class)
                .setHint(QueryHints.HINT_PASS_DISTINCT_THROUGH, false)
                .getResultList();

        String queryCores = "select a from Aparelho a left join fetch a.cores";
        aparelhos = manager.createQuery(queryCores, Aparelho.class)
                .setHint(QueryHints.HINT_PASS_DISTINCT_THROUGH, false)
                .getResultList();

        String queryPagamentos = "select a from Aparelho a left join fetch a.pagamentos";
        aparelhos = manager.createQuery(queryPagamentos, Aparelho.class)
                .setHint(QueryHints.HINT_PASS_DISTINCT_THROUGH, false)
                .getResultList();

        return aparelhos;

    }

    @Override
    public List<Aparelho> criteriaSearch(String nomeTipo, String nomeMarca, String nomeModelo, SituacaoAparelhoEnum situacao,
                                         OffsetDateTime dataEntradaIni, OffsetDateTime dataEntradaLimite,
                                         OffsetDateTime dataSaidaIni, OffsetDateTime dataSaidaLimite)
    {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Aparelho> criteria = builder.createQuery(Aparelho.class);
        Root<Aparelho> root = criteria.from(Aparelho.class);
        var predicate = new ArrayList<Predicate>();

        if(StringUtils.hasText(nomeTipo)){
            Join<Aparelho, Tipo> tipo = (Join) root.fetch(Aparelho_.tipo);
            predicate.add(builder.like(tipo.get("tipoAparelho"), "%" + nomeTipo + "%"));
        }
        if(StringUtils.hasText(nomeMarca)){
            Join<Aparelho, Marca> marca = (Join) root.fetch(Aparelho_.marca);
            predicate.add(builder.like(marca.get("marcaAparelho"), "%" + nomeMarca + "%"));
        }
        if(StringUtils.hasText(nomeModelo)){
            Join<Aparelho, Marca> modelo = (Join) root.fetch(Aparelho_.modelo);
            predicate.add(builder.like(modelo.get("modeloAparelho"), "%" + nomeModelo + "%"));
        }
        if(situacao != null){
            predicate.add(builder.equal(root.get("situacaoAparelho"), situacao));
        }
        if(dataEntradaIni != null){
            predicate.add(builder.greaterThanOrEqualTo(root.get("dataEntrada"), dataEntradaIni));
        }
        if(dataEntradaLimite != null){
            predicate.add(builder.lessThanOrEqualTo(root.get("dataEntrada"), dataEntradaLimite));
        }
        if(dataSaidaIni != null){
            predicate.add(builder.greaterThanOrEqualTo(root.get("dataSaida"), dataSaidaIni));
        }
        if(dataSaidaLimite != null){
            predicate.add(builder.lessThanOrEqualTo(root.get("dataSaida"), dataSaidaLimite));
        }
        criteria.where(predicate.toArray(new Predicate[0]));

        TypedQuery<Aparelho> query = manager.createQuery(criteria);

        return query.getResultList();
    }


}
