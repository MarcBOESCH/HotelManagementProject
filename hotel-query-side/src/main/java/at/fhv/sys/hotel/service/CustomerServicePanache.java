package at.fhv.sys.hotel.service;

import at.fhv.sys.hotel.models.CustomerQueryPanacheModel;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class CustomerServicePanache {

    public List<CustomerQueryPanacheModel> getAllCustomers() {
        return CustomerQueryPanacheModel.listAll();
    }
    public CustomerQueryPanacheModel findCustomerById(String customerId) {
        return CustomerQueryPanacheModel.findById(customerId);
    }

    @Transactional
    public void createCustomer(CustomerQueryPanacheModel customer) {
        customer.persist();
    }

    @Transactional
    public void updateCustomer(CustomerQueryPanacheModel customer) {
        customer.persist();
    }
}