package ornelas.tech.gerenciamentoOficina.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ornelas.tech.gerenciamentoOficina.domain.exception.ContatoNaoEncontradoException;
import ornelas.tech.gerenciamentoOficina.domain.exception.EntidadeEmUsoException;
import ornelas.tech.gerenciamentoOficina.domain.model.Contato;
import ornelas.tech.gerenciamentoOficina.domain.repository.ContatoRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContatoService implements ServiceInterface<Contato, Long> {

    @PersistenceContext
    private final EntityManager manager;

    private final ContatoRepository contatoRepository;

    @Override
    public List<Contato> findAll() {
        return contatoRepository.findAll();
    }

    @Override
    public Contato findById(Long idContato) {
        return contatoRepository.findById(idContato).orElseThrow(
                () -> new ContatoNaoEncontradoException(idContato));
    }

    public List<Contato> findByIdAparelho(Long idAparelho){
        return contatoRepository.findByAparelhoIdAparelho(idAparelho);
    }

    @Override
    @Transactional
    public Contato save(Contato contato) {
        contatoRepository.save(contato);
        contatoRepository.flush();
        manager.detach(contato);
        return contatoRepository.findById(contato.getIdContato()).orElseThrow(
                () -> new ContatoNaoEncontradoException(contato.getIdContato()));
    }

    @Override
    @Transactional
    public void deleteById(Long idContato) {
        try {
            contatoRepository.deleteById(idContato);
            contatoRepository.flush();
        } catch (EmptyResultDataAccessException e){
            throw new ContatoNaoEncontradoException(idContato);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format("O contato de id %d está em uso e não pode ser excluido", idContato));
        }
    }
}
