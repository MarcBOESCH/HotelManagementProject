package at.fhv.sys.hotel.controller;

import at.fhv.sys.hotel.commands.CreateRoomCommand;
import at.fhv.sys.hotel.commands.RoomAggregate;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.io.Reader;
import java.util.Map;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RoomCommandController {

    private final RoomAggregate roomAggregate;

    public RoomCommandController(RoomAggregate roomAggregate) {
        this.roomAggregate = roomAggregate;
    }

    @POST
    @Path("/createRoom")
    public Response createRoom(@Valid CreateRoomCommand command) {
        String id = roomAggregate.handleCreateRoom(command);
        return Response.status(Response.Status.CREATED).entity(Map.of("roomNumber", id)).build();
    }
}
