package at.fhv.sys.hotel.commands;

import at.fhv.sys.hotel.client.EventBusClient;
import at.fhv.sys.hotel.commands.shared.events.CustomerCreatedEvent;
import at.fhv.sys.hotel.commands.shared.events.CustomerDeletedEvent;
import at.fhv.sys.hotel.commands.shared.events.CustomerUpdatedEvent;
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

    public String handleCreateCustomer(CreateCustomerCommand command) {
        CustomerCreatedEvent event = new CustomerCreatedEvent(
                command.userId(),
                command.name(),
                command.email(),
                command.address(),
                command.birthdate()
        );

        Logger.getAnonymousLogger().info(eventClient.processCustomerCreatedEvent(event).toString());

        return command.userId();
    }

    // TODO: Remove String customerId and read it from the command
    public String handleUpdateCustomer(String customerId, UpdateCustomerCommand command) {
        CustomerUpdatedEvent event = new CustomerUpdatedEvent(
                customerId,
                command.name(),
                command.email(),
                command.address(),
                command.birthdate()
        );

        Logger.getAnonymousLogger().info(eventClient.processCustomerUpdatedEvent(event).toString());

        return customerId;
    }

    public String handleDeleteCustomer(DeleteCustomerCommand command) {
        CustomerDeletedEvent event = new CustomerDeletedEvent(command.customerId());
        eventClient.processCustomerDeletedEvent(event);
        return command.customerId();
    }

}
