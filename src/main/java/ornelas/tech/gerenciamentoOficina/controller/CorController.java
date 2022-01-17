package ornelas.tech.gerenciamentoOficina.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ornelas.tech.gerenciamentoOficina.application.controllerapi.CorDocApi;
import ornelas.tech.gerenciamentoOficina.application.mapper.assembler.CorAssembler;
import ornelas.tech.gerenciamentoOficina.application.mapper.desassembler.CorDesassembler;
import ornelas.tech.gerenciamentoOficina.application.model.CorModel;
import ornelas.tech.gerenciamentoOficina.application.model.input.CorInputModel;
import ornelas.tech.gerenciamentoOficina.domain.model.Cor;
import ornelas.tech.gerenciamentoOficina.domain.service.CorService;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/cor-aparelho")
public class CorController implements CorDocApi {

    private final CorService corService;
    private final CorAssembler assembler;
    private final CorDesassembler desassembler;

    @Override
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CorModel> findlAll() {
        return assembler.toCollectionModel(corService.findlAll());
    }

    @Override
    @GetMapping("/{idCor}")
    @ResponseStatus(HttpStatus.OK)
    public CorModel findById(@PathVariable Long idCor) {
        return assembler.toModel(corService.findById(idCor));
    }

    @Override
    @GetMapping("/por-nome")
    @ResponseStatus(HttpStatus.OK)
    public List<CorModel> findByNomeCor(@RequestParam String nomeCor) {
        return assembler.toCollectionModel(corService.findByNomeCor(nomeCor));
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CorModel save(@RequestBody CorInputModel corInputModel) {
        corService.existsByNomeCor(corInputModel.getNomeCor());
        return assembler.toModel(corService.save(desassembler.toDomainModel(corInputModel)));
    }

    @Override
    @PutMapping("/{idCor}")
    @ResponseStatus(HttpStatus.OK)
    public CorModel update(@PathVariable Long idCor, @RequestBody CorInputModel corInputModel) {
        corService.existsByNomeCor(corInputModel.getNomeCor());
        Cor corAtual = corService.findById(idCor);
        desassembler.copyToDomainModel(corInputModel, corAtual);
        return assembler.toModel(corService.save(corAtual));
    }

    @Override
    @DeleteMapping("/{idCor}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long idCor) {
        corService.deleteById(idCor);
    }
}
