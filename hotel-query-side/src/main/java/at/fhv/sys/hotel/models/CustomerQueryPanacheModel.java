package at.fhv.sys.hotel.models;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
public class CustomerQueryPanacheModel extends PanacheEntity {

    // Panache provides Auto-CRUD for everything
    public String userId;

    public String name;
    public String email;
    public String address;
    public LocalDate birthdate;



}
