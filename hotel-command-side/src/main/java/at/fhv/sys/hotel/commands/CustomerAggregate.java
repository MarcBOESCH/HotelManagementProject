package at.fhv.sys.hotel.commands;

import at.fhv.sys.hotel.client.EventBusClient;
import at.fhv.sys.hotel.commands.shared.events.CustomerCreatedEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.UUID;
import java.util.logging.Logger;

@ApplicationScoped
public class CustomerAggregate {

    @Inject
    @RestClient
    EventBusClient eventClient;

    public String handle(CreateCustomerCommand command) {
        String userId = UUID.randomUUID().toString();
        CustomerCreatedEvent event = new CustomerCreatedEvent(
                userId,
                command.name(),
                command.email(),
                command.address(),
                command.birthdate()
        );

        Logger.getAnonymousLogger().info(eventClient.processCustomerCreatedEvent(event).toString());

        return userId;
    }

}
