package th.thserver;
import th.*;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.EnumMap;

public class THImpl extends UnicastRemoteObject implements THInterface {
    Theater theater;

    THImpl() throws RemoteException {
        super(0);
        EnumMap<SeatType, SeatCategory> seats = new EnumMap<SeatType, SeatCategory>(SeatType.class);
        seats.put(SeatType.SA, new SeatCategory(100, SeatType.SA, 30.0f));
        seats.put(SeatType.SB, new SeatCategory(200, SeatType.SB, 20.0f));
        seats.put(SeatType.SC, new SeatCategory(300, SeatType.SC, 15.0f));
        seats.put(SeatType.CB, new SeatCategory(300, SeatType.CB, 40.0f));
        seats.put(SeatType.SG, new SeatCategory(300, SeatType.SG, 35.0f));

        theater = new Theater(seats);
    }

    @Override
    public SeatList list() throws RemoteException {
        return new TheaterSeatList(theater.getSeats());
    }

    @Override
    public BookingStatus book(SeatType type, int num, String name) 
        throws RemoteException {
        GuestBookingStatus guestBookingStatus;
        if (theater.book(type, num, name)) {
            guestBookingStatus = new GuestBookingStatus(
                true, 
                num, 
                num * theater.getSeats().get(type).price
            );
        } else {
            int availableSeats = theater.getSeats().get(type).availableSeats;
            guestBookingStatus = new GuestBookingStatus(
                false,
                availableSeats,
                availableSeats * theater.getSeats().get(type).price
            );
        }

        return guestBookingStatus;
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