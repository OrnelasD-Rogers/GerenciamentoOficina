package ornelas.tech.gerenciamentoOficina.domain.exception;

import java.math.BigInteger;

public class ClienteNaoEncontradoException extends EntidadeNaoEncontradaException{

    public ClienteNaoEncontradoException() {
        super("Não foram encontrados resultados para a pesquisa");
    }

    public ClienteNaoEncontradoException(Long idCliente) {
        super(String.format("O Cliente com o id %d, não foi encontrado", idCliente));
    }

    public ClienteNaoEncontradoException(String cpf) {
        super(String.format("O Cliente com o cpf: %s, não foi encontrado", cpf));
    }
}
