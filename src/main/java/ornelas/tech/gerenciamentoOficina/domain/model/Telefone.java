package ornelas.tech.gerenciamentoOficina.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Data
@Table(name = "telefone")
public class Telefone {

    @Id
    @Column(name = "idTelefone")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTelefone;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_numero", nullable = false)
    private TipoNumeroEnum tipoNumero;

    @Column(name = "numero", nullable = false)
    private String numero;

    @Column(name = "whats", nullable = false)
    private boolean whats;
}
