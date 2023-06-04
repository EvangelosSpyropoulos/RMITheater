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
                    switch (type) {
                        case SA: {
                            msg += 
                                "\t" + reservedSeats.get(type) + " κρατήσεις " + 
                                "θέσεων τύπου Πλατεία - Ζώνη Α (κωδικός: ΠΑ)\n"
                            ;
                            break;
                        }
                        case SB: {
                            msg += 
                                "\t" + reservedSeats.get(type) + " κρατήσεις " + 
                                "θέσεων τύπου Πλατεία - Ζώνη Β (κωδικός: ΠΒ)\n"
                            ;
                            break;
                        }
                        case SC: {
                            msg += 
                                "\t" + reservedSeats.get(type) + " κρατήσεις " + 
                                "θέσεων τύπου Πλατεία - Ζώνη Γ (κωδικός: ΠΓ)\n"
                            ;
                            break;
                        }
                        case CB: {
                            msg += 
                                "\t" + reservedSeats.get(type) + " κρατήσεις " + 
                                "θέσεων τύπου Κεντρικός Εξώστης (κωδικός: ΚΕ)\n"
                            ;
                            break;
                        }
                        case SG: {
                            msg += 
                                "\t" + reservedSeats.get(type) + " κρατήσεις " + 
                                "θέσεων τύπου Πλαϊνά Θεωρεία (κωδικός: ΠΘ)\n"
                            ;
                            break;
                        }
                    }
                    
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