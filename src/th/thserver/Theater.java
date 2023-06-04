package th.thserver;
import th.*;

import java.util.EnumMap;
import java.util.LinkedHashMap;

public class Theater {
    private EnumMap<SeatType, SeatCategory> seats;
    private LinkedHashMap<String, Guest> guests;

    public Theater(EnumMap<SeatType, SeatCategory> seats) {
        this.seats = seats;
        guests = new LinkedHashMap<String, Guest>();
    }

    public boolean book(SeatType type, int num, String name) {
        if (!guests.containsKey(name)) {
            guests.put(name, new Guest(name));
        }

        if (num <= seats.get(type).availableSeats) {
            seats.get(type).availableSeats -= num;
            guests.get(name).reserve(type, num);
            return true;
        } else {
            return false;
        }
    }

    public EnumMap<SeatType, SeatCategory> getSeats() {
        return seats;
    }
}