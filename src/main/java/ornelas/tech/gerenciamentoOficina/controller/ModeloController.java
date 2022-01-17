package ornelas.tech.gerenciamentoOficina.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ornelas.tech.gerenciamentoOficina.application.controllerapi.ModeloDocApi;
import ornelas.tech.gerenciamentoOficina.application.mapper.assembler.ModeloAssembler;
import ornelas.tech.gerenciamentoOficina.application.mapper.desassembler.ModeloDesassembler;
import ornelas.tech.gerenciamentoOficina.application.model.ModeloModel;
import ornelas.tech.gerenciamentoOficina.application.model.input.ModeloInputModel;
import ornelas.tech.gerenciamentoOficina.domain.model.Modelo;
import ornelas.tech.gerenciamentoOficina.domain.service.ModeloService;

import java.util.List;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/modelo-aparelho")
public class ModeloController implements ModeloDocApi {

    private final ModeloService modeloService;
    private final ModeloAssembler assembler;
    private final ModeloDesassembler desassembler;


    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<ModeloModel> findAll() {
        return assembler.toCollectionModel(modeloService.findAll());
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{idModelo}")
    public ModeloModel findById(@PathVariable Long idModelo) {
        return assembler.toModel(modeloService.findById(idModelo));
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/por-nome")
    public List<ModeloModel> findByNomeModelo(@RequestParam String nomeModelo) {
        return assembler.toCollectionModel(modeloService.findByNomeModelo(nomeModelo));
    }

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ModeloModel save(@RequestBody ModeloInputModel modeloInputModel) {
        modeloService.existsByNomeModelo(modeloInputModel.getModeloAparelho());
        return assembler.toModel(modeloService.save(desassembler.toDomainModel(modeloInputModel)));
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{idModelo}")
    public ModeloModel update(@PathVariable Long idModelo, @RequestBody ModeloInputModel modeloInputModel) {
        modeloService.existsByNomeModelo(modeloInputModel.getModeloAparelho());
        Modelo modeloAtual = modeloService.findById(idModelo);
        desassembler.copyToDomainModel(modeloInputModel, modeloAtual);
        return assembler.toModel(modeloService.save(modeloAtual));
    }

    @Override
    @DeleteMapping("/{idModelo}")
    public void delete(@PathVariable Long idModelo) {
        modeloService.deleteById(idModelo);
    }
}
