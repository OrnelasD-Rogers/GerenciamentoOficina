package ornelas.tech.gerenciamentoOficina.application.model;

import lombok.Data;
import ornelas.tech.gerenciamentoOficina.domain.model.SexoEnum;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class ClienteModel {

    private BigInteger idCliente;
    private String nomeCliente;
    private LocalDateTime dataCadastro;
    private String cpf;
    private SexoEnum sexo;
    private List<TelefoneModel> telefones = new ArrayList<>();
}
