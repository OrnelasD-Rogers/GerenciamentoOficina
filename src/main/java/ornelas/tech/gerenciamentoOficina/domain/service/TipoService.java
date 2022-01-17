package ornelas.tech.gerenciamentoOficina.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ornelas.tech.gerenciamentoOficina.domain.exception.EntidadeEmUsoException;
import ornelas.tech.gerenciamentoOficina.domain.exception.TipoExistenteException;
import ornelas.tech.gerenciamentoOficina.domain.exception.TipoNaoEncontradoExeption;
import ornelas.tech.gerenciamentoOficina.domain.model.Tipo;
import ornelas.tech.gerenciamentoOficina.domain.repository.TipoRepository;
import ornelas.tech.gerenciamentoOficina.util.StringsMethods;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TipoService implements ServiceInterface<Tipo, Long>{

    private final TipoRepository tipoRepository;

    @Override
    public List<Tipo> findAll(){
        return tipoRepository.findAll();
    }

    @Override
    public Tipo findById(Long idTipo){
        return tipoRepository.findById(idTipo).orElseThrow(
                () -> new TipoNaoEncontradoExeption(idTipo));
    }

    public List<Tipo> findByTipoAparelho(String tipoAparelho){
        return tipoRepository.findAllByTipoAparelhoContainingIgnoreCase(tipoAparelho);
    }


    public void existsByTipoAparelho(String tipo){
        boolean tipoExistente = tipoRepository.existsByTipoAparelhoEqualsIgnoreCase(tipo);
        if (tipoExistente){
            throw new TipoExistenteException(tipo);
        }
    }

    @Override
    @Transactional
    public Tipo save(Tipo tipo){
        tipo.setTipoAparelho(StringsMethods.upperCaseAllFirstWord(tipo.getTipoAparelho()));
        return tipoRepository.save(tipo);
    }

    @Override
    @Transactional
    public void deleteById(Long idTipo){
        try {
            tipoRepository.deleteById(idTipo);
            tipoRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new TipoNaoEncontradoExeption(idTipo);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException("O tipo de aparelho está em uso e não pode ser excluído");
        }
    }

}
