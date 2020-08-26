package com.revature.dao;

import java.util.List;

import com.revature.models.Application;

public interface IApplicationDAO {
	
	public List<Application> findAll();
	public Application findById(int userId);
	public boolean insert(Application app);
	public boolean update(Application app);
	public boolean delete(int userId);
	public Application findBySocial(int ssn);
	

}
