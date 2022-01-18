package ornelas.tech.gerenciamentoOficina.application.model.input;

import lombok.Data;
import ornelas.tech.gerenciamentoOficina.domain.model.SituacaoAparelhoEnum;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ContatoInputModel {
    @Valid
    private AparelhoIdInputModel aparelho;
    @Valid
    private TelefoneIdInputModel telefone;
    @Valid
    private TecnicoIdInputModel tecnico;
    @NotBlank
    private String mensagem;
    @NotNull
    private SituacaoAparelhoEnum situacaoAparelho;
}
