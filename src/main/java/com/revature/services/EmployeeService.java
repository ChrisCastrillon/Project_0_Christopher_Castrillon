package com.revature.services;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.revature.Driver;
import com.revature.dao.ApplicationDAO;
import com.revature.dao.BankAccountDAO;
import com.revature.dao.IApplicationDAO;
import com.revature.dao.IBankAccountDAO;
import com.revature.dao.IUserDAO;
import com.revature.dao.UserDAO;
import com.revature.models.Application;
import com.revature.models.User;
import com.revature.uis.EmployeeUIs;

public class EmployeeService implements IEmployeeService{

	private IUserDAO userDAO;
	private IBankAccountDAO bankAccountDAO;
	private IApplicationDAO applicationDAO;
	private ApplicationService appService;
	private IAccountService accountService;
	private IUserService userService;
	
	public EmployeeService() {
		super();
		this.userDAO = new UserDAO();
		this.bankAccountDAO = new BankAccountDAO();
		this.applicationDAO = new ApplicationDAO();
	}
	public String validateApplication() {
		// TODO Auto-generated method stub
		return null;
	}

	public String viewAccountInformation() {
		// TODO Auto-generated method stub
		return null;
	}

	public String viewUserInformation() {
		// TODO Auto-generated method stub
		return null;
	}

	public String viewAllInformation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double ValidateSalary(double salary) throws InputMismatchException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int validateSSN(int ssn) throws InputMismatchException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int validateResponseForEmployeeMenu() {
		Scanner input = new Scanner(System.in);
		int response = 0;
		boolean inputFail = true;
		do {
			try {
				response = Math.abs(input.nextInt());
				if ((response == 0 )|(response > 6)) {
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

	@Override
	public void mainServiceMenuForEmployee(User u) {
		IUserService userService = new UserService();
		ApplicationService appService = new ApplicationService();
		IAccountService accountService = new AccountService();
		
		int response = validateResponseForEmployeeMenu();
		switch(response) {
		case 1:
			userService.getAllUserInformation(accountService.viewAnAccount());
			break;
		case 2:
			appService.viewAnApplication();
			break;
		case 3:
			accountService.viewAnAccount();
			break;
		case 4:
			appService.viewAnApplication();
			userService.getAllUserInformation(accountService.viewAnAccount());
			break;
		case 5:
			Application app = new Application();
			app = appService.viewAnApplication();
			appService.editStatus(app);
			applicationDAO.update(app);
			break;
		case 6: 
			System.out.println("YOU HAVE BEEN LOGGED OUT");
			System.exit(1);
			break;
		}
		System.out.println("\n\n\n");
		Driver.checkRole(u);
	}
	
}
