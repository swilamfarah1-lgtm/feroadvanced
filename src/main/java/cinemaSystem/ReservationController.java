package cinemaSystem;

import java.sql.Connection;

public class ReservationController {

    private ReservationDAO reservationDAO;

    public ReservationController(Connection conn) {
        this.reservationDAO = new ReservationDAO(conn);
    }

    // =========================
    // MAIN BUSINESS LOGIC
    // =========================
    public boolean checkSeatAvailability(int showtimeID, int seatID) {

        if (showtimeID <= 0 || seatID <= 0)
            return false;

        if (!reservationDAO.showtimeExists(showtimeID))
            return false;

        if (!reservationDAO.isSeatValidForShowtime(showtimeID, seatID))
            return false;

        if (reservationDAO.isSeatBooked(showtimeID, seatID))
            return false;

        return true;
    }

    
    // USER-FRIENDLY METHOD
    
    public boolean checkSeatAvailability(int movieID,
                                         int screenID,
                                         String seatLabel,
                                         String date,
                                         String time) {

        int showtimeID = reservationDAO.getShowtimeID(movieID, screenID, date, time);
        int seatID = reservationDAO.getSeatID(screenID, seatLabel);

        if (showtimeID == -1 || seatID == -1)
            return false;

        return checkSeatAvailability(showtimeID, seatID);
    }
}