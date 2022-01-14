package ornelas.tech.gerenciamentoOficina.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ornelas.tech.gerenciamentoOficina.application.controllerapi.AparelhoDocApi;
import ornelas.tech.gerenciamentoOficina.application.mapper.assembler.AparelhoAssembler;
import ornelas.tech.gerenciamentoOficina.application.mapper.desassembler.AparelhoDesassembler;
import ornelas.tech.gerenciamentoOficina.application.model.AparelhoModel;
import ornelas.tech.gerenciamentoOficina.application.model.input.AparelhoInputModel;
import ornelas.tech.gerenciamentoOficina.domain.model.Aparelho;
import ornelas.tech.gerenciamentoOficina.domain.model.SituacaoAparelhoEnum;
import ornelas.tech.gerenciamentoOficina.domain.service.AparelhoService;

import java.time.OffsetDateTime;
import java.util.List;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("v1/aparelho")
public class AparelhoController implements AparelhoDocApi {

    private final AparelhoService aparelhoService;
    private final AparelhoAssembler assembler;
    private final AparelhoDesassembler desassembler;

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<AparelhoModel> findAll() {
        return assembler.toCollectionModel(aparelhoService.findAll());
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{idAparelho}")
    public AparelhoModel findById(@PathVariable Long idAparelho) {
        return assembler.toModel(aparelhoService.findById(idAparelho));
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/por-nome-cliente")
    public List<AparelhoModel> findByNomeCliente(@RequestParam String nomeCliente) {
        return assembler.toCollectionModel(aparelhoService.findByNomeCliente(nomeCliente));
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/tipo")
    public List<AparelhoModel> findByTipo(@RequestParam String tipoAparelho) {
        return assembler.toCollectionModel(aparelhoService.findByTipo(tipoAparelho));
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/marca")
    public List<AparelhoModel> findByMarca(@RequestParam String marcaAparelho) {
        return assembler.toCollectionModel(aparelhoService.findByMarca(marcaAparelho));
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/modelo")
    public List<AparelhoModel> findByModelo(@RequestParam String modeloAparelho) {
        return assembler.toCollectionModel(aparelhoService.findByModelo(modeloAparelho));
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/situacao")
    public List<AparelhoModel> findBySituacao(@RequestParam SituacaoAparelhoEnum situacao) {
        return assembler.toCollectionModel(aparelhoService.findBySituacao(situacao));
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/data-entrada")
    public List<AparelhoModel> findByDataEntrada(@DateTimeFormat(pattern = "uuuu-MM-dd'T'HH:mm:ssXXXXX") @RequestParam OffsetDateTime inicio,
                                                 @DateTimeFormat(pattern = "uuuu-MM-dd'T'HH:mm:ssXXXXX") @RequestParam OffsetDateTime limite) {
        return assembler.toCollectionModel(aparelhoService.findByDataEntrada(inicio, limite));
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/data-saida")
    public List<AparelhoModel> findByDataSaida(@DateTimeFormat(pattern = "uuuu-MM-dd'T'HH:mm:ssXXXXX") @RequestParam OffsetDateTime inicio,
                                               @DateTimeFormat(pattern = "uuuu-MM-dd'T'HH:mm:ssXXXXX") @RequestParam OffsetDateTime limite) {
        return assembler.toCollectionModel(aparelhoService.findByDataSaida(inicio, limite));
    }

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public AparelhoModel save(@RequestBody AparelhoInputModel aparelhoInputModel) {
        return assembler.toModel(aparelhoService.save(desassembler.toDomainModel(aparelhoInputModel)));
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{idAparelho}")
    public AparelhoModel update(@PathVariable Long idAparelho, @RequestBody AparelhoInputModel aparelhoInputModel) {
        Aparelho aparelhoAtual = aparelhoService.findById(idAparelho);
        desassembler.copyToDomainClass(aparelhoInputModel, aparelhoAtual);
        return assembler.toModel(aparelhoService.save(aparelhoAtual));
    }

    @Override
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{idAparelho}")
    public void delete(Long idAparelho) {
        aparelhoService.delete(idAparelho);
    }
}
