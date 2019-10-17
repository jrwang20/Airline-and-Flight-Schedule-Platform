package seller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Fleet {

    private List<Airplane> airplaneList;
    private Set<String> checkId;

    public Fleet() {
        this.airplaneList = new ArrayList<>();
        this.checkId = new HashSet<>();
    }

    public List<Airplane> getAirplaneList() {
        return airplaneList;
    }

    public boolean addAirplane(Airplane airplane) {

        if(checkId.add(airplane.getAirplaneId())) {
            airplaneList.add(airplane);
        } else {
            return false;
        }

        return true;

    }

    public Airplane searchById(String airplaneId) {
        if(!checkId.contains(airplaneId)) {
            return null;
        }
        for(Airplane airplane : airplaneList) {
            if(airplane.getAirplaneId().equals(airplaneId)) {
                return airplane;
            }
        }
        return null;
    }
}
