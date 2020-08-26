package com.revature.services;

import java.util.InputMismatchException;

import com.revature.models.User;

public interface IEmployeeService {
	public String validateApplication();
	public String viewAccountInformation();
	public String viewUserInformation();
	public String viewAllInformation();
	public double ValidateSalary(double salary) throws InputMismatchException;
	public int validateSSN(int ssn) throws InputMismatchException;
	public void mainServiceMenuForEmployee(User u);
	public int validateResponseForEmployeeMenu();
}
