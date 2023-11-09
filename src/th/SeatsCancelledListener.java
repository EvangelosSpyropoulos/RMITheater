package th;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SeatsCancelledListener extends Remote {
    public void seatsCancelled(SeatType type, int num) throws RemoteException;
};
