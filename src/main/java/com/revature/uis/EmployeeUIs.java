package com.revature.uis;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.revature.models.User;
import com.revature.services.EmployeeService;
import com.revature.services.IEmployeeService;

public class EmployeeUIs {
	private final static String EMPLOYEE_SERVICE_MAIN_MENU = "files/EmployeeUIs/EmployeeUIMain";
	private static BufferedReader br = null;
	private static IEmployeeService employeeService;
	
	public static void employeeMainMenu(User u) throws IOException {
		employeeService = new EmployeeService();
		System.out.println("WELCOME TO THE BANKING APP " + u.getUsername() );
		try {
			br = new BufferedReader(new FileReader(EMPLOYEE_SERVICE_MAIN_MENU));
			String line = "";
			while((line = br.readLine()) != null) {
				System.out.println(line);
			}
			
		}finally {
			br.close();
		}
		employeeService.mainServiceMenuForEmployee(u);
	}
}
