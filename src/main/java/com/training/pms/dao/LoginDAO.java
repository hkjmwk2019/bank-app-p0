package com.training.pms.dao;

import java.util.List;

import com.training.pms.model.Login;

public interface LoginDAO {
	public boolean register(Login login);
	public boolean validate(String username, String password);
	public Login checkuserstatus(String username, String password);
	public  boolean bankapplicant(String username, String password);
	public Login approveOrDeny(int applicantstatus);
	public  boolean deleteLoginRecord(Login login);
	public  boolean updateLoginStatus(Login login);
	public boolean addBankAccount(Login login);
	
}
