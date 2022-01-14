package ornelas.tech.gerenciamentoOficina.application.model.input;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ModeloIdInputModel {

    @NotNull
    private Long idModelo;
}
