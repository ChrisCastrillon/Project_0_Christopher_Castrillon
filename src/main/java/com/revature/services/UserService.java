package com.revature.services;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.revature.Driver;
import com.revature.dao.BankAccountDAO;
import com.revature.dao.IBankAccountDAO;
import com.revature.dao.IUserDAO;
import com.revature.dao.UserDAO;
import com.revature.models.BankAccount;
import com.revature.models.Role;
import com.revature.models.User;
import com.revature.uis.AccountUIs;

public class UserService implements IUserService {
	
	private IUserDAO userDAO;
	private IBankAccountDAO bankAccountDAO;
	private IAccountService accountService;
	
	public UserService() {
		super();
		this.userDAO = new UserDAO();
	}
	/**
	 * @param userDAO: takes in a mocked useDAO object for unit testing
	 */
	public UserService(IUserDAO userDAO) {
		super();
		this.userDAO = userDAO;
	}
	@Override
	public boolean getAllUserInformation(BankAccount acc) {
		userDAO = new UserDAO();
		int id = acc.getId();
		User user = userDAO.findById(id);
		System.out.println(user.toString());
		return false;
	}
	/**
	 * @param username, password, takes in string object to then pass to the UserDAO in order to access the account services
	 */
	public User login() {
		Scanner input = new Scanner(System.in);
		boolean fail = true;
		String username = "";
		String password = "";
		User u = new User();
		do {
			System.out.println("ENTER YOUR USERNAME");
			username = input.nextLine();
			System.out.println("ENTER YOUR PASSWORD: ");
			password = input.nextLine();
			if (userDAO.findByUsername(username) == null) {
				System.out.println("USERNAME NOT FOUND");
			}
			if (!(userDAO.findByUsername(username).getPassword().equals(password))) {
				System.out.println("YOU HAVE ENTERED AN INCORRECT PASSWORD");
			}
			else {
				u = userDAO.findByUsername(username);
				fail = false;
				
			}
		}while(fail);
		return u;
	}

	public User register() {
		Scanner input = new Scanner(System.in);
		System.out.println("ENTER YOUR USERNAME");
		String username = input.nextLine();
		username = validateUsername(username);
		System.out.println("ENTER YOUR PASSWORD: YOUR PASSWORD MUST CONTAIN A COMBINATION OF NUMBERS AND LETTERS");
		String password = input.nextLine();
		password = validatePassword(password);
		
		User u = new User(0,username, password, Role.Client);
		int new_id = userDAO.insert(u);
		
		if (new_id == 0) {
			return null;
		}
		u.setId(new_id);
		return u;
	}
	public String validateUsername(String username) {
		Scanner input = new Scanner(System.in);
		boolean fail = true;
		while(fail) {
			if (userDAO.findByUsername(username) != null) {
				System.out.println("THE USERNAME ALREADY EXISTS, REENTER A USERNAME");
				username = input.nextLine();
				System.out.println(username);
			}
			else {
				fail = false;
				return username;
			}
		}
		return username;
	}
	public String validatePassword(String password) {
		Scanner input = new Scanner(System.in);
		char[] passwordArray = password.toCharArray();
		int count = 0;
		boolean fail = true;
		do{
			for (int i = 0; i < passwordArray.length; i++) {
				System.out.println(Character.getNumericValue(passwordArray[i]));
				if ((Character.getNumericValue(passwordArray[i]) >= 0) && (Character.getNumericValue(passwordArray[i]) <= 9)) {
					count++;
				}
			}
			if (count == 0 | (passwordArray.length < 8)) {
				System.out.println("THE PASSWORD MUST CONTAINS LETTERS AND NUMBERS, AND BE AT LEAST 8 CHARACTERS LONG");
				System.out.println("ENTER A NEW PASSWORD: ");
				password = input.nextLine();
				passwordArray = password.toCharArray();
			}
			else {
				fail = false;
			}
		}while(fail);
		return password;
	}
	@Override
	public void mainServiceMenuWithAccount(User u) {
		accountService = new AccountService();
		bankAccountDAO = new BankAccountDAO();
		BankAccount acc = bankAccountDAO.findById(u.getId());
		int response = verifyMenuResponse();
		boolean success;
		
		switch(response) {
		case 1:
			success = accountService.deposit(acc);
			if (success) {
				System.out.println("DEPOSIT COMPLETED SUCCESSFULLY");
			}
			break;
		case 2:
			success = accountService.withdraw(acc);
			if (success) {
				System.out.println("WITHDRAWL COMPLETED SUCCESSFULLY");
			}
			break;
		case 3:
			success = accountService.transfer(acc);
			if (success) {
				System.out.println("TRANSFER COMPLETED SUCCESSFULLY");
			}
			
			break;
		}
		Driver.checkRole(u);
	}
	@Override
	public void mainServiceMenuWithoutAccount(User u) {
		accountService = new AccountService();
		int response = verifyMenuResponse();
		boolean success;
		
		switch(response) {
		case 1:
			AccountUIs accountUI = new AccountUIs();
			try {
				accountUI.createApplicationInstruction();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			success = accountService.applyForAnAccount(u);
			if (success) {
				System.out.println("APPLICATION CREATED SUCCESSFULLY");
			}
			break;
		case 2:
			System.exit(1);
		}
		
	}
	public int verifyMenuResponse() {
		Scanner input = new Scanner(System.in);
		int response = 0;
		boolean inputFail = true;
		do {
			try {
				response = Math.abs(input.nextInt());
				if (response > 3) {
					System.out.println("INVALID CHOICE, ONLY INPUT THE NUMBERS THAT CORRESPOND TO A MENU OPTION");
					input.nextLine();
				}
				else {
					inputFail = false;
				}
			}catch(InputMismatchException e) {
				System.out.println("INVALID CHOICE, ONLY INPUT THE NUMBERS THAT CORRESPOND TO A MENU OPTION");
				input.nextLine();
			}
		}while(inputFail);
		return response;
	}
	

}
