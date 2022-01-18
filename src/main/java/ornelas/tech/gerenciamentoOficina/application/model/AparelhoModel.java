package ornelas.tech.gerenciamentoOficina.application.model;

import lombok.Data;
import ornelas.tech.gerenciamentoOficina.domain.model.SituacaoAparelhoEnum;

import java.time.OffsetDateTime;
import java.util.List;

@Data
public class AparelhoModel {

    private Long idAparelho;
    private ClienteAparelhoModel cliente;
    private TipoModel tipo;
    private MarcaModel marca;
    private ModeloModel modelo;
    private TecnicoModel tecnico;
    private List<CorModel> cores;
    private SituacaoAparelhoEnum situacaoAparelho;
    private OffsetDateTime dataEntrada;
    private OffsetDateTime dataSaida;
    private OffsetDateTime dataAlteracao;
    private String problema;
    private String observacao;
    private Boolean urgencia;
    private Boolean revisao;
    private Double maoDeObra;
    private String orcamento;
}
