package ornelas.tech.gerenciamentoOficina.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ornelas.tech.gerenciamentoOficina.domain.exception.EntidadeEmUsoException;
import ornelas.tech.gerenciamentoOficina.domain.exception.ModeloExistenteException;
import ornelas.tech.gerenciamentoOficina.domain.exception.ModeloNaoEncontradoException;
import ornelas.tech.gerenciamentoOficina.domain.model.Modelo;
import ornelas.tech.gerenciamentoOficina.domain.repository.ModeloRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ModeloService implements ServiceInterface<Modelo, Long>{

    private final ModeloRepository modeloRepository;

    @Override
    public List<Modelo> findAll(){
        return modeloRepository.findAll();
    }

    @Override
    public Modelo findById(Long idModelo){
        return modeloRepository.findById(idModelo).orElseThrow(
                () -> new ModeloNaoEncontradoException(idModelo));
    }

    public List<Modelo> findByNomeModelo(String modelo){
        return modeloRepository.findAllByModeloAparelhoContainingIgnoringCase(modelo);
    }

    public void existsByNomeModelo(String modelo){
        boolean modeloExistente = modeloRepository.existsByModeloAparelhoEqualsIgnoreCase(modelo);
        if(modeloExistente)
            throw new ModeloExistenteException(modelo);

    }

    @Override
    public Modelo save(Modelo modelo){
        return modeloRepository.save(modelo);
    }

    @Override
    public void deleteById(Long idModelo){
        try {
            modeloRepository.deleteById(idModelo);
            modeloRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new ModeloNaoEncontradoException(idModelo);
        } catch (DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(
                    String.format("O modelo de id '%d' está em uso e não pode ser excluido", idModelo));
        }
    }
}
