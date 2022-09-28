package com.stackroute.userservice.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {

	@Id
	private String userId;
	private String userPassword;
	private Date userAddedDate;
	private String firstName;
	private String lastName;
	private String userEmail;

	public User() {

	}

	public User(String userId, String userPassword, String userRole, Date userAddedDate, String firstName,
			String lastName,String userEmail) {
		super();
		this.userId = userId;
		this.userPassword = userPassword;
		this.userAddedDate = new Date();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userEmail = userEmail;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public Date getUserAddedDate() {
		return userAddedDate;
	}

	public void setUserAddedDate(Date userAddedDate) {
		this.userAddedDate = userAddedDate;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
}
