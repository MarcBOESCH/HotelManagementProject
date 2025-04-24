package at.fhv.sys.hotel.projection;

import at.fhv.sys.hotel.commands.shared.events.RoomCreatedEvent;
import at.fhv.sys.hotel.models.RoomQueryPanacheModel;
import at.fhv.sys.hotel.service.RoomServicePanache;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;


import java.util.logging.Logger;


@ApplicationScoped
public class RoomProjection {

    @Inject
    RoomServicePanache roomServicePanache;

    @Transactional
    public void processRoomCreatedEvent(RoomCreatedEvent roomCreatedEvent) {
        Logger.getAnonymousLogger().info("Processing event: " + roomCreatedEvent);

        RoomQueryPanacheModel room = new RoomQueryPanacheModel();
        room.roomNumber = roomCreatedEvent.getRoomNumber();
        room.capacity = roomCreatedEvent.getCapacity();
        room.description = roomCreatedEvent.getDescription();
        room.hasFullyLoadedBeerRefrigerator = roomCreatedEvent.getHasFullyLoadedBeerRefrigerator();
        room.price = roomCreatedEvent.getPrice();

        roomServicePanache.createRoom(room);
    }
}
