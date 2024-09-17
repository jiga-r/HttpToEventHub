package test.httptrigger.AFileHandler;

import com.azure.messaging.eventhubs.EventData;
import com.azure.messaging.eventhubs.EventHubProducerClient;
import com.microsoft.azure.functions.ExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class EventHubProducerService {
    private final EventHubProducerClient producerClient;

    @Autowired
    public EventHubProducerService(EventHubProducerClient producerClient) {
        this.producerClient = producerClient;
    }

    public void sendMessage(String message, ExecutionContext context) {
        context.getLogger().info("Sending message to Event Hub: " + message);
        EventData eventData = new EventData(message);
        producerClient.send(Collections.singletonList(eventData));
        context.getLogger().info("Message sent successfully");
    }
}
