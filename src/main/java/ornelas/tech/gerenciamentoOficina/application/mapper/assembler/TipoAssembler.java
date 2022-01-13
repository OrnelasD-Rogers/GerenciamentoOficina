package ornelas.tech.gerenciamentoOficina.application.mapper.assembler;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ornelas.tech.gerenciamentoOficina.application.model.TipoModel;
import ornelas.tech.gerenciamentoOficina.domain.model.Tipo;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class TipoAssembler {

    private final ModelMapper mapper;

    public TipoModel toModel(Tipo tipo){
        return mapper.map(tipo, TipoModel.class);
    }

    public List<TipoModel> toCollectionModel(List<Tipo> tipos){
        return tipos.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
