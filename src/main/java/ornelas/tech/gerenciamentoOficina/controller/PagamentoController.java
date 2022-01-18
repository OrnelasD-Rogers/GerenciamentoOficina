package ornelas.tech.gerenciamentoOficina.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ornelas.tech.gerenciamentoOficina.application.controllerapi.PagamentoDocApi;
import ornelas.tech.gerenciamentoOficina.application.mapper.assembler.PagamentoAssembler;
import ornelas.tech.gerenciamentoOficina.application.mapper.desassembler.PagamentoDesassembler;
import ornelas.tech.gerenciamentoOficina.application.model.PagamentoModel;
import ornelas.tech.gerenciamentoOficina.application.model.input.PagamentoInputModel;
import ornelas.tech.gerenciamentoOficina.domain.model.Pagamento;
import ornelas.tech.gerenciamentoOficina.domain.service.PagamentoService;

import javax.validation.Valid;
import java.util.List;

@Valid
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/pagamento")
public class PagamentoController implements PagamentoDocApi {

    private final PagamentoService pagamentoService;
    private final PagamentoAssembler assembler;
    private final PagamentoDesassembler desassembler;


    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<PagamentoModel> findAll() {
        return assembler.toCollectionModel(pagamentoService.findAll());
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{idPagamento}")
    public PagamentoModel findById(@PathVariable Long idPagamento) {
        return assembler.toModel(pagamentoService.findById(idPagamento));
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/por-aparelho/{idAparelho}")
    public List<PagamentoModel> findAllByIdAparelho(@PathVariable Long idAparelho) {
        return assembler.toCollectionModel(pagamentoService.findAllByIdAparelho(idAparelho));
    }

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public PagamentoModel save(@RequestBody PagamentoInputModel pagamentoInputModel) {
        return assembler.toModel(pagamentoService.save(desassembler.toDomainModel(pagamentoInputModel)));
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{idPagamento}")
    public PagamentoModel update(@PathVariable Long idPagamento,
                                 @RequestBody PagamentoInputModel pagamentoInputModel) {
        Pagamento pagamentoAtual = pagamentoService.findById(idPagamento);
        desassembler.copyToDomainModel(pagamentoInputModel, pagamentoAtual);
        return assembler.toModel(pagamentoService.save(pagamentoAtual));
    }

    @Override
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{idPagamento}")
    public void deleteById(@PathVariable Long idPagamento) {
        pagamentoService.deleteById(idPagamento);
    }
}
