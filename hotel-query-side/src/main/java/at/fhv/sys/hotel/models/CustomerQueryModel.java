package at.fhv.sys.hotel.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class CustomerQueryModel {

    @Id
    private String userId;

    private String name;
    private String email;
    private String address;
    private String birthdate;

    public CustomerQueryModel() {}

    public CustomerQueryModel(String userId, String name, String email, String address, String birthdate) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.address = address;
        this.birthdate = birthdate;
    }

    public String getUserId() {
        return "Customer-" + userId;
    }

    public String getName() {
        return "Customer Name is: " + name;
    }

    public String getEmail() {
        return "Customer Email is: " + email;
    }

    public String getAddress() {
        return "Customer Address is: " + address;
    }

    public String getBirthdate() {
        return "Customer Birthdate is " + birthdate;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }
}
