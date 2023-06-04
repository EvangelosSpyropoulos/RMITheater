package th;

import java.util.EnumMap;

public abstract class SeatList extends RMIMessage {
    protected EnumMap<SeatType, SeatCategory> seats;
}