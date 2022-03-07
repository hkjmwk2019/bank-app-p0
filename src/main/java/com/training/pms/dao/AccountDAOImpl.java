package com.training.pms.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.training.pms.model.Account;
import com.training.pms.model.Login;
import com.training.pms.utility.DBConnection;

public class AccountDAOImpl implements AccountDAO {
	 
	Connection connection=DBConnection.getConnection();
	 
	@Override
	public List<Account>  searchAccountByCustomerNamePassword(String accountname, String password) {
	 //public Account  searchAccountByCustomerNamePassword(String accountname, String password) {
		
		Connection con = DBConnection.getConnection();
		//System.out.println("## searching customer all accounts with accountname and passowrd... " );
		
		//System.out.println(accountname);
		List <Account> accounts= new ArrayList<Account>();
		
		Account account2= new Account();
		PreparedStatement stat;
		
		try {
			stat = con.prepareStatement("select * from accounts where name = ? and passwords = ?; ");
			stat.setString(1, accountname);
			stat.setString(2, password);
			
			ResultSet res = stat.executeQuery();	
			
			while(res.next()) {
				//Login login2 = new Login();
				
				account2.setId(res.getInt(1));
				account2.setAccountName(res.getString(2));
				account2.setPassword(res.getString(3));
				account2.setBalance(res.getDouble(4));
				accounts.add(account2);
				}
			
			res.close();
			stat.close();
			con.close();
			
			
			

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return accounts;
		
	}
	@Override
	public Account  searchAccountByCustomerNamePassword_02(String accountname, String password) {
	 //public Account  searchAccountByCustomerNamePassword(String accountname, String password) {
		
		Connection con = DBConnection.getConnection();
		//System.out.println("## searching customer all accounts with accountname and passowrd... " );
		
		//System.out.println(accountname);
		List <Account> accounts= new ArrayList<Account>();
		
		Account account2= new Account();
		PreparedStatement stat;
		
		try {
			stat = con.prepareStatement("select * from accounts where name = ? and passwords = ?; ");
			stat.setString(1, accountname);
			stat.setString(2, password);
			
			ResultSet res = stat.executeQuery();	
			
			while(res.next()) {
				//Login login2 = new Login();
				
				account2.setId(res.getInt(1));
				account2.setAccountName(res.getString(2));
				account2.setPassword(res.getString(3));
				account2.setBalance(res.getDouble(4));
				accounts.add(account2);
				}
			
			res.close();
			stat.close();
			con.close();
			
			
			

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return account2;
		
	}


	@Override
	public boolean addMoreBankAccount(String username, String password) {
		
		Connection con = DBConnection.getConnection();
		//int userstatus=0;
		System.out.println("## Adding more bankaccount for customer :" + username);
		
		int rows=0;
		Account account2= new Account();
		//List<Account> customers = new ArrayList<Account>();
		PreparedStatement stat;
		
		try {
			
		    // select * ...
			stat = con.prepareStatement("insert into accounts values(default,?,?, 0 );");
			stat.setString(1, username);
			stat.setString(2, password);
			
			rows =stat.executeUpdate();
			
			stat.close();
			con.close();

			//ResultSet res = stat.executeQuery();
			
			//while(res.next()) {
				//Login login2 = new Login();
				
			//	account2.setLoginId(res.getInt(1));
			
			//	account2.setUsername(res.getString(2));
			//	account2.setPassword(res.getString(3));
			//	account2.setUserOrEmploy(res.getInt(4));
			//	}
			
			//res.close();
			stat.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (rows == 0){
			System.out.println("Fails to update the bank customer status.");
			return false;
		}
		else {
			System.out.println("Success to update the bank customer status");
			return true;
		}
		
	}

	@Override
	public boolean withdrawFromAccount(Account account, int amount) {
		
		Connection con = DBConnection.getConnection();
		
		
		// get customer's own account Id
		int sender=  (int) account.getId();
		int receiver= (int)account.getId();
	   // int tamount= (int) amount;
		
		CallableStatement stat;
	
		try {
			stat = con.prepareCall("call withdraw(?,?)");
			stat.setInt(1, sender);
			//stat.setInt(2,receiver);
			stat.setInt(2,amount);

			stat.execute();
			
			stat.close();
			con.close();
			System.out.println("Withdraw done/completed");
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	
	}
	
	@Override
	public boolean depositToAccount(Account account,int amount) {
		
		Connection con = DBConnection.getConnection();
		
		
		// get customer's own account Id
		//int sender=  (int) account.getId();
		System.out.println("## " + account);
		int receiver= (int)account.getId();
	   // int tamount= (int) amount;
		
		CallableStatement stat;
	
		try {
			stat = con.prepareCall("call deposit(?,?)");
			//stat.setInt(1, sender);
			stat.setInt(1,receiver);
			stat.setInt(2,amount);
			

			stat.execute();
			
			stat.close();
			con.close();
			System.out.println("Deposit done/completed");
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
		
	}
	
	@Override
	public boolean transferToOtherAccount(Account account,int otheraccountid, int amount) {
		

		Connection con = DBConnection.getConnection();
		
		
		// get customer's own account Id
		int sender=  (int) account.getId();
		int receiver= otheraccountid;
	   // int tamount= (int) amount;
	    CallableStatement stat;
		
		try {
			stat = con.prepareCall("call transfer(?,?,?)");
			stat.setInt(1, sender);
			stat.setInt(2,receiver);
			stat.setInt(3,amount);

			stat.execute();
			
			stat.close();
			con.close();
			System.out.println("transfer done/completed");
			
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	    	
	}

	
	
	//public Account addBankAccount(String accountname, String password) {
		
//	}

}
