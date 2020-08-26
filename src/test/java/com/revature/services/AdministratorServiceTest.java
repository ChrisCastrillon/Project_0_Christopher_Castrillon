package com.revature.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.revature.dao.IApplicationDAO;
import com.revature.dao.IBankAccountDAO;
import com.revature.dao.IUserDAO;
import com.revature.models.Application;
import com.revature.models.BankAccount;
import com.revature.models.Role;
import com.revature.models.User;

public class AdministratorServiceTest {
	@Mock
	private IUserDAO mockedUserDAO;
	@Mock
	private IApplicationDAO mockedApplicationDAO;
	@Mock
	private IBankAccountDAO mockedBankAccountDAO;
	
	private AdministratorService testInstance = new AdministratorService(mockedUserDAO, mockedBankAccountDAO, mockedApplicationDAO);
	private User chris;
	private Application chrisApp;
	private Application updatedChrisApp;
	private BankAccount chrisAccount;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		testInstance = new AdministratorService(mockedUserDAO, mockedBankAccountDAO, mockedApplicationDAO);
		chris = new User(1,"ccastrillon","password",Role.Admin);
		chrisApp = new Application(1,"Christopher","Castrillon", 111111111, 55000,"pending");
		updatedChrisApp = new Application(1, "Christopher", "Castrillon", 111111111, 55000, "approved");
		chrisAccount = new BankAccount(1,1000);
		when(mockedApplicationDAO.findBySocial(111111111)).thenReturn(chrisApp);
	}
	
	

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetAllApplications() {
		
	}

	@Test
	public void testEditApplication() {
		assertEquals(testInstance.editApplication(chris,chrisApp),updatedChrisApp);
		
		
	}

	@Test
	public void testDeleteApplication() {
	
	}

	@Test
	public void testEditUsername() {
		
	}

	@Test
	public void testEditPassword() {
		
	}

	@Test
	public void testTransfer() {
		
	}

	@Test
	public void testAddAccountFromApplication() {
		
	}

	@Test
	public void testCancelAccount() {
	
	}

	@Test
	public void testDeleteUserByUsername() {
		
	}

	@Test
	public void testValidateApplication() {

	}
		
		//because you are accepting only int values, the ssn in onyl going to be an int value
		//so the validation should occurs before  ValidateAPP is called to make the (Edited) application
//		int ssn = chrisApp.getSocialSecurityNumber();
//		Scanner input = new Scanner(System.in);
//		String ssnString = String.valueOf(ssn);
//		int length = ssnString.length();
//		boolean pass = false;
//		while(!pass) {
//			if (length < 9 | length > 9) {
//				System.out.println("THE SOCIAL SECURITY NUMBER ENTERED IS AN INCORRECT LENGTH");
//				System.out.println("RE-ENTER A SOCIAL SECURITY NUMBER OF THE CORRECT LENGTH");
//				boolean inputPass = false;
//				while(!inputPass)
//					try {
//						ssn = input.nextInt();
//						length = String.valueOf(ssn).length();
//						inputPass = true;
//					}catch(InputMismatchException e) {
//						System.out.println("YOU CAN ONLY ENTER NUMBERS AS THE SOCIAL SECURITY NUMBER");
//					}
//			}
//			else {
//				pass = true;
//			}
//		}
//		return ssn;

	@Test
	public void testValidateSalary() {
//		double salary = chrisApp.getSalary();
//		Scanner input = new Scanner(System.in);
//		boolean pass = false;
//		while (!pass) {
//			if (salary < 0 ) {
//				System.out.println("THE SALARY ENTERED CANNOT BE A NEGATIVE NUMBER");
//				System.out.println("RE-ENTER THE SALARY");
//				boolean inputPass = false;
//				while(!inputPass)
//					try {
//						salary = input.nextDouble();
//						inputPass = true;
//					}catch(InputMismatchException e) {
//						System.out.println("YOU CAN ONLY ENTER NUMBERS, THE SALARY ENTERED CONTAINED NON-NUMBERS");	
//					}
//			}
//			else {
//				pass = true;
//			}
//		}
//		return salary;
	}

	@Test
	public void testViewAccountInformation() {
		
	}

	@Test
	public void testViewUserInformation() {
	
	}

	@Test
	public void testViewAllInformation() {
		
	}
	

}
