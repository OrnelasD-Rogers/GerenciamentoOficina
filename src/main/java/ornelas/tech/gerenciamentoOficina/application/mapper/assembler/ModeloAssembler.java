package ornelas.tech.gerenciamentoOficina.application.mapper.assembler;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ornelas.tech.gerenciamentoOficina.application.model.ModeloModel;
import ornelas.tech.gerenciamentoOficina.domain.model.Modelo;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ModeloAssembler {

    private final ModelMapper mapper;

    public ModeloModel toModel(Modelo modelo){
        return mapper.map(modelo, ModeloModel.class);
    }

    public List<ModeloModel> toCollectionModel(List<Modelo> modelos){
        return modelos.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
