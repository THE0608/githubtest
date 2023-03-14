package vo;

public class User {
	private String userID;
	private String password;
	private String chrName;
	private String role;
	@Override
	public String toString() {
		return "User [userID=" + userID + ", password=" + password + ", chrName=" + chrName + ", role=" + role + "]";
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getChrName() {
		return chrName;
	}
	public void setChrName(String chrName) {
		this.chrName = chrName;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public User(String userID, String password, String chrName, String role) {
		super();
		this.userID = userID;
		this.password = password;
		this.chrName = chrName;
		this.role = role;
	}
	public User() {};
	
}
