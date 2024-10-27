package br.com.one_community.infra.exception;

import br.com.one_community.entities.ValidationException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity errorHandler404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity errorHandlerSqlIntegrity(SQLIntegrityConstraintViolationException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity errorHandler400(MethodArgumentNotValidException ex) {
        var erros = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(
                erros.stream().map(dataValidationError::new).toList()
        );
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity handleMegocioRulesErrors(ValidationException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    private record dataValidationError (String campo, String mensagem) {
        public dataValidationError(FieldError erro) {
            this(
                    erro.getField(),
                    erro.getDefaultMessage()
            );
        }
    }
}
