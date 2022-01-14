package ornelas.tech.gerenciamentoOficina.application.mapper.desassembler;

import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;
import ornelas.tech.gerenciamentoOficina.application.model.input.AparelhoInputModel;
import ornelas.tech.gerenciamentoOficina.application.model.input.CorInputModel;
import ornelas.tech.gerenciamentoOficina.domain.model.Aparelho;
import ornelas.tech.gerenciamentoOficina.domain.model.Cor;

@Component
@RequiredArgsConstructor
public class AparelhoDesassembler {

    private final ModelMapper mapper;

    Converter<AparelhoInputModel, Aparelho> aparelhoInputToModelConverter = new Converter<>() {
        @Override
        public Aparelho convert(MappingContext<AparelhoInputModel, Aparelho> context) {
            ModelMapper mdc = new ModelMapper();
            Aparelho aparelho = new Aparelho();
//            if (context.getDestination().getIdAparelho() == null) {
                aparelho = mdc.map(context.getSource(), Aparelho.class);
                aparelho.getCores().clear();
                for (CorInputModel corInput : context.getSource().getCores()) {
                    Cor novaCor = new Cor();
                    novaCor.setIdCor(corInput.getIdCor());
                    novaCor.setNomeCor(null);
                    aparelho.getCores().add(novaCor);
                }
//            }
//            else{
//                for (int i = 0; i <= context.getSource().getCores().size() ; i++) {
//                    mdc.map(context.getSource(), context.getDestination());
//                    context.getDestination().getCores().get(i).setIdCor(context.getSource().getCores().get(i).getIdCor());
//                }
//                aparelho = context.getDestination();
//            }
            return aparelho;
        }
    };

    public Aparelho toDomainModel(AparelhoInputModel aparelhoInputModel){
        mapper.addConverter(aparelhoInputToModelConverter);
        return mapper.map(aparelhoInputModel, Aparelho.class);
    }

    public void copyToDomainClass(AparelhoInputModel aparelhoInputModel, Aparelho aparelho){
        mapper.map(aparelhoInputModel, aparelho);
    }
}
