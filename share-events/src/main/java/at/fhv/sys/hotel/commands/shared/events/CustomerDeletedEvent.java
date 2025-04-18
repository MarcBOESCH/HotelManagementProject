package at.fhv.sys.hotel.commands.shared.events;

public class CustomerDeletedEvent {
    private String customerId;

    public CustomerDeletedEvent() {}

    public CustomerDeletedEvent(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "CustomerDeletedEvent{customerId=" + customerId + "}";
    }

}
