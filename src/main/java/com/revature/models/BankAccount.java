package com.revature.models;

import java.util.Objects;

public class BankAccount {
	private int id;
	private double balance;

	public BankAccount() {
		super();
	}
	public BankAccount(int id, double balance) {
		super();
		this.id = id;
		this.balance = balance;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return this.id;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public double getBalance() {
		return this.balance;
	}
	@Override
	public String toString() {
		return "THE FOLLOWING INFORMATION FOR THE BANK ACCOUNT:\n"
				+ "ID: " + id + "\n"
				+ "BALANCE: $" + balance + "\n";
	}
	@Override
	public int hashCode() {
		return Objects.hash(balance, id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof BankAccount)) {
			return false;
		}
		BankAccount other = (BankAccount) obj;
		return Double.doubleToLongBits(balance) == Double.doubleToLongBits(other.balance) && id == other.id;
	}
	

	
	
}
