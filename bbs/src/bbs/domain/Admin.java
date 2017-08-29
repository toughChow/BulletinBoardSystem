package bbs.domain;

public class Admin {

	private Integer adminId;
	private String name;
	private String password;
	public Integer getAdminId() {
		return adminId;
	}
	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Admin(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}
	public Admin() {
		super();
	}
	@Override
	public String toString() {
		return "admin [adminId=" + adminId + ", name=" + name + ", password="
				+ password + "]";
	}
	
}
