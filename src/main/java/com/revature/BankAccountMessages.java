package com.revature;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BankAccountMessages implements IBankAccountMessages {
	
	private final String CREATE_ACCOUNT_INSTRUCTION = "files/CREATE_ACCOUNT_INSTRUCTION";
	private BufferedReader br = null;

	public void askCreateAccount() {
		System.out.println("Would you like to create an account?");
		
	}

	public void createAccountSuccess() {
		// TODO Auto-generated method stub
		
	}

	public void createAccountInstructions() throws IOException{
		try {
			br = new BufferedReader(new FileReader(CREATE_ACCOUNT_INSTRUCTION));
			String line = "";
			while((line = br.readLine()) != null) {
				System.out.println(line);
			}
			
		}finally {
			br.close();
		}
		
	}

}
