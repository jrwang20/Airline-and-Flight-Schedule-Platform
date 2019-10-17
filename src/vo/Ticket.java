package vo;

import buyer.Customer;
import seller.Seat;
import service.Flight;

public class Ticket {

    Customer customer;

    Flight flight;

    Seat seat;

    public Ticket(Customer customer, Flight flight, Seat seat) {
        this.customer = customer;
        this.flight = flight;
        this.seat = seat;
    }
    
    public Customer getCustomer() {
        return customer;
    }

    public Flight getFlight() {
        return flight;
    }

    public Seat getSeat() {
        return seat;
    }
}
