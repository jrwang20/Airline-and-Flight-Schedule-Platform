package service;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FlightSchedule {

    List<Flight> flightList;

    Set<String> checkId;

    public FlightSchedule() {
        this.flightList = new ArrayList<>();
        checkId = new HashSet<>();
    }

    public Flight searchById(String flightId) {

        if(!checkId.contains(flightId)) {
            return null;
        }

        for(Flight flight : flightList) {
            if(flight.getFlightId().equals(flightId)) {
                return flight;
            }
        }

        return null;
    }

    public List<Flight> getFlightList() {
        return flightList;
    }

    public boolean addFlight(Flight flight) {

        if(checkId.add(flight.getFlightId())) {
            flightList.add(flight);
            System.out.println("flight number is: " + flightList.size());
        } else {
            return false;
        }

        return true;

    }

    public boolean cancelFlight(String flightId) {

        if(!checkId.contains(flightId)) {
            return false;
        }

        for(Flight flight : flightList) {
            if(flight.getFlightId().equals(flightId)) {
                flight.setAvailable(false);
                break;
            }
        }

        return true;

    }

    public boolean updateFlight(Flight flight) {

        if(!checkId.contains(flight.getFlightId())) {
            return false;
        }

        String fromCity = flight.getFromCity();
        String toCity = flight.getToCity();
        boolean available = flight.isAvailable();
        String time = flight.getTime();
        int price = flight.getPrice();

        for(Flight target : flightList) {
            if(target.getFlightId().equals(flight.getFlightId())) {
                target.setFromCity(fromCity);
                target.setToCity(toCity);
                target.setAvailable(available);
                target.setTime(time);
                target.setPrice(price);

                break;
            }
        }

        return true;

    }



}
