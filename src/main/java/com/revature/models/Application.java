/**
 * This class is used to model an application object
 * Each application object has the information necessary to make
 * an application object that can then be added to the database
 * and retrieved by an administrator in order to either approve or
 * deny the application.
 * 
 * A user object contains things like an ID, a username, a password and a role
 * but it seems like you only need the get the ID from the user object.
 * 
 * So in the database, if you are inserting an application object, the only fields
 * that need to for
 */
package com.revature.models;

import java.util.Objects;

public class Application {
	private int id;
	private String firstName;
	private String lastName;
	private int socialSecurityNumber;
	private double salary;
	private User owner;
	private String status;
	
	//this application object that is returned should have and ID field
	//but the application submitted should not have an ID field, because it autmatically assigned 
	public Application() {
		super();
	}
	public Application(String firstName, String lastName, int socialSecurityNumber, double salary, String status){
		this.firstName = firstName;
		this.lastName = lastName;
		this.socialSecurityNumber = socialSecurityNumber;
		this.salary = salary;
	
		this.status = status;
	}
	//this is the returning application object, notice that 
	public Application(int id, String firstName, String lastName, int socialSecurityNumber, double salary, String status) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.socialSecurityNumber = socialSecurityNumber;
		this.salary = salary;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public boolean setFirstName(String firstName) {
		this.firstName = firstName;
		return false;
		
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getSocialSecurityNumber() {
		return socialSecurityNumber;
	}

	public void setSocialSecurityNumber(int socialSecurityNumber) {
		this.socialSecurityNumber = socialSecurityNumber;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}
	public String getStatus() {
		return this.status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
 	@Override
	public String toString() {
		return "THE FOLLOWING INFORMATION FOR THE APPLICATION:\n\n"
		+ "ID: " + id + "\n"
		+ "FIRST NAME: " + firstName + "\n"
		+ "LAST NAME: " + lastName + "\n"
		+ "SOCIAL SECURITY NUMBER: " + socialSecurityNumber + "\n"
		+ "SALARY: $" + salary + "\n"
		+ "STATUS: " + status;
// 		
// 		return "Application [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName
//				+ ", socialSecurityNumber=" + socialSecurityNumber + ", salary=" + salary + ", owner=" + owner
//				+ ", status=" + status + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(firstName, id, lastName, owner, salary, socialSecurityNumber, status);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Application)) {
			return false;
		}
		Application other = (Application) obj;
		return Objects.equals(firstName, other.firstName) && id == other.id && Objects.equals(lastName, other.lastName)
				&& Objects.equals(owner, other.owner)
				&& Double.doubleToLongBits(salary) == Double.doubleToLongBits(other.salary)
				&& socialSecurityNumber == other.socialSecurityNumber && Objects.equals(status, other.status);
	}

	
	
}
