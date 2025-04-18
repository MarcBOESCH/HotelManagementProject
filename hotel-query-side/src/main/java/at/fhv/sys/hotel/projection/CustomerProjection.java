package at.fhv.sys.hotel.projection;

import at.fhv.sys.hotel.commands.shared.events.CustomerCreatedEvent;
import at.fhv.sys.hotel.commands.shared.events.CustomerUpdatedEvent;
import at.fhv.sys.hotel.models.CustomerQueryModel;
import at.fhv.sys.hotel.models.CustomerQueryPanacheModel;
import at.fhv.sys.hotel.service.CustomerService;
import at.fhv.sys.hotel.service.CustomerServicePanache;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.NoResultException;

import java.util.logging.Logger;

@ApplicationScoped
public class CustomerProjection {

    @Inject
    CustomerService customerService;

    @Inject
    CustomerServicePanache customerServicePanache;

    public void processCustomerCreatedEvent(CustomerCreatedEvent customerCreatedEvent) {
        Logger.getAnonymousLogger().info("Processing event: " + customerCreatedEvent);

        CustomerQueryModel customerQueryModel = new CustomerQueryModel(
                customerCreatedEvent.getUserId(),
                customerCreatedEvent.getName(),
                customerCreatedEvent.getEmail(),
                customerCreatedEvent.getAddress(),
                customerCreatedEvent.getBirthdate()
        );

        customerService.createCustomer(customerQueryModel);

        CustomerQueryPanacheModel customer = new CustomerQueryPanacheModel();
        customer.userId = customerCreatedEvent.getUserId();
        customer.name = customerCreatedEvent.getName();
        customer.email = customerCreatedEvent.getEmail();
        customer.address = customerCreatedEvent.getAddress();
        customer.birthdate = customerCreatedEvent.getBirthdate();

        customerServicePanache.createCustomer(customer);
    }

    public void processCustomerUpdatedEvent(CustomerUpdatedEvent customerUpdatedEvent) {
        Logger.getAnonymousLogger().info("Processing event: " + customerUpdatedEvent);

            CustomerQueryModel customer = customerService.findCustomerById(customerUpdatedEvent.getUserId());
            customerUpdatedEvent.getName().ifPresent(customer::setName);
            customerUpdatedEvent.getEmail().ifPresent(customer::setEmail);
            customerUpdatedEvent.getAddress().ifPresent(customer::setAddress);
            customerUpdatedEvent.getBirthdate().ifPresent(customer::setBirthdate);

            customerService.updateCustomer(customer);

            CustomerQueryPanacheModel customerPanache = customerServicePanache.findCustomerById(customerUpdatedEvent.getUserId());

    }
}
