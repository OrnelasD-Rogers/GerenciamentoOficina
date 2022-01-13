package ornelas.tech.gerenciamentoOficina.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ornelas.tech.gerenciamentoOficina.domain.exception.ModeloExistenteException;
import ornelas.tech.gerenciamentoOficina.domain.exception.ModeloNaoEncontradoException;
import ornelas.tech.gerenciamentoOficina.domain.model.Modelo;
import ornelas.tech.gerenciamentoOficina.repository.ModeloRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ModeloService {

    private final ModeloRepository modeloRepository;

    public List<Modelo> findAll(){
        return modeloRepository.findAll();
    }

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

    public Modelo save(Modelo modelo){
        return modeloRepository.save(modelo);
    }

    public void delete(Long idModelo){
        modeloRepository.deleteById(idModelo);
    }
}
