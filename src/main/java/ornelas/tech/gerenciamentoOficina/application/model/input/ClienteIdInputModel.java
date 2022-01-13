package ornelas.tech.gerenciamentoOficina.application.model.input;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ClienteIdInputModel {

    @NotNull
    private Long idCliente;
}
