package io.github.maykonalves.mdpaymentplatform.adapter.input.rest.handler;

import io.github.maykonalves.mdpaymentplatform.adapter.input.rest.handler.dto.ErrorDTO;
import io.github.maykonalves.mdpaymentplatform.adapter.input.rest.handler.dto.ValidationErrorDTO;
import io.github.maykonalves.mdpaymentplatform.application.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

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

    @ExceptionHandler(InvalidUserTransferException.class)
    public ResponseEntity<ErrorDTO> invalidUserTransferException(InvalidUserTransferException invalidUserTransferException) {
        ErrorDTO errorDTO = new ErrorDTO(invalidUserTransferException.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDTO);
    }

    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<ErrorDTO> insufficientBalanceException(InsufficientBalanceException insufficientBalanceException){
        ErrorDTO errorDTO = new ErrorDTO(insufficientBalanceException.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDTO);
    }

    @ExceptionHandler(UnauthorizedTransferException.class)
    public ResponseEntity<ErrorDTO> unauthorizedTransferException(UnauthorizedTransferException unauthorizedTransferException) {
        ErrorDTO errorDTO = new ErrorDTO(unauthorizedTransferException.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorDTO);
    }

    @ExceptionHandler(GatewayTimeoutException.class)
    public ResponseEntity<ErrorDTO> gatewayTimeoutException(GatewayTimeoutException gatewayTimeoutException) {
        ErrorDTO errorDTO = new ErrorDTO(gatewayTimeoutException.getMessage());
        return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body(errorDTO);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorDTO> handleValidationException(MethodArgumentNotValidException ex) {

        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .toList();

        ValidationErrorDTO validationErrorDTO = new ValidationErrorDTO(errors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationErrorDTO);
    }
}
