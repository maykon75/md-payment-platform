package io.github.maykonalves.mdpaymentplatform.application.exception;

public class InvalidUserTransferException extends RuntimeException{

    public InvalidUserTransferException(String message) {
        super(message);
    }
}
