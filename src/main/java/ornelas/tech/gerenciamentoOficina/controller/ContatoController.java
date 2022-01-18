package ornelas.tech.gerenciamentoOficina.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ornelas.tech.gerenciamentoOficina.application.controllerapi.ContatoDocApi;
import ornelas.tech.gerenciamentoOficina.application.mapper.assembler.ContatoAssembler;
import ornelas.tech.gerenciamentoOficina.application.mapper.desassembler.ContatoDesassembler;
import ornelas.tech.gerenciamentoOficina.application.model.ContatoModel;
import ornelas.tech.gerenciamentoOficina.application.model.input.ContatoInputModel;
import ornelas.tech.gerenciamentoOficina.domain.model.Contato;
import ornelas.tech.gerenciamentoOficina.domain.service.ContatoService;

import java.util.List;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/contato-aparelho")
public class ContatoController implements ContatoDocApi {

    private final ContatoService contatoService;
    private final ContatoAssembler assembler;
    private final ContatoDesassembler desassembler;


    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<ContatoModel> findAll() {
        return assembler.toCollectionModel(contatoService.findAll());
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{idContato}")
    public ContatoModel findById(@PathVariable Long idContato) {
        return assembler.toModel(contatoService.findById(idContato));
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/por-aparelho/{idAparelho}")
    public List<ContatoModel> findByIdAparelho(@PathVariable Long idAparelho) {
        return assembler.toCollectionModel(contatoService.findByIdAparelho(idAparelho));
    }

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ContatoModel save(@RequestBody ContatoInputModel contatoInputModel) {
        return assembler.toModel(contatoService.save(desassembler.toDomainModel(contatoInputModel)));
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{idContato}")
    public ContatoModel update(@PathVariable Long idContato, @RequestBody ContatoInputModel contatoInputModel) {
        Contato contatoAtual = contatoService.findById(idContato);
        desassembler.copyToDomainModel(contatoInputModel, contatoAtual);
        return assembler.toModel(contatoService.save(contatoAtual));
    }

    @Override
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{idContato}")
    public void deleteById(@PathVariable Long idContato) {
        contatoService.deleteById(idContato);
    }
}
