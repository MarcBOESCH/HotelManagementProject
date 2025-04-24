package at.fhv.sys.hotel.models;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class RoomQueryPanacheModel extends PanacheEntity {
    public String roomNumber;
    public int capacity;
    public String description;
    public Boolean hasFullyLoadedBeerRefrigerator;
    public double price;

    public RoomQueryPanacheModel() {}

    public RoomQueryPanacheModel(String roomNumber, int capacity, String description, Boolean hasFullyLoadedBeerRefrigerator, double price) {
        this.roomNumber = roomNumber;
        this.capacity = capacity;
        this.description = description;
        this.hasFullyLoadedBeerRefrigerator = hasFullyLoadedBeerRefrigerator;
        this.price = price;
    }
}
