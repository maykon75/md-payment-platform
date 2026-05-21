package io.github.maykonalves.mdpaymentplatform.adapter.output.client.webclient;

import io.github.maykonalves.mdpaymentplatform.application.exception.UnauthorizedTransferException;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Component
public class AuthorizationWebClient {

    private final WebClient webClient =
            WebClient.builder()
                    .baseUrl("https://util.devi.tools/api/v2")
                    .build();

    public void authorize() {
        try {
            webClient.get()
                    .uri("/authorize")
                    .retrieve()
                    .toBodilessEntity()
                    .block();

        } catch (WebClientResponseException.Forbidden ex) {

            throw new UnauthorizedTransferException(
                    "Transfer not authorized");
        }
    }
}
