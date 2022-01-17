package ornelas.tech.gerenciamentoOficina.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
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
