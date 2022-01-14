package ornelas.tech.gerenciamentoOficina.domain.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "cor")
public class Cor {

    @Id
    @Column(name = "idCor")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCor;

    @Column(name = "cor")
    private String nomeCor;

    @ManyToMany(mappedBy = "cores")
    private List<Aparelho> aparelhos = new ArrayList<>();
}
