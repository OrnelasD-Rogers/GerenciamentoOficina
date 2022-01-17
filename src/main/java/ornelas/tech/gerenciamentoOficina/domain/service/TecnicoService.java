package ornelas.tech.gerenciamentoOficina.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ornelas.tech.gerenciamentoOficina.domain.exception.EntidadeEmUsoException;
import ornelas.tech.gerenciamentoOficina.domain.exception.TecnicoNaoEncontradoException;
import ornelas.tech.gerenciamentoOficina.domain.model.Tecnico;
import ornelas.tech.gerenciamentoOficina.domain.repository.TecnicoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TecnicoService implements ServiceInterface<Tecnico, Long>{

    private final TecnicoRepository tecnicoRepository;

    public List<Tecnico> findAll(){
        return tecnicoRepository.findAll();
    }

    @Override
    public Tecnico findById(Long idTecnico) {
        return tecnicoRepository.findById(idTecnico).orElseThrow(
                () -> new TecnicoNaoEncontradoException(idTecnico));
    }

    public List<Tecnico> findByName(String nomeTecnico){
        return tecnicoRepository.findByNomeTecnicoContainingIgnoringCase(nomeTecnico);
    }

    public List<Tecnico> findByNumero(String numeroTecnico){
        return tecnicoRepository.findByNumeroContaining(numeroTecnico);
    }

    @Override
    public Tecnico save(Tecnico tecnico) {
        return tecnicoRepository.save(tecnico);
    }

    @Override
    public void deleteById(Long idTecnico) {
        try {
            tecnicoRepository.deleteById(idTecnico);
            tecnicoRepository.flush();
        } catch (EmptyResultDataAccessException e){
            throw new TecnicoNaoEncontradoException(idTecnico);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format("O tecnico com id '%d' está em uso e não pode ser Excluido", idTecnico));
        }
    }
}
