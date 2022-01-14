package ornelas.tech.gerenciamentoOficina.application.model;

import lombok.Data;
import ornelas.tech.gerenciamentoOficina.domain.model.TipoPagamentoEnum;

import java.time.OffsetDateTime;

@Data
public class PagamentoModel {

    private Long idPagamento;
    private OffsetDateTime dataPagamento;
    private Double valor;
    private TipoPagamentoEnum tipoPagamento;
}
