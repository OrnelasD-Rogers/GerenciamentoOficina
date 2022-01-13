package ornelas.tech.gerenciamentoOficina.domain.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Data
@Table(name = "cor")
public class Cor {

    @Id
    @Column(name = "idCor")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCor;

    @Column(name = "cor", nullable = false)
    private String cor;
}
