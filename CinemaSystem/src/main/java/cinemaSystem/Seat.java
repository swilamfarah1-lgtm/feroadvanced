package cinemaSystem;

public class Seat {

    private int seatId;
    private int row;
    private int number;
    private boolean reserved;

    // Constructor
    public Seat(int seatId, int row, int number, boolean reserved) {
        this.seatId = seatId;
        this.row = row;
        this.number = number;
        this.reserved = reserved;
    }

    // Getters and Setters
    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }
}