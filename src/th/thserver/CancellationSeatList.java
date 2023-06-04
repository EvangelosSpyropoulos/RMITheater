package th.thserver;
import th.*;

import java.util.EnumMap;

public class CancellationSeatList extends ReservedSeatList {
    public CancellationSeatList(
        boolean couldCancel,
        int numSeats,
        EnumMap<SeatType, Integer> reservedSeats
    ) {
        this.couldCancel = couldCancel;
        this.numSeats = numSeats;
        this.reservedSeats = reservedSeats;
    }

    @Override
    protected String showMessage() {
        String msg;
        if (couldCancel) {
            msg = 
                "Ακυρώθηκαν επιτυχώς " + numSeats + " θέσεις.\n" +
                "Υπόλοιπες κρατήσεις:\n"
            ;
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
                }}
            }
        } else {
            msg = "Η ακύρωση ήταν ανεπιτυχής. ";
            if (reservedSeats == null) {
                msg += "Ο πελάτης δεν έχει κρατήσεις στο θέατρο.\n";
            } else if (numSeats == -1) {
                msg += "Ο πελάτης δεν έχει κρατήσεις αυτού του τύπου.\n";
            } else {
                msg += 
                    (numSeats == 1? " Υπάρχει" : " Υπάρχουν") + " μόνο " + 
                    numSeats +  
                    (numSeats == 1? " κρατημένη θέση" : " κρατημένες θέσεις") + 
                    " αυτού του τύπου.\n" +
                    "Θα θέλατε να ακυρώσετε " + numSeats + 
                    (numSeats == 1? " θέση" : " θέσεις") + ";\n"
                ;
            }
        }
        return msg;
    }
}