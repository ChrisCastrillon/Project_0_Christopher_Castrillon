package com.revature.uis;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.revature.dao.BankAccountDAO;
import com.revature.dao.IBankAccountDAO;
import com.revature.models.User;
import com.revature.services.IUserService;
import com.revature.services.UserService;

public class UserUIs {
	private final static String USER_SERVICE_ACCOUNT_MENU = "files/AccountUIs/UserServiceAccountMenu"; 
	private final static String USER_SERVICE_NO_ACCOUNT_MENU = "files/AccountUIs/UserServiceNoAccountMenu";
	private static BufferedReader br = null;
	private static IBankAccountDAO bankAccountDAO;
	private static IUserService userService;

	
	
	
	public static void userWelcomeMessage(User u) throws IOException {
		bankAccountDAO = new BankAccountDAO();
		userService = new UserService();
		
		System.out.println("\nWELCOME TO THE BANKING APP " + u.getUsername());
		if (bankAccountDAO.findById(u.getId()) != null) {
			try {
				br = new BufferedReader(new FileReader(USER_SERVICE_ACCOUNT_MENU));
				String line = "";
				while((line = br.readLine()) != null) {
					System.out.println(line);
				}
			}finally {
				br.close();
			}
			userService.mainServiceMenuWithAccount(u);
		}
		else {
			try {
				br = new BufferedReader(new FileReader(USER_SERVICE_NO_ACCOUNT_MENU));
				String line = "";
				while((line = br.readLine()) != null) {
					System.out.println(line);
				}
				
				
			}finally {
				br.close();
			}
			userService.mainServiceMenuWithoutAccount(u);
		}
	}
	

	public void registrationMessage() {
		System.out.println("IN ORDER TO CREATE AN ACCOUNT, YOU NEED A USERNAME AND A PASSWORD");
	}
	

}
