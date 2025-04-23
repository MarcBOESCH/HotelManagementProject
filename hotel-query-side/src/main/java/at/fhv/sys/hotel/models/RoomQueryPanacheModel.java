package at.fhv.sys.hotel.models;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class RoomQueryPanacheModel extends PanacheEntity {
    public String roomNumber;
    public int capacity;

    public RoomQueryPanacheModel() {}

    public RoomQueryPanacheModel(String roomNumber, int capacity) {
        this.roomNumber = roomNumber;
        this.capacity = capacity;
    }
}
