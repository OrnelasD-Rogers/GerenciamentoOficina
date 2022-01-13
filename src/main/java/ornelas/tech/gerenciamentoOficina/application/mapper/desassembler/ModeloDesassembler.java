package ornelas.tech.gerenciamentoOficina.application.mapper.desassembler;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ornelas.tech.gerenciamentoOficina.application.model.input.ModeloInputModel;
import ornelas.tech.gerenciamentoOficina.domain.model.Modelo;

@Component
@RequiredArgsConstructor
public class ModeloDesassembler {

    private final ModelMapper mapper;

    public Modelo toDomainModel(ModeloInputModel modeloInputModel){
        return mapper.map(modeloInputModel, Modelo.class);
    }

    public void copyToDomainModel(ModeloInputModel modeloInputModel, Modelo modelo){
        mapper.map(modeloInputModel, modelo);
    }
}
