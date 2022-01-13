package ornelas.tech.gerenciamentoOficina.domain.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "tipo")
public class Tipo {

    @Id
    @Column(name = "idTipo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTipo;

    @Column(name = "tipo_aparelho", nullable = false)
    private String tipoAparelho;
}
