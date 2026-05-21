package io.github.maykonalves.mdpaymentplatform.adapter.output.client;

import io.github.maykonalves.mdpaymentplatform.adapter.output.client.webclient.NotificationWebClient;
import io.github.maykonalves.mdpaymentplatform.application.port.output.INotificationOutputPort;
import org.springframework.stereotype.Component;

@Component
public class NotificationOutputAdapter implements INotificationOutputPort {
    private final NotificationWebClient notificationWebClient;

    public NotificationOutputAdapter(final NotificationWebClient notificationWebClient) {
        this.notificationWebClient = notificationWebClient;
    }

    @Override
    public void notifyUser() {
       notificationWebClient.notifyUser();
    }
}
