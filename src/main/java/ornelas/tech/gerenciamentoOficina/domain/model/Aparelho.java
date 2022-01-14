package ornelas.tech.gerenciamentoOficina.domain.model;

import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.ArrayList;
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
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_tipo", nullable = false)
    private Tipo tipo;

    @ManyToOne
    @JoinColumn(name = "id_marca", nullable = false)
    private Marca marca;

    @ManyToOne
    @JoinColumn(name = "id_modelo", nullable = false)
    private Modelo modelo;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "aparelho_cor",
    joinColumns = @JoinColumn(name = "id_aparelho", nullable = false),
    inverseJoinColumns = @JoinColumn(name = "id_cor", nullable = false))
    private List<Cor> cores = new ArrayList<>();

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

    @OneToMany(mappedBy = "aparelho",orphanRemoval = true)
    private List<Pagamento> pagamentos = new ArrayList<>();
}
