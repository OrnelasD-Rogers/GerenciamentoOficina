package ornelas.tech.gerenciamentoOficina.application.model;

import lombok.Data;
import ornelas.tech.gerenciamentoOficina.domain.model.SituacaoAparelhoEnum;

@Data
public class ContatoModel {

    private Long idContato;
    private TelefoneModel telefone;
    private TecnicoModel tecnico;
    private String mensagem;
    private SituacaoAparelhoEnum situacaoAparelho;
}
