package com.revature.services;

import java.util.InputMismatchException;
import java.util.List;

import com.revature.dao.ApplicationDAO;
import com.revature.dao.BankAccountDAO;
import com.revature.dao.UserDAO;
import com.revature.models.Application;
import com.revature.models.BankAccount;
import com.revature.models.User;

public interface IAdministratorService extends IEmployeeService {
	public List<Application> getAllApplications(ApplicationDAO application);
	public Application editApplication(User u, Application application);
	public boolean deleteApplication(ApplicationDAO applicationDAO);
	public boolean editUsername(UserDAO user);
	public boolean editPassword(UserDAO user);
	public boolean deleteUserByUsername(String Username);
	public boolean transfer(BankAccount...bankAccount);
	public boolean addAccountFromApplication(Application application);
	public boolean cancelAccount(BankAccount bankAccount);
	public void mainServiceMenuForAdministrator(User u);
}
