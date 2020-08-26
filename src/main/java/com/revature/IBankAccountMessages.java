package com.revature;

import java.io.IOException;

public interface IBankAccountMessages {
	
	public void askCreateAccount();
	public void createAccountInstructions() throws IOException;
	public void createAccountSuccess();
	
}
