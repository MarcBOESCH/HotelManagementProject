package at.fhv.sys.hotel.projection;

import at.fhv.sys.hotel.commands.shared.events.CustomerCreatedEvent;
import at.fhv.sys.hotel.commands.shared.events.CustomerDeletedEvent;
import at.fhv.sys.hotel.commands.shared.events.CustomerUpdatedEvent;
import at.fhv.sys.hotel.models.CustomerQueryPanacheModel;
import at.fhv.sys.hotel.service.CustomerServicePanache;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.logging.Logger;

@ApplicationScoped
public class CustomerProjection {

    @Inject
    CustomerServicePanache customerServicePanache;

    public void processCustomerCreatedEvent(CustomerCreatedEvent customerCreatedEvent) {
        Logger.getAnonymousLogger().info("Processing event: " + customerCreatedEvent);

        CustomerQueryPanacheModel customer = new CustomerQueryPanacheModel();
        customer.userId = customerCreatedEvent.getUserId();
        if (customer.userId == null || customer.userId.trim().isEmpty()){
            throw new IllegalArgumentException("User ID is null");
        }
        customer.name = customerCreatedEvent.getName();
        if (customer.name == null || customer.name.trim().isEmpty()){
            throw new IllegalArgumentException("Name is null");
        }
        customer.email = customerCreatedEvent.getEmail();
        if (customer.email == null || customer.email.trim().isEmpty()){
            throw new IllegalArgumentException("Email is null");
        }
        customer.address = customerCreatedEvent.getAddress();
        if (customer.address == null || customer.address.trim().isEmpty()){
            throw new IllegalArgumentException("Address is null");
        }
        customer.birthdate = customerCreatedEvent.getBirthdate();
        if (customer.birthdate == null){
            throw new IllegalArgumentException("Birthdate is null");
        }

        customerServicePanache.createCustomer(customer);
    }

    @Transactional
    public void processCustomerUpdatedEvent(CustomerUpdatedEvent customerUpdatedEvent) {
        Logger.getAnonymousLogger().info("Processing event: " + customerUpdatedEvent);

            CustomerQueryPanacheModel customer = customerServicePanache.findCustomerById(customerUpdatedEvent.getUserId());
            if (customer == null) {
                Logger.getAnonymousLogger().info("Could not find customer with id: " + customerUpdatedEvent.getUserId());
                return;
            }

            customerUpdatedEvent.getName().ifPresent(customer::setName);
            customerUpdatedEvent.getEmail().ifPresent(customer::setEmail);
            customerUpdatedEvent.getAddress().ifPresent(customer::setAddress);
            customerUpdatedEvent.getBirthdate().ifPresent(customer::setBirthdate);
    }

    public void processCustomerDeletedEvent(CustomerDeletedEvent customerDeletedEvent) {
        Logger.getAnonymousLogger().info("Processing event: " + customerDeletedEvent);

        CustomerQueryPanacheModel customer = customerServicePanache.findCustomerById(customerDeletedEvent.getCustomerId());
        if (customer == null) {
            Logger.getAnonymousLogger().info("Could not find customer with id: " + customerDeletedEvent.getCustomerId());
            return;
        }

        customerServicePanache.deleteCustomer(customer);
    }
}
