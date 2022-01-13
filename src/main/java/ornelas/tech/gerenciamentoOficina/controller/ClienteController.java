package ornelas.tech.gerenciamentoOficina.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ornelas.tech.gerenciamentoOficina.application.controllerapi.ClienteDocApi;
import ornelas.tech.gerenciamentoOficina.application.mapper.assembler.ClienteAssembler;
import ornelas.tech.gerenciamentoOficina.application.mapper.desassembler.ClienteDesassembler;
import ornelas.tech.gerenciamentoOficina.application.model.ClienteModel;
import ornelas.tech.gerenciamentoOficina.application.model.input.ClienteInputModel;
import ornelas.tech.gerenciamentoOficina.domain.model.Cliente;
import ornelas.tech.gerenciamentoOficina.domain.service.ClienteService;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;


@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/cliente")
public class ClienteController implements ClienteDocApi {

    private final ClienteService clienteService;
    private final ClienteAssembler clienteAssembler;
    private final ClienteDesassembler clienteDesassembler;

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<ClienteModel> findAll() {
        return clienteAssembler.toCollectionModel(clienteService.findAll());
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{idCliente}")
    public ClienteModel findById(@NotNull @PathVariable Long idCliente) {
        return clienteAssembler.toModel(clienteService.findById(idCliente));
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/por-nome")
    public List<ClienteModel> findByNome(@NotBlank @RequestParam String nome) {
        return clienteAssembler.toCollectionModel(clienteService.findByNome(nome));
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/por-cpf")
    public ClienteModel findByCpf(@NotBlank @RequestParam String cpf) {
        return clienteAssembler.toModel(clienteService.findByCpf(cpf));
    }

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ClienteModel save(@Valid @RequestBody ClienteInputModel clienteInputModel ) {
        Cliente cliente = clienteDesassembler.toDomainModel(clienteInputModel);
        return clienteAssembler.toModel(clienteService.save(cliente));
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{idCliente}")
    public ClienteModel update(@NotNull @PathVariable Long idCliente,
                               @Valid @RequestBody ClienteInputModel clienteInputModel) {
        Cliente clienteAtual = clienteService.findById(idCliente);
        clienteDesassembler.copyToDomainModel(clienteInputModel, clienteAtual);
        return clienteAssembler.toModel(clienteService.update(clienteAtual));
    }

    @Override
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{idCliente}")
    public void delete(@NotNull @PathVariable Long idCliente) {
        clienteService.delete(idCliente);
    }

    @Override
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{idCliente}/tel/{idTel}")
    public void deleteTel(@NotNull @PathVariable Long idCliente, @NotNull @PathVariable Long idTel) {
        Cliente cliente = clienteService.findById(idCliente);
        clienteService.deleteTel(cliente, idTel);
    }
}
