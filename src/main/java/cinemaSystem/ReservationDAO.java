package cinemaSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReservationDAO {

    private Connection conn;

    public ReservationDAO(Connection conn) {
        this.conn = conn;
    }

    
    public boolean showtimeExists(int showtimeID) {

        String sql = "SELECT 1 FROM Showtimes WHERE showtimeID = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, showtimeID);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    
    public boolean isSeatValidForShowtime(int showtimeID, int seatID) {

        String sql =
            "SELECT 1 FROM Seats s " +
            "JOIN Showtimes sh ON s.screenID = sh.screenID " +
            "WHERE sh.showtimeID = ? AND s.seatID = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, showtimeID);
            stmt.setInt(2, seatID);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    
    public boolean isSeatBooked(int showtimeID, int seatID) {

        String sql =
            "SELECT 1 FROM Bookings b " +
            "JOIN Booking_Seat bs ON b.bookingID = bs.bookingID " +
            "WHERE b.showtimeID = ? AND bs.seatID = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, showtimeID);
            stmt.setInt(2, seatID);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    
    public int getShowtimeID(int movieID, int screenID, String date, String time) {

        String sql =
            "SELECT showtimeID FROM Showtimes " +
            "WHERE movieID = ? AND screenID = ? AND showDate = ? AND startTime = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, movieID);
            stmt.setInt(2, screenID);
            stmt.setDate(3, java.sql.Date.valueOf(date));
            stmt.setTime(4, java.sql.Time.valueOf(time));

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next())
                    return rs.getInt("showtimeID");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    
    public int getSeatID(int screenID, String seatLabel) {

        String sql =
            "SELECT seatID FROM Seats " +
            "WHERE screenID = ? AND CONCAT(CHAR(64 + rowNum), seatNumber) = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, screenID);
            stmt.setString(2, seatLabel);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next())
                    return rs.getInt("seatID");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }
}