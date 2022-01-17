package ornelas.tech.gerenciamentoOficina.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ornelas.tech.gerenciamentoOficina.domain.exception.CorExistenteException;
import ornelas.tech.gerenciamentoOficina.domain.exception.CorNaoEncontradaException;
import ornelas.tech.gerenciamentoOficina.domain.exception.EntidadeEmUsoException;
import ornelas.tech.gerenciamentoOficina.domain.model.Cor;
import ornelas.tech.gerenciamentoOficina.domain.repository.CorRepository;
import ornelas.tech.gerenciamentoOficina.util.StringsMethods;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CorService {

    private final CorRepository corRepository;

    public List<Cor> findlAll(){
        return corRepository.findAll();
    }

    public Cor findById(Long idCor){
        return corRepository.findById(idCor).orElseThrow(
                () -> new CorNaoEncontradaException(idCor));
    }

    public List<Cor> findByNomeCor(String nomeCor){
        return corRepository.findByNomeCorContainingIgnoringCase(nomeCor);
    }

    public  void existsByNomeCor(String nomeCor){
        boolean marcaExistente = corRepository.existsByNomeCorEqualsIgnoreCase(nomeCor);
        if (marcaExistente){
            throw new CorExistenteException(nomeCor);
        }
    }

    @Transactional
    public Cor save(Cor cor){
        cor.setNomeCor(StringsMethods.upperCaseAllFirstWord(cor.getNomeCor()));
        return corRepository.save(cor);
    }

    @Transactional
    public void deleteById(Long idCor){
        try {
            corRepository.deleteById(idCor);
            corRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new CorNaoEncontradaException(idCor);
        } catch (DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(
                    String.format("A cor com o id '%d' está em uso e não pode ser excluida", idCor));
        }
    }

}
