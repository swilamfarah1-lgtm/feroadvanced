package cinemaSystem;

public class seat {
	private int seatid;
	private int row ;
	private int number;
	private boolean reserved;
	public Seat(int seatid, int row, int number) {
		super();
		this.seatid = seatid;
		this.row = row;
		this.number = number;
	}
	public int getSeatid() {
		return seatid;
	}
	public void setSeatid(int seatid) {
		this.seatid = seatid;
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


}
