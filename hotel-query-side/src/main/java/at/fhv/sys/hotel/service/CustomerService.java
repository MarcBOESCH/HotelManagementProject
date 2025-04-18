package at.fhv.sys.hotel.service;

import at.fhv.sys.hotel.models.CustomerQueryModel;
import jakarta.enterprise.context.ApplicationScoped;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class CustomerService {

    @PersistenceContext
    EntityManager entityManager;

    public CustomerQueryModel findCustomerById(String customerId) {
        try {
            return entityManager.createQuery("SELECT c FROM CustomerQueryModel c WHERE c.id = :customerId", CustomerQueryModel.class)
                    .setParameter("customerId", customerId)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<CustomerQueryModel> getAllCustomers() {
        return entityManager.createQuery("SELECT c FROM CustomerQueryModel c", CustomerQueryModel.class).getResultList();
    }

    @Transactional
    public void createCustomer(CustomerQueryModel customer) {
        entityManager.persist(customer);
    }

    @Transactional
    public void updateCustomer(CustomerQueryModel customer) {
        entityManager.merge(customer);
    }
}
