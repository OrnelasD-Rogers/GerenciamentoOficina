package ornelas.tech.gerenciamentoOficina.domain.exception;

public class ClienteExistenteException extends EntidadeExistenteException{

    public ClienteExistenteException(String cpf) {
        super(String.format("O cliente com o cpf: %s, já está cadastrado", cpf));
    }
}
