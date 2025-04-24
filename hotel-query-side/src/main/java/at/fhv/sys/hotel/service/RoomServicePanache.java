package at.fhv.sys.hotel.service;

import at.fhv.sys.hotel.models.RoomQueryPanacheModel;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class RoomServicePanache {

    public void createRoom(RoomQueryPanacheModel room) {
        room.persist();
    }

    public List<RoomQueryPanacheModel> getAllRooms() {
        return RoomQueryPanacheModel.listAll();
    }
}
