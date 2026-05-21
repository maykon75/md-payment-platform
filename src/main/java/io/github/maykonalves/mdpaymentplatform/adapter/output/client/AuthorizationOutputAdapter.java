package io.github.maykonalves.mdpaymentplatform.adapter.output.client;


import io.github.maykonalves.mdpaymentplatform.adapter.output.client.webclient.AuthorizationWebClient;
import io.github.maykonalves.mdpaymentplatform.application.port.output.IAuthorizationOutputPort;
import org.springframework.stereotype.Component;

@Component
public class AuthorizationOutputAdapter implements IAuthorizationOutputPort {

    private final AuthorizationWebClient authorizationWebClient;

    public AuthorizationOutputAdapter(final AuthorizationWebClient authorizationWebClient) {
        this.authorizationWebClient = authorizationWebClient;
    }

    @Override
    public void authorize() {
        authorizationWebClient.authorize();
    }
}
