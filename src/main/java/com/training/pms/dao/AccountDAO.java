package com.training.pms.dao;

import java.util.List;

import com.training.pms.model.Account;
import com.training.pms.model.Login;

public interface AccountDAO {
	public boolean addMoreBankAccount(String username, String password);
	public boolean withdrawFromAccount(Account account,double amount);
	public boolean transferToOtherAccount(Account account,int otheraccountid, double amount);
	public boolean depositToAccount(Account account,double amount);
	public Account searchAccountByCustomerNamePassword(String accountname, String password);
	//public Account addBankAccount(String accountname, String password);
     //public List<Account> searchAccountByCustomerNamePassword(String username, String password);
}
