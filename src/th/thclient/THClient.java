package th.thclient;
import th.*;

import java.rmi.Naming;
import java.rmi.RemoteException;

public class THClient {
    public static void main(String[] args) {
        try {
            THInterface theater = (THInterface) Naming.lookup("rmi://localhost/theater");

            if (args.length == 0) {
                printUsage();
            } else if (args[0].equals("list") && args.length == 2) {
                try {
                    System.out.println(theater.list());
                } catch (RemoteException re) {
                    System.err.println(
                        "Error calling remote method list: "
                    );
                    re.getStackTrace();
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                }
            } else if (args[0].equals("book") && args.length == 5) {
                try {
                    System.out.println(
                        theater.book(
                            SeatType.valueOf(args[2]), 
                            Integer.parseInt(args[3]), 
                            args[4]
                        )
                    );
                } catch (IllegalArgumentException e) {
                    System.out.println(args[2] + " is not a valid seat type.");
                } catch (RemoteException re) {
                    System.err.println(
                        "Error calling remote method book: "
                    );
                    re.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                }
            } else if (args[0].equals("guests") && args.length == 2) {
                try {
                    System.out.println(theater.guests());
                } catch (RemoteException re) {
                    System.err.println(
                        "Error calling remote method guests: "
                    );
                    re.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                }
            } else if (args[0].equals("cancel") && args.length == 5) {

            } else {
                printUsage();
            }

        } catch (Exception e) {
            System.err.println("Could not connect to RMI server");
        }
    }

    private static void printUsage() {
        System.out.println(
            "Usage: java THClient cmd options\n\n" +
            "Commands:\n" +
            "java THClient list <hostname>\n" +
            "\t Lists available seats at theater located at <hostname>\n\n" +
            "java THClient book <hostname> <type> <number> <name>\n" +
            "\t Books <number> seats of type <type> for guest with name " + 
            "<name> at theater located at <hostname>\n\n" +
            "java THClient guests <hostname>\n" +
            "\t Lists all theater guests and the reservations booked by " + 
            "each guest at theater located at <hostname>\n\n" +
            "java THClient cancel <hostname> <type> <number> <name>\n" +
            "\t Cancels <number> reservations for seats of type <type> " + 
            "booked by guest with name <name> at theater located at <hostname>\n"
        );
    }
}