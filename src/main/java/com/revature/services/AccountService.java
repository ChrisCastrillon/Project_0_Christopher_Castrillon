package com.revature.services;

import java.util.Scanner;

import com.revature.dao.ApplicationDAO;
import com.revature.dao.BankAccountDAO;
import com.revature.dao.IApplicationDAO;
import com.revature.dao.IBankAccountDAO;
import com.revature.dao.IUserDAO;
import com.revature.dao.UserDAO;
import com.revature.models.Application;
import com.revature.models.BankAccount;
import com.revature.models.User;

public class AccountService implements IAccountService{
	private IUserDAO userDAO;
	private IBankAccountDAO accountDAO;
	private IApplicationDAO applicationDAO;
	private BankAccount bankAccount;
	private ApplicationService applicationService;
	
	public AccountService() {
		super();
		this.accountDAO = new BankAccountDAO();
	}
	public AccountService(IBankAccountDAO bankAccountDAO) {
		super();
		this.accountDAO = bankAccountDAO;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public BankAccount viewAnAccount() {
		accountDAO = new BankAccountDAO();
		applicationDAO = new ApplicationDAO();
		applicationService = new ApplicationService();
		bankAccount = new BankAccount();
		Application app;
		boolean fail = true;
		Scanner input = new Scanner(System.in);
		do {
			System.out.println("ENTER THE SOCIAL SECURITY NUMBER OF THE ACCOUNT HOLDER");
			int ssn = Math.abs(applicationService.validateSSN(input.nextInt()));
			app = applicationDAO.findBySocial(ssn);
			int id = app.getId();
			try {
				bankAccount = accountDAO.findById(id);
				System.out.println("CLIENT " + app.getFirstName() + " " + app.getLastName());
				System.out.println(bankAccount.toString());
				fail = false;
			}catch(NullPointerException e) {
				System.out.println("THERE IS NO ACCOUNT ASSOCIATED WITH THIS SOCIAL SECURTY NUMBER");
			}
		}while(fail);
		return bankAccount;
	}
	@Override
	public boolean deposit() {
		BankAccount acc = new BankAccount();
		acc = viewAnAccount();
		deposit(acc);
		return true;
	}
	@Override
	public boolean withdraw() {
		BankAccount acc = new BankAccount();
		acc = viewAnAccount();
		withdraw(acc);
		return true;
	}
	@Override
	public boolean transfer() {
		BankAccount acc = new BankAccount();
		acc = viewAnAccount();
		transfer(acc);
		return true;	
	}

	@Override
	public boolean deposit(BankAccount acc) {
		accountDAO = new BankAccountDAO();
		Scanner input = new Scanner(System.in);
		System.out.println("THE CURRENT BALANCE IS: " + acc.getBalance());
		System.out.println("ENTER THE AMOUNT THAT YOU WOULD LIKE DEPOSIT INTO YOUR ACCOUNT");
		double depositAmount = Math.abs(input.nextDouble());
		input.nextLine();
		System.out.println("THE OLD AMOUNT IS $" + acc.getBalance());
		double newAmount = acc.getBalance() + depositAmount;
		System.out.println("THE NEW AMOUNT IS $" + newAmount);
		acc.setBalance(newAmount);
		accountDAO.update(acc);
		return true;
	}
	public boolean withdraw(BankAccount acc) {
		accountDAO = new BankAccountDAO();
		Scanner input = new Scanner(System.in);
		System.out.println("YOUR CURRENT BALANCE IS: " + acc.getBalance());
		System.out.println("ENTER THE AMOUNT THAT YOU WOULD LIKE WITHDRAW INTO YOUR ACCOUNT");
		double withdrawlAmount = Math.abs(input.nextDouble());
		withdrawlAmount = validateWithdrawl(acc, withdrawlAmount);
		input.nextLine();
		System.out.println("THE OLD AMOUNT IS $" + acc.getBalance());
		double newAmount = acc.getBalance() - withdrawlAmount;
		System.out.println("THE NEW AMOUNT IS $" + newAmount);
		acc.setBalance(newAmount);
		accountDAO.update(acc);
		return true;
	}
	
	@Override
	public boolean applyForAnAccount(User u) {
		applicationService = new ApplicationService();
		applicationDAO = new ApplicationDAO();
		
		Scanner input = new Scanner(System.in);
		System.out.println("ENTER YOUR FIRST NAME: ");
		String firstName = input.nextLine();
		System.out.println("ENTER YOUR LAST NAME: ");
		String lastName = input.nextLine();
		System.out.println("ENTER YOUR SOCIAL SECURITY NUMBER WITH NO SPACES INBETWEEN THE NUMBER");
		int ssn = Math.abs(applicationService.validateSSN(input.nextInt()));
		System.out.println("ENTER YOUR SALARY $");
		double salary = Math.abs(applicationService.validateSalary(input.nextDouble()));
		
		Application a = new Application(u.getId(), firstName, lastName, ssn, salary, "pending");
		applicationDAO.insert(a);
		
		return true;
	}
	@Override
	public boolean transfer(BankAccount senderAcc) {
		userDAO = new UserDAO();
		accountDAO = new BankAccountDAO();
		Scanner input = new Scanner(System.in);
		System.out.println("ENTER THE USERNAME OF THE RECEIVER: ");	
		User receiver = userDAO.findByUsername(input.nextLine());
		BankAccount receiverAccount = accountDAO.findById(receiver.getId());
		System.out.println("ENTER THE AMOUNT THAT YOU WOULD LIKE TO SEND TO " + receiver.getUsername());
		double amount = Math.abs(validateWithdrawl(senderAcc, input.nextDouble()));
		input.nextLine();
		receiverAccount.setBalance(receiverAccount.getBalance() + amount);
		senderAcc.setBalance(senderAcc.getBalance() - amount);
		accountDAO.update(senderAcc);
		accountDAO.update(receiverAccount);
		System.out.println("TRANSFER COMPLETE");
		return true;
	}
	
	public double validateWithdrawl(BankAccount acc, double amount) {
		Scanner input = new Scanner(System.in);
		boolean fail = true;
		do {
			if (acc.getBalance() < amount) {
				System.out.println("YOU CANNOT OVERDRAW YOUR ACCOUNT");
				System.out.println("YOUR CURRENT BALANCE IS " + acc.getBalance());
				System.out.println("YOU ARE TRYING TO WITHDRAW " + amount);
				System.out.println("ENTER A NEW WITHDRAWL AMOUNT");
				amount = input.nextDouble();
			}
			else {
				fail = false;
			}
		}while(fail);
		return amount;
	}
	@Override
	public boolean createAnAccountFromApplication() {
		applicationService = new ApplicationService();
		accountDAO = new BankAccountDAO();
		Application app = applicationService.viewAnApplication();
		app = applicationService.editStatus(app);
		BankAccount acc = new BankAccount(app.getId(), 0);
		accountDAO.insert(acc);
		System.out.println(acc.toString());
		return true;
	}
	@Override
	public boolean closeAnAccount(BankAccount acc) {
		accountDAO = new BankAccountDAO();
		Scanner input = new Scanner(System.in);
		System.out.println("\nWARNING YOU WILL NOW PERMANAENTLY CLOSE THIS ACCOUNT");
		System.out.println("TO ABORT ENTER: N");
		System.out.println("TO CONTINUE ENTER: Y");
		String response = input.nextLine().toLowerCase();
		boolean fail = true;
		do {
			if ((!response.equals("y")) | (!response.equals("n"))) {
				System.out.println("INVALID INPUT ONLY ENTER Y or N");
				response = input.nextLine().toLowerCase();
			}
			else {
				fail = false;
			}
		}while(fail);
		
		switch(response) {
		case "y":
			accountDAO.delete(acc);
			break;
		case "n":
			System.out.println("YOU HAVE BEEN LOGGED OUT");
			System.exit(1);
			break;
		}
		
		
		
		return false;
	}
	
	
}
