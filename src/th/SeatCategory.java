package th;

import java.io.Serializable;

public class SeatCategory implements Serializable {
    public int maxSeats;
    public int availableSeats;
    public SeatType type;
    public float price;

    public SeatCategory(int maxSeats, SeatType type, float price) {
        this.maxSeats = maxSeats;
        this.availableSeats = availableSeats;
        this.type = type;
        this.price = price;
    }
}

