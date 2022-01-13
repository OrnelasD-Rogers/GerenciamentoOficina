package ornelas.tech.gerenciamentoOficina.application.mapper.assembler;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ornelas.tech.gerenciamentoOficina.application.model.TelefoneModel;
import ornelas.tech.gerenciamentoOficina.domain.model.Telefone;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TelefoneAssembler {

    private final ModelMapper mapper;

    public TelefoneModel toModel(Telefone telefone){
        return mapper.map(telefone, TelefoneModel.class);
    }

    public List<TelefoneModel> toCollectionModel(List<Telefone> telefones){
        return telefones.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
