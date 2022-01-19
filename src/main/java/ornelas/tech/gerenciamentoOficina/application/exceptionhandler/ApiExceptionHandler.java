package ornelas.tech.gerenciamentoOficina.application.exceptionhandler;

import com.fasterxml.jackson.databind.exc.IgnoredPropertyException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.lang.Nullable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ornelas.tech.gerenciamentoOficina.domain.exception.EntidadeEmUsoException;
import ornelas.tech.gerenciamentoOficina.domain.exception.EntidadeExistenteException;
import ornelas.tech.gerenciamentoOficina.domain.exception.EntidadeNaoEncontradaException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    public static final String MSG_ERRO_GENERICA_USUARIO_FINAL = "Ocorreu um erro interno inesperado no sistema. " +
            "Tente novamente e se o problema persistir, entre em contado com o administrador do sistema.";

    @Autowired
    MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ProblemType problemType = ProblemType.DADOS_INVALIDOS;
        String details = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente";
        BindingResult bindingResult = ex.getBindingResult();
        List<ProblemDetails.Objeto> problemObjects = getProblemaObjetos(bindingResult);

        ProblemDetails problemDetails = createProblem(status, problemType, details)
                .objetos(problemObjects)
                .userMessage(details).build();
        return handleExceptionInternal(ex, problemDetails, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        ProblemType problemType = ProblemType.RECURSO_NAO_ENCONTRADO;
        String detail = String.format("Não existe um recurso com a URL '%s'", ex.getRequestURL());
        ProblemDetails problemDetails = createProblem(status, problemType, detail)
                .userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
                .build();
        return handleExceptionInternal(ex, problemDetails, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (ex instanceof MethodArgumentTypeMismatchException){
            MethodArgumentTypeMismatchException exception = (MethodArgumentTypeMismatchException)ex;
            return handleMethodArgumentTypeMismatchException(exception, new HttpHeaders(), status, request);
        }
        return handleExceptionInternal(ex,null, headers, status,request);
    }

    private ResponseEntity<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ProblemType problemType = ProblemType.PARAMETRO_INVALIDO;
        String detail = String.format("O parâmetro de URL '%s' recebeu o valor '%s', que é de um tipo inválido. " +
                        "Corrija e informe um valor compatível com o tipo '%s' ",
                ex.getName(), ex.getValue() ,ex.getRequiredType().getSimpleName());
        ProblemDetails problemDetails = createProblem(status, problemType, detail)
                .userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
                .build();
        return handleExceptionInternal(ex, problemDetails, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        Throwable rootCause = ExceptionUtils.getRootCause(ex);

        if(rootCause instanceof InvalidFormatException){
            InvalidFormatException exception = (InvalidFormatException) rootCause;
            return handleInvalidFormatException(exception, headers, status, request);
        }
        else if(rootCause instanceof PropertyBindingException){
            PropertyBindingException exception = (PropertyBindingException) rootCause;
            return handlePropertyBindingException(exception, headers, status, request);
        }

        ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
        String detalhe = "O corpo da requisição está inválido. Vefifique erro de sintaxe";
        ProblemDetails problemDetails = createProblem(status, problemType, detalhe)
                .userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
                .build();
        return super.handleExceptionInternal(ex, problemDetails, new HttpHeaders(), status, request);
    }

    private ResponseEntity<Object> handlePropertyBindingException(PropertyBindingException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ProblemType tipoProblema = null;
        String detalhe = "";
        String path = ex.getPath().stream()
                .map(ref -> ref.getFieldName())
                .collect(Collectors.joining("."));

        if(ex instanceof IgnoredPropertyException){
            tipoProblema = ProblemType.PROPRIEDADE_INEXISTENTE;
            detalhe = String.format("A propriedade '%s' não existe. " +
                    "Corrija ou remova esta propriedade e tente novamente", path);
        }
        else if(ex instanceof UnrecognizedPropertyException){
            tipoProblema = ProblemType.PROPRIEDADE_DESCONHECIDA;
            detalhe = String.format("A propriedade '%s' não faz parte do domínio", path);
        }
        ProblemDetails problemaDetails = createProblem(status, tipoProblema, detalhe)
                .userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
                .build();
        return handleExceptionInternal(ex, problemaDetails, headers, status, request);
    }

    private ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String path = ex.getPath().stream()
                .map(ref -> ref.getFieldName())
                .collect(Collectors.joining("."));

        ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
        String detail = String.format("A propriedade '%s' recebeu o valor '%s', que é de um tipo invalido. " +
                        "Corrija e informe um valor compatível com o tipo '%s'", path, ex.getValue(),
                ex.getTargetType().getSimpleName());
        ProblemDetails problemDetails = createProblem(status, problemType, detail)
                .userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
                .build();
        return handleExceptionInternal(ex, problemDetails, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        status = HttpStatus.BAD_REQUEST;
        ProblemType problemType = ProblemType.PARAMETRO_INVALIDO;
        String details = String.format("O parâmetro %s está ausente na requisição. Verifique o erro e tente novamente", ex.getVariableName());
        ProblemDetails problemDetails = createProblem(status, problemType, details)
                .userMessage(details)
                .build();
        return handleExceptionInternal(ex, problemDetails, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ProblemType problemType = ProblemType.PARAMETRO_INVALIDO;
        String details = problemType.getTitle();
        List<ProblemDetails.Objeto> objetos = getConstraintViolationObjetos(ex.getConstraintViolations());
        ProblemDetails problemDetails = createProblem(status, problemType, details)
                .userMessage(details)
                .objetos(objetos)
                .build();
        return handleExceptionInternal(ex, problemDetails, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EntidadeExistenteException.class)
    public ResponseEntity<?> handleEntidadeExistenteException(EntidadeExistenteException ex, WebRequest request){
        HttpStatus status = HttpStatus.CONFLICT;
        ProblemType problemType = ProblemType.PROPRIEDADE_JA_EXISTENTE;
        String details = ex.getMessage();
        ProblemDetails problemDetails = createProblem(status, problemType, details)
                .userMessage(details).build();
        return handleExceptionInternal(ex, problemDetails, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<?> handleEntidadeNaoEncontradaException(EntidadeNaoEncontradaException ex, WebRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;
        ProblemType problemType = ProblemType.RECURSO_NAO_ENCONTRADO;
        String details = ex.getMessage();
        ProblemDetails problemDetails = createProblem(status, problemType, details)
                .userMessage(details).build();
        return handleExceptionInternal(ex, problemDetails, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EntidadeEmUsoException.class)
    public ResponseEntity<?> handleEntidadeEmUsoException(EntidadeEmUsoException ex, WebRequest request){
        HttpStatus status = HttpStatus.CONFLICT;
        ProblemType problemType = ProblemType.ENTIDADE_EM_USO;
        String details = ex.getMessage();
        ProblemDetails problemDetails = createProblem(status, problemType, details)
                .userMessage(details).build();
        return handleExceptionInternal(ex, problemDetails, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGenericException(Exception ex, WebRequest request){
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ProblemType problemType = ProblemType.ERRO_NEGOCIO;
        String details = MSG_ERRO_GENERICA_USUARIO_FINAL;
        ProblemDetails problemDetails = createProblem(status, problemType, details)
                .userMessage(details).build();
        return handleExceptionInternal(ex, problemDetails, new HttpHeaders(), status, request);
    }


    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if(body == null){
            body = ProblemDetails.builder()
                    .status(status.value())
                    .title(status.getReasonPhrase())
                    .build();
        } else if(body instanceof String) {
            body = ProblemDetails.builder()
                    .status(status.value())
                    .title((String)body)
                    .build();
        }
        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    public ProblemDetails.ProblemDetailsBuilder createProblem(HttpStatus status, ProblemType problemType, String details){
        return ProblemDetails.builder()
                .status(status.value())
                .timestamp(OffsetDateTime.now())
                .title(problemType.getTitle())
                .details(details);
    }

    private List<ProblemDetails.Objeto> getProblemaObjetos(BindingResult bindingResult){
        return bindingResult.getAllErrors().stream()
                .map(objectError -> {
                    String message = messageSource.getMessage(objectError, LocaleContextHolder.getLocale());
                    String name = objectError.getObjectName();
                    if(objectError instanceof FieldError){
                        name = ((FieldError) objectError).getField();
                    }
                    return ProblemDetails.Objeto.builder()
                            .name(name)
                            .userMessage(message).build();
                }).collect(Collectors.toList());
    }

    private List<ProblemDetails.Objeto> getConstraintViolationObjetos(Set<ConstraintViolation<?>> constraintViolations){
        return constraintViolations.stream().map(cv -> {
            String[] propArray = cv.getPropertyPath().toString().split("\\.");
            return ProblemDetails.Objeto.builder()
                    .name(propArray[propArray.length-1])
                    .userMessage(cv.getMessage()).build();
        }).collect(Collectors.toList());
    }
}
