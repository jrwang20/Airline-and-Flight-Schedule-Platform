package seller;

import service.Flight;
import service.FlightSchedule;
import vo.FlightAirplane;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Airliner {

    private String airlinerName;

    private Fleet fleet;

    private FlightSchedule schedule;

    private Set<FlightAirplane> flightAirplaneSet;

    /**
     * 如果是true的话，就说明当前airplane已经被assign到了当前航班上
     * 如果是false的话，就说明当前airplane没有和flight建立关系
     * @param flightAirplane
     * @return 
     */
    public boolean checkFlightAirplane(FlightAirplane flightAirplane) {
        
        if(this.flightAirplaneSet.contains(flightAirplane)) {
            return true;
        } else {
            return false;
        }
    }

    public String getAirlinerName() {
        return airlinerName;
    }

    public Airliner(String airlinerName, int price) {

        this.airlinerName = airlinerName;

        this.fleet = new Fleet();

        this.schedule = new FlightSchedule();

        this.addAirplane(new Airplane(airlinerName + " - plane737"));
        this.addAirplane(new Airplane(airlinerName + " - plane747"));
        this.addAirplane(new Airplane(airlinerName + " - plane757"));
        this.addAirplane(new Airplane(airlinerName + " - plane767"));

        this.addFlightToSchedule(new Flight(airlinerName + " - Flight001", airlinerName, "BOS", "LAX", "morning", price, true));

        flightAirplaneSet = new HashSet<>();

    }
    
    public Airliner(String airlinerName, String airplaneId, Flight flight) {

        this.airlinerName = airlinerName;

        this.fleet = new Fleet();

        this.schedule = new FlightSchedule();

        this.addAirplaneById(airplaneId);

        this.addFlightToSchedule(flight);

        flightAirplaneSet = new HashSet<>();

    }

    /**
     * **********************************************
     * Airliner基本信息
     * **********************************************
     */

    /**
     * 获取这家航空公司的飞机数量
     * @return
     */
    public int getNumOfAirplane() {
        return fleet.getAirplaneList().size();
    }

    /**
     * 获取这家航空公司的航班数量
     * @return
     */
    public int getNumOfFlight() {
        return schedule.getFlightList().size();
    }

    /**
     * **********************************************
     * 飞机和航班指派
     * **********************************************
     */

    /**
     * 将飞机指派给航班，Airplane + Flight
     * @param flightId
     * @param airplaneId
     * @return
     */
    public FlightAirplane assignAirplaneToFlight(String flightId, String airplaneId) {

        Flight flight = schedule.searchById(flightId);
        Airplane airplane = fleet.searchById(airplaneId);

        if(flight == null || airplane == null) {
            System.out.println("We can't find target Flight ot Airplane");
            return null;
        }

        if(!airplane.isAssigned()) {
            System.out.println("Airplane has already been assigned, please change an airplane");
            return null;
        }

        FlightAirplane flightAirplane = new FlightAirplane(flight, airplane);

        if(flightAirplaneSet.add(flightAirplane)) {
            return flightAirplane;
        }

        return null;

    }

    /**
     * **********************************************
     * 飞机相关(Fleet、Airplane)
     * **********************************************
     */
    
    /**
     * 给出Id来进行添加AirPlane
     * @param airplaneId
     * @return
     */
    public boolean addAirplaneById(String airplaneId) {

        Airplane airplane = new Airplane(this.airlinerName + " - plane" + airplaneId);

        return addAirplane(airplane);

    }

    /**
     * 添加飞机
     * @param airplane
     * @return
     */
    public boolean addAirplane(Airplane airplane) {
        return fleet.addAirplane(airplane);
    }

    /**
     * 列出所有飞机
     * @return
     */
    public List<Airplane> showAllAirplane() {
        return fleet.getAirplaneList();
    }

    /**
     * **********************************************
     * 航班相关(Flight)
     * **********************************************
     */

    /**
     * 根据Id查找Flight
     * @param flightId
     * @return
     */
    public Flight searchFlightById(String flightId) {
        return schedule.searchById(flightId);
    }

    /**
     * 列出所有航班
     * @return
     */
    public List<Flight> showAllFlight() {
        return schedule.getFlightList();
    }

    /**
     * 添加航班
     * @param flight
     * @return
     */
    public boolean addFlightToSchedule(Flight flight) {
        boolean add = schedule.addFlight(flight);
        if(add) {
            System.out.println("*****");
            System.out.println("Add Success and Now flight numer is" + this.getNumOfFlight());
            System.out.println("*****");
        } else {
            System.out.println("Fail");
        }
        return add;
    }

    /**
     * 更新航班
     * @param flight
     * @return
     */
    public boolean updateFlightInfo(Flight flight) {
        return schedule.updateFlight(flight);
    }

    /**
     * 删除航班
     * @param flightId
     * @return
     */
    public boolean cancelFlight(String flightId) {
        return schedule.cancelFlight(flightId);
    }

    public void setAirlinerName(String airlinerName) {
        this.airlinerName = airlinerName;
    }

    @Override
    public String toString() {
        return "[" + airlinerName + "]";
    }

}
