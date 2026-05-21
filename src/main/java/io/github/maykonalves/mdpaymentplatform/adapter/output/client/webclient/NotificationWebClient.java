package io.github.maykonalves.mdpaymentplatform.adapter.output.client.webclient;

import io.github.maykonalves.mdpaymentplatform.application.exception.GatewayTimeoutException;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Component
public class NotificationWebClient {
    private final WebClient webClient =
            WebClient.builder()
                    .baseUrl("https://util.devi.tools/api/v1")
                    .build();

    public void notifyUser() {
        try {
            webClient.post()
                    .uri("/notify")
                    .retrieve()
                    .toBodilessEntity()
                    .block();

        } catch (WebClientResponseException.GatewayTimeout ex) {

            throw new GatewayTimeoutException(
                    "Notification service unavailable");
        }
    }
}
