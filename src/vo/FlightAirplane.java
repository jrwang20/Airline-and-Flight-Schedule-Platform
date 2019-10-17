package vo;

import seller.Airplane;
import service.Flight;

public class FlightAirplane {

    Flight flight;

    Airplane airplane;

    public FlightAirplane(Flight flight, Airplane airplane) {
        this.flight = flight;
        this.airplane = airplane;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public Airplane getAirplane() {
        return airplane;
    }

    public void setAirplane(Airplane airplane) {
        this.airplane = airplane;
    }
}
