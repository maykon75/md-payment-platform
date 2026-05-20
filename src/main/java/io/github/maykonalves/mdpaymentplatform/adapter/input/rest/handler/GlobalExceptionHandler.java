package io.github.maykonalves.mdpaymentplatform.adapter.input.rest.handler;

import io.github.maykonalves.mdpaymentplatform.adapter.input.rest.handler.dto.ErrorDTO;
import io.github.maykonalves.mdpaymentplatform.application.exception.DuplicateResourceException;
import io.github.maykonalves.mdpaymentplatform.application.exception.InvalidValueException;
import io.github.maykonalves.mdpaymentplatform.application.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ErrorDTO> duplicateResourceException (DuplicateResourceException ex){
        ErrorDTO errorDTO = new ErrorDTO(ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorDTO);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorDTO> handlerNotFoundException(NotFoundException notFoundException) {
        ErrorDTO errorDTO = new ErrorDTO(notFoundException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);

    }

    @ExceptionHandler(InvalidValueException.class)
    public ResponseEntity<ErrorDTO> invalidValueException(InvalidValueException invalidValueException) {
        ErrorDTO errorDTO = new ErrorDTO(invalidValueException.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDTO);

    }
}
