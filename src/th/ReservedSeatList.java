package th;

import java.util.EnumMap;

public abstract class ReservedSeatList extends RMIMessage {
    protected EnumMap<SeatType, Integer> reservedSeats;
}