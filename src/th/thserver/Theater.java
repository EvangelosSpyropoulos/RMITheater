package th.thserver;
import th.*;

import java.util.ArrayList;

public class Theater {
    private ArrayList<SeatCategory> seats;
    private ArrayList<Guest> guests;

    public Theater(ArrayList<SeatCategory> seats, ArrayList<Guest> guests) {
        this.seats = seats;
        this.guests = guests;
    }

    public ArrayList<SeatCategory> getSeats() {
        return seats;
    }
}