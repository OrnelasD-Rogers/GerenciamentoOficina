package ornelas.tech.gerenciamentoOficina.application.controllerapi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import ornelas.tech.gerenciamentoOficina.application.model.PagamentoModel;
import ornelas.tech.gerenciamentoOficina.application.model.input.PagamentoInputModel;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Tag(name = "Pagamento", description = "Pagamentos de um determinado aparelho")
public interface PagamentoDocApi {


    @Operation(summary = "Lista todos os pagamentos",tags = {"Pagamento"})
    @ApiResponses(value = {
    @ApiResponse(responseCode="200", description = "Operação realizada com sucesso"
    , content = @Content(array = @ArraySchema(schema = @Schema(implementation = PagamentoModel.class))))})
    List<PagamentoModel> findAll();


    @Operation(summary = "Busca um pagamento pelo seu id",tags = {"Pagamento"})
    @ApiResponses(value = {
    @ApiResponse(responseCode="200", description = "Operação realizada com sucesso"
    , content = @Content(array = @ArraySchema(schema = @Schema(implementation = PagamentoModel.class)))),
    @ApiResponse(responseCode="404", description = "Pagamento não encontrado")})
    PagamentoModel findById(
            @Parameter(description = "Id do pagamento a ser buscado. Não pode ser nulo", required = true)
            @NotNull Long idPagamento);

    @Operation(summary = "Lista os pagamentos de um aparelho",tags = {"Pagamento"})
    @ApiResponses(value = {
    @ApiResponse(responseCode="200", description = "Operação realizada com sucesso"
    , content = @Content(array = @ArraySchema(schema = @Schema(implementation = PagamentoModel.class))))})
    List<PagamentoModel> findAllByIdAparelho(
            @Parameter(description = "Id do aparelho a ter os pagamentos buscados. Não pode ser nulo", required = true)
            @NotNull Long idAparelho);

    @Operation(summary = "Adiciona um novo pagamento",tags = {"Pagamento"})
    @ApiResponses(value = {
    @ApiResponse(responseCode="200", description = "Inserção realizada com sucesso"
    , content = @Content(schema = @Schema(implementation = PagamentoModel.class))),
    @ApiResponse(responseCode="400", description = "Entrada de dados inválida")})
    PagamentoModel save(
            @Parameter(description = "Pagamento a ser inserido. Não pode ser nulo",schema = @Schema(implementation = PagamentoInputModel.class), required = true)
            @Valid PagamentoInputModel pagamentoInputModel);

    @Operation(summary = "Atualiza um pagamento",tags = {"Pagamento"})
    @ApiResponses(value = {
    @ApiResponse(responseCode="200", description = "Inserção realizada com sucesso"
    , content = @Content(schema = @Schema(implementation = PagamentoModel.class))),
    @ApiResponse(responseCode="400", description = "Entrada de dados inválida"),
    @ApiResponse(responseCode="404", description = "Pagamento não encontrado")})
    PagamentoModel update(
            @Parameter(description = "Id do pagamento a ser atualizado. Não pode ser nulo", required = true)
            @NotNull Long idPagamento,
            @Parameter(description = "Pagamento a ser atualizado. Não pode ser nulo",schema = @Schema(implementation = PagamentoInputModel.class), required = true)
            @Valid PagamentoInputModel pagamentoInputModel);

    @Operation(summary = "Exclui um pagamento",tags = {"Pagamento"})
    @ApiResponses(value = {
    @ApiResponse(responseCode="204", description = "Exclusão realizada com sucesso"),
    @ApiResponse(responseCode="404", description = "Pagamento não encontrado")})
    void deleteById(
            @Parameter(description = "Id do pagamento a ser excluido. Não pode ser nulo", required = true)
            @NotNull Long idPagamento);


}
