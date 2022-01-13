package ornelas.tech.gerenciamentoOficina.application.controllerapi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import ornelas.tech.gerenciamentoOficina.application.model.ClienteModel;
import ornelas.tech.gerenciamentoOficina.application.model.input.ClienteInputModel;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;


@Tag(name = "Cliente")
public interface ClienteDocApi {


    @Operation(summary = "Lista todos os clientes",tags = {"cliente"})
    @ApiResponses(value = {
            @ApiResponse(responseCode="200", description = "Operação realizada com sucesso"
                    , content = @Content(array = @ArraySchema(schema = @Schema(implementation = ClienteModel.class))))})
    List<ClienteModel> findAll();


    @Operation(summary = "Procura um cliente pelo seu id",tags = {"cliente"})
    @ApiResponses(value = {
            @ApiResponse(responseCode="200", description = "Operação realizada com sucesso"
                    , content = @Content(schema = @Schema(implementation = ClienteModel.class))),
            @ApiResponse(responseCode="404", description = "Cliente não encontrado")})
    ClienteModel findById(
            @Parameter(description = "Id do cliente a ser buscado. Não pode ser nulo", required = true)
            @NotNull Long id);


    @Operation(summary = "Procura um cliente pelo seu nome",tags = {"cliente"})
    @ApiResponses(value = {
            @ApiResponse(responseCode="200", description = "Operação realizada com sucesso"
                    , content = @Content(schema = @Schema(implementation = ClienteModel.class)))})
    List<ClienteModel> findByNome(
            @Parameter(description = "Nome do cliente a ser buscado. Não pode ser nulo", required = true)
            @NotBlank String nome);


    @Operation(summary = "Procura um cliente pelo seu cpf",tags = {"cliente"})
    @ApiResponses(value = {
            @ApiResponse(responseCode="200", description = "Operação realizada com sucesso"
                    , content = @Content(schema = @Schema(implementation = ClienteModel.class))),
            @ApiResponse(responseCode="404", description = "Cliente não encontrado")})
    ClienteModel findByCpf(
            @Parameter(description = "Cpf do cliente a ser buscado. Não pode ser nulo", required = true)
            @NotBlank String cpf);


    @Operation(summary = "Adiciona um novo cliente",tags = {"cliente"})
    @ApiResponses(value = {
            @ApiResponse(responseCode="200", description = "Inserção realizada com sucesso"
                    , content = @Content(schema = @Schema(implementation = ClienteModel.class))),
            @ApiResponse(responseCode="400", description = "Entrada de dados inválida")})
    ClienteModel save(
            @Parameter(description = "Cliente a ser cadastrado.", required = true, schema = @Schema(implementation = ClienteInputModel.class))
            @Valid ClienteInputModel clienteInputModel);


    @Operation(summary = "Atualiza um cliente",tags = {"cliente"})
    @ApiResponses(value = {
            @ApiResponse(responseCode="200", description = "Atualização realizada com sucesso"
                    , content = @Content(schema = @Schema(implementation = ClienteModel.class))),
            @ApiResponse(responseCode="404", description = "cliente não encontrado"),
            @ApiResponse(responseCode="400", description = "Dados inválidos")})
    ClienteModel update(
            @Parameter(description = "Id do cliente a ser atualizado. Não pode ser nulo", required = true)
            @NotNull Long id ,
            @Parameter(description = "Cliente a ser atualizado.", required = true, schema = @Schema(implementation = ClienteInputModel.class))
            @Valid ClienteInputModel clienteInputModel);


    @Operation(summary = "Exclui um cliente",tags = {"cliente"})
    @ApiResponses(value = {
            @ApiResponse(responseCode="204", description = "Exclusão realizada com sucesso"),
            @ApiResponse(responseCode="404", description = "Cliente não encontrado")})
    void delete(
            @Parameter(description = "Id do cliente a ser excluido. Não pode ser nulo", required = true)
            @NotNull Long id);

    @Operation(summary = "Exclui um telefone de um cliente",tags = {"cliente", "telefone"})
    @ApiResponses(value = {
            @ApiResponse(responseCode="204", description = "Exclusão realizada com sucesso"),
            @ApiResponse(responseCode="404", description = "Telefone do cliente não encontrado")})
    void deleteTel(
            @Parameter(description = "Id do cliente a ter o telefone excluido. Não pode ser nulo", required = true)
            @NotNull Long idCliente,
            @Parameter(description = "Id do telefone a ser excluido. Não pode ser nulo", required = true)
            @NotNull Long idTel);
}
