package th;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface THInterface extends Remote {
    public SeatList list() throws RemoteException;
    public BookingStatus book(SeatType type, int num, String name) 
        throws RemoteException;
    public GuestList guests() throws RemoteException;
    public ReservedSeatList cancel(SeatType type, int num, String name) 
        throws RemoteException;
}





