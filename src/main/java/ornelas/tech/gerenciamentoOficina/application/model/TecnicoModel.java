package ornelas.tech.gerenciamentoOficina.application.model;

import lombok.Data;
import ornelas.tech.gerenciamentoOficina.domain.model.TipoNumeroEnum;

@Data
public class TecnicoModel {

    private Long idTecnico;
    private String nomeTecnico;
    private TipoNumeroEnum tipoNumero;
    private String numero;
}
