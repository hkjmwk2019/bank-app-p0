package com.training.pms.model;

import java.util.Objects;

public class Account {
	private int id;
	private String accountName;
	private String password;
	private double balance;
	
	public Account() {
		super();
	}
	
	public Account(int id, String accountName, String password, double balance) {
		super();
		this.id = id;
		this.accountName = accountName;
		this.password = password;
		this.balance = balance;
	}

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public int hashCode() {
		return Objects.hash(accountName, balance, id, password);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		return Objects.equals(accountName, other.accountName)
				&& Double.doubleToLongBits(balance) == Double.doubleToLongBits(other.balance) && id == other.id
				&& Objects.equals(password, other.password);
	}

	@Override
	public String toString() {
		//return "Accounts [id=" + id + ", accountName=" + accountName + ", password=" + password + ", balance=" + balance
		//		+ "]";
		
		return "Accounts [id=" + id + ", accountName=" + accountName +  ", balance=" + balance + "]" ;
		
	}
	
	

}
