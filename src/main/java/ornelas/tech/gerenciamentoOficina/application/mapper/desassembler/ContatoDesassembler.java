package ornelas.tech.gerenciamentoOficina.application.mapper.desassembler;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ornelas.tech.gerenciamentoOficina.application.model.input.ContatoInputModel;
import ornelas.tech.gerenciamentoOficina.domain.model.Contato;

@Component
@RequiredArgsConstructor
public class ContatoDesassembler {

    private final ModelMapper mapper;


    public Contato toDomainModel(ContatoInputModel contatoInputModel){
        return mapper.map(contatoInputModel, Contato.class);
    }

    public void copyToDomainModel(ContatoInputModel contatoInputModel, Contato contato){
        mapper.map(contatoInputModel, contato);
    }
}
