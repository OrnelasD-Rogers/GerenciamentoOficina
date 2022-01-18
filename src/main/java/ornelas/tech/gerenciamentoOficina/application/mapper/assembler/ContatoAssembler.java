package ornelas.tech.gerenciamentoOficina.application.mapper.assembler;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ornelas.tech.gerenciamentoOficina.application.model.ContatoModel;
import ornelas.tech.gerenciamentoOficina.domain.model.Contato;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class ContatoAssembler {

    private final ModelMapper mapper;

    public ContatoModel toModel(Contato contato){
        return mapper.map(contato, ContatoModel.class);
    }

    public List<ContatoModel> toCollectionModel(List<Contato> contatos){
        return contatos.stream().map(this::toModel).collect(Collectors.toList());
    }
}
