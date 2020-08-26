package com.revature.dao;

import java.util.List;

import com.revature.models.BankAccount;

public interface IBankAccountDAO {
	public List<BankAccount> findAll();
	public BankAccount findById(int id);
	public int insert(BankAccount a);
	public boolean update(BankAccount a);
	public boolean delete(BankAccount a);//targetBankAccount refers to
}
