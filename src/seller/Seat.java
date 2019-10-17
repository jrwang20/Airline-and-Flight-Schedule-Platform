package seller;

public class Seat {

    private final String seatId;

    private boolean isAvailable;

    public Seat(String seatId) {
        this.seatId = seatId;
        this.isAvailable = true;
    }

    public String getSeatId() {
        return seatId;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
