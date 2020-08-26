package com.revature.services;

import com.revature.models.BankAccount;
import com.revature.models.Role;
import com.revature.models.User;

public interface IUserService {
	// the Userservice should as if they are an employe
	public User login();
	public User register();
	public void mainServiceMenuWithAccount(User u);
	public void mainServiceMenuWithoutAccount(User u);
	public boolean getAllUserInformation(BankAccount acc);
	

}
