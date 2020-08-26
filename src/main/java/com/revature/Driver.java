package com.revature;
import java.io.IOException;
import java.util.Scanner;

import com.revature.models.User;
import com.revature.services.AdministratorService;
import com.revature.services.EmployeeService;
import com.revature.services.IAdministratorService;
import com.revature.services.IEmployeeService;
import com.revature.services.IUserService;
import com.revature.services.UserService;
import com.revature.uis.AdministratorUIs;
import com.revature.uis.EmployeeUIs;
import com.revature.uis.MainScreenUIs;
import com.revature.uis.UserUIs;

public class Driver {
	public static MainScreenUIs ms = new MainScreenUIs();
	public static UserUIs userUI = new UserUIs();
	public static IUserService userService = new UserService();
	public static IEmployeeService employeeService = new EmployeeService();
	public static IAdministratorService adminService = new AdministratorService();
	
	public static void main(String[] args) {
		welcomeScreen();
	
	}
	public static void welcomeScreen() {
		try {
			ms.getWelcomeScreen();
			welcomeMessageInput();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void welcomeMessageInput() {
		Scanner input = new Scanner(System.in);	
		boolean fail = false;
		do {
			String choice = input.nextLine();
			if (choice.toLowerCase().equals("c")) {
				userUI.registrationMessage();
				userService.register();
			}
			else if (choice.toLowerCase().equals("l")) {
				User u = userService.login();
				checkRole(u);
			}
			else {
				System.out.println("YOU HAVE TO ENTER EITHER c OR l");
				fail = true;
			}
		}while(fail);
	}
	public static void checkRole(User u) {
		switch(u.getRole()) {
		case Client:
			try {
				UserUIs.userWelcomeMessage(u);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case Employee:
			try {
				EmployeeUIs.employeeMainMenu(u);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case Admin:
			try {
				AdministratorUIs.adminMainMenu(u);
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;	
		}
	}

}
