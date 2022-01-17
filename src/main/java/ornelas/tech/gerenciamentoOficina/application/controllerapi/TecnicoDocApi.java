package ornelas.tech.gerenciamentoOficina.application.controllerapi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import ornelas.tech.gerenciamentoOficina.application.model.TecnicoModel;
import ornelas.tech.gerenciamentoOficina.application.model.input.TecnicoInputModel;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Tag(name = "Tecnico",description = "Tecnico da oficina")
public interface TecnicoDocApi {


    @Operation(summary = "Lista todos os tecnicos",tags = {"Tecnico"})
    @ApiResponses(value = {
    @ApiResponse(responseCode="200", description = "Operação realizada com sucesso"
    , content = @Content(array = @ArraySchema(schema = @Schema(implementation = TecnicoModel.class))))})
    List<TecnicoModel> findAll();

    @Operation(summary = "Busca o tecnico com base em seu id",tags = {"Tecnico"})
    @ApiResponses(value = {
    @ApiResponse(responseCode="200", description = "Operação realizada com sucesso"
    , content = @Content(array = @ArraySchema(schema = @Schema(implementation = TecnicoModel.class))))})
    TecnicoModel findById(
            @Parameter(description = "Id do tecnico a ser buscado. Não pode ser nulo", required = true)
            @NotNull Long idTecnico);

    @Operation(summary = "Busca o tecnico com base em seu nome",tags = {"Tecnico"})
    @ApiResponses(value = {
    @ApiResponse(responseCode="200", description = "Operação realizada com sucesso"
    , content = @Content(array = @ArraySchema(schema = @Schema(implementation = TecnicoModel.class))))})
    List<TecnicoModel> findByName(
            @Parameter(description = "Nome do tecnico a ser buscado. Não pode ser nulo", required = true)
            @NotBlank String nomeTecnico);

    @Operation(summary = "Busca o tecnico com base em seu numero",tags = {"Tecnico"})
    @ApiResponses(value = {
    @ApiResponse(responseCode="200", description = "Operação realizada com sucesso"
    , content = @Content(array = @ArraySchema(schema = @Schema(implementation = TecnicoModel.class))))})
    List<TecnicoModel> findByNumero(
            @Parameter(description = "Numero do tecnico a ser buscado. Não pode ser nulo", required = true)
            @NotBlank String numeroTecnico);

    @Operation(summary = "Adiciona um novo tecnico",tags = {"Tecnico"})
    @ApiResponses(value = {
    @ApiResponse(responseCode="200", description = "Inserção realizada com sucesso"
    , content = @Content(schema = @Schema(implementation = TecnicoModel.class))),
    @ApiResponse(responseCode="400", description = "Entrada de dados inválida")})
    TecnicoModel save(
            @Parameter(description = "Tecnico a ser cadastrado.", required = true, schema = @Schema(implementation = TecnicoInputModel.class))
            @Valid TecnicoInputModel tecnicoInputModel);


    @Operation(summary = "Atualiza um tecnico",tags = {"Tecnico"})
    @ApiResponses(value = {
    @ApiResponse(responseCode="200", description = "Inserção realizada com sucesso"
    , content = @Content(schema = @Schema(implementation = TecnicoModel.class))),
    @ApiResponse(responseCode="404", description = "Tecnico não encontrado"),
    @ApiResponse(responseCode="400", description = "Entrada de dados inválida")})
    TecnicoModel update(
            @Parameter(description = "Id do tecnico a ser atualizado. Não pode ser nulo", required = true)
            @NotNull Long idTecnico,
            @Parameter(description = "Tecnico a ser atualizado.", required = true, schema = @Schema(implementation = TecnicoInputModel.class))
            @Valid TecnicoInputModel tecnicoInputModel);

    @Operation(summary = "Exclui um tecnico", tags = {"Tecnico"})
    @ApiResponses(value = {
            @ApiResponse(responseCode="204", description = "Exclusão realizada com sucesso"),
            @ApiResponse(responseCode="404", description = "Tecnico não encontrado"),
            @ApiResponse(responseCode="409", description = "O tecnico está em uso e não pode ser excluido")})
    void deleteById(
            @Parameter(description = "Id do tecnico a ser excluido. Não pode ser nulo", required = true)
            @NotNull Long idTecnico);
}
