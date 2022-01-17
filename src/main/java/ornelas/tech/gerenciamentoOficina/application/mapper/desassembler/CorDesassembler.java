package ornelas.tech.gerenciamentoOficina.application.mapper.desassembler;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ornelas.tech.gerenciamentoOficina.application.model.input.CorInputModel;
import ornelas.tech.gerenciamentoOficina.domain.model.Cor;

@Component
@RequiredArgsConstructor
public class CorDesassembler {

    private final ModelMapper mapper;


    public Cor toDomainModel(CorInputModel corInputModel){
        return mapper.map(corInputModel, Cor.class);
    }

    public void copyToDomainModel(CorInputModel corInputModel, Cor cor){
        mapper.map(corInputModel, cor);
    }
}
