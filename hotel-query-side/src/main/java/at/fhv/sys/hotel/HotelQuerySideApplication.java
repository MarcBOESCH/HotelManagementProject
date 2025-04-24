package at.fhv.sys.hotel;

import at.fhv.sys.hotel.models.RoomQueryPanacheModel;
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.StartupEvent;
import io.quarkus.runtime.annotations.QuarkusMain;
import jakarta.enterprise.event.Observes;
import jakarta.transaction.Transactional;
import org.jboss.logging.Logger;

@QuarkusMain
public class HotelQuerySideApplication {
    @Transactional
    void onStart(@Observes StartupEvent ev) {
        Logger.getLogger(HotelQuerySideApplication.class).info("Starting Hotel Query Side Application");
    }

    public static void main(String[] args) {
        Quarkus.run(args);
    }
}
