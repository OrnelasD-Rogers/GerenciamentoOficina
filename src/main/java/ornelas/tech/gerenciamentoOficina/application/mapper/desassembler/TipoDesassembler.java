package ornelas.tech.gerenciamentoOficina.application.mapper.desassembler;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ornelas.tech.gerenciamentoOficina.application.model.input.TipoInputModel;
import ornelas.tech.gerenciamentoOficina.domain.model.Tipo;

@Component
@RequiredArgsConstructor
public class TipoDesassembler {

    private final ModelMapper mapper;

    public Tipo toDomainModel(TipoInputModel tipoInputModel){
        return mapper.map(tipoInputModel, Tipo.class);
    }

    public void copyToDomainModel(TipoInputModel tipoInputModel, Tipo tipo){
        mapper.map(tipoInputModel, tipo);
    }
}
