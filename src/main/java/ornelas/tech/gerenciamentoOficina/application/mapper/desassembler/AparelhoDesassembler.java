package ornelas.tech.gerenciamentoOficina.application.mapper.desassembler;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ornelas.tech.gerenciamentoOficina.application.model.input.AparelhoInputModel;
import ornelas.tech.gerenciamentoOficina.domain.model.Aparelho;

@Component
@RequiredArgsConstructor
public class AparelhoDesassembler {

    private final ModelMapper mapper;

    public Aparelho toDomainModel(AparelhoInputModel aparelhoInputModel){
        return mapper.map(aparelhoInputModel, Aparelho.class);
    }

    public void copyToDomainClass(AparelhoInputModel aparelhoInputModel, Aparelho aparelho){
        mapper.map(aparelhoInputModel, aparelho);
    }
}
