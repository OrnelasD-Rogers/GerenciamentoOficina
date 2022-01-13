package ornelas.tech.gerenciamentoOficina.application.mapper.desassembler;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ornelas.tech.gerenciamentoOficina.application.model.input.MarcaInputModel;
import ornelas.tech.gerenciamentoOficina.domain.model.Marca;

@Component
@RequiredArgsConstructor
public class MarcaDesassembler {

    private final ModelMapper mapper;

    public Marca toDomainModel(MarcaInputModel marcaInputModel){
        return mapper.map(marcaInputModel, Marca.class);
    }

    public void copyToDomainModel(MarcaInputModel marcaInputModel, Marca marca){
        mapper.map(marcaInputModel, marca);
    }
}
