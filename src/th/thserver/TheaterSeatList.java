package th.thserver;
import th.*;

import java.io.Serializable;
import java.util.EnumMap;

public class TheaterSeatList extends SeatList {
    public TheaterSeatList(EnumMap<SeatType, SeatCategory> seats) {
        this.seats = seats;
    }

    @Override
    protected String showMessage() {
        String msg = new String();
        for (SeatType type : seats.keySet()) {
            SeatCategory seat = seats.get(type);
            msg += 
                Integer.toString(seat.availableSeats) + 
                (seat.availableSeats == 1 ? " θέση " : " θέσεις ")
            ;
            switch(seat.type) {
                case SA: {
                    msg += "Πλατεία - Ζώνη Α (κωδικός: ΠΑ) - τιμή: " + 
                        seat.price + " Ευρώ\n";
                    break;
                }
                case SB: {
                    msg += "Πλατεία - Ζώνη Β (κωδικός: ΠΒ) - τιμή: " + 
                        seat.price + " Ευρώ\n";
                    break;
                }
                case SC: {
                    msg += "Πλατεία - Ζώνη Γ (κωδικός: ΠΓ) - τιμή: " + 
                        seat.price + " Ευρώ\n";
                    break;
                }
                case CB: {
                    msg += "Κεντρικός Εξώστης (κωδικός: ΚΕ) - τιμή: " + 
                        seat.price + " Ευρώ\n";
                    break;
                }
                case SG: {
                    msg += "Πλαϊνά Θεωρεία (κωδικός: ΠΘ) - τιμή: " + 
                        seat.price + " Ευρώ\n";
                    break;
                }
                default: {
                    break;
                }
            }
        }
        return msg;
    }
    
}