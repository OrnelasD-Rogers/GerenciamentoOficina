package ornelas.tech.gerenciamentoOficina.application.mapper.desassembler;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ornelas.tech.gerenciamentoOficina.application.model.input.PagamentoInputModel;
import ornelas.tech.gerenciamentoOficina.domain.model.Pagamento;

@Component
@RequiredArgsConstructor
public class PagamentoDesassembler {

    private final ModelMapper mapper;

    public Pagamento toDomainModel(PagamentoInputModel pagamentoInputModel){
        return mapper.map(pagamentoInputModel, Pagamento.class);
    }

    public void copyToDomainModel(PagamentoInputModel pagamentoInputModel, Pagamento pagamento){
        mapper.map(pagamentoInputModel, pagamento);
    }
}
