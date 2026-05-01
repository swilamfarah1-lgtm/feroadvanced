package cinemaSystem;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class JDBCCINEMA {
	

		    static final String DB_URL = ;
	    static final String USER = ; 
	    static final String PASSWORD = ; 

	    public static void main(String[] args) {
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
	            System.out.println("✅ Connected to MySQL database: cinema_project");

	            createTables(conn);

	            conn.close();
	            System.out.println("✅ Connection closed.");
	        } catch (ClassNotFoundException e) {
	            System.out.println("❌ MySQL driver not found.");
	            e.printStackTrace();
	        } catch (SQLException e) {
	            System.out.println("❌ SQL error.");
	            e.printStackTrace();
	        }
	    }

	    public static void createTables(Connection conn) throws SQLException {
	        Statement stmt = conn.createStatement();

	        // USERS
	        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Users (" +
	                "userID INT AUTO_INCREMENT PRIMARY KEY, " +
	                "name VARCHAR(100) NOT NULL, " +
	                "email VARCHAR(100) UNIQUE NOT NULL, " +
	                "password VARCHAR(100) NOT NULL, " +
	                "isAdmin BOOLEAN DEFAULT FALSE)");

	        // MOVIES
	        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Movies (" +
	                "movieID INT AUTO_INCREMENT PRIMARY KEY, " +
	                "title VARCHAR(200) NOT NULL, " +
	                "description TEXT, " +
	                "genre VARCHAR(100), " +
	                "duration INT, " +
	                "rating DOUBLE, " +
	                "trailerURL VARCHAR(500))");

	        // SCREENS (renamed from Halls)
	        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Screens (" +
	                "screenID INT AUTO_INCREMENT PRIMARY KEY, " +
	                "name VARCHAR(100) NOT NULL, " +
	                "totalSeats INT NOT NULL)");

	        // SHOWTIMES
	        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Showtimes (" +
	                "showtimeID INT AUTO_INCREMENT PRIMARY KEY, " +
	                "startTime DATETIME NOT NULL, " +
	                "showDate DATE NOT NULL, " +
	                "movieID INT NOT NULL, " +
	                "screenID INT NOT NULL, " +
	                "FOREIGN KEY (movieID) REFERENCES Movies(movieID), " +
	                "FOREIGN KEY (screenID) REFERENCES Screens(screenID))");

	        // SEATS
	        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Seats (" +
	                "seatID INT AUTO_INCREMENT PRIMARY KEY, " +
	                "screenID INT NOT NULL, " +
	                "rowNum INT NOT NULL, " +
	                "seatNumber INT NOT NULL, " +
	                "isAvailable BOOLEAN DEFAULT TRUE, " +
	                "FOREIGN KEY (screenID) REFERENCES Screens(screenID))");

	        // BOOKINGS
	        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Bookings (" +
	                "bookingID INT AUTO_INCREMENT PRIMARY KEY, " +
	                "userID INT NOT NULL, " +
	                "showtimeID INT NOT NULL, " +
	                "bookingTime DATETIME DEFAULT CURRENT_TIMESTAMP, " +
	                "totalPrice DOUBLE NOT NULL, " +
	                "FOREIGN KEY (userID) REFERENCES Users(userID), " +
	                "FOREIGN KEY (showtimeID) REFERENCES Showtimes(showtimeID))");

	        // BOOKING_SEAT
	        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Booking_Seat (" +
	                "bookingID INT, " +
	                "seatID INT, " +
	                "PRIMARY KEY (bookingID, seatID), " +
	                "FOREIGN KEY (bookingID) REFERENCES Bookings(bookingID), " +
	                "FOREIGN KEY (seatID) REFERENCES Seats(seatID))");

	        // PAYMENTS
	        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Payments (" +
	                "paymentID INT AUTO_INCREMENT PRIMARY KEY, " +
	                "bookingID INT NOT NULL, " +
	                "paymentMethod VARCHAR(50), " +
	                "paymentStatus VARCHAR(50), " +
	                "amount DOUBLE NOT NULL, " +
	                "FOREIGN KEY (bookingID) REFERENCES Bookings(bookingID))");

	        // ADMINS
	        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Admins (" +
	                "id INT AUTO_INCREMENT PRIMARY KEY, " +
	                "username VARCHAR(100) UNIQUE NOT NULL, " +
	                "password VARCHAR(100) NOT NULL)");

	        System.out.println("✅ All tables created successfully.");
	        stmt.close();
	    }
	}
}