package ornelas.tech.gerenciamentoOficina.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ornelas.tech.gerenciamentoOficina.domain.exception.AparelhoNaoEncontrado;
import ornelas.tech.gerenciamentoOficina.domain.exception.EntidadeEmUsoException;
import ornelas.tech.gerenciamentoOficina.domain.model.Aparelho;
import ornelas.tech.gerenciamentoOficina.domain.model.SituacaoAparelhoEnum;
import ornelas.tech.gerenciamentoOficina.domain.repository.AparelhoRepository;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AparelhoService {

    private final AparelhoRepository aparelhoRepository;


    public List<Aparelho> findAll(){
        return aparelhoRepository.findAllJoining();
    }

    public Aparelho findById(Long idAparelho){
        return aparelhoRepository.findById(idAparelho).orElseThrow(
                () -> new AparelhoNaoEncontrado(idAparelho));
    }

    public List<Aparelho> findByNomeCliente(String nomeCliente){
        return aparelhoRepository.findByClienteNomeClienteContainingIgnoringCase(nomeCliente);
    }

    public List<Aparelho> findByTipo(String tipoAparelho){
        return aparelhoRepository.findByTipoTipoAparelhoContainingIgnoringCase(tipoAparelho);
    }

    public List<Aparelho> findByMarca(String marcaAparelho){
        return aparelhoRepository.findByMarcaMarcaAparelhoContainingIgnoringCase(marcaAparelho);
    }

    public List<Aparelho> findByModelo(String modeloAparelho){
        return aparelhoRepository.findByModeloModeloAparelhoContainingIgnoringCase(modeloAparelho);
    }

    public List<Aparelho> findBySituacao(SituacaoAparelhoEnum situacao){
        return aparelhoRepository.findBySituacaoAparelhoEquals(situacao);
    }

    public List<Aparelho> findByDataEntrada(OffsetDateTime inicio, OffsetDateTime limite){
        return aparelhoRepository.findByDataEntradaBetween(inicio, limite);
    }

    public List<Aparelho> findByDataSaida(OffsetDateTime inicio, OffsetDateTime limite){
        return aparelhoRepository.findByDataSaidaBetween(inicio, limite);
    }

    @Transactional
    public Aparelho save(Aparelho aparelho){
        return aparelhoRepository.saveAparelho(aparelho);
    }

    @Transactional
    public void delete(Long idAparelho){
        try {
            aparelhoRepository.deleteById(idAparelho);
            aparelhoRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new AparelhoNaoEncontrado(idAparelho);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format("O aparelho com o id %d não pode ser excluido pois está em uso", idAparelho));
        }
    }

}
