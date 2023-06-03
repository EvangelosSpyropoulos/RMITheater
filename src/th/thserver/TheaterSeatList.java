package th.thserver;
import th.*;

import java.io.Serializable;
import java.util.ArrayList;

public class TheaterSeatList extends SeatList {
    public TheaterSeatList(ArrayList<SeatCategory> seats) {
        this.seats = seats;
    }

    @Override
    protected String showMessage() {
        String msg = new String();
        for (SeatCategory seat : seats) {
            msg = Integer.toString(seat.availableSeats) + " θέσεις ";
            switch(seat.type) {
                case SA:
                    msg += "Πλατεία - Ζώνη Α (κωδικός: ΠΑ) - τιμή: " + 
                        seat.price + " Ευρώ\n";
                    break;
                case SB:
                    msg += "Πλατεία - Ζώνη Β (κωδικός: ΠΒ) - τιμή: " + 
                        seat.price + " Ευρώ\n";
                    break;
                case SC:
                    msg += "Πλατεία - Ζώνη Γ (κωδικός: ΠΓ) - τιμή: " + 
                        seat.price + " Ευρώ\n";
                    break;
                case CB:
                    msg += "Κεντρικός Εξώστης (κωδικός: ΚΕ) - τιμή: " + 
                        seat.price + " Ευρώ\n";
                    break;
                case SG:
                    msg += "Πλαϊνά Θεωρεία (κωδικός: ΠΘ) - τιμή: " + 
                        seat.price + " Ευρώ\n";
                    break;
                default:
                    break;
            }
        }
        return msg;
    }
    
}