package com.revature.uis;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.revature.models.User;
import com.revature.services.AdministratorService;
import com.revature.services.IAdministratorService;

public class AdministratorUIs {
	private final static String ADMIN_SERVICE_EDIT_APPLICATION_MENU = "files/EmployeeUIs/AdministratorServiceEditApplicationMenu";
	private final static String ADMIN_SERVICE_MAIN_MENU = "files/EmployeeUIs/AdministratorUIMain";
	private static BufferedReader br = null;
	private static IAdministratorService administratorService;
	
	public static void editApplicationMenu() throws IOException {
		try {
			br = new BufferedReader(new FileReader(ADMIN_SERVICE_EDIT_APPLICATION_MENU));
			String line = "";
			while((line = br.readLine()) != null) {
				System.out.println(line);
			}
			
		}finally {
			br.close();
		}
	}
	public static void adminMainMenu(User u) throws IOException {
		administratorService = new AdministratorService();
		System.out.println("WELCOME TO THE BANKING APP " + u.getUsername());
		try {
			br = new BufferedReader(new FileReader(ADMIN_SERVICE_MAIN_MENU));
			String line = "";
			while((line = br.readLine()) != null) {
				System.out.println(line);
			}
		}finally {
			br.close();
		}
		administratorService.mainServiceMenuForAdministrator(u);
		
	}

}
