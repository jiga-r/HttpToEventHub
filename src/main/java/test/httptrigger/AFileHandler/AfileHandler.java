package test.httptrigger.AFileHandler;

import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.function.Function;

@Component
public class AfileHandler {

    private final EventHubProducerService eventHubProducerService;

    @Autowired
    public AfileHandler(EventHubProducerService eventHubProducerService) {
        this.eventHubProducerService = eventHubProducerService;
    }

    @FunctionName("sendDataToEventHub")
    public HttpResponseMessage execute(
            @HttpTrigger(
                    name = "req",
                    methods = {HttpMethod.GET, HttpMethod.POST},
                    authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<String>> request,
            ExecutionContext context
    ) {
        context.getLogger().info("HTTP trigger function received a request.");

        String message = request.getBody().get();
        context.getLogger().info("Message received: " + message);

        eventHubProducerService.sendMessage(message, context);

        context.getLogger().info("HTTP trigger function completed.");
        return request.createResponseBuilder(HttpStatus.OK).body("Message sent to Event Hub").build();
    }
}
