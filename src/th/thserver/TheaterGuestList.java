package th.thserver;
import th.*;

import java.util.LinkedHashMap;
import java.util.EnumMap;

public class TheaterGuestList extends GuestList {
    private Theater theater;

    public TheaterGuestList(Theater theater) {
        this.theater = theater;
    }

    @Override
    protected String showMessage() {
        String msg = new String();
        LinkedHashMap<String, Guest> guests = theater.getGuests();
        for (String guestName : guests.keySet()) {
            Guest guest = guests.get(guestName);
            msg += "Πελάτης " + guestName + ":\n";
            EnumMap<SeatType, Integer> reservedSeats = guest.getReservedSeats();
            if (reservedSeats.keySet().isEmpty()) {
                msg += "\tΔεν έχουν γίνει κρατήσεις.\n";
            } else {
                for (SeatType type : reservedSeats.keySet()) {
                    msg += 
                        "\t" + reservedSeats.get(type) + " κρατήσεις " + 
                        "θέσεων τύπου Πλατεία - Ζώνη Α (κωδικός: ΠΑ)\n"
                    ;
                }
            }
            msg += 
                "\tΣυνολικό κόστος κρατήσεων: " 
                + theater.calculateGuestTotalCost(guestName) + " Ευρώ.\n"
            ;
        }
        return msg;
    }
}