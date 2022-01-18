package ornelas.tech.gerenciamentoOficina.domain.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "contato")
public class Contato {

    @Id
    @Column(name = "idContato")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idContato;

    @ManyToOne
    @JoinColumn(name = "id_telefone")
    private Telefone telefone;

    @ManyToOne
    @JoinColumn(name = "id_aparelho", nullable = false)
    private Aparelho aparelho;

    @ManyToOne
    @JoinColumn(name = "id_tecnico", nullable = false)
    private Tecnico tecnico;

    @Lob
    @Column(name = "mensagem", nullable = false)
    private String mensagem;

    @Enumerated(EnumType.STRING)
    @Column(name = "situacao_aparelho", nullable = false)
    private SituacaoAparelhoEnum situacaoAparelho;


}
