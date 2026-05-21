package io.github.maykonalves.mdpaymentplatform.application.exception;

public class GatewayTimeoutException extends RuntimeException{
    public GatewayTimeoutException(String message){
        super(message);
    }
}
