package com.revature.services;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.revature.Driver;
import com.revature.dao.ApplicationDAO;
import com.revature.dao.BankAccountDAO;
import com.revature.dao.IApplicationDAO;
import com.revature.dao.IBankAccountDAO;
import com.revature.dao.IUserDAO;
import com.revature.dao.UserDAO;
import com.revature.models.Application;
import com.revature.models.BankAccount;
import com.revature.models.User;
import com.revature.uis.AdministratorUIs;

public class AdministratorService extends EmployeeService implements IAdministratorService{
	private IUserDAO userDAO;
	private IBankAccountDAO bankAccountDAO;
	private IApplicationDAO applicationDAO;
	private ApplicationService appService = new ApplicationService();
	private IAccountService accountService;
	private IUserService userService;
	
	public AdministratorService() {
		super();
		this.userDAO = new UserDAO();
		this.bankAccountDAO = new BankAccountDAO();
		this.applicationDAO = new ApplicationDAO();
	}
	//This is the constructor for mocking
	public AdministratorService(IUserDAO userDAO, IBankAccountDAO bankAccountDAO, IApplicationDAO applicationDAO) {
		super();
		this.userDAO = new UserDAO();
		this.bankAccountDAO = new BankAccountDAO();
		this.applicationDAO = new ApplicationDAO();
	}
	@Override
	public List<Application> getAllApplications(ApplicationDAO application) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Application editApplication(User u,Application app) throws InputMismatchException {
		Scanner input = new Scanner(System.in);
		System.out.println("YOU HAVE CHOSEN TO EDIT THE APPLICATION FOR " + app.getFirstName() + " " + app.getLastName());
		try {
			AdministratorUIs.editApplicationMenu();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String fullName = app.getFirstName() + " " + app.getLastName();
		int response = verifyEditApplicationMenuResponse();
		switch (response) {
		case 1: 
			System.out.println("YOU HAVE CHOSEN TO EDIT THE FIRST NAME FOR: " + fullName);
			app = appService.editFirstName(app);
			applicationDAO.update(app);
			break;
		case 2:
			System.out.println("YOU HAVE CHOSEN TO EDIT THE LAST NAME FOR: " + fullName);
			app = appService.editLastName(app);
			applicationDAO.update(app);
			break;
		case 3:
			System.out.println("YOU HAVE CHOSEN TO EDIT THE SALARY OF APPLICATION HOLDER: " + fullName);
			app = appService.editSalary(app);
			applicationDAO.update(app);
			
			break;
		case 4: 
			System.out.println("YOU HAVE CHOSEN TO EDIT THE SOCIAL SECURITY NUMBER OF: " + fullName);
			app = appService.editSocialSecurityNumber(app);
			applicationDAO.update(app);
			break;
		case 5:
			System.out.println("YOU HAVE CHOSEN TO EDIT THE STATUS OF THE APPLICATION OF HOLDER: " + fullName);
			app = appService.editStatus(app);
			applicationDAO.update(app);
			break;
		case 6:
			System.out.println("YOU HAVE CHOSEN TO EDIT THE USER ASSOCIATED WITH THE APPLICATION OF HOLDER: " + fullName);
//				app = appService.editUser(app);
			break;
		case 7:
			System.out.println("YOU HAVE BEEN LOGGED OUT");
			System.exit(1);
		}	
		return app;
	}
	@Override
	public boolean deleteApplication(ApplicationDAO applicationDAO) {
		
		return false;
	}
	
	@Override
	public boolean editUsername(UserDAO user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean editPassword(UserDAO user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean transfer(BankAccount... bankAccount) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean addAccountFromApplication(Application application) {
		
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean cancelAccount(BankAccount bankAccount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteUserByUsername(String Username) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void mainServiceMenuForAdministrator(User u) {
		appService = new ApplicationService();
		accountService = new AccountService();
		userService = new UserService();
		int response = verifyMainAdminMenuResponse();
	
		switch (response) {
		case 1:
			appService.viewAnApplication();
			break;
		case 2:
			editApplication(u,appService.viewAnApplication());
			break;
		case 3:
			accountService.createAnAccountFromApplication();
			break;
		case 4: 
			accountService.viewAnAccount();
			break;
		case 5: 
			accountService.deposit();
			break;
		case 6:
			accountService.withdraw();
			break;
		case 7:
			accountService.transfer();
			break;
		case 8:
			accountService.closeAnAccount(accountService.viewAnAccount());
			break;
		case 9:
			appService.viewAnApplication();
			userService.getAllUserInformation(accountService.viewAnAccount());
			break;
			
		case 10:
			System.out.println("YOU HAVE BEEN LOGGED OUT");
			System.exit(1);
		}
		System.out.println("\n\n\n");
		Driver.checkRole(u);
		
	}
	public int verifyEditApplicationMenuResponse() {
		Scanner input = new Scanner(System.in);
		int response = 0;
		boolean inputFail = true;
		do {
			try {
				response = Math.abs(input.nextInt());
				if ((response == 0) | (response > 7)) {
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
	public int verifyMainAdminMenuResponse() {
		Scanner input = new Scanner(System.in);
		int response = 0;
		boolean inputFail = true;
		do {
			try {
				response = Math.abs(input.nextInt());
				if ((response == 0 )|(response > 10)) {
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
