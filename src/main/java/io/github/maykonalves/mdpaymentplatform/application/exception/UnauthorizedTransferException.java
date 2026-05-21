package io.github.maykonalves.mdpaymentplatform.application.exception;

public class UnauthorizedTransferException extends RuntimeException{
    public UnauthorizedTransferException(String message){
        super(message);
    }
}
