package ornelas.tech.gerenciamentoOficina.application.controllerapi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import ornelas.tech.gerenciamentoOficina.application.model.MarcaModel;
import ornelas.tech.gerenciamentoOficina.application.model.input.MarcaInputModel;
import ornelas.tech.gerenciamentoOficina.domain.model.Marca;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Tag(name = "Marca", description = "Marca do aparelho")
public interface MarcaDocApi {

    @Operation(summary = "Lista todos as marcas de Aparelhos",tags = {"Marca"})
    @ApiResponses(value = {
            @ApiResponse(responseCode="200", description = "Operação realizada com sucesso",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = MarcaModel.class))))})
    public List<MarcaModel> findAll();

    @Operation(summary = "Lista a marca de um Aparelho com base em seu id",tags = {"Marca"})
    @ApiResponses(value = {
            @ApiResponse(responseCode="200", description = "Operação realizada com sucesso",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = MarcaModel.class)))),
            @ApiResponse(responseCode = "404", description = "Marca não encontrado")})
    MarcaModel findById(
            @Parameter(description = "Id da marca a ser buscada. Não pode ser nulo", required = true)
            @NotNull Long idMarca);

    @Operation(summary = "Procura uma marca pelo seu nome",tags = {"Marca"})
    @ApiResponses(value = {
            @ApiResponse(responseCode="200", description = "Operação realizada com sucesso"
                    , content = @Content(schema = @Schema(implementation = MarcaModel.class)))})
    List<MarcaModel> findByMarca(
            @Parameter(description = "Nome da marca de aparelho a ser buscado. Não pode ser nulo", required = true)
            @NotBlank String marca);

    @Operation(summary = "Adiciona uma nova marca",tags = {"Marca"})
    @ApiResponses(value = {
            @ApiResponse(responseCode="200", description = "Inserção realizada com sucesso"
                    , content = @Content(schema = @Schema(implementation = MarcaModel.class))),
            @ApiResponse(responseCode="400", description = "Entrada de dados inválida")})
    MarcaModel save(
            @Parameter(description = "Marca de aparelho a ser cadastrado.", required = true, schema = @Schema(implementation = MarcaInputModel.class))
            @Valid MarcaInputModel marcaInputModel);

    @Operation(summary = "Atualiza uma marca",tags = {"Marca"})
    @ApiResponses(value = {
            @ApiResponse(responseCode="200", description = "Atualização realizada com sucesso"
                    , content = @Content(schema = @Schema(implementation = MarcaModel.class))),
            @ApiResponse(responseCode="400", description = "Entrada de dados inválida")})
    MarcaModel update(
            @Parameter(description = "Id da marca de aparelho a ser atualizada. Não pode ser nulo", required = true)
            @NotNull Long idMarca,
            @Parameter(description = "Marca de aparelho a ser atualizada.", required = true, schema = @Schema(implementation = MarcaInputModel.class))
            @Valid MarcaInputModel marcaInputModel);

    @Operation(summary = "Exclui uma marca de aparelho",tags = {"Marca"})
    @ApiResponses(value = {
            @ApiResponse(responseCode="204", description = "Exclusão realizada com sucesso"),
            @ApiResponse(responseCode="404", description = "Marca de aparelho não encontrado")})
    void delete(
            @Parameter(description = "Id da marca de aparelho a ser excluida. Não pode ser nulo", required = true)
            @NotNull Long idMarca);
}
