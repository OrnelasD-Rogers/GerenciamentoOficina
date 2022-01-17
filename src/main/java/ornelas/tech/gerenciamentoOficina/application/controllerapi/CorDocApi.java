package ornelas.tech.gerenciamentoOficina.application.controllerapi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import ornelas.tech.gerenciamentoOficina.application.model.CorModel;
import ornelas.tech.gerenciamentoOficina.application.model.input.CorInputModel;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Tag(name = "Cor", description = "Cor de aparelho")
public interface CorDocApi {

    @Operation(summary = "Lista todas as cores de Aparelhos",tags = {"Cor"})
    @ApiResponses(value = {
    @ApiResponse(responseCode="200", description = "Operação realizada com sucesso",
    content = @Content(array = @ArraySchema(schema = @Schema(implementation = CorModel.class))))})
    List<CorModel> findlAll();

    @Operation(summary = "Procura uma cor com base em seu id",tags = {"Cor"})
    @ApiResponses(value = {
    @ApiResponse(responseCode="200", description = "Operação realizada com sucesso",
    content = @Content(array = @ArraySchema(schema = @Schema(implementation = CorModel.class))))})
    CorModel findById(
            @Parameter(description = "Id da cor a ser buscada. Não pode ser nulo", required = true)
            @NotNull Long idCor);

    @Operation(summary = "Procura uma cor com base em seu nome",tags = {"Cor"})
    @ApiResponses(value = {
    @ApiResponse(responseCode="200", description = "Operação realizada com sucesso",
    content = @Content(array = @ArraySchema(schema = @Schema(implementation = CorModel.class))))})
    List<CorModel> findByNomeCor(
            @Parameter(description = "Nome da cor a ser buscada. Não pode ser nulo", required = true)
            @NotBlank String nomeCor);

    @Operation(summary = "Adiciona uma nova cor",tags = {"Cor"})
    @ApiResponses(value = {
    @ApiResponse(responseCode="200", description = "Inserção realizada com sucesso"
                    , content = @Content(schema = @Schema(implementation = CorModel.class))),
    @ApiResponse(responseCode="400", description = "Entrada de dados inválida")})
    CorModel save(
            @Parameter(description = "Cor de aparelho a ser cadastrado.", required = true, schema = @Schema(implementation = CorInputModel.class))
            @Valid CorInputModel corInputModel);

    @Operation(summary = "Atualiza uma cor",tags = {"Cor"})
    @ApiResponses(value = {
    @ApiResponse(responseCode="200", description = "Atualização realizada com sucesso"
                    , content = @Content(schema = @Schema(implementation = CorModel.class))),
    @ApiResponse(responseCode="400", description = "Entrada de dados inválida")})
    CorModel update(
            @Parameter(description = "Id da cor a ser atualizada. Não pode ser nulo", required = true)
            @NotNull Long idCor,
            @Parameter(description = "Cor de aparelho a ser atualizada.", required = true, schema = @Schema(implementation = CorInputModel.class))
            @Valid CorInputModel corInputModel);

    @Operation(summary = "Exclui uma cor de aparelho",tags = {"Cor"})
    @ApiResponses(value = {
    @ApiResponse(responseCode="204", description = "Exclusão realizada com sucesso"),
    @ApiResponse(responseCode="404", description = "Cor de aparelho não encontrado"),
    @ApiResponse(responseCode="409", description = "A Cor está em uso e não pode ser excluida")})
    void deleteById(
            @Parameter(description = "Id da cor a ser excluida. Não pode ser nulo", required = true)
            @NotNull Long idCor);
}
