package ornelas.tech.gerenciamentoOficina.application.mapper.assembler;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ornelas.tech.gerenciamentoOficina.application.model.TecnicoModel;
import ornelas.tech.gerenciamentoOficina.domain.model.Tecnico;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TecnicoAssembler {

    private final ModelMapper mapper;

    public TecnicoModel toModel(Tecnico tecnico){
        return mapper.map(tecnico, TecnicoModel.class);
    }

    public List<TecnicoModel> toCollectionModel(List<Tecnico> tecnicos){
        return tecnicos.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
