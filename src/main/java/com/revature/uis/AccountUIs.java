package com.revature.uis;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class AccountUIs {
	private final static String USER_SERVICE_ACCOUNT_MENU = "files/EmployeeUIs/UserServiceAccountMenu"; 
	private final static String CREATE_APPLICATION_INSTRUCTIONS = "files/AccountUIs/AccountUICreateAccount";
	private static BufferedReader br = null;
	
	public static void createApplicationInstruction() throws IOException {
		try {
			br = new BufferedReader(new FileReader(CREATE_APPLICATION_INSTRUCTIONS));
			String line = "";
			while((line = br.readLine()) != null) {
				System.out.println(line);
			}
		}finally {
			br.close();
		}
	}
	
}
