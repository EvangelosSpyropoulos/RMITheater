package th.thclient;
import th.*;

import java.rmi.Naming;

public class THClient {
    public static void main(String[] args) {
   
        try {
            THInterface theater = (THInterface) Naming.lookup("rmi://localhost/theater");
        
            System.out.println(theater.list());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}