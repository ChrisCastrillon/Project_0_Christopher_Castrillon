package com.revature.services;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.revature.dao.ApplicationDAO;
import com.revature.dao.IApplicationDAO;
import com.revature.models.Application;

public class ApplicationService {
	
	private IApplicationDAO applicationDAO;
	
	public Application findById(int id) {
		applicationDAO = new ApplicationDAO();
		Application app = applicationDAO.findById(id);
		return app;
	}
	public Application viewAnApplication() {
		applicationDAO = new ApplicationDAO();
		Scanner input = new Scanner(System.in);
		System.out.println("ENTER THE SOCIAL SECURITY NUMBER ASSOCIATED WITH THE APPLICATION");
		int ssn = validateSSN(input.nextInt());
		Application app = applicationDAO.findBySocial(ssn);
		System.out.println(app.toString());
		return app;
	}
	public Application editFirstName(Application app) {
		Scanner input = new Scanner(System.in);
		System.out.println("ENTER THE NEW FIRST NAME");
		String newFirstName = input.next();
		Application newApp = new Application(app.getId(), newFirstName, app.getLastName(), app.getSocialSecurityNumber(), app.getSalary(), app.getStatus());
		System.out.println("FIRST NAME CHANGED SUCCESSFULLY");
		return newApp;
	}

	public Application editLastName(Application app) {
		Scanner input = new Scanner(System.in);
		System.out.println("ENTER THE NEW LAST NAME");
		String newLastName = input.next();
		Application newApp = new Application(app.getId(), app.getFirstName(), newLastName, app.getSocialSecurityNumber(), app.getSalary(), app.getStatus());
		System.out.println("LAST NAME CHANGED SUCCESSFULLY");
		return newApp;
	}
	public Application editSocialSecurityNumber(Application app) {
		Scanner input = new Scanner(System.in);
		System.out.println("ENTER THE NEW SOCIAL SECURITY NUMBER");
		int newSSN = 0;
		boolean fail = true;
		do {
			try {
				newSSN = Math.abs(input.nextInt());
				newSSN = validateSSN(newSSN);
				fail = false;
			}catch(InputMismatchException e) {
				System.out.println("YOU CAN ONLY ENTER NUMBERS AS THE SOCIAL SECURITY NUMBER");
				System.out.println("RE-ENTER THE SOCIAL SECURITY NUMBER");
			}
		}while(fail);
		Application newApp = new Application(app.getId(), app.getFirstName(), app.getLastName(), newSSN, app.getSalary(), app.getStatus());
		System.out.println("SOCIAL SECURITY NUMBER CHANGED SUCCESSFULLY");
		return newApp;
	}
	public Application editSalary(Application app) throws InputMismatchException {
		Scanner input = new Scanner(System.in);
		System.out.println("ENTER THE NEW SALARY");
		double newSalary = 0;
		boolean fail = true;
		do {
			try {
				newSalary = input.nextDouble();
				newSalary = validateSalary(newSalary);
				fail = false;
			}catch(InputMismatchException e) {
				System.out.println("YOU CAN ONLY ENTER NUMBERS, THE SALARY ENTERED CONTAINED NON-NUMBERS");
				input.nextLine();
			}
		}while(fail);
		Application newApp = new Application(app.getId(), app.getFirstName(), app.getLastName(), app.getSocialSecurityNumber(), Math.abs(newSalary), app.getStatus());
		System.out.println("SALARY CHANGED SUCCESSFULLY");
		return newApp;
	}
	public Application editStatus(Application app) {
		applicationDAO = new ApplicationDAO();
		Scanner input = new Scanner(System.in);
		System.out.println("ENTER THE NUMBER THAT CORRESPONDS TO THE TASK YOU ARE LOOKING TO COMPLETE:");
		System.out.println("1. CHANGE STATUS TO APPROVED");
		System.out.println("2. CHANGE STATUS TO DENIED");
		System.out.println("3. CHANGE STATUS TO PENDING");
		String newStatus = "";
		int response = verifyMenuResponse();
		switch (response) {
		case 1: 
			newStatus = "approved";
			break;
		case 2:
			newStatus = "denied";
			break;
		case 3:
			newStatus = "pending";
		}
		Application newApp = new Application(app.getId(), app.getFirstName(), app.getLastName(), app.getSocialSecurityNumber(), app.getSalary(), newStatus);
		applicationDAO.update(newApp);
		System.out.println("STATUS CHANGED SUCCESSFULLY");
		return newApp;
	}
	public double validateSalary(double salary) throws InputMismatchException {
		Scanner input = new Scanner(System.in);
		boolean fail = true;
		while (fail) {
			if (salary < 0 ) {
				System.out.println("THE SALARY ENTERED CANNOT BE A NEGATIVE NUMBER");
				System.out.println("RE-ENTER THE SALARY");
				boolean inputFail = true;
				do {
					try {
						salary = input.nextDouble();
						inputFail = false;
					}catch(InputMismatchException e) {
						System.out.println("YOU CAN ONLY ENTER NUMBERS, THE SALARY ENTERED CONTAINED NON-NUMBERS");	
						input.nextLine();
					}
				}while(inputFail);
			}
			else {
				fail = false;
			}
		}
		return salary;
	}
	public int validateSSN(int ssn) throws InputMismatchException {
		Scanner input = new Scanner(System.in);
		String ssnString = String.valueOf(ssn);
		int length = ssnString.length();
		boolean fail = true;
		while(fail) {
			if (length > 9) {
				System.out.println("THE SOCIAL SECURITY NUMBER ENTERED IS AN INCORRECT LENGTH");
				System.out.println("RE-ENTER A SOCIAL SECURITY NUMBER OF THE CORRECT LENGTH");
				boolean inputFail = true;
				do {
					try {
						ssn = Math.abs(input.nextInt());
						length = String.valueOf(ssn).length();
						inputFail = false;
					}catch(InputMismatchException e) {
						System.out.println("YOU CAN ONLY ENTER NUMBERS AS THE SOCIAL SECURITY NUMBER");
						System.out.println("RE-ENTER THE SOCIAL SECURITY NUMBER");
						input.nextLine();
					}
				}while(inputFail);
				
			}
			else {
				fail = false;
			}
		}
		return ssn;
	}
	public int verifyMenuResponse() {
		Scanner input = new Scanner(System.in);
		int response = 0;
		boolean inputFail = true;
		do {
			try {
				response = Math.abs(input.nextInt());
				inputFail = false;
			}catch(InputMismatchException e) {
				System.out.println("INVALID CHOICE, ONLY INPUT THE NUMBERS THAT CORRESPOND TO A MENU OPTION");
				input.nextLine();
			}
		}while(inputFail);
		return response;
	}
}
