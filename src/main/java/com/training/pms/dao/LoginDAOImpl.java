package com.training.pms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.training.pms.model.Login;

import com.training.pms.utility.DBConnection;

public class LoginDAOImpl implements LoginDAO {

	Connection connection= DBConnection.getConnection();
	
	@Override
	public boolean register(Login login) {
		
		Connection con=DBConnection.getConnection();
		
		System.out.println("## Adding user :" + login);
		PreparedStatement statement = null;
		int rows = 0;
		try {
			statement = con.prepareStatement("insert into login values(default,?,?,?)");
			statement.setString(1, login.getUsername());
			statement.setString(2, login.getPassword());
			statement.setInt(3, login.getUserOrEmploy());

			rows = statement.executeUpdate();
			//System.out.println(rows + " Customer's application is processing ... ");
				
			
			statement.close();
			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (rows == 0)
			return false;
		else			
			return true;
		
	}
	

	@Override
	public boolean validate(String username, String password) {
		
		Connection con = DBConnection.getConnection();
		boolean userValid = false;
		PreparedStatement stat;
		try {
			stat = con.prepareStatement("select * from login where username = ? and passwords = ? ");
			stat.setString(1, username);
			stat.setString(2, password);
			
			ResultSet res = stat.executeQuery();	
			userValid = res.next();
			
			res.close();
			stat.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// if can find the user and matched password, then userValid is true, otherwise false
		return userValid;
	}


	@Override
	public Login checkuserstatus(String username, String password) {
		
		Connection con = DBConnection.getConnection();
		
		//int userstatus=0;
		System.out.println("## searching user with longin name and passowrd... " );
		System.out.println(username);
		
		Login login2= new Login();
		PreparedStatement stat;
		
		try {
			
		    // select * ...
			stat = con.prepareStatement("select * from login where username = ? and passwords = ? ");
			stat.setString(1, username);
			stat.setString(2, password);

			ResultSet res = stat.executeQuery();
			
			while(res.next()) {
				//Login login2 = new Login();
				
				login2.setLoginId(res.getInt(1));
			
				login2.setUsername(res.getString(2));
				login2.setPassword(res.getString(3));
				login2.setUserOrEmploy(res.getInt(4));
				}
			
			res.close();
			stat.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return login2;
	}


	@Override
	public  boolean bankapplicant(String username, String password) {
		
		Connection con = DBConnection.getConnection();
		//int userstatus=0;
		System.out.println("## Welcome new applicant: " + username);
		int rows = 0;
		
		//Login login2= new Login();
		PreparedStatement stat;
		try {
			stat = con.prepareStatement("insert into login values(default,?,?,0)");
			stat.setString(1, username);
			stat.setString(2, password);

			rows = stat.executeUpdate();
			//System.out.println(rows + " user registered successfully");
			
			stat.close();
			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (rows == 0){
			System.out.println(username+  " your application has problems.");
			return false;
		}
		else {
			System.out.println(username+  " your application successfully, please wait for bank evaluation.");
			return true;
		}
			
	}


	@Override
	public Login approveOrDeny(int applicantstatus ) {
		
		Connection con=DBConnection.getConnection();
		
		Login login2= new Login();
		PreparedStatement stat;
		System.out.println("Search for the pending applicant ");
		
		try {
				stat = connection.prepareStatement("select * from login where useroremploy= ? limit 1;");
				stat.setInt(1, applicantstatus);
				// =============
				ResultSet res = stat.executeQuery();
				res.next();
				
				login2.setLoginId(res.getInt(1));
				login2.setUsername(res.getString(2));
				login2.setPassword(res.getString(3));
				login2.setUserOrEmploy(res.getInt(4));
				
				res.close();
				stat.close();
				con.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		return login2;
		
	} 
	
	@Override
	public  boolean deleteLoginRecord(Login login) {
		
		int rows = 0;
		int tloginid= login.getLoginId();
		String tusername = login.getUsername();
		String tpassword = login.getPassword();
		int tstatus= login.getUserOrEmploy();
		//Login login2= new Login();
		PreparedStatement stat;
		
		Connection con = DBConnection.getConnection();
		
		try {
			
			// because the loginId is serial, when we update it's value change and will report error,
			// so we delete the old record, then update the new records
			
			stat = con.prepareStatement("DELETE FROM login WHERE loginid= ?");
			stat.setInt(1, login.getLoginId());
			rows = stat.executeUpdate();
				
			stat.close();
			con.close();
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (rows == 0){
			System.out.println("Fails to delete the record.");
			return false;
		}
		else {
			System.out.println("Success to delte therecord");
			return true;
		}
	
	}
	
	@Override
	public  boolean updateLoginStatus(Login login) {
		int rows = 0;
		PreparedStatement stat;
		Connection con=DBConnection.getConnection();
		
		try {
			
			// because the loginId is serial, when we update it's value change and will report error,
			// so we delete the old record, then update the new records
			
			
			stat = con.prepareStatement("UPDATE login SET useroremploy = ? where loginid= ? ;");
			stat.setInt(1, login.getUserOrEmploy());
			stat.setInt(2, login.getLoginId());
			
			rows = stat.executeUpdate();
		
			stat.close();
			con.close();
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (rows == 0){
			System.out.println("Fails to update the user status.");
			return false;
		}
		else {
			System.out.println("Success to update user status");
			return true;
		}
	
	}
	
	@Override
	public boolean addBankAccount(Login login) {
		int rows = 0;
		PreparedStatement stat = null;
		System.out.println("## Adding bankaccount for  :" + login);
		
		Connection con=DBConnection.getConnection();
		
		try {
			stat= con.prepareStatement("insert into accounts values(default,?,?, 0)");
			stat.setString(1, login.getUsername());
			stat.setString(2, login.getPassword());
			
			rows = stat.executeUpdate();
			//System.out.println(rows + " Customer's application is processing ... ");
			
			stat.close();
			con.close();
				

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (rows == 0){
			System.out.println("Fails to update the user status.");
			return false;
		}
		else {
			System.out.println("Success to update user status");
			return true;
		}
		
	}
	
}