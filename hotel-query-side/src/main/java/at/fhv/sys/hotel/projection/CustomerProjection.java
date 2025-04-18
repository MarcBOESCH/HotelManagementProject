package at.fhv.sys.hotel.projection;

import at.fhv.sys.hotel.commands.shared.events.CustomerCreatedEvent;
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
        customer.name = customerCreatedEvent.getName();
        customer.email = customerCreatedEvent.getEmail();
        customer.address = customerCreatedEvent.getAddress();
        customer.birthdate = customerCreatedEvent.getBirthdate();

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
}
