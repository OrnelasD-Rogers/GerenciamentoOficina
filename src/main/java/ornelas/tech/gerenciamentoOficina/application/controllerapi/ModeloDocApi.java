package ornelas.tech.gerenciamentoOficina.application.controllerapi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import ornelas.tech.gerenciamentoOficina.application.model.ModeloModel;
import ornelas.tech.gerenciamentoOficina.application.model.input.ModeloInputModel;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Tag(name = "Modelo", description = "Modelo de aparelho")
public interface ModeloDocApi {

    @Operation(summary = "Lista todos os modelos de Aparelhos",tags = {"Modelo"})
    @ApiResponses(value = {
            @ApiResponse(responseCode="200", description = "Operação realizada com sucesso",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = ModeloModel.class))))})
    List<ModeloModel> findAll();

    @Operation(summary = "Busca um modelo pelo seu id",tags = {"Modelo"})
    @ApiResponses(value = {
            @ApiResponse(responseCode="200", description = "Operação realizada com sucesso",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ModeloModel.class))))})
    ModeloModel findById(
            @Parameter(description = "Id do modelo de aparelho a ser buscado. Não pode ser nulo", required = true)
            @NotNull Long idModelo);

    @Operation(summary = "Lista todos os modelos de Aparelhos com base em seus nomes",tags = {"Modelo"})
    @ApiResponses(value = {
            @ApiResponse(responseCode="200", description = "Operação realizada com sucesso",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = ModeloModel.class))))})
    List<ModeloModel> findByNomeModelo(
            @Parameter(description = "Nome do modelo de aparelho a ser buscado. Não pode ser nulo", required = true)
            @NotBlank String nomeModelo);

    @Operation(summary = "Adiciona um novo modelo",tags = {"Modelo"})
    @ApiResponses(value = {
            @ApiResponse(responseCode="200", description = "Inserção realizada com sucesso"
                    , content = @Content(schema = @Schema(implementation = ModeloModel.class))),
            @ApiResponse(responseCode="400", description = "Entrada de dados inválida")})
    ModeloModel save(
            @Parameter(description = "Modelo de aparelho a ser atualizado.", required = true, schema = @Schema(implementation = ModeloInputModel.class))
            @Valid ModeloInputModel modeloInputModel);

    @Operation(summary = "Atualiza um modelo",tags = {"Modelo"})
    @ApiResponses(value = {
            @ApiResponse(responseCode="200", description = "Atualização realizada com sucesso"
                    , content = @Content(schema = @Schema(implementation = ModeloModel.class))),
            @ApiResponse(responseCode="400", description = "Entrada de dados inválida")})
    ModeloModel update(
            @Parameter(description = "Id do modelo de aparelho a ser atualizado. Não pode ser nulo", required = true)
            @NotNull Long idModelo,
            @Parameter(description = "Modelo de aparelho a ser atualizado.", required = true, schema = @Schema(implementation = ModeloInputModel.class))
            @Valid ModeloInputModel modeloInputModel);

    @Operation(summary = "Exclui um modelo de aparelho",tags = {"Modelo"})
    @ApiResponses(value = {
            @ApiResponse(responseCode="204", description = "Exclusão realizada com sucesso"),
            @ApiResponse(responseCode="404", description = "Modelo de aparelho não encontrado")})
    void delete(
            @Parameter(description = "Id do modelo de aparelho a ser excluido. Não pode ser nulo", required = true)
            @NotNull Long idModelo);
}
