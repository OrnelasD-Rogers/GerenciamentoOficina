package ornelas.tech.gerenciamentoOficina.application.model.input;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CorInputModel {
    @NotNull
    private Long idCor;
}
