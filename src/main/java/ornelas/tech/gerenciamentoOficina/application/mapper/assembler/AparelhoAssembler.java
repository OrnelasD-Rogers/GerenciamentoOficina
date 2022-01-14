package ornelas.tech.gerenciamentoOficina.application.mapper.assembler;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ornelas.tech.gerenciamentoOficina.application.model.AparelhoModel;
import ornelas.tech.gerenciamentoOficina.domain.model.Aparelho;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AparelhoAssembler {

    private final ModelMapper mapper;

    public AparelhoModel toModel(Aparelho aparelho){
        return mapper.map(aparelho, AparelhoModel.class);
    }

    public List<AparelhoModel> toCollectionModel(List<Aparelho> aparelhos){
        return aparelhos.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
