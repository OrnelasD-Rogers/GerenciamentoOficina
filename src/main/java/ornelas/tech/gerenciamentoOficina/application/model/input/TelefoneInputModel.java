package ornelas.tech.gerenciamentoOficina.application.model.input;

import lombok.Data;
import ornelas.tech.gerenciamentoOficina.domain.model.TipoNumeroEnum;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class TelefoneInputModel {

    @Valid
    private ClienteIdInputModel idCliete;
    private Long idTelefone;
    @NotBlank
    private TipoNumeroEnum tipoNumero;
    @NotBlank
    private String numero;
    @NotNull
    private boolean whats;
}
