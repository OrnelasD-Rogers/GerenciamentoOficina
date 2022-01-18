package ornelas.tech.gerenciamentoOficina.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ornelas.tech.gerenciamentoOficina.domain.exception.EntidadeEmUsoException;
import ornelas.tech.gerenciamentoOficina.domain.exception.PagamentoNaoEncontradoException;
import ornelas.tech.gerenciamentoOficina.domain.model.Pagamento;
import ornelas.tech.gerenciamentoOficina.domain.repository.PagamentoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PagamentoService implements ServiceInterface<Pagamento, Long>{

    private final PagamentoRepository pagamentoRepository;

    @Override
    public List<Pagamento> findAll() {
        return pagamentoRepository.findAll();
    }

    @Override
    public Pagamento findById(Long idPagamento) {
        return pagamentoRepository.findById(idPagamento).orElseThrow(
                ()-> new PagamentoNaoEncontradoException(idPagamento));
    }

    public List<Pagamento> findAllByIdAparelho(Long idAparelho){
        return pagamentoRepository.findByAparelhoIdAparelho(idAparelho);
    }

    @Override
    public Pagamento save(Pagamento pagamento) {
        return pagamentoRepository.save(pagamento);
    }

    @Override
    public void deleteById(Long idPagamento) {
        try {
            pagamentoRepository.deleteById(idPagamento);
            pagamentoRepository.flush();
        } catch (EmptyResultDataAccessException e){
            throw new PagamentoNaoEncontradoException(idPagamento);
        } catch (DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(
                    String.format("O pagamento de id '%d' está em uso e não pode ser excluido", idPagamento));
        }

    }
}
