package ornelas.tech.gerenciamentoOficina.application.mapper.assembler;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ornelas.tech.gerenciamentoOficina.application.model.CorModel;
import ornelas.tech.gerenciamentoOficina.domain.model.Cor;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CorAssembler {

    private final ModelMapper mapper;

    public CorModel toModel(Cor cor){
        return mapper.map(cor, CorModel.class);
    }

    public List<CorModel> toCollectionModel(List<Cor> cores){
        return cores.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

}
