package test.httptrigger.AFileHandler;

import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.messaging.eventhubs.EventData;
import com.azure.messaging.eventhubs.EventHubClientBuilder;
import com.azure.messaging.eventhubs.EventHubProducerClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.function.Function;

@Configuration
public class Config {

    @Value("${eventhub.namespace}")
    private String namespace;

    @Value("${eventhub.name}")
    private String eventHubName;

    @Bean
    public EventHubProducerClient eventHubProducerClient() {
        return new EventHubClientBuilder()
                .credential(namespace, eventHubName, new DefaultAzureCredentialBuilder().build())
                .buildProducerClient();
    }
}
