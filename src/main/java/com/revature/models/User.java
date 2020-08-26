package com.revature.models;

import java.util.Objects;


//User refers to someone with app access and not necessarily an account.
public class User {
	private int id;
	private String username;
	private String password;
	private Role role;
	
	public User() {
		super();
	}
	public User(int id, String username, String password, Role role) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return this.username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return this.password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "USER CREDENTIALS:\n"
				+ "ID: " + id + "\n"
				+ "USERNAME: " + username + "\n"
				+ "PASSWORD: " + password + "\n"
				+ "ROLE: " + role;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id, password, role, username);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof User)) {
			return false;
		}
		User other = (User) obj;
		return id == other.id && password == other.password && role == other.role
				&& Objects.equals(username, other.username);
	}
}
