package th.thserver;
import th.*;

import java.util.EnumMap;

public class CancellationSeatList extends ReservedSeatList {
    public CancellationSeatList(
        EnumMap<SeatType, Integer> reservedSeats, 
        int seatsNotCancelled
    ) {
        this.reservedSeats = reservedSeats;
    }

    @Override
    protected String showMessage() {
        return "";
    }
}