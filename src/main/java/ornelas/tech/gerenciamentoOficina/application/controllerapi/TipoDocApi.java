package ornelas.tech.gerenciamentoOficina.application.controllerapi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import ornelas.tech.gerenciamentoOficina.application.model.TipoModel;
import ornelas.tech.gerenciamentoOficina.application.model.input.TipoInputModel;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Tag(name = "Tipo", description = "O tipo de um aparelho")
public interface TipoDocApi {

    @Operation(summary = "Lista todos os tipos de Aparelhos",tags = {"Tipo"})
    @ApiResponses(value = {
            @ApiResponse(responseCode="200", description = "Operação realizada com sucesso",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = TipoModel.class))))})
    List<TipoModel> findAll();

    @Operation(summary = "Lista o tipo de Aparelho com base em seu id",tags = {"Tipo"})
    @ApiResponses(value = {
            @ApiResponse(responseCode="200", description = "Operação realizada com sucesso",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = TipoModel.class)))),
                @ApiResponse(responseCode = "404", description = "Tipo não encontrado")})
    TipoModel findById(
            @Parameter(description = "Id do tipo a ser buscado. Não pode ser nulo", required = true)
            @NotNull Long idTipo);

    @Operation(summary = "Procura um tipo pelo seu nome",tags = {"Tipo"})
    @ApiResponses(value = {
            @ApiResponse(responseCode="200", description = "Operação realizada com sucesso"
                    , content = @Content(schema = @Schema(implementation = TipoModel.class)))})
    List<TipoModel> findByTipoAparelho(
            @Parameter(description = "Nome do tipo de aparelho a ser buscado. Não pode ser nulo", required = true)
            @NotBlank String tipoAparelho);

    @Operation(summary = "Adiciona um novo tipo",tags = {"Tipo"})
    @ApiResponses(value = {
            @ApiResponse(responseCode="200", description = "Inserção realizada com sucesso"
                    , content = @Content(schema = @Schema(implementation = TipoModel.class))),
            @ApiResponse(responseCode="400", description = "Entrada de dados inválida")})
    TipoModel save(
            @Parameter(description = "Tipo de aparelho a ser cadastrado.", required = true, schema = @Schema(implementation = TipoInputModel.class))
            @Valid TipoInputModel tipoInputModel);

    @Operation(summary = "Atualiza um tipo",tags = {"Tipo"})
    @ApiResponses(value = {
            @ApiResponse(responseCode="200", description = "Atualização realizada com sucesso"
                    , content = @Content(schema = @Schema(implementation = TipoModel.class))),
            @ApiResponse(responseCode="400", description = "Entrada de dados inválida")})
    TipoModel update(
            @Parameter(description = "Id do tipo a ser atualizado. Não pode ser nulo", required = true)
            @NotNull Long idTipo,
            @Parameter(description = "Tipo de aparelho a ser atualizado.", required = true, schema = @Schema(implementation = TipoInputModel.class))
            @Valid TipoInputModel tipoInputModel);

    @Operation(summary = "Exclui um tipo de aparelho",tags = {"Tipo"})
    @ApiResponses(value = {
            @ApiResponse(responseCode="204", description = "Exclusão realizada com sucesso"),
            @ApiResponse(responseCode="404", description = "Tipo de aparelho não encontrado"),
            @ApiResponse(responseCode="409", description = "O tipo está em uso e não pode ser excluido")})
    void delete(
            @Parameter(description = "Id do tipo de aparelho a ser excluido. Não pode ser nulo", required = true)
            @NotNull Long idTipo);
}
