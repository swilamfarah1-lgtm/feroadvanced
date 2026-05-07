package cinemaSystem;

import java.util.List;

//logic layer 
public class AdminController {

	public boolean login (String username , String password ) {
		if (username == null || password == null || username.isEmpty()|| username.isEmpty()) {
			System.out.println("Invalid input");
            return false;
        }
		return AdminDAO.checkLogin (username,password); //calling db 3ashan a check eno mawgoud 3andy fel db 
		}
	
	 public boolean register(String username, String password) {
	        if (username == null || password == null) {
	            System.out.println("Invalid data");
	            return false;
	        }

	        Admin admin = new Admin(username, password); // 3amalt admin object
	        return AdminDAO.insertAdmin(admin); //hateto fel db 
	    }
	 public List<Admin> getAllAdmins() {
	        return AdminDAO.getAllAdmins(); 
	    }

	    public boolean deleteAdmin(String username) {
	        return AdminDAO.deleteAdmin(username);
	    }
	}

