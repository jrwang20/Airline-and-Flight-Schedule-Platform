package seller;

public class Airplane {

    private final Seat[][] seatDistribute;

    private final String airplaneId;

    private boolean isAssigned;

    private int totalAvailableSeat;

    private int currentSeat;

    public Airplane(String airplaneId) {

        this.airplaneId = airplaneId;

        this.seatDistribute = new Seat[25][6];

        for(int i = 0; i < seatDistribute.length; i++) {
            for(int j = 0; j < seatDistribute[0].length; j++) {
                seatDistribute[i][j] = new Seat("[" + airplaneId + "] " + i + " - " + j);
            }
        }

        this.totalAvailableSeat = 25 * 6;

        this.isAssigned = false;

        this.currentSeat = 0;
    }

    public Seat findSeat() {

        if(totalAvailableSeat == 0) {
            return null;
        }
        
        int cur = currentSeat;
        int curRow = 0;
        int curCol = 0;
        
        while(cur < 150) {
            
            curRow = cur / 6;
            curCol = cur % 6;
            
            if(!seatDistribute[curRow][curCol].isAvailable()) {
                cur++;
                continue;
            } else {
                seatDistribute[curRow][curCol].setAvailable(false);
                break;
            }
        }

        this.currentSeat = cur + 1;
        this.totalAvailableSeat--;

        return seatDistribute[curRow][curCol];

    }
    
    public Seat reserveSeat(int row, int col) {
        
        if(totalAvailableSeat == 0) {
            return null;
        } else if(!seatDistribute[row][col].isAvailable()) {
            return null;
        }
        
        this.totalAvailableSeat--;
        seatDistribute[row][col].setAvailable(false);
        
        return seatDistribute[row][col];
        
    }

    public Seat[][] getSeatDistribute() {
        return seatDistribute;
    }

    public String getAirplaneId() {
        return airplaneId;
    }

    public boolean isAssigned() {
        return isAssigned;
    }

    public void setAssigned(boolean assigned) {
        isAssigned = assigned;
    }

    public int getTotalAvailableSeat() {
        return totalAvailableSeat;
    }

    public void setTotalAvailableSeat(int totalAvailableSeat) {
        this.totalAvailableSeat = totalAvailableSeat;
    }
    
    @Override
    public String toString() {
        return "[" + airplaneId + "]";
    }
}
