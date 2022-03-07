package com.training.pms.dao;

import java.util.List;

import com.training.pms.model.Account;
import com.training.pms.model.Login;

public interface AccountDAO {
	public boolean addMoreBankAccount(String username, String password);
	public boolean withdrawFromAccount(Account account,int amount);
	public boolean transferToOtherAccount(Account account,int otheraccountid,int amount);
	public boolean depositToAccount(Account account,int amount);
	
	//public Account addBankAccount(String accountname, String password);
    public List<Account> searchAccountByCustomerNamePassword(String username, String password);
   public Account searchAccountByCustomerNamePassword_02(String accountname, String password);
}
