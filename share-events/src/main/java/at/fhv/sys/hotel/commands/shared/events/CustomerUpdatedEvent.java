package at.fhv.sys.hotel.commands.shared.events;

import java.time.LocalDate;
import java.util.Optional;

public class CustomerUpdatedEvent {
    private String userId;
    private Optional<String> name;
    private Optional<String> email;
    private Optional<String> address;
    private Optional<LocalDate> birthdate;

    public CustomerUpdatedEvent() {}

    public CustomerUpdatedEvent(String userId,
                                Optional<String> name,
                                Optional<String> email,
                                Optional<String> address,
                                Optional<LocalDate> birthdate
    ) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.address = address;
        this.birthdate = birthdate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Optional<String> getName() {
        return name;
    }

    public void setName(Optional<String> name) {
        this.name = name;
    }

    public Optional<String> getEmail() {
        return email;
    }

    public void setEmail(Optional<String> email) {
        this.email = email;
    }

    public Optional<String> getAddress() {
        return address;
    }

    public void setAddress(Optional<String> address) {
        this.address = address;
    }

    public Optional<LocalDate> getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Optional<LocalDate> birthdate) {
        this.birthdate = birthdate;
    }

    @Override
    public String toString() {
        return "CustomerCreated{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", birthdate='" + birthdate + '}';
    }
}
