package ornelas.tech.gerenciamentoOficina.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ornelas.tech.gerenciamentoOficina.application.controllerapi.MarcaDocApi;
import ornelas.tech.gerenciamentoOficina.application.mapper.assembler.MarcaAssembler;
import ornelas.tech.gerenciamentoOficina.application.mapper.desassembler.MarcaDesassembler;
import ornelas.tech.gerenciamentoOficina.application.model.MarcaModel;
import ornelas.tech.gerenciamentoOficina.application.model.input.MarcaInputModel;
import ornelas.tech.gerenciamentoOficina.domain.model.Marca;
import ornelas.tech.gerenciamentoOficina.domain.service.MarcaService;

import java.util.List;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/marca-aparelho")
public class MarcaController implements MarcaDocApi {

    private final MarcaService marcaService;
    private final MarcaAssembler assembler;
    private final MarcaDesassembler desassembler;

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<MarcaModel> findAll() {
        return assembler.toCollectionModel(marcaService.findAll());
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{idMarca}")
    public MarcaModel findById(@PathVariable Long idMarca) {
        return assembler.toModel(marcaService.findById(idMarca));
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/por-marca")
    public List<MarcaModel> findByMarca(@RequestParam String marca) {
        return assembler.toCollectionModel(marcaService.findByMarca(marca));
    }

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public MarcaModel save(@RequestBody MarcaInputModel marcaInputModel) {
        marcaService.existsByNomeMarca(marcaInputModel.getMarcaAparelho());
        return assembler.toModel(marcaService.save(desassembler.toDomainModel(marcaInputModel)));
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{idMarca}")
    public MarcaModel update(@PathVariable Long idMarca, @RequestBody MarcaInputModel marcaInputModel) {
        marcaService.existsByNomeMarca(marcaInputModel.getMarcaAparelho());
        Marca marcaAtual = marcaService.findById(idMarca);
        desassembler.copyToDomainModel(marcaInputModel, marcaAtual);
        return assembler.toModel(marcaService.save(marcaAtual));
    }

    @Override
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{idMarca}")
    public void delete(@PathVariable Long idMarca) {
        marcaService.deleteById(idMarca);
    }
}
