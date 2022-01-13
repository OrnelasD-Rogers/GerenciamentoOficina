package ornelas.tech.gerenciamentoOficina.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ornelas.tech.gerenciamentoOficina.application.controllerapi.TipoDocApi;
import ornelas.tech.gerenciamentoOficina.application.mapper.assembler.TipoAssembler;
import ornelas.tech.gerenciamentoOficina.application.mapper.desassembler.TipoDesassembler;
import ornelas.tech.gerenciamentoOficina.application.model.TipoModel;
import ornelas.tech.gerenciamentoOficina.application.model.input.TipoInputModel;
import ornelas.tech.gerenciamentoOficina.domain.model.Tipo;
import ornelas.tech.gerenciamentoOficina.domain.service.TipoService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/tipo-aparelho")
public class TipoController implements TipoDocApi {

    private final TipoService tipoService;
    private final TipoAssembler assembler;
    private final TipoDesassembler desassembler;

    @Override
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TipoModel> findAll() {
        return assembler.toCollectionModel(tipoService.findAll());
    }

    @Override
    @GetMapping("/{idTipo}")
    @ResponseStatus(HttpStatus.OK)
    public TipoModel findById(@NotNull @PathVariable Long idTipo) {
        return assembler.toModel(tipoService.findById(idTipo));
    }

    @Override
    @GetMapping("/por-tipo")
    @ResponseStatus(HttpStatus.OK)
    public List<TipoModel> findByTipoAparelho(@RequestParam String tipoAparelho) {
        return assembler.toCollectionModel(tipoService.findByTipoAparelho(tipoAparelho));
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TipoModel save(@Valid @RequestBody TipoInputModel tipoInputModel) {
        tipoService.tipoExistente(tipoInputModel.getTipoAparelho());
        return assembler.toModel(tipoService.save(desassembler.toDomainModel(tipoInputModel)));
    }

    @Override
    @PutMapping("/{idTipo}")
    public TipoModel update(@PathVariable @NotNull Long idTipo, @Valid @RequestBody TipoInputModel tipoInputModel) {
       tipoService.tipoExistente(tipoInputModel.getTipoAparelho());
        Tipo tipoAtual = tipoService.findById(idTipo);
       desassembler.copyToDomainModel(tipoInputModel, tipoAtual);
        return assembler.toModel(tipoService.save(tipoAtual));
    }

    @Override
    @DeleteMapping("/{idTipo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull Long idTipo) {
        tipoService.delete(idTipo);
    }
}
