package th.thserver;
import th.*;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.EnumMap;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class THImpl extends UnicastRemoteObject implements THInterface {
    private Theater theater;
    private EnumMap<
        SeatType, 
        ArrayList<SeatsCancelledListener>
    > seatsCancelledListeners;


    THImpl() throws RemoteException {
        super(0);
        EnumMap<SeatType, SeatCategory> seats = new EnumMap<SeatType, SeatCategory>(SeatType.class);
        seats.put(SeatType.SA, new SeatCategory(100, SeatType.SA, 45.0f));
        seats.put(SeatType.SB, new SeatCategory(200, SeatType.SB, 35.0f));
        seats.put(SeatType.SC, new SeatCategory(400, SeatType.SC, 25.0f));
        seats.put(SeatType.CB, new SeatCategory(225, SeatType.CB, 30.0f));
        seats.put(SeatType.SG, new SeatCategory(75, SeatType.SG, 20.0f));

        seatsCancelledListeners = new EnumMap<
            SeatType,
            ArrayList<SeatsCancelledListener>
        >(SeatType.class);

        theater = new Theater(seats);
    }

    @Override
    public SeatList list() throws RemoteException {
        return new TheaterSeatList(theater.getSeats());
    }

    @Override
    public BookingStatus book(SeatType type, int num, String name) 
        throws RemoteException {
        if (theater.book(type, num, name)) {
            return new GuestBookingStatus(
                true, 
                num, 
                num * theater.getSeats().get(type).price
            );
        } else {
            int availableSeats = theater.getSeats().get(type).availableSeats;
            return new GuestBookingStatus(
                false,
                availableSeats,
                availableSeats * theater.getSeats().get(type).price
            );
        }
    }

    @Override
    public GuestList guests() throws RemoteException {
        return new TheaterGuestList(theater);
    }

    @Override
    public ReservedSeatList cancel(SeatType type, int num, String name) 
        throws RemoteException {
        LinkedHashMap<String, Guest> guests = theater.getGuests();
        if (theater.cancel(type, num, name)) {
            for (SeatsCancelledListener seatsCancelledListener : seatsCancelledListeners.get(type)) {
                seatsCancelledListener.seatsCancelled(type, num);
            }

            return new CancellationSeatList(
                true,
                num,
                guests.get(name).getReservedSeats()
            );
        } else {
            return new CancellationSeatList(
                false,
                (
                    guests.containsKey(name) && 
                    guests.get(name).getReservedSeats().containsKey(type) ?
                        guests.get(name).getReservedSeats().get(type)
                        : -1
                ),
                (guests.containsKey(name) ?
                    guests.get(name).getReservedSeats()
                    : null
                )
            );
        }
    }

    @Override
    public void registerSeatsCancelledListener(
        SeatType type, 
        SeatsCancelledListener seatsCancelledListener
    ) throws RemoteException {
        if (!seatsCancelledListeners.containsKey(type)) {
            ArrayList<SeatsCancelledListener> typeCancelledListeners = 
                new ArrayList<SeatsCancelledListener>()
            ;
            typeCancelledListeners.add(seatsCancelledListener);

            seatsCancelledListeners.put(type, typeCancelledListeners);
        } else {
            seatsCancelledListeners.get(type).add(seatsCancelledListener);
        }
    }
    
    @Override
    public void deregisterSeatsCancelledListener(
        SeatType type, 
        SeatsCancelledListener seatsCancelledListener
    ) throws RemoteException {
        if (!seatsCancelledListeners.containsKey(type)) { return; }

        seatsCancelledListeners.get(type).remove(seatsCancelledListener);
    }


}
