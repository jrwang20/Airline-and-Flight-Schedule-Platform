package service;

import java.util.Date;

public class Flight {

    private final String flightId;
    private final String airlinerName;

    private String fromCity;
    private String toCity;
    private String time;
    private Date date;
    private int price;

    private boolean isAvailable;

    public Flight(String flightId, String airlinerName) {
        this.flightId = flightId;
        this.airlinerName = airlinerName;
    }

    public Flight(String flightId, String airlinerName, String fromCity, String toCity, Date date, String time, int price, boolean isAvailable) {
        this.flightId = flightId;
        this.airlinerName = airlinerName;
        this.fromCity = fromCity;
        this.toCity = toCity;
        this.date = date;
        this.time = time;
        this.price = price;
        this.isAvailable = isAvailable;
    }

    public String getAirlinerName() {
        return airlinerName;
    }

    public String getFlightId() {
        return flightId;
    }

    public String getFromCity() {
        return fromCity;
    }

    public void setFromCity(String fromCity) {
        this.fromCity = fromCity;
    }

    public String getToCity() {
        return toCity;
    }

    public void setToCity(String toCity) {
        this.toCity = toCity;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String timeEnum) {
        this.time = timeEnum;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    
    
    @Override
    public String toString() {
        return "[" + flightId + "]";
    }
}
