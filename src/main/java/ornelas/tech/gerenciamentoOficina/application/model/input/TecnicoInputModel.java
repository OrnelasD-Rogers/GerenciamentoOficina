package ornelas.tech.gerenciamentoOficina.application.model.input;

import lombok.Data;
import ornelas.tech.gerenciamentoOficina.domain.model.TipoNumeroEnum;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class TecnicoInputModel {
    @NotBlank
    private String nomeTecnico;
    @NotNull
    private TipoNumeroEnum tipoNumero;
    @NotBlank
    private String numero;
}
