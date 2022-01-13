package ornelas.tech.gerenciamentoOficina.application.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.OffsetDateTime;
import java.util.List;

@JsonInclude(content = JsonInclude.Include.NON_NULL, value = JsonInclude.Include.NON_EMPTY)
@Getter
@Builder
@ToString
public class ProblemDetails {

    private Integer status;
    private OffsetDateTime timestamp;
    private String title;
    private String details;
    private String userMessage;
    private List<Objeto> objetos;

    @Getter
    @Builder
    public static class Objeto {
        private String name;
        private String userMessage;
    }
}
