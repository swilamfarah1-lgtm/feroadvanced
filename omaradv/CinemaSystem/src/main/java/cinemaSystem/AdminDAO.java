package cinemaSystem;
import java.sql.*;
import java.util.ArrayList;

import java.util.List;
public class AdminDAO {
	private static final String DB_URL = ;
    private static final String USER = ;
    private static final String PASSWORD = ;

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }
    
    public static String hashPassword (String password) {
    	return Integer.toHexString(password.hashCode());
    } // adds more safety 3ashan kda el pw mesh haytkhazen fel DB zay mahowa elawal haythawel l format (encoded )tany bel hash code fun w ba3deen el hashcode hatthawel l hex decimal
    //hashcode () hat- generate an integer based 3an el string content 
    // to hex hat convert el hash format l hexadic string format  
    
    public static boolean insertAdmin ( Admin admin ) { 
    	String sql = "INSERT INTO Admins (username, password) VALUES (?, ?)";
    	 try (Connection conn = connect(); 
    			 PreparedStatement stmt = conn.prepareStatement(sql)) {
    		 stmt.setString(1, admin.getUsername());
             stmt.setString(2, hashPassword(admin.getPassword()));

             return stmt.executeUpdate() > 0;

    	 }
     catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
    public static boolean checkLogin(String username, String password) {
        String sql = "SELECT 1 FROM Admins WHERE username = ? AND password = ?";

        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, hashPassword(password));

            ResultSet rs = stmt.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static List<Admin> getAllAdmins() {
        List<Admin> admins = new ArrayList<>();
        String sql = "SELECT id, username, password FROM Admins";

        try (Connection conn = connect(); Statement stmt = conn.createStatement()) {

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                admins.add(new Admin(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return admins;
    }

    public static boolean deleteAdmin(String username) {
        String sql = "DELETE FROM Admins WHERE username = ?";

        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    }


