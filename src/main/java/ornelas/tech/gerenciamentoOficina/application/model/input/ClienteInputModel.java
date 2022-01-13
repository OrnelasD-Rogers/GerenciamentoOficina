package ornelas.tech.gerenciamentoOficina.application.model.input;

import lombok.Data;
import ornelas.tech.gerenciamentoOficina.domain.model.SexoEnum;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class ClienteInputModel {

    @NotBlank
    private String nomeCliente;
    @NotNull
    private LocalDateTime dataCadastro;
    @NotBlank
    private String cpf;
    @NotNull
    private SexoEnum sexo;
    @NotNull
    private List<TelefoneInputModel> telefones = new ArrayList<>();
}
