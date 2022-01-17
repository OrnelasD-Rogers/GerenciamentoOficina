package ornelas.tech.gerenciamentoOficina.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ornelas.tech.gerenciamentoOficina.domain.exception.ClienteExistenteException;
import ornelas.tech.gerenciamentoOficina.domain.exception.ClienteNaoEncontradoException;
import ornelas.tech.gerenciamentoOficina.domain.exception.EntidadeEmUsoException;
import ornelas.tech.gerenciamentoOficina.domain.exception.TelefoneNaoEncontradoException;
import ornelas.tech.gerenciamentoOficina.domain.model.Cliente;
import ornelas.tech.gerenciamentoOficina.domain.model.Telefone;
import ornelas.tech.gerenciamentoOficina.domain.repository.ClienteRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClienteService implements ServiceInterface<Cliente, Long>{

    private final ClienteRepository clienteRepository;

    @Override
    public List<Cliente> findAll(){
        return clienteRepository.findAll();
    }

    @Override
    public Cliente findById(Long clienteId){
        return clienteRepository.findById(clienteId).orElseThrow(
                () -> new ClienteNaoEncontradoException(clienteId));
    }

    public Cliente findByCpf(String cpf){
        return clienteRepository.findByCpf(cpf).orElseThrow(
                ()-> new ClienteNaoEncontradoException(cpf));
    }

    public List<Cliente> findByNome(String nome){
        return clienteRepository.findByNomeCliente(nome);
    }

    @Override
    @Transactional
    public Cliente save(Cliente cliente){
        boolean cpfCadastrado = clienteRepository.findByCpf(cliente.getCpf()).isPresent();
        if (cpfCadastrado){
            throw new ClienteExistenteException(cliente.getCpf());
        }
        cliente.getTelefones().forEach(tel -> tel.setCliente(cliente));
        return clienteRepository.save(cliente);
    }

    @Transactional
    public Cliente update(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    @Override
    @Transactional
    public void deleteById(Long idCliente){
        try {
            clienteRepository.deleteById(idCliente);
            clienteRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new ClienteNaoEncontradoException(idCliente);
        } catch (DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(
                    String.format("O cliente com o id '%d' está em uso e não pode ser excluido", idCliente));
        }
    }

    @Transactional
    public void deleteTel(Cliente cliente, Long idTel){
        List<Telefone> telefones = cliente.getTelefones().stream()
                .filter(tel -> tel.getIdTelefone().equals(idTel))
                .collect(Collectors.toList());
        if (telefones.isEmpty()){
            throw new TelefoneNaoEncontradoException(idTel);
        }
        cliente.getTelefones().removeAll(telefones);
        clienteRepository.saveAndFlush(cliente);
    }


}
