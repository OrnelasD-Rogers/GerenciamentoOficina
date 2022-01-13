package ornelas.tech.gerenciamentoOficina.domain.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.OffsetDateTime;

@Entity
@Data
@Table(name = "pagamento")
public class Pagamento {

    @Id
    @Column(name = "idPagamento")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPagamento;

    @Column(name = "data_pagamento", nullable = false)
    private OffsetDateTime dataPagamento;

    @Column(name = "valor", nullable = false)
    private Double valor;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_pagamento", nullable = false)
    private TipoPagamentoEnum tipoPagamento;
}
