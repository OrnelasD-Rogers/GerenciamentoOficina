package ornelas.tech.gerenciamentoOficina.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ornelas.tech.gerenciamentoOficina.domain.exception.EntidadeEmUsoException;
import ornelas.tech.gerenciamentoOficina.domain.exception.MarcaExistenteException;
import ornelas.tech.gerenciamentoOficina.domain.exception.MarcaNaoEncontradaException;
import ornelas.tech.gerenciamentoOficina.domain.model.Marca;
import ornelas.tech.gerenciamentoOficina.domain.repository.MarcaRepository;
import ornelas.tech.gerenciamentoOficina.util.StringsMethods;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MarcaService {

    private final MarcaRepository marcaRepository;

    public List<Marca> findAll(){
        return marcaRepository.findAll();
    }

    public Marca findById(Long idMarca){
        return marcaRepository.findById(idMarca).orElseThrow(
                () -> new MarcaNaoEncontradaException(idMarca));
    }

    public List<Marca> findByMarca(String marca){
        return marcaRepository.findAllByMarcaAparelhoContainingIgnoringCase(marca);
    }

    public  void existsByNomeMarca(String marca){
        boolean marcaExistente = marcaRepository.existsByMarcaAparelhoEqualsIgnoreCase(marca);
        if (marcaExistente){
            throw new MarcaExistenteException(marca);
        }
    }

    @Transactional
    public Marca save(Marca marca){
        marca.setMarcaAparelho(StringsMethods.upperCaseAllFirstWord(marca.getMarcaAparelho()));
        return marcaRepository.save(marca);
    }

    @Transactional
    public void delete(Long idMarca){
        try {
            marcaRepository.deleteById(idMarca);
            marcaRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new MarcaNaoEncontradaException(idMarca);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format("A marca com id %d está em uso e não pode ser excluida",idMarca));
        }
    }



}
