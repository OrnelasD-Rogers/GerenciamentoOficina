package ornelas.tech.gerenciamentoOficina.application.controllerapi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import ornelas.tech.gerenciamentoOficina.application.model.ContatoModel;
import ornelas.tech.gerenciamentoOficina.application.model.input.ContatoInputModel;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Tag(name = "Contato", description = "Contato efetuado para um aparelho")
public interface ContatoDocApi {

    @Operation(summary = "Lista todos os contatos",tags = {"Contato"})
    @ApiResponses(value = {
    @ApiResponse(responseCode="200", description = "Operação realizada com sucesso"
    , content = @Content(array = @ArraySchema(schema = @Schema(implementation = ContatoModel.class))))})
    List<ContatoModel> findAll();

    @Operation(summary = "Busca um contato com base em seu id",tags = {"Contato"})
    @ApiResponses(value = {
            @ApiResponse(responseCode="200", description = "Operação realizada com sucesso"
                    , content = @Content(array = @ArraySchema(schema = @Schema(implementation = ContatoModel.class))))})
    ContatoModel findById(
            @Parameter(description = "Id do contato a ser buscado. Não pode ser nulo", required = true)
            @NotNull Long idContato);

    @Operation(summary = "Lista todos os contatos cadastrados em um determinado id de Aparelho",tags = {"Contato", "Aparelho"})
    @ApiResponses(value = {
    @ApiResponse(responseCode="200", description = "Operação realizada com sucesso"
    , content = @Content(array = @ArraySchema(schema = @Schema(implementation = ContatoModel.class))))})
    List<ContatoModel> findByIdAparelho(
            @Parameter(description = "Id do aparelho a ter os contatos buscados. Não pode ser nulo", required = true)
            @NotNull Long idAparelho);


    @Operation(summary = "Adiciona um novo contato",tags = {"Contato"})
    @ApiResponses(value = {
    @ApiResponse(responseCode="200", description = "Inserção realizada com sucesso"
    , content = @Content(schema = @Schema(implementation = ContatoModel.class))),
    @ApiResponse(responseCode="400", description = "Entrada de dados inválida")})
    ContatoModel save(
            @Parameter(description = "Contato a ser cadastrado. Não pode ser nulo",schema = @Schema(implementation = ContatoInputModel.class), required = true)
            @Valid ContatoInputModel contatoInputModel);


    @Operation(summary = "Atualiza um contato",tags = {"Contato"})
    @ApiResponses(value = {
    @ApiResponse(responseCode="200", description = "Inserção realizada com sucesso"
    , content = @Content(schema = @Schema(implementation = ContatoModel.class))),
    @ApiResponse(responseCode="400", description = "Entrada de dados inválida"),
    @ApiResponse(responseCode="404", description = "Contato não encontrado")})
    ContatoModel update(
            @Parameter(description = "Id do contato a ser atualizado. Não pode ser nulo", required = true)
            @NotNull Long idContato,
            @Parameter(description = "Contato a ser atualizado. Não pode ser nulo",schema = @Schema(implementation = ContatoInputModel.class), required = true)
            @Valid ContatoInputModel contatoInputModel);

    @Operation(summary = "Exclui um contato",tags = {"Contato"})
    @ApiResponses(value = {
    @ApiResponse(responseCode="204", description = "Exclusão realizada com sucesso"),
    @ApiResponse(responseCode="404", description = "Contato não encontrado")})
    void deleteById(
            @Parameter(description = "Id do contato a ser excluido. Não pode ser nulo", required = true)
            @NotNull Long idContato);


}
