package cinemaSystem;

import java.sql.Connection;

public class ReservationController {

    private ReservationDAO reservationDAO;

    public ReservationController(Connection conn) {
        this.reservationDAO = new ReservationDAO(conn);
    }

   
    //. CORE LOGIC ( USE IDS ONLY)
    
    public boolean checkSeatAvailability(int showtimeID, int seatID) {

        // validate IDs
        if (showtimeID <= 0 || seatID <= 0)
            return false;

        // check showtime exists
        if (!reservationDAO.showtimeExists(showtimeID))
            return false;

        // check seat belongs to correct screen
        if (!reservationDAO.isSeatValidForShowtime(showtimeID, seatID))
            return false;

        // check if already booked
        if (reservationDAO.isSeatBooked(showtimeID, seatID))
            return false;

        return true;
    }

  // user 
    public boolean checkSeatAvailability(String movie,
                                         int screen,
                                         String seat,
                                         String date,
                                         String time) {

        // basic validation
        if (movie == null || movie.isBlank())
            return false;

        if (seat == null || !seat.matches("[A-Z][0-9]+"))
            return false;

        if (screen <= 0)
            return false;

        // convert to IDs using DAO 
        int showtimeID = reservationDAO.getShowtimeID(movie, screen, date, time);//Find the showtime in the database that matcheskol dah return its ID.”
        int seatID = reservationDAO.getSeatID(screen, seat);//Find the seat inside that screen  and return its database ID

        // if conversion failed
        if (showtimeID == -1 || seatID == -1)
            return false;

        
        return checkSeatAvailability(showtimeID, seatID);
    }
}