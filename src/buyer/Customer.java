package buyer;

import java.util.Date;


public class Customer {

    private String customerName;

    private String fromCity;
    private String toCity;
    private Date date;
    private String time;
    private int maxPrice;
    
    public Customer(String customerName, String fromCity, String toCity, Date date, String time, int maxPrice) {
        this.customerName = customerName;
        this.fromCity = fromCity;
        this.toCity = toCity;
        this.date = date;
        this.time = time;
        this.maxPrice = maxPrice;
    }

    public Customer(String customerName) {
        this.customerName = customerName;
    }

    public Customer(String customerName, String fromCity, String toCity) {
        this.customerName = customerName;
        this.fromCity = fromCity;
        this.toCity = toCity;
    }

    public Customer(String customerName, String fromCity, String toCity, String time) {
        this.customerName = customerName;
        this.fromCity = fromCity;
        this.toCity = toCity;
        this.time = time;
    }

    public Customer(String customerName, String fromCity, String toCity, int maxPrice) {
        this.customerName = customerName;
        this.fromCity = fromCity;
        this.toCity = toCity;
        this.maxPrice = maxPrice;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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

    public void setTime(String time) {
        this.time = time;
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    @Override
    public String toString() {
        return customerName;
    }
}
