package ornelas.tech.gerenciamentoOficina.domain.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Data
@Table(name = "modelo")
public class Modelo {

    @Id
    @Column(name = "idModelo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idModelo;

    @Column(name = "modelo_aparelho", nullable = false)
    private String modeloAparelho;
}
