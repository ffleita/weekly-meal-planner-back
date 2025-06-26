package ffleitas.back.handlers;

import ffleitas.back.exceptions.DependenciasActivasException;
import ffleitas.back.exceptions.ElementoInexistenteException;
import ffleitas.back.exceptions.ErrorAlCrearObjetoException;
import io.swagger.v3.oas.annotations.Hidden;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Hidden
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        LOG.error("Manejando exception: {}", e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        LOG.error("Manejando illegalArgumentException: {}", e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ElementoInexistenteException.class)
    public ResponseEntity<String> handleElementoInexistenteException(final ElementoInexistenteException e) {
        LOG.error("Manejando elemento inexistente: {}", e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DependenciasActivasException.class)
    public ResponseEntity<String> handleDependenciasActivasException(final DependenciasActivasException e) {
        LOG.error("Manejando dependenciasActivasException: {}", e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ErrorAlCrearObjetoException.class)
    public ResponseEntity<String> handleErrorAlCrearObjetoException(final ErrorAlCrearObjetoException e) {
        LOG.error("Manejando error al crear objeto: {}", e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
