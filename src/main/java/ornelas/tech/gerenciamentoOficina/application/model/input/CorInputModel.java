package ornelas.tech.gerenciamentoOficina.application.model.input;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CorInputModel {

    @NotBlank
    private String nomeCor;
}
