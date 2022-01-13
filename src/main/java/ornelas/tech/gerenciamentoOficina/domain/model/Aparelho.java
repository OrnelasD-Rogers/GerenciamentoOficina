package ornelas.tech.gerenciamentoOficina.domain.model;

import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "aparelho")
public class Aparelho {

    @Id
    @Column(name = "idAparelho")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAparelho;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente idCliente;

    @ManyToOne
    @JoinColumn(name = "id_tipo", nullable = false)
    private Tipo idTipo;

    @ManyToOne
    @JoinColumn(name = "id_marca", nullable = false)
    private Marca idMarca;

    @ManyToOne
    @JoinColumn(name = "id_modelo", nullable = false)
    private Modelo idModelo;

    @Enumerated(EnumType.STRING)
    @Column(name = "situacao_aparelho", nullable = false)
    private SituacaoAparelhoEnum situacaoAparelho;

    @Column(name = "data_entrada", nullable = false)
    private OffsetDateTime dataEntrada;

    @Column(name = "data_saida")
    private OffsetDateTime dataSaida;

    @UpdateTimestamp
    @Column(name = "data_alteracao")
    private OffsetDateTime dataAlteracao;

    @Lob
    @Column(name = "problema", nullable = false)
    private String problema;

    @Lob
    @Column(name = "observacao")
    private String observacao;

    @Column(name = "urgencia", nullable = false)
    private Boolean urgencia;

    @Column(name = "revisao", nullable = false)
    private Boolean revisao;

    @Column(name = "mao_de_obra")
    private Double maoDeObra;

    @Lob
    @Column(name = "orcamento")
    private String orcamento;

    @ManyToMany
    @JoinTable(name = "aparelho_pagamento",
    joinColumns = @JoinColumn(name = "id_aparelho"),
    inverseJoinColumns = @JoinColumn(name = "id_pagamento"))
    private List<Pagamento> pagamentos;
}
