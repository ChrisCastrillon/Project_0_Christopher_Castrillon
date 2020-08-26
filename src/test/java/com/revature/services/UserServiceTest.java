package com.revature.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.revature.dao.IUserDAO;
import com.revature.models.Role;
import com.revature.models.User;

public class UserServiceTest {
	
	@Mock
	private IUserDAO mockedDAO;
	/**
	 * add a UserService object calling the constructor that takes in the mocked DAO
	 */
	private UserService testInstance = new UserService(mockedDAO);
	private User chris;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		testInstance = new UserService(mockedDAO);
		chris = new User(1, "ccastrillon","password",Role.Admin);
		
		when(mockedDAO.findByUsername("ccastrillon")).thenReturn(chris);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testLogin() {
		
		
	}
	@Test
	public void testLoginSuccessful() {
		/*
		 * I am asserting that when if I were to login successfully, that I would return a userObject 
		 * that corresponds to that login.
		 */
		
	}

	@Test
	public void testRegister() {
		fail("Not yet implemented");
	}

}
