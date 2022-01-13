package ornelas.tech.gerenciamentoOficina.application.mapper.desassembler;

import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;
import ornelas.tech.gerenciamentoOficina.application.model.input.ClienteInputModel;
import ornelas.tech.gerenciamentoOficina.application.model.input.TelefoneInputModel;
import ornelas.tech.gerenciamentoOficina.domain.model.Cliente;
import ornelas.tech.gerenciamentoOficina.domain.model.Telefone;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ClienteDesassembler {

    private final ModelMapper mapper;

    Converter<ClienteInputModel, Cliente> atualizaCliente = new Converter<>() {
        @Override
        public Cliente convert(MappingContext<ClienteInputModel, Cliente> context) {
            ModelMapper mapperConverter = new ModelMapper();
            context.getDestination().setNomeCliente(context.getSource().getNomeCliente());
            context.getDestination().setDataCadastro(context.getSource().getDataCadastro());
            context.getDestination().setCpf(context.getSource().getCpf());
            context.getDestination().setSexo(context.getSource().getSexo());

            //Se for um novo Cliente então só adiciono os novos telefones
            if (context.getDestination().getIdCliente() == null) {
                for (TelefoneInputModel telInput : context.getSource().getTelefones()) {
                    context.getDestination().getTelefones().add(mapperConverter.map(telInput, Telefone.class));
                }
            } else {
                for (TelefoneInputModel telIn : context.getSource().getTelefones()) {
                    for (Telefone telModel : context.getDestination().getTelefones()) {
                        if (telIn.getIdTelefone() != null && telIn.getIdTelefone().equals(telModel.getIdTelefone())) {
                            telModel.setNumero(telIn.getNumero());
                            telModel.setTipoNumero(telIn.getTipoNumero());
                            telModel.setWhats(telIn.isWhats());
                        }
                    }
                }
                List<Telefone> telefonesToAdd = context.getSource().getTelefones().stream()
                        .filter(telIn -> telIn.getIdTelefone() == null)
                        .map(telIn -> {
                            Telefone novoTel = mapperConverter.map(telIn, Telefone.class);
                            novoTel.setCliente(context.getDestination());
                            return novoTel;
                        })
                        .collect(Collectors.toList());
                context.getDestination().getTelefones().addAll(telefonesToAdd);
            }
            return context.getDestination();
        }
    };

    public Cliente toDomainModel(ClienteInputModel clienteInputModel){
        return mapper.map(clienteInputModel, Cliente.class);
    }

    public void copyToDomainModel(ClienteInputModel clienteInput, Cliente cliente){
        mapper.addConverter(atualizaCliente);
        mapper.map(clienteInput, cliente);
    }


}
