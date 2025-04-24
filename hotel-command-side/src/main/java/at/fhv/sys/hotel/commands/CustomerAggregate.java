package at.fhv.sys.hotel.commands;

import at.fhv.sys.hotel.client.EventBusClient;
import at.fhv.sys.hotel.commands.shared.events.CustomerCreatedEvent;
import at.fhv.sys.hotel.commands.shared.events.CustomerDeletedEvent;
import at.fhv.sys.hotel.commands.shared.events.CustomerUpdatedEvent;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@ApplicationScoped
public class CustomerAggregate {

    @Inject
    @RestClient
    EventBusClient eventClient;

    private final List<CustomerCreatedEvent> customers = new ArrayList<>();

    public boolean customerExists(String customerID){
        return customers.stream()
                .anyMatch(c -> c.getUserId().equals(customerID));
    }

    public String handleCreateCustomer(CreateCustomerCommand command) {
         if(customerExists(command.userId())){
            throw new IllegalArgumentException("Customer with customerID " + command.userId() + " already exists.");
        }
        CustomerCreatedEvent event = new CustomerCreatedEvent(
                command.userId(),
                command.name(),
                command.email(),
                command.address(),
                command.birthdate()
        );

        Logger.getAnonymousLogger().info(eventClient.processCustomerCreatedEvent(event).toString());

        customers.add(event);

        return command.userId();
    }

    public String handleUpdateCustomer(String customerId, UpdateCustomerCommand command) {
        CustomerUpdatedEvent event = new CustomerUpdatedEvent(
                customerId,
                command.name(),
                command.email(),
                command.address(),
                command.birthdate()
        );

        Logger.getAnonymousLogger().info(eventClient.processCustomerUpdatedEvent(event).toString());


        CustomerCreatedEvent customerEvent = customers.stream()
                .filter(customer -> customer.getUserId().equals(customerId))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No customer found for customerId: " + customerId));
        command.name().ifPresent(customerEvent::setName);
        command.email().ifPresent(customerEvent::setEmail);
        command.address().ifPresent(customerEvent::setAddress);
        command.birthdate().ifPresent(customerEvent::setBirthdate);

        return customerId;
    }

    public String handleDeleteCustomer(DeleteCustomerCommand command) {
        CustomerDeletedEvent event = new CustomerDeletedEvent(command.customerId());

        Logger.getAnonymousLogger().info(eventClient.processCustomerDeletedEvent(event).toString());

        boolean exists = customers.removeIf(customer -> customer.getUserId().equals(command.customerId()));
        if (!exists) {
            throw new IllegalStateException("No customer found for customerId: " + command.customerId());
        }

        return command.customerId();
    }

    public void clear() {
        Logger.getAnonymousLogger().info("Clearing all customers");
        customers.clear();
        Logger.getAnonymousLogger().info("All customers deleted");
    }

    //When replaying Events:
    public void customerCreated(CustomerCreatedEvent event){
        customers.add(event);
    }

    public void customerUpdated(CustomerUpdatedEvent event){
        CustomerCreatedEvent customerEvent = customers.stream()
                .filter(customer -> customer.getUserId().equals(event.getUserId()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No customer found for customerId: " + event.getUserId()));
        event.getName().ifPresent(customerEvent::setName);
        event.getEmail().ifPresent(customerEvent::setEmail);
        event.getAddress().ifPresent(customerEvent::setAddress);
        event.getBirthdate().ifPresent(customerEvent::setBirthdate);


    }

    public void customerDeleted(CustomerDeletedEvent event){
        customers.removeIf(customer -> customer.getUserId().equals(event.getCustomerId()));
    }

}
