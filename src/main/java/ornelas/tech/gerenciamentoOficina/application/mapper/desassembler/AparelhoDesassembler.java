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

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class AparelhoDesassembler {

    private final ModelMapper mapper;


    public Aparelho toDomainModel(AparelhoInputModel aparelhoInputModel){
        return mapper.map(aparelhoInputModel, Aparelho.class);
    }

    public void copyToDomainClass(AparelhoInputModel aparelhoInputModel, Aparelho aparelho){
        mapper.typeMap(AparelhoInputModel.class, Aparelho.class).setConverter(update);
        mapper.map(aparelhoInputModel, aparelho);
    }

    Converter<AparelhoInputModel, Aparelho> update = new Converter<AparelhoInputModel, Aparelho>() {
        @Override
        public Aparelho convert(MappingContext<AparelhoInputModel, Aparelho> context) {
            context.getDestination().getCliente().setIdCliente(context.getSource().getCliente().getIdCliente());
            context.getDestination().getTipo().setIdTipo(context.getSource().getTipo().getIdTipo());
            context.getDestination().getMarca().setIdMarca(context.getSource().getMarca().getIdMarca());
            context.getDestination().getModelo().setIdModelo(context.getSource().getModelo().getIdModelo());
            context.getDestination().setSituacaoAparelho(context.getSource().getSituacaoAparelho());
            context.getDestination().setDataEntrada(context.getSource().getDataEntrada());
            context.getDestination().setDataSaida(context.getSource().getDataSaida());
            context.getDestination().setProblema(context.getSource().getProblema());
            context.getDestination().setObservacao(context.getSource().getObservacao());
            context.getDestination().setUrgencia(context.getSource().getUrgencia());
            context.getDestination().setRevisao(context.getSource().getRevisao());
            context.getDestination().setMaoDeObra(context.getSource().getMaoDeObra());
            context.getDestination().setOrcamento(context.getSource().getOrcamento());
            context.getDestination().getCores().clear();
            for (CorInputModel cor: context.getSource().getCores()) {
                context.getDestination().getCores().add(new Cor(cor.getIdCor(), null, new ArrayList<>()));
            }

            return context.getDestination();
        }
    };

}
