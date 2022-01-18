package ornelas.tech.gerenciamentoOficina.application.model.input;

import lombok.Data;
import ornelas.tech.gerenciamentoOficina.domain.model.TipoPagamentoEnum;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;

@Data
public class PagamentoInputModel {

    @NotNull
    private OffsetDateTime dataPagamento;
    @Min(value = 1L)
    private Double valor;
    @NotNull
    private TipoPagamentoEnum tipoPagamento;
    @Valid
    private AparelhoIdInputModel aparelho;
}
