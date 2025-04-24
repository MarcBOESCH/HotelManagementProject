package at.fhv.sys.hotel.commands.shared.events;

public class RoomCreatedEvent {
    private String roomNumber;
    private int capacity;
    private String description;
    private Boolean hasFullyLoadedBeerRefrigerator;
    private double price;

    public RoomCreatedEvent() {}

    public RoomCreatedEvent(String roomNumber, int capacity, String description, Boolean hasFullyLoadedBeerRefrigerator, double price) {
        this.roomNumber = roomNumber;
        this.capacity = capacity;
        this.description = description;
        this.hasFullyLoadedBeerRefrigerator = hasFullyLoadedBeerRefrigerator;
        this.price = price;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getHasFullyLoadedBeerRefrigerator() {
        return hasFullyLoadedBeerRefrigerator;
    }

    public void setHasFullyLoadedBeerRefrigerator(Boolean hasFullyLoadedBeerRefrigerator) {
        this.hasFullyLoadedBeerRefrigerator = hasFullyLoadedBeerRefrigerator;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "RoomCreatedEvent{" +
                "roomNumber='" + roomNumber + '\'' +
                ", capacity='" + capacity + '\'' +
                ", description='" + description + '\'' +
                ", hasFullyLoadedBeerRefrigerator=" + hasFullyLoadedBeerRefrigerator +
                ", price=" + price +
                '}';
    }
}
