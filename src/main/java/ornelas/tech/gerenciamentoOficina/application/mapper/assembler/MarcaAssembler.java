package ornelas.tech.gerenciamentoOficina.application.mapper.assembler;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ornelas.tech.gerenciamentoOficina.application.model.MarcaModel;
import ornelas.tech.gerenciamentoOficina.domain.model.Marca;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MarcaAssembler {

    private final ModelMapper mapper;

    public MarcaModel toModel(Marca marca){
        return mapper.map(marca, MarcaModel.class);
    }

    public List<MarcaModel> toCollectionModel(List<Marca> marcas){
        return marcas.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
