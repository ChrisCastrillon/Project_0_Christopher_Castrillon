package com.revature.services;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.revature.dao.IBankAccountDAO;
import com.revature.models.Application;
import com.revature.models.BankAccount;

public class AccountServiceTest {
	@Mock
	private IBankAccountDAO mockedDAO;
	
	private AccountService testInstance = new AccountService(mockedDAO);
	private BankAccount chrisAcc;
	private Application chrisApp;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		testInstance = new AccountService(mockedDAO);
		chrisAcc = new BankAccount(1, 25000.0);
		chrisApp = new Application(1,"chris","c",123456789,55000,"pending");
		when(mockedDAO.findById(1)).thenReturn(chrisAcc);
		when(mockedDAO.delete(chrisAcc)).thenReturn(true);
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testViewAnAccount() {
//		fail("Not yet implemented");
	}

	@Test
	public void testDeposit() {
//		fail("Not yet implemented");
	}

	@Test
	public void testWithdraw() {
//		fail("Not yet implemented");
	}

	@Test
	public void testTransfer() {
//		fail("Not yet implemented");
	}

	@Test
	public void testDepositBankAccount() {
//		fail("Not yet implemented");
	}

	@Test
	public void testWithdrawBankAccount() {
//		fail("Not yet implemented");
	}

	@Test
	public void testApplyForAnAccount() {
//		fail("Not yet implemented");
	}

	@Test
	public void testTransferBankAccount() {
//		fail("Not yet implemented");
	}

	@Test
	public void testValidateWithdrawl() {
//		fail("Not yet implemented");
	}

	@Test
	public void testCreateAnAccountFromApplication() {
//		fail("Not yet implemented");
	}

	@Test
	public void testCloseAnAccount() {
		//assertTrue(mockedDAO.delete(chrisAcc));
		//assertFalse(mockedDAO.delete());
		
	}

}
