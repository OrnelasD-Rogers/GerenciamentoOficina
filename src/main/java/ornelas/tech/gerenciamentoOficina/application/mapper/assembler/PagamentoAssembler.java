package ornelas.tech.gerenciamentoOficina.application.mapper.assembler;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ornelas.tech.gerenciamentoOficina.application.model.PagamentoModel;
import ornelas.tech.gerenciamentoOficina.domain.model.Pagamento;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PagamentoAssembler {

    private final ModelMapper mapper;

    public PagamentoModel toModel(Pagamento pagamento){
        return mapper.map(pagamento, PagamentoModel.class);
    }

    public List<PagamentoModel> toCollectionModel(List<Pagamento> pagamentos){
        return pagamentos.stream().map(this::toModel).collect(Collectors.toList());
    }
}
