package platform;

import service.Flight;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MasterTravelSchedule {

    List<Flight> allFlight;
    Set<Flight> set;

    public MasterTravelSchedule(List<Flight> allFlight) {
        this.allFlight = allFlight;
        this.set = new HashSet<>();
    }
    
    public void addFlight(Flight flight) {
        
        if(set.add(flight)) {
            this.allFlight.add(flight);
        }
        
    }

    public void addFlight(List<Flight> flightList) {
        
        List<Flight> res = new ArrayList<>();
        
        for(Flight flight : flightList) {
            if(set.add(flight)) {
                res.add(flight);
            }
        }

        this.allFlight.addAll(flightList);

    }
    
    /**
     * 获取所有available的飞机
     * @return
     */
    public List<Flight> getAllAvailableFlight() {

        List<Flight> res = new ArrayList<>();

        for(Flight flight : allFlight) {
            if(flight.isAvailable()) {
                res.add(flight);
            }
        }

        return res;

    }

    /**
     * 根据所有指标进行筛选
     * @param from
     * @param to
     * @param time
     * @param price
     * @return
     */
    public List<Flight> filter(String from, String to, String time, int price) {
        List<Flight> res = new ArrayList<>();

        for(Flight flight : allFlight) {
            if(!flight.isAvailable()) {
                continue;
            }

            if(flight.getPrice() < price
            && flight.getFromCity().equals(to)
            && flight.getFromCity().equals(from)
            && flight.getTime().equals(time)) {
                res.add(flight);
            }
        }

        return res;
    }
    
    /**
     * 多重搜索
     * @param from
     * @param to
     * @param time
     * @param price
     * @return
     */
    public List<Flight> multipleFilter(String from, String to, Date date, String time, Integer price) {
        List<Flight> res = new ArrayList<>();
        
        for(Flight flight : allFlight) {
            
            if(!flight.isAvailable()) {
                continue;
            }
            
            if((from == null || flight.getFromCity().equals(from))
            && (to == null || flight.getToCity().equals(to))
            && (time == null || flight.getTime().equals(time))
            && (price == null || flight.getPrice() <= price)
            && (date == null || flight.getDate().equals(date))) {
                res.add(flight);
            }
            
        }
        
        return res;
    }

    /**
     * 根据from...to...两个地址进行筛选
     * @param from
     * @param to
     * @return
     */
    public List<Flight> filter(String from, String to) {
        List<Flight> res = new ArrayList<>();

        for(Flight flight : allFlight) {
            if(!flight.isAvailable()) {
                continue;
            }

            if(flight.getFromCity().equals(to)
            && flight.getFromCity().equals(from)) {
                res.add(flight);
            }
        }

        return res;
    }

    /**
     * 根据时间进行筛选
     * @param time
     * @return
     */
    public List<Flight> filterByTime(String time) {
        List<Flight> res = new ArrayList<>();

        for(Flight flight : allFlight) {
            if(flight.isAvailable() && flight.getTime().equals(time)) {
                res.add(flight);
            }
        }

        return res;
    }

    /**
     * 根据价格进行筛选
     * @param price
     * @return
     */
    public List<Flight> filterByPrice(int price) {
        List<Flight> res = new ArrayList<>();

        for(Flight flight : allFlight) {
            if(flight.isAvailable() && flight.getPrice() <= price) {
                res.add(flight);
            }
        }

        return res;
    }
}
