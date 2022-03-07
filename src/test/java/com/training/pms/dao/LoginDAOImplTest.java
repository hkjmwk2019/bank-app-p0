package com.training.pms.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.training.pms.model.Login;

import com.training.pms.utility.DBConnection;

class LoginDAOImplTest {

	LoginDAO loginDAO;
	int loginId;
	Login login;
	int rows=0;
	String testPassword;
	String testName;
    boolean testPass= false;

	@BeforeEach
	void setUp() throws Exception {
		Connection con=DBConnection.getConnection();
		int rows = 0;
		
		//Login login2= new Login();
		PreparedStatement statTest;
		
		//try {
		//	statTest = con.prepareStatement("delete from accounts where name='test0' ;");		
		//	rows = statTest.executeUpdate();
			
	//	} catch (SQLException e) {
		
	//		e.printStackTrace();
	//	}
		
		LoginDAO loginDAO = new LoginDAOImpl();
		testName = "test0";
		testPassword="pp" ;
		login = new Login();
		
	}

	@AfterEach
	void tearDown() throws Exception {
	}
    
	// test new customer to apply an account
	@Test
	void testBankapplicant() {
		//fail("Not yet implemented");
		testPass= loginDAO.bankapplicant(testName, testPassword);
		assertTrue(testPass);
		
		
	}

}
