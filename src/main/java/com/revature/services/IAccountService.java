package com.revature.services;

import com.revature.models.BankAccount;
import com.revature.models.User;

public interface IAccountService {
	public boolean deposit();
	public boolean withdraw();
	public boolean transfer();
	public boolean deposit(BankAccount acc);
	public boolean withdraw(BankAccount acc);
	public boolean transfer(BankAccount senderAcc);
	public boolean applyForAnAccount(User u);
	public BankAccount viewAnAccount();
	public boolean createAnAccountFromApplication();
	public boolean closeAnAccount(BankAccount acc);
	
}
