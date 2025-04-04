package at.fhv.sys.hotel.projection;

import at.fhv.sys.hotel.commands.shared.events.CustomerCreatedEvent;
import at.fhv.sys.hotel.models.CustomerQueryModel;
import at.fhv.sys.hotel.models.CustomerQueryPanacheModel;
import at.fhv.sys.hotel.service.CustomerService;
import at.fhv.sys.hotel.service.CustomerServicePanache;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

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
}
