package th.thserver;
import th.*;

import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.io.Serializable;

public class Theater implements Serializable {
    private EnumMap<SeatType, SeatCategory> seats;
    private LinkedHashMap<String, Guest> guests;

    public Theater(EnumMap<SeatType, SeatCategory> seats) {
        this.seats = seats;
        guests = new LinkedHashMap<String, Guest>();
    }
    
    public EnumMap<SeatType, SeatCategory> getSeats() {
        return seats;
    }

    public LinkedHashMap<String, Guest> getGuests() {
        return guests;
    }

    public boolean book(SeatType type, int num, String name) {
        if (!guests.containsKey(name)) {
            guests.put(name, new Guest(name));
        }

        if (num > seats.get(type).availableSeats) { return false; }
        
        seats.get(type).availableSeats -= num;
        guests.get(name).reserve(type, num);
        return true;
        
    }

    public boolean cancel(SeatType type, int num, String name) {
        if (!guests.containsKey(name)) { return false; }

        Guest guest = guests.get(name);
        if (
            !guest.getReservedSeats().containsKey(type)
            || num > guest.getReservedSeats().get(type)
        ) { return false; }
        
        seats.get(type).availableSeats += num;
        guest.cancel(type, num);
        return true;
    }

    public float calculateGuestTotalCost(String name) {
        if (!guests.containsKey(name)) { return 0.0f; }
        
        float totalCost = 0.0f;
        Guest guest = guests.get(name);
        for (SeatType type : seats.keySet()) {
            if (guest.getReservedSeats().containsKey(type)) {
                totalCost += seats.get(type).price * guest.getReservedSeats().get(type);
            }
        }

        return totalCost;
    }
}