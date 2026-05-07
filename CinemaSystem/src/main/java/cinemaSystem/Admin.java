package cinemaSystem;

public class Admin {
	private int id;
	private String username;
	private String password; 
	
	
	//han3mel 2 constructor 
	public Admin(int id, String username, String password) {
	    this.id = id;
	    this.username = username;
	    this.password = password;
	}// da ll user elle aready ll admin elle already registered 3ashan a retrieve their data men Db	
	
	public Admin(String username, String password) {
	    this.username = username;
	    this.password = password;
	} // notice : da mn gher id ( 3ashan el user mesh b =y3mel lnafso id ) f keda da lle lessa bysagel 3andy fel db (mesh bakhod mno id ana elle ba3melo ) (new admin) 

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
