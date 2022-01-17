package ornelas.tech.gerenciamentoOficina.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ornelas.tech.gerenciamentoOficina.application.controllerapi.TecnicoDocApi;
import ornelas.tech.gerenciamentoOficina.application.mapper.assembler.TecnicoAssembler;
import ornelas.tech.gerenciamentoOficina.application.mapper.desassembler.TecnicoDesassembler;
import ornelas.tech.gerenciamentoOficina.application.model.TecnicoModel;
import ornelas.tech.gerenciamentoOficina.application.model.input.TecnicoInputModel;
import ornelas.tech.gerenciamentoOficina.domain.model.Tecnico;
import ornelas.tech.gerenciamentoOficina.domain.service.TecnicoService;

import java.util.List;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/tecnico")
public class TecnicoController implements TecnicoDocApi {

    private final TecnicoService tecnicoService;
    private final TecnicoAssembler assembler;
    private final TecnicoDesassembler desassembler;

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<TecnicoModel> findAll() {
        return assembler.toCollectionModel(tecnicoService.findAll());
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{idTecnico}")
    public TecnicoModel findById(@PathVariable Long idTecnico) {
        return assembler.toModel(tecnicoService.findById(idTecnico));
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/por-nome")
    public List<TecnicoModel> findByName(@RequestParam String nomeTecnico) {
        return assembler.toCollectionModel(tecnicoService.findByName(nomeTecnico));
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/por-numero")
    public List<TecnicoModel> findByNumero(@RequestParam String numeroTecnico) {
        return assembler.toCollectionModel(tecnicoService.findByNumero(numeroTecnico));
    }

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public TecnicoModel save(@RequestBody TecnicoInputModel tecnicoInputModel) {
        return assembler.toModel(tecnicoService.save(desassembler.toDomainModel(tecnicoInputModel)));
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{idTecnico}")
    public TecnicoModel update(@PathVariable Long idTecnico, @RequestBody TecnicoInputModel tecnicoInputModel) {
        Tecnico tecnicoAtual = tecnicoService.findById(idTecnico);
        desassembler.copyToDomainModel(tecnicoInputModel, tecnicoAtual);
        return assembler.toModel(tecnicoService.save(tecnicoAtual));
    }

    @Override
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{idTecnico}")
    public void deleteById(@PathVariable Long idTecnico) {
        tecnicoService.deleteById(idTecnico);
    }
}
