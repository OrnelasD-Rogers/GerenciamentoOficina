package ornelas.tech.gerenciamentoOficina.application.mapper.desassembler;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ornelas.tech.gerenciamentoOficina.application.model.input.TecnicoInputModel;
import ornelas.tech.gerenciamentoOficina.domain.model.Tecnico;

@Component
@RequiredArgsConstructor
public class TecnicoDesassembler {

    private final ModelMapper mapper;

    public Tecnico toDomainModel(TecnicoInputModel tecnicoInputModel){
        return mapper.map(tecnicoInputModel, Tecnico.class);
    }

    public void copyToDomainModel(TecnicoInputModel tecnicoInputModel, Tecnico tecnico){
        mapper.map(tecnicoInputModel, tecnico);
    }
}
