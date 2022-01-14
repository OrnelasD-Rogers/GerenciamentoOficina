package ornelas.tech.gerenciamentoOficina.application.controllerapi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import ornelas.tech.gerenciamentoOficina.application.model.AparelhoModel;
import ornelas.tech.gerenciamentoOficina.application.model.ClienteModel;
import ornelas.tech.gerenciamentoOficina.application.model.input.AparelhoInputModel;
import ornelas.tech.gerenciamentoOficina.application.model.input.ClienteInputModel;
import ornelas.tech.gerenciamentoOficina.domain.model.SituacaoAparelhoEnum;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.List;

@Tag(name = "Aparelho", description = "Aparelhos da oficina")
public interface AparelhoDocApi {

    @Operation(summary = "Lista todos os aparelhos",tags = {"Aparelho"})
    @ApiResponses(value = {
            @ApiResponse(responseCode="200", description = "Operação realizada com sucesso"
            , content = @Content(array = @ArraySchema(schema = @Schema(implementation = AparelhoModel.class))))})
    List<AparelhoModel> findAll();

    @Operation(summary = "Procura um aparelho pelo seu id",tags = {"Aparelho"})
    @ApiResponses(value = {
            @ApiResponse(responseCode="200", description = "Operação realizada com sucesso"
            , content = @Content(schema = @Schema(implementation = AparelhoModel.class))),
            @ApiResponse(responseCode="404", description = "Aparelho não encontrado")})
    AparelhoModel findById(
            @Parameter(description = "Id do aparelho a ser buscado. Não pode ser nulo", required = true)
            @NotNull Long idAparelho);

    @Operation(summary = "Procura um aparelho pelo nome do cliente",tags = {"Aparelho"})
    @ApiResponses(value = {
            @ApiResponse(responseCode="200", description = "Operação realizada com sucesso"
            , content = @Content(schema = @Schema(implementation = AparelhoModel.class)))})
    List<AparelhoModel> findByNomeCliente(
            @Parameter(description = "nome do cliente a ter o aparelho buscado. Não pode ser nulo", required = true)
            @NotBlank String nomeCliente);

    @Operation(summary = "Procura um aparelho pelo seu tipo",tags = {"Aparelho"})
    @ApiResponses(value = {
            @ApiResponse(responseCode="200", description = "Operação realizada com sucesso"
            , content = @Content(schema = @Schema(implementation = AparelhoModel.class)))})
    List<AparelhoModel> findByTipo(
            @Parameter(description = "Tipo do aparelho a ser buscado. Não pode ser nulo", required = true)

            @NotBlank String tipoAparelho);

    @Operation(summary = "Procura um aparelho pela sua marca",tags = {"Aparelho"})
    @ApiResponses(value = {
            @ApiResponse(responseCode="200", description = "Operação realizada com sucesso"
            , content = @Content(schema = @Schema(implementation = AparelhoModel.class)))})
    List<AparelhoModel> findByMarca(
            @Parameter(description = "Marca do aparelho a ser buscado. Não pode ser nulo", required = true)
            @NotBlank String marcaAparelho);

    @Operation(summary = "Procura um aparelho pelo seu modelo",tags = {"Aparelho"})
    @ApiResponses(value = {
            @ApiResponse(responseCode="200", description = "Operação realizada com sucesso"
            , content = @Content(schema = @Schema(implementation = AparelhoModel.class)))})
    List<AparelhoModel> findByModelo(
            @Parameter(description = "Modelo do aparelho a ser buscado. Não pode ser nulo", required = true)
            @NotBlank String modeloAparelho);

    @Operation(summary = "Procura um aparelho pela sua situacao",tags = {"Aparelho"})
    @ApiResponses(value = {
            @ApiResponse(responseCode="200", description = "Operação realizada com sucesso"
            , content = @Content(schema = @Schema(implementation = AparelhoModel.class)))})
    List<AparelhoModel> findBySituacao(
            @Parameter(description = "Situacao do aparelho a ser buscado. Não pode ser nulo", required = true)
            @NotNull SituacaoAparelhoEnum situacao);

    @Operation(summary = "Procura um aparelho pela sua data de entrada",tags = {"Aparelho"})
    @ApiResponses(value = {
            @ApiResponse(responseCode="200", description = "Operação realizada com sucesso"
            , content = @Content(schema = @Schema(implementation = AparelhoModel.class)))})
    List<AparelhoModel> findByDataEntrada(
            @Parameter(description = "Inicio da data de Entrada do aparelho a ser buscado. Não pode ser nulo", required = true)
            @NotNull OffsetDateTime inicio,
            @Parameter(description = "Limite da data de entrada do aparelho a ser buscado. Não pode ser nulo", required = true)
            @NotNull OffsetDateTime limite);

    @Operation(summary = "Procura um aparelho pela sua data de saida",tags = {"Aparelho"})
    @ApiResponses(value = {
            @ApiResponse(responseCode="200", description = "Operação realizada com sucesso"
            , content = @Content(schema = @Schema(implementation = AparelhoModel.class)))})
    List<AparelhoModel> findByDataSaida(
            @Parameter(description = "inicio da data de saida do aparelho a ser buscado. Não pode ser nulo", required = true)
            @NotNull OffsetDateTime inicio,
            @Parameter(description = "limite da data de saida do aparelho a ser buscado. Não pode ser nulo", required = true)
            @NotNull OffsetDateTime limite);

    @Operation(summary = "Cadastra um aparelho",tags = {"Aparelho"})
    @ApiResponses(value = {
            @ApiResponse(responseCode="201", description = "Operação realizada com sucesso"
            , content = @Content(schema = @Schema(implementation = AparelhoModel.class)))})
    AparelhoModel save(
            @Parameter(description = "Aparelho a ser cadastrado.", required = true, schema = @Schema(implementation = AparelhoInputModel.class))
            @Valid AparelhoInputModel aparelhoInputModel);

    @Operation(summary = "Atualiza um aparelho pelo seu id",tags = {"Aparelho"})
    @ApiResponses(value = {
            @ApiResponse(responseCode="201", description = "Operação realizada com sucesso"
            , content = @Content(schema = @Schema(implementation = AparelhoModel.class)))})
    AparelhoModel update(
            @Parameter(description = "Id do aparelho a ser atualizado. Não pode ser nulo", required = true)
            @NotNull Long idAparelho,
            @Parameter(description = "Aparelho a ser atualizado.", required = true, schema = @Schema(implementation = AparelhoInputModel.class))
            @Valid AparelhoInputModel aparelhoInputModel);

    @Operation(summary = "Exclui um aparelho",tags = {"Aparelho"})
    @ApiResponses(value = {
    @ApiResponse(responseCode="204", description = "Operação realizada com sucesso")})
    void delete(
            @Parameter(description = "Id do aparelho a ser excluido. Não pode ser nulo", required = true)
            @NotNull Long idAparelho);
}
