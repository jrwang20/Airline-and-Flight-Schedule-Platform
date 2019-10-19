package platform;

import buyer.Customer;
import buyer.CustomerDirectory;
import java.util.Date;
import service.Flight;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TravelOffice {

    private CustomerDirectory customerDirectory;

    private MasterTravelSchedule master;

    private Map<Customer, List<Flight>> customerFlightMap;

    public TravelOffice(List<Flight> flightList) {

        master = new MasterTravelSchedule(flightList);
        customerDirectory = new CustomerDirectory();
        customerFlightMap = new HashMap<>();

    }
    
    /**
     * 获取所有available飞机
     * @return
     */
    public List<Flight> getAllAvailableFlight() {
        return master.getAllAvailableFlight();
    }

    public void addSchedule(List<Flight> flightList) {

        master.addFlight(flightList);

    }
    
    public void addSchedule(Flight flight) {

        master.addFlight(flight);

    }

    public void addCustomer(Customer customer) {

        customerDirectory.addCustomer(customer);

    }

    /**
     *  根据用户需求，找到合适的Flight列表
     * @param customer
     * @return
     */
    public List<Flight> getCustomerFlightList(Customer customer) {

        String fromCity = customer.getFromCity();
        String toCity = customer.getToCity();
        Date date = customer.getDate();
        String time = customer.getTime();
        int maxPrice = customer.getMaxPrice();

        List<Flight> res = master.multipleFilter(fromCity, toCity, date, time, maxPrice);

        return res;

    }
    
    /**
     *  根据自定义需求，找到合适的Flight列表
     * @param
     * @return
     */
    public List<Flight> getCustomizedFlightList(String from, String to, Date date, String time, int price) {
        
        List<Flight> res = master.multipleFilter(from, to, date, time, price);

        return res;

    }

    /**
     * 首先要有customerDirectory
     * 然后要有FlightList
     * 也就是说要从master里面进行过滤
     * 因此master当中必须传入FlightList
     * @return
     */
    public Map<Customer, List<Flight>> buildInfoMap() {

        for(Customer customer : customerDirectory.getCustomerList()) {

            List<Flight> customizedFlightList = getCustomerFlightList(customer);
            this.customerFlightMap.put(customer, customizedFlightList);

        }

        return this.customerFlightMap;

    }

    public CustomerDirectory getCustomerDirectory() {
        return customerDirectory;
    }







}
