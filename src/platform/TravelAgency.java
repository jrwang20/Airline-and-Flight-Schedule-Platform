package platform;

import buyer.Customer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import seller.Airliner;
import seller.Airplane;
import seller.Seat;
import service.Flight;
import vo.FlightAirplane;
import vo.Ticket;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class TravelAgency {

    private TravelOffice office;

    private List<Airliner> airlinerList;

    private Set<String> checkAirLiner;
    
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
    
    Random random = new Random();

    public TravelAgency() throws ParseException {

        checkAirLiner = new HashSet<>();

        airlinerList = new ArrayList<>();
        airlinerList.add(new Airliner("NEU", 50000));
        airlinerList.add(new Airliner("MIT", 40000));
        airlinerList.add(new Airliner("Harvard", 30000));
        airlinerList.add(new Airliner("BU", 20000));
        airlinerList.add(new Airliner("BC", 10000));
        airlinerList.add(new Airliner("Tufts", 60000));

        checkAirLiner.add("NEU");
        checkAirLiner.add("MIT");

        office = new TravelOffice(this.getAllFlight(this.airlinerList));
        
        this.addCustomer(new Customer("Jack", "BOS", "LAX", sdf.parse("2020-01-01"), "morning", 10000));
        this.addCustomer(new Customer("Ruby", "BOS", "LAX", sdf.parse("2020-01-01"), "morning", 90000));
        
        this.addCustomer(new Customer("Mark", "BOS", "LAX", sdf.parse("2020-01-01"), "morning", 30000));
        this.addCustomer(new Customer("Luke", "BOS", "LAX", sdf.parse("2020-01-01"), "morning", 50000));
    }

    /**
     * 逻辑一：添加Airliner航空公司信息
     * @param airliner
     * @return
     */
    public boolean addAirliner(Airliner airliner) {

        if(checkAirLiner.add(airliner.getAirlinerName())) {
            this.airlinerList.add(airliner);
            this.office.addSchedule(airliner.showAllFlight());
        } else {
            return false;
        }

        return true;
    }

    /**
     * 逻辑二：添加Customer顾客出行需求信息
     * @param customer
     */
    public void addCustomer(Customer customer) {

        this.office.addCustomer(customer);

    }

    /**
     * 逻辑三：根据Customer需求信息，返回合适的Flight
     * @param customer
     * @return
     */
    public List<Flight> customizedFlight(Customer customer) {

        return office.getCustomerFlightList(customer);

    }

    /**
     * 逻辑四：根据顾客需求选择航班后，生成Ticket
     * @param customer
     * @param flight
     * @return
     */
    public Ticket buyTicket(Customer customer, Flight flight) {

        if(!flight.isAvailable()) {
            return null;
        }

        String airlinerName = flight.getAirlinerName();

        Airliner targetAirLiner = null;

        for(Airliner airliner : airlinerList) {
            if(airliner.getAirlinerName().equals(airlinerName)) {
                targetAirLiner = airliner;
                break;
            }
        }

        if(targetAirLiner == null) {
            System.out.println("No Airliner");
            return null;
        } else {
            System.out.println("We have Airliner" + targetAirLiner.getAirlinerName());
        }

        Airplane targetAirplane = null;

        for(Airplane airplane : targetAirLiner.showAllAirplane()) {
            //检查是否该航空公司已经为当前航班assign了一架飞机
            if(targetAirLiner.checkFlightAirplane(new FlightAirplane(flight, airplane))) {
                if(airplane.getTotalAvailableSeat() > 0) {
                    targetAirplane = airplane;
                    break;
                }
            }
        }
        
        //如果循环结束后targetAirplane仍然为空，说明当前航班没有被assign过飞机
        //遍历所有的飞机，找到第一架没有被assign的飞机，然后返回
        for(Airplane airplane : targetAirLiner.showAllAirplane()) {
            if(!airplane.isAssigned()) {
                targetAirplane = airplane;
                System.out.println("Assigned New Airplane!!!!");
                airplane.setAssigned(true);
                break;
            }
        }
        
        if(targetAirplane == null) {
            System.out.println("No Airplane");
            return null;
        }else {
            System.out.println("We have Airplane" + targetAirplane.getAirplaneId());
        }

        String flightId = flight.getFlightId();

        System.out.println("******************************");
        System.out.println("Airplane and Flight are Ready!");
        System.out.println("******************************");
        System.out.println(flightId);
        System.out.println(targetAirplane.getAirplaneId());

        FlightAirplane flightAirplane = targetAirLiner.assignAirplaneToFlight(flightId, targetAirplane.getAirplaneId());

        if(flightAirplane == null) {
            System.out.println("Fail to Assign Airplane to Flight");
            return null;
        }

        Airplane airplane = flightAirplane.getAirplane();
        Seat seat = airplane.findSeat();

        if(seat == null) {
            return null;
        }

        return new Ticket(customer, flight, seat);
    }
    
    /**
     * 按照偏好位置订票
     * @param customer
     * @param flight
     * @param position
     * @return 
     */
    public Ticket buyTicket(Customer customer, Flight flight, String position) {
        if(position.equals("window")) {
            return buyTicket(customer, flight, random.nextInt(25), Math.random() > 0.5 ? 0 : 5);
        } else if(position.equals("middle")) {
            return buyTicket(customer, flight, random.nextInt(25), Math.random() > 0.5 ? 1 : 4);
        } else {
            return buyTicket(customer, flight, random.nextInt(25), Math.random() > 0.5 ? 2 : 3);
        }
    }
    
    
    /**
     * 按照座位订票
     * @param customer
     * @param flight
     * @param row
     * @param col
     * @return 
     */
    public Ticket buyTicket(Customer customer, Flight flight, int row, int col) {

        if(!flight.isAvailable()) {
            return null;
        }

        String airlinerName = flight.getAirlinerName();

        Airliner targetAirLiner = null;

        for(Airliner airliner : airlinerList) {
            if(airliner.getAirlinerName().equals(airlinerName)) {
                targetAirLiner = airliner;
                break;
            }
        }

        if(targetAirLiner == null) {
            System.out.println("No Airliner");
            return null;
        } else {
            System.out.println("We have Airliner" + targetAirLiner.getAirlinerName());
        }

        Airplane targetAirplane = null;

        for(Airplane airplane : targetAirLiner.showAllAirplane()) {
            if(!airplane.isAssigned()) {
                targetAirplane = airplane;
                airplane.setAssigned(true);
            }
        }

        String airplaneId = targetAirplane.getAirplaneId();
        if(targetAirplane == null) {
            System.out.println("No Airplane");
            return null;
        }else {
            System.out.println("We have Airplane" + airplaneId);
        }

        String flightId = flight.getFlightId();

        System.out.println("******************************");
        System.out.println("Airplane and Flight are Ready!");
        System.out.println("******************************");
        System.out.println(flightId);
        System.out.println(airplaneId);

        FlightAirplane flightAirplane = targetAirLiner.assignAirplaneToFlight(flightId, airplaneId);

        if(flightAirplane == null) {
            System.out.println("Fail to Assign Airplane to Flight");
            return null;
        }

        Airplane airplane = flightAirplane.getAirplane();
        Seat seat = airplane.reserveSeat(row, col);

        if(seat == null) {
            return null;
        }

        return new Ticket(customer, flight, seat);
    }

    /**
     * 从航空公司获取所有Flight
     * @param airlinerList
     * @return
     */
    public List<Flight> getAllFlight(List<Airliner> airlinerList) {

        List<Flight> res = new ArrayList<>();

        for(Airliner airliner : airlinerList) {

            List<Flight> flightList = airliner.showAllFlight();

            res.addAll(flightList);

        }

        return res;
    }

    public List<Airliner> getAirlinerList() {
        return airlinerList;
    }

    public TravelOffice getOffice() {
        return office;
    }
    
    public List<Flight> getAllAvailableFlight() {
        
        return office.getAllAvailableFlight();
        
    }

}
