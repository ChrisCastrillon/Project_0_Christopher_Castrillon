package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Application;
import com.revature.models.BankAccount;
import com.revature.models.Role;
import com.revature.models.User;
import com.revature.utils.ConnectionUtil;

public class ApplicationDAO implements IApplicationDAO{

	public List<Application> findAll() {
		List<Application> allApplications = new ArrayList<>();
		
		//get a connection to the database
		try (Connection conn = ConnectionUtil.getConnection()) {
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM project0.APPLICATIONS";
			ResultSet rs = stmt.executeQuery(sql);
			//iterate through the set of users
			while(rs.next()) {
				int id = rs.getInt("id");
				String firstName = rs.getString("firstname");
				String lastName = rs.getString("lastname");
				int ssn = rs.getInt("socialsecuritynumber");//this gets the string from the column name
				double salary = rs.getDouble("salary");
				String status = rs.getString("status");
				Application apps = new Application(id,firstName, lastName, ssn, salary,status);
				//add the user to the list of users
				allApplications.add(apps);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("WE FAILED TO RETRIEVE ALL APPLICATIONS");
			//if it returns null then you should try again.
			return null;
		}
 		return allApplications;
	}

	public Application findById(int userId) {
		Application app = new Application();
		try (Connection conn = ConnectionUtil.getConnection()) {
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM project0.APPLICATIONS WHERE id = " + userId;
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String firstName = rs.getString("firstname");
				String lastName = rs.getString("lastname");
				int ssn = rs.getInt("socialsecuritynumber");
				double salary = rs.getDouble("salary");
				String status = rs.getString("status");
				app = new Application(userId, firstName, lastName, ssn, salary, status);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("WE FAILED TO FIND THE APPLICATION");
			//if it returns null then you should try again.
			return null;
		}
		return app;
	}
	public Application findBySocial(int ssn) {
		Application app = new Application();
		try (Connection conn = ConnectionUtil.getConnection()) {
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM project0.APPLICATIONS WHERE socialsecuritynumber = " + ssn;
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				int userId = rs.getInt("id");
				String firstName = rs.getString("firstname");
				String lastName = rs.getString("lastname");
				double salary = rs.getDouble("salary");
				String status = rs.getString("status");
				app = new Application(userId, firstName, lastName, ssn, salary, status);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("WE FAILED TO RETRIEVE THE APPLICATION");
			//if it returns null then you should try again.
			return null;
		}
		return app;
	}
	public boolean insert(Application app) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "INSERT INTO project0.APPLICATIONS (id, firstname, lastname, socialsecuritynumber, salary, status, owner) VALUES (?,?,?,?,?,?,?)";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1,app.getId());
			stmt.setString(2, app.getFirstName());
			stmt.setString(3, app.getLastName());
			stmt.setInt(4, app.getSocialSecurityNumber());
			stmt.setDouble(5, app.getSalary());
			stmt.setString(6, app.getStatus());
			stmt.setInt(7, app.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("WE FAILED TO INSERT APPLICATION");
		}
		return true;
	}

	public boolean update(Application app) {
		try (Connection conn = ConnectionUtil.getConnection()) {		
			String sql = "UPDATE project0.APPLICATIONS SET id = ?, firstname = ?, lastname = ?, socialsecuritynumber = ?, salary = ?, status = ? WHERE id = ?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, app.getId());
			stmt.setString(2, app.getFirstName());
			stmt.setString(3, app.getLastName());
			stmt.setInt(4, app.getSocialSecurityNumber());
			stmt.setDouble(5, app.getSalary());
			stmt.setString(6, app.getStatus());
			stmt.setInt(7, app.getId());
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("WE FAILED TO UPDATE THE APPLICATION");
		}
		return true;
	}

	public boolean delete(int ssn) {
		try (Connection conn = ConnectionUtil.getConnection()) {		
			String sql = "DELETE FROM project0.APPLICATIONS WHERE socialsecuritynumber = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, ssn);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("WE FAILED TO UPDATE THE APPLICATION");
		}
		return true;
	}

}
