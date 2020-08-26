package com.revature.dao;
import java.util.List;

import com.revature.models.User;

public interface IUserDAO {
	
	public List<User> findAll();
	public User findByUsername(String username);
	public User findById(int id);
	//boolean t see if it was sucessful or not?
	public int insert(User u);
	//boolean to determine if delete worked! 
	public boolean deleteByUsername(String username);
	public boolean update(User u);
	
	/*
	 * There are many different RDBS vendors, we may have a 
	 * dao specific to different venders. Each of the sql dialects cna 
	 * be a different implementation of the same interface.
	 * We dont actually care what databse vendor we use, we just
	 * care that we can objtain our usres
	 */

}

	


