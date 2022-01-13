package ornelas.tech.gerenciamentoOficina.domain.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Data
@Table(name = "tecnico")
public class Tecnico {

    @Id
    @Column(name = "idTecnico")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTecnico;

    @Column(name = "nome_tecnico", nullable = false)
    private String nomeTecnico;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_numero", nullable = false)
    private TipoNumeroEnum tipoNumero;

    @Column(name = "numero", nullable = false)
    private String numero;
}
