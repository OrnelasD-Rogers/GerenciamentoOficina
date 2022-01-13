package ornelas.tech.gerenciamentoOficina.application.mapper.desassembler;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ornelas.tech.gerenciamentoOficina.application.model.input.TelefoneInputModel;
import ornelas.tech.gerenciamentoOficina.domain.model.Telefone;

@Component
@RequiredArgsConstructor
public class TelefoneDesassembler {

    private final ModelMapper mapper;

    public Telefone toDomainModel(TelefoneInputModel telefoneInputModel){
        return mapper.map(telefoneInputModel, Telefone.class);
    }

    public void copyToDomainModel(TelefoneInputModel telefoneInputModel, Telefone telefone){
        mapper.map(telefoneInputModel, telefone);
    }
}
