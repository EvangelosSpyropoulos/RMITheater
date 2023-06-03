package th.thserver;
import th.*;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.EnumMap;

public class THImpl extends UnicastRemoteObject implements THInterface {
    Theater theater;

    THImpl() throws RemoteException {
        super(0);
        ArrayList<SeatCategory> seats = new ArrayList<SeatCategory>();
        seats.add(new SeatCategory(100, SeatType.SA, 30.0f));
        seats.add(new SeatCategory(200, SeatType.SB, 20.0f));
        seats.add(new SeatCategory(300, SeatType.SC, 15.0f));
        seats.add(new SeatCategory(300, SeatType.CB, 40.0f));
        seats.add(new SeatCategory(300, SeatType.SG, 35.0f));

        theater = new Theater(seats, new ArrayList<Guest>());
    }

    @Override
    public SeatList list() throws RemoteException {
        return new TheaterSeatList(theater.getSeats());
    }

    @Override
    public BookingStatus book(SeatType type, int num, String name) 
        throws RemoteException {
        return new GuestBookingStatus();
    }

    @Override
    public GuestList guests() throws RemoteException {
        return new TheaterGuestList();
    }

    @Override
    public ReservedSeatList cancel(SeatType type, int num, String name) 
        throws RemoteException {
        return new CancellationSeatList(
            new EnumMap<SeatType, 
            Integer>(SeatType.class), 0
        );
    }


}