package ornelas.tech.gerenciamentoOficina.application.model.input;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import ornelas.tech.gerenciamentoOficina.domain.model.SituacaoAparelhoEnum;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;

@Data
public class AparelhoInputModel {

    @Valid
    private ClienteIdInputModel cliente;
    @Valid
    private TipoIdInputModel tipo;
    @Valid
    private MarcaIdInputModel marca;
    @Valid
    private ModeloIdInputModel modelo;
    @NotNull
    private SituacaoAparelhoEnum situacaoAparelho;
    @NotNull
    @JsonFormat(pattern= "uuuu-MM-dd'T'HH:mm:ssXXXXX")
    private OffsetDateTime dataEntrada;
    @NotBlank
    private String problema;
    @NotBlank
    private String observacao;
    @NotNull
    private Boolean urgencia;
    @NotNull
    private Boolean revisao;
    private Double maoDeObra;
    private String orcamento;
}
