package org.mybatis.domain;

public class TB_USER {
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public String getUserGrade() {
		return userGrade;
	}
	public void setUserGrade(String userGrade) {
		this.userGrade = userGrade;
	}
	public String getUserLocation() {
		return userLocation;
	}
	public void setUserLocation(String userLocation) {
		this.userLocation = userLocation;
	}
	public TB_USER() {
		super();
	}
	public TB_USER(int userId, String userName, String userRole, String userGrade, String userLocation) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userRole = userRole;
		this.userGrade = userGrade;
		this.userLocation = userLocation;
	}
    
    private int userId;
	private String userName;
	private String userRole;
	private String userGrade;
	private String userLocation;
}