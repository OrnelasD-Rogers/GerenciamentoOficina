package ornelas.tech.gerenciamentoOficina.application.model;

import lombok.Data;
import ornelas.tech.gerenciamentoOficina.domain.model.TipoNumeroEnum;

@Data
public class TelefoneModel {

    private Long idTelefone;
    private TipoNumeroEnum tipoNumero;
    private String numero;
    private boolean whats;
}
