package th.thserver;
import th.*;

public class GuestBookingStatus extends BookingStatus {
    public GuestBookingStatus(
        boolean couldReserve, 
        int numSeats, 
        float bookingCost
    ) {
        this.couldReserve = couldReserve;
        this.numSeats = numSeats;
        this.bookingCost = bookingCost;
    }

    @Override
    protected String showMessage() {
        String msg;
        if (couldReserve) {
            msg = 
                "Κρατήθηκαν επιτυχώς " + numSeats + " θέσεις.\n" +
                "Συνολικό κόστος κράτησης: " + bookingCost + " Ευρώ.\n"; 
            ;
        } else {
            msg = 
                "Η κράτηση ήταν ανεπιτυχής. " + 
                (numSeats == 1 ? "Υπάρχει" : "Υπάρχουν") + " μόνο " + numSeats + 
                (numSeats == 1 ? " διαθέσιμη θέση" : " διαθέσιμες θέσεις") + 
                " αυτού του τύπου.\n" +
                "Θα θέλατε να κάνετε κράτηση " + numSeats + 
                (numSeats == 1 ? " θέσης" : " θέσεων") + ";\n" +
                "Συνολικό κόστος κράτησης: 0.0 Ευρώ.\n" +
                "Κόστος προτεινόμενης κράτησης: " + bookingCost + " Ευρώ.\n"; 
            ;
        }

        return msg;
    }
}