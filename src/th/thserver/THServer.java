package th.thserver;
import th.*;

import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.Naming;

public class THServer {
    public THServer() {
        try {
            LocateRegistry.createRegistry(1099);
            System.out.println("RMI registry created");
        } catch (RemoteException e) {
            System.out.println("RMI registry already exists");
        }

        try {
            THImpl thimpl = new THImpl();
            Naming.rebind("rmi://localhost/theater", thimpl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new THServer();
    }
}