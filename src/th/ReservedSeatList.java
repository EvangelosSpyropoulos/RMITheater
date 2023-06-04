package th;

import java.util.EnumMap;

public abstract class ReservedSeatList extends RMIMessage {
    protected boolean couldCancel;
    protected int numSeats;
    protected EnumMap<SeatType, Integer> reservedSeats;
}