package th.thclient;
import th.*;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

public class SeatsNotificationListener extends UnicastRemoteObject implements SeatsCancelledListener {
    public SeatsNotificationListener() throws RemoteException {
    }

    @Override
    public void seatsCancelled(SeatType type, int num) throws RemoteException {
        String msg = num + (num == 1 ? " θέση" : " θέσεις") + " τύπου ";
        switch (type) {
        case SA: {
            msg += "Πλατεία - Ζώνη Α (κωδικός: ΠΑ)";
            break;
        }
        case SB: {
            msg += "Πλατεία - Ζώνη Β (κωδικός: ΠΒ)";
            break;
        }
        case SC: {
            msg += "Πλατεία - Ζώνη Γ (κωδικός: ΠΓ)";
            break;
        }
        case CB: {
            msg += "Κεντρικός Εξώστης (κωδικός: ΚΕ)";
            break;
        }
        case SG: {
            msg += "Πλαϊνά Θεωρεία (κωδικός: ΠΘ)";
            break;
        }}
        
        msg += 
            " ακυρώθηκαν.\n" + 
            "Μπορείτε να κάνετε κράτηση.\n"
        ;
        
        System.out.println(msg);
    }
}
