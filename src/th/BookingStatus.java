package th;

public abstract class BookingStatus extends RMIMessage {
    protected boolean couldReserve;
    protected int numSeats;
    protected float bookingCost;

    public boolean couldReserve() { return couldReserve; };
}
