package at.fhv.sys.hotel.controller;

import at.fhv.sys.hotel.models.BookingQueryPanacheModel;
import at.fhv.sys.hotel.models.CustomerQueryPanacheModel;
import at.fhv.sys.hotel.models.RoomQueryPanacheModel;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

import java.io.Reader;

@Path("/api")
public class DataQueryController {

    @Transactional
    @DELETE
    @Path("/query-models")
    public Response deleteQueryModels() {
        CustomerQueryPanacheModel.deleteAll();
        BookingQueryPanacheModel.deleteAll();
        RoomQueryPanacheModel.deleteAll();
        return Response.noContent().build();
    }
}
