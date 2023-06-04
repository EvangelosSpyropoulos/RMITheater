package th.thserver;
import th.*;

import java.io.Serializable;
import java.util.EnumMap;

public class Guest implements Serializable {
    String name;
    private EnumMap<SeatType, Integer> reservedSeats;

    public Guest(String name) {
        this.name = name;
        reservedSeats = new EnumMap<SeatType, Integer>(SeatType.class);
    }

    public EnumMap<SeatType, Integer> getReservedSeats() {
        return reservedSeats;
    }

    public void reserve(SeatType type, int num) {
        if (num < 0) { return; }

        if (!reservedSeats.containsKey(type)) {
            reservedSeats.put(type, num);
        } else {
            reservedSeats.put(type, reservedSeats.get(type) + num);
        }
    }

    public void cancel(SeatType type, int num) {
        if (reservedSeats.containsKey(type)) {
            int remaining = reservedSeats.get(type) - num;
            reservedSeats.put(type, remaining > 0 ? remaining : 0);
        }
    }
}