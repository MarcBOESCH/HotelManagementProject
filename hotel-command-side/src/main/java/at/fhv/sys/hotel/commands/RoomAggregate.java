package at.fhv.sys.hotel.commands;

import at.fhv.sys.hotel.client.EventBusClient;
import at.fhv.sys.hotel.commands.shared.events.RoomCreatedEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logmanager.Logger;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class RoomAggregate {

    @Inject
    @RestClient
    EventBusClient eventClient;

    private final List<RoomCreatedEvent> rooms = new ArrayList<>();

    public String handleCreateRoom(CreateRoomCommand command) {
        if (rooms.stream().anyMatch(room -> room.getRoomNumber().equals(command.roomNumber()))) {
            throw new IllegalStateException("Room already exists: " + command.roomNumber());
        }

        RoomCreatedEvent event = new RoomCreatedEvent(
                command.roomNumber(),
                command.capacity(),
                command.description(),
                command.hasFullyLoadedBeerRefrigerator(),
                command.price()
        );
        rooms.add(event);
        Logger.getAnonymousLogger().info(eventClient.processRoomCreatedEvent(event).toString());
        return command.roomNumber();
    }

    public void clear() {
        rooms.clear();
    }
}
