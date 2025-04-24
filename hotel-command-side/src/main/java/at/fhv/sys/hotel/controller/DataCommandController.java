package at.fhv.sys.hotel.controller;

import at.fhv.sys.hotel.commands.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

import java.io.Reader;
import java.time.LocalDate;

@Path("/api")
@ApplicationScoped
public class DataCommandController {

    @Inject
    BookingAggregate bookingAggregate;

    @Inject
    CustomerAggregate customerAggregate;

    @Inject
    RoomAggregate roomAggregate;

    @POST
    @Path("/createInitialModels")
    public Response createInitialModels() {
        roomAggregate.clear();
        customerAggregate.clear();
        bookingAggregate.clear();

        // Creating initial rooms
        CreateRoomCommand createRoomCommand1 = new CreateRoomCommand(
                "1",
                2,
                "Ich bin ein Raum",
                true,
                420.00
        );

        CreateRoomCommand createRoomCommand2 = new CreateRoomCommand(
                "2",
                4,
                "Ich bin auch ein Raum",
                false,
                69.00
        );

        CreateRoomCommand createRoomCommand3 = new CreateRoomCommand(
                "3", 2, "Jesse´s geheime Bumskabine", true, 69.00
        );
        roomAggregate.handleCreateRoom(createRoomCommand1);
        roomAggregate.handleCreateRoom(createRoomCommand2);
        roomAggregate.handleCreateRoom(createRoomCommand3);

        //Creating initial customers
        CreateCustomerCommand createCustomerCommand1 = new CreateCustomerCommand(
                "1", "Jesse", "jesse@example.com", "Wonderland 1", LocalDate.parse("1996-12-23")
        );

        CreateCustomerCommand createCustomerCommand2 = new CreateCustomerCommand(
                "2", "Marc",   "marc@example.com",   "Wonderland 2", LocalDate.parse("1997-11-01")
        );
        customerAggregate.handleCreateCustomer(createCustomerCommand1);
        customerAggregate.handleCreateCustomer(createCustomerCommand2);

        //Creating initial bookings
        BookRoomCommand bookRoomCommand1 = new BookRoomCommand(
                "1", "1", "3", LocalDate.parse("2025-06-01"), LocalDate.parse("2025-06-05")
        );

        BookRoomCommand bookRoomCommand2 = new BookRoomCommand(
                "2", "2", "1", LocalDate.parse("2025-06-10"), LocalDate.parse("2025-06-12")
        );
        bookingAggregate.handleBookRoom(bookRoomCommand1);
        bookingAggregate.handleBookRoom(bookRoomCommand2);

        return Response.ok("Command-models initialized").build();
    }

    @DELETE
    @Path("/deleteCommandModels")
    public Response deleteCommandModels() {
        bookingAggregate.clear();
        customerAggregate.clear();
        roomAggregate.clear();
        return Response.noContent().build();
    }
}
