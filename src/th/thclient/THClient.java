package th.thclient;
import th.*;

import java.rmi.Naming;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class THClient {
    public static void main(String[] args) {
        try {

            if (args.length == 0) {
                printUsage();
            } else if (args.length >= 2){
                THInterface theater = (THInterface) Naming.lookup("rmi://" + args[1] + "/theater");

                if (args[0].equals("list") && args.length == 2) {
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
                        BookingStatus bookingStatus;
                        SeatType seatType = SeatType.valueOf(args[2]);

                        bookingStatus = theater.book(
                            seatType,
                            Integer.parseInt(args[3]),
                            args[4]
                        );
                        System.out.println(bookingStatus);

                        if (!bookingStatus.couldReserve()) {
                            BufferedReader reader = new BufferedReader(
                                new InputStreamReader(System.in)
                            );
                            String answer;
                            do {
                                System.out.print("[y/n]: ");
                                answer = reader.readLine();
                            } while (
                                !answer.toLowerCase().equals("y") &&
                                !answer.toLowerCase().equals("n")
                            );

                            if (answer.equals("y")) {
                                SeatsNotificationListener cancelledSeatsListener =
                                    new SeatsNotificationListener()
                                ;
                                theater.registerSeatsCancelledListener(
                                    seatType,
                                    cancelledSeatsListener
                                );

                                System.out.println(
                                    "Πληκτρολογείστε exit για να διαγραφείτε" +
                                    " από τη λίστα αναμονής."
                                );
                                do {
                                    answer = reader.readLine();
                                } while (!answer.equals("exit"));

                                theater.deregisterSeatsCancelledListener(
                                    seatType,
                                    cancelledSeatsListener
                                );

                                UnicastRemoteObject.unexportObject(cancelledSeatsListener, false);
                            }
                        }
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
                    try {
                        System.out.println(
                            theater.cancel(
                                SeatType.valueOf(args[2]), 
                                Integer.parseInt(args[3]), 
                                args[4]
                            )
                        );
                    } catch (RemoteException re) {
                        System.err.println(
                            "Error calling remote method guests: "
                        );
                        re.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace(System.err);
                    }
                } else {
                    printUsage();
                }
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
            "\tLists available seats at theater located at <hostname>\n\n" +
            "java THClient book <hostname> <type> <number> <name>\n" +
            "\tBooks <number> seats of type <type> for guest with name " + 
            "<name> at theater located at <hostname>\n\n" +
            "java THClient guests <hostname>\n" +
            "\tLists all theater guests and the reservations booked by " + 
            "each guest at theater located at <hostname>\n\n" +
            "java THClient cancel <hostname> <type> <number> <name>\n" +
            "\tCancels <number> reservations for seats of type <type> " + 
            "booked by guest with name <name> at theater located at <hostname>\n\n" +
            "Valid <type> values:\n" +
            "\tSA\n" +
            "\tSB\n" +
            "\tSC\n" +
            "\tCB\n" +
            "\tSG\n"
        );
    }
}
