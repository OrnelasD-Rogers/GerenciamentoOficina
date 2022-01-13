package ornelas.tech.gerenciamentoOficina.domain.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Data
@Table(name = "marca")
public class Marca {

    @Id
    @Column(name = "idMarca")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMarca;

    @Column(name = "marca_aparelho", nullable = false)
    private String marcaAparelho;
}
