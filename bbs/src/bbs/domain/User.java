package bbs.domain;

import java.sql.Date;

public class User {

	private Integer userId;
	
	private String name;
	
	private String password;
	
	private String phone;
	
	private Date createTime;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer id) {
		this.userId = id;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public User(Integer id, String name, String password, String phone,
			Date createTime) {
		super();
		this.userId = id;
		this.name = name;
		this.password = password;
		this.phone = phone;
		this.createTime = createTime;
	}

	public User() {
		super();
	}

	public User(int userId2, String name2, String password2, String phone2) {
		this.userId = userId2;
		this.name = name2;
		this.password = password2;
		this.phone = phone2;
	}
	
	@Override
	public String toString() {
		return "User [id=" + userId + ", name=" + name + ", password=" + password
				+ ", phone=" + phone + ", createTime=" + createTime + "]";
	}
	
	
}
