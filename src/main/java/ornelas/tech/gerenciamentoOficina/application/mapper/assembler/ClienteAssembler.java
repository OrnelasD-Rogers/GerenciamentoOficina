package ornelas.tech.gerenciamentoOficina.application.mapper.assembler;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ornelas.tech.gerenciamentoOficina.application.model.ClienteModel;
import ornelas.tech.gerenciamentoOficina.domain.model.Cliente;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ClienteAssembler {

    private final ModelMapper mapper;

    public ClienteModel toModel(Cliente cliente){
        return mapper.map(cliente, ClienteModel.class);
    }

    public List<ClienteModel> toCollectionModel(List<Cliente> clienteList){
        return clienteList.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
