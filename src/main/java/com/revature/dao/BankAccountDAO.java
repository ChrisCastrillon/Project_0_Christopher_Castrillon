package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Application;
import com.revature.models.BankAccount;
import com.revature.models.Role;
import com.revature.models.User;
import com.revature.utils.ConnectionUtil;

public class BankAccountDAO implements IBankAccountDAO {

	public List<BankAccount> findAll() {
		List<BankAccount> allAccounts = new ArrayList<>();
		
		//get a connection to the database
		try (Connection conn = ConnectionUtil.getConnection()) {
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM project0.ACCOUNTS";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt("id");
				double balance = rs.getDouble("balance");
				BankAccount ba = new BankAccount(id, balance);
				allAccounts.add(ba);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("WE FAILED TO RETRIEVE ALL ACCOUNTS");
			//if it returns null then you should try again.
			return null;
		}
 		return allAccounts;
	}
	public BankAccount findById(int id) {
		BankAccount bankAccount = new BankAccount();
		try (Connection conn = ConnectionUtil.getConnection()) {
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM project0.ACCOUNTS WHERE id = " + id;
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				int accountID = rs.getInt("id");
				double balance = rs.getDouble("balance");
				bankAccount = new BankAccount(accountID, balance);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("WE FAILED TO RETRIEVE THE ACCOUNT");
			//if it returns null then you should try again.
			return null;
		}
		if (bankAccount.getId() != 0) {
			return bankAccount;
		}
		else {
			return null;
		}
	}

	public int insert(BankAccount a) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "INSERT INTO project0.ACCOUNTS (id, balance, owner) VALUES (?,?,?)";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1,a.getId());
			stmt.setDouble(2, a.getBalance());
			stmt.setInt(3, a.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("WE FAILED TO INSERT THE ACCOUNT");
		}
		return 0;
	}

	public boolean update(BankAccount a) {
		try (Connection conn = ConnectionUtil.getConnection()) {		
			String sql = "UPDATE project0.ACCOUNTS SET id = ?, balance = ? WHERE id = ?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, a.getId());
			stmt.setDouble(2, a.getBalance());
			stmt.setInt(3, a.getId());
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("WE FAILED TO UPDATE THE ACCOUNT");
		}
		return true;
	}
	public boolean delete(BankAccount a) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "DELETE * FROM project0.ACCOUNTS WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs;
			stmt.setInt(1, a.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("WE FAILED TO DELETE THE ACCOUNT");
		}
		return true;
	}

}
