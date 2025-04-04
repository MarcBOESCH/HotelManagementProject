package at.fhv.sys.hotel.commands.shared.events;

public class CustomerCreatedEvent {

    private String userId;
    private String name;
    private String email;
    private String address;
    private String birthdate;

    public CustomerCreatedEvent() {}

    public CustomerCreatedEvent(String userId, String name, String email, String address, String birthdate) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.address = address;
        this.birthdate = birthdate;
    }

    public String getUserId() {
        return userId;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getAddress() {
        return address;
    }
    public String getBirthdate() {
        return birthdate;
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
