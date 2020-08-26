package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.BankAccount;
import com.revature.models.Role;
import com.revature.models.User;
import com.revature.utils.ConnectionUtil;

public class UserDAO implements IUserDAO {

	public List<User> findAll() {
		List<User> allUsers = new ArrayList<>();
		
		//get a connection to the database
		try (Connection conn = ConnectionUtil.getConnection()) {
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM project0.USERS";
			ResultSet rs = stmt.executeQuery(sql);
			//iterate through the set of users
			while(rs.next()) {
				int id = rs.getInt("id");	//this gets the string from the column name
				String username = rs.getString("username");
				String password = rs.getString("password");
				Role role = Role.valueOf(rs.getString("role"));
				User u = new User(id,username,password,role);
				//add the uer to the list of users
				allUsers.add(u);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("WE FAILED TO RETRIEVE ALL USERS");
			//if it returns null then you should try again.
			return null;
		}
 		return allUsers;
	}
	//this should return the User object, but you should also have to pass it a password?
	public User findByUsername(String username) {
		User u = new User();
		try (Connection conn = ConnectionUtil.getConnection()) {
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM project0.USERS WHERE username = " + "\'"+ username + "\'";
			ResultSet rs = stmt.executeQuery(sql);
			//iterate through the set of users
				while(rs.next()) {
					int id = rs.getInt("id");	//this gets the string from the column name
					String password = rs.getString("password");
					Role role = Role.valueOf(rs.getString("role"));
					u = new User(id, username, password,role);
				}	
		}catch(SQLException e) {
			System.out.println("WE FAILED TO RETRIEVE USER");
			//if it returns null then you should try again.
			return null;
		}
		
		if (u.getId() != 0) {
			return u;
		}
		else {
			return null;
		}
	}

	public int insert(User u) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "INSERT INTO project0.users (username, password, role) VALUES (?,?,?) RETURNING project0.users.id";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1,u.getUsername());
			stmt.setString(2, u.getPassword());
			stmt.setObject(3, u.getRole(), Types.OTHER);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("WE FAILED TO INSERT THE USER");
		}
		return 0;
	}

	public boolean deleteByUsername(String username) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "DELETE * FROM project0.users WHERE username = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1,username);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("WE FAILED TO DELETE THE USER");
		}
		return false;
	}

	public boolean update(User u) {
		try (Connection conn = ConnectionUtil.getConnection()) {		
			String sql = "UPDATE project0.USERS SET id = ?, username = ?, password = ?, role = ? WHERE id = ?";		
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, u.getId());
			stmt.setString(2, u.getUsername());
			stmt.setString(3, u.getPassword());
			stmt.setObject(4, u.getRole(),Types.OTHER);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("WE FAILED TO UPDATE THE USER");
		}
		return true;
	}
	@Override
	public User findById(int id) {
		User user = new User();
		try (Connection conn = ConnectionUtil.getConnection()) {
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM project0.USERS WHERE id = " + id;
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				int theId = rs.getInt("id");
				String username = rs.getString("username");
				String password = rs.getString("password");
				Role role = Role.valueOf(rs.getString("role"));
				user = new User(theId, username, password, role);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("WE FAILED TO RETRIEVE THE USER");
			//if it returns null then you should try again.
			return null;
		}
		if (user.getId() != 0) {
			return user;
		}
		else {
			return null;
		}
	}

}
