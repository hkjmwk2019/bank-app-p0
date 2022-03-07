
package com.training.pms.product_app_alstrom;

import java.io.IOException;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.training.pms.dao.AccountDAO;
import com.training.pms.dao.AccountDAOImpl;
import com.training.pms.dao.LoginDAO;
import com.training.pms.dao.LoginDAOImpl;
import com.training.pms.model.Account;
import com.training.pms.model.Login;


/* code write to line 141 */
public class BankProductApp 
{
	
	boolean result;
	int choice =0;
	int subchoice=0;
	Scanner sc = new Scanner(System.in);
	Login login = new Login();
	Login bankcustomlogin= new Login();
	LoginDAO loginDAO= new LoginDAOImpl();
	Account account = new Account();
	AccountDAO accountDAO= new AccountDAOImpl();
				
	List<Login> logins = new ArrayList<Login>();
	List<Account> accounts = new ArrayList<Account>();
	
	public void startBankApp()throws IOException 
	{
		//declare local variables for input
		int choice=0;
		int loginId=0;
		int tuserOrEmployStatus= 0;
		int accountId=0;
		int amount=0;
		
		String userName=null;
		String password= null;
		String customerName=null;
		boolean isValidTransfer = true;
		boolean isValidAdd = true;
		boolean wantRun = true;
		List<Account> accounts = new ArrayList<Account>();
	//	List<Transactions> transaction = new ArrayList<Transactions>();
		
		
		while (true) 
		{
			String tuserName=null;
			String tpassword= null;
			String tcustomerName= null;
			int userOrEmploy=0;
			
			//String tstring=null;
			System.out.println("1. New  Application");
			System.out.println("2. Current User");
			System.out.println("Please enter your choice : ");
			
			choice = sc.nextInt();
			
			switch(choice) 
			{
				case 1:		
					System.out.println("======================================================= ");
					System.out.println("Welcome Customers  ");
					System.out.print("Please enter your user name: ");
					tuserName = sc.next();
				
					System.out.print("Please enter your user password: ");
					tpassword = sc.next();
					System.out.println("======================================================= ");
					
					loginDAO.bankapplicant(tuserName, tpassword);	
					
					System.out.println("Your application is pending, our bank will mail you while your first account gets approved.");
					continue;
					//System.exit(0);
				
				case 2:
					break;
				default:
					System.out.println("Invalid choice ");
					//System.exit(0);
					continue;
				
			}
			
			
	           // for current users
			
		   while(choice==2 ) 
		   {	
			   while(true) 
			   {
				   // String tuserName= null;
				   // String tpassword= null;
				   // int userOrEmploy=0;
			
				   System.out.println("===================================================== ");
				   System.out.println("Welcome, please enter your name and password.");
				   System.out.println("Please enter your name : ");
				   tuserName=sc.next();
			
				   System.out.println("Please enter your password : ");
				   tpassword=sc.next();
			
				   if(loginDAO.validate(tuserName, tpassword))
				   {
					   System.out.println("Login Sucessfully. ");
					   userName=tuserName;
					   password=tpassword;
					   break;
				   } 
				   else
				   {
				    System.out.println("===================================================== ");
				    System.out.println("Wrong name or wrong password. ");
					
					continue;
				   }
			
			   }	
		   
		
			
		       /* ---- get login objcet  -----*/
			   
			   login= loginDAO.checkuserstatus(userName, password);
			   tuserOrEmployStatus= login.getUserOrEmploy();
		
			   //logins.getUserOrEmploy()
			   //while(true) {
			   /* check if the person is user or employ */	
			   //if the person is employ
			   if((tuserOrEmployStatus==0))  // tuserOrEmployStatus =0 is new applicant.
			   {
				   System.out.println("Your account application is  pending.");
				   System.exit(0);
			   }
			   else if (tuserOrEmployStatus==3) 
			   {
				   System.out.println("Your account application is rejected.");
				   System.exit(0);
			   }
			   else if (tuserOrEmployStatus==1) //tuserOrEmployStatus =1, is employ
			   {
				   while(true) 
				   {

			
					   /* *** **********************************************************
					    *    Employ   Screen
					    *  
					    * **************************************************************** */
					   System.out.println("====================================================");
					   System.out.println("=                                                  =");
					   System.out.println("=      O N L Y    F O R   E M P L O Y E E          =");
					   System.out.println("=                                                  =");
					   System.out.println("====================================================");
					   System.out.println("10. Approve/Deny customer ");
					   System.out.println("11. Search Customer's All accounts by name and passowrd ");
					   System.out.println("9. E  X  I  T  ");
					   System.out.println("====================================================");
		
					   System.out.println("Please enter your choice : ");
					   //choice = sc.nextInt();
					   subchoice = sc.nextInt();
			
					   //while(true) 
					   //{
					   //subchoice = sc.nextInt();
					   switch(subchoice)
					   {
					
						case 10:
							boolean approvechoice= false;
							String temp=null;
							String ttemp=null;
							//Login login2 = new Login();
						
							login= loginDAO.approveOrDeny(0);
							System.out.println("====================================================");
							System.out.println("The Pending Applicant information :  Name, status");
							System.out.println(login.getUsername()+ ", " + login.getUserOrEmploy());
							System.out.println("prove or not: (Y/N)");
						
							temp= sc.next();
		
							if(temp.equalsIgnoreCase("Y") )
							{
								System.out.println("## Before, the applicant status is : " + login.getUserOrEmploy());
								System.out.println("chance satuts to 2: Application is approved.");
								login.setUserOrEmploy(2);
								loginDAO.updateLoginStatus(login);
								System.out.println("## Now, the applicant status is : " + login.getUserOrEmploy());
								loginDAO.addBankAccount(login);	
								
								
							}
							else 
							{
								System.out.println("## Before, the applicant status is : " + login.getUserOrEmploy());
								System.out.println("chance satuts to 3: Application is rejected. ");
								login.setUserOrEmploy(3);
							
								loginDAO.updateLoginStatus(login);
								System.out.println("## Now, the applicant status is : " + login.getUserOrEmploy());
							}
			
							//System.out.println("Continue working for next Pending Applicant: (Y/N)");
							//	ttemp= sc.next();
							//	System.out.println("ttemp = " + ttemp);
			
							//	if(ttemp.equalsIgnoreCase("Y")) {
							//		continue;
							//	}
													
							break;
						
			        
						case 11:
						
							System.out.println("===================================================== ");
							System.out.println("Welcome, please enter customer name and password.");
							System.out.println("Please enter customer name : ");
							tcustomerName=sc.next();
						
							System.out.println("Please enter customer password : ");
							tpassword=sc.next();
							accounts= accountDAO.searchAccountByCustomerNamePassword(tcustomerName, tpassword);
						
							System.out.println("print the custmoer's all account information:");
							System.out.println(accounts);
							break;
						
			
					case 9:
						System.exit(0);
						break;
		 
					default: 
						System.out.println("Invalid Choise, Please enter (10-12) or 9 to Exit");
						break;	
						
				}
					   
			 }
		}
	   else  // tuserOrEmployStatus  =2 is current customers
	   {
		   while(true) 
			{
			   System.out.println("====================================================");
			   System.out.println("=                                                  =");
			   System.out.println("=     W E L C O M E     C U S T O M E R            =");
			   System.out.println("=                                                  =");
			   System.out.println("====================================================");
			   System.out.println("1. View all Your Accounts Information  ");
			   System.out.println("2. Withdraw from Personal Account  ");
			   System.out.println("3. Deposit to Personal Account ");
			   System.out.println("4. Transfer to Another Account ");
			   System.out.println("9. E  X  I  T  ");
			   System.out.println("====================================================");
			   
			   System.out.println("Please enter your choice : ");
			   subchoice = sc.nextInt();
			   
			  // System.out.println("Welcome, please enter your name and password.");
			 //  System.out.println("Please enter your name : ");
			 //  tcustomerName=sc.next();
		
			 //  System.out.println("Please enter your password : ");
			//	tpassword=sc.next();
			   
			   
				switch(subchoice)
				{

					case 1:
						 System.out.println("Welcome, please enter your name and password.");
						  System.out.print ("Please enter your name : ");
						  tcustomerName=sc.next();
					
						  System.out.println("Please enter your password : ");
							tpassword=sc.next();

						System.out.println("===================================================== ");
						//System.out.println("print all your account information:");
						
						accounts= accountDAO.searchAccountByCustomerNamePassword(tcustomerName, tpassword);
				
						System.out.println("print all your account information:");
						System.out.println(accounts);
				
						break;
					
					case 2:
						System.out.println("Welcome, please enter your name and password.");
						  System.out.print("Please enter your name : ");
						  tcustomerName=sc.next();
					
						  System.out.println("Please enter your password : ");
						  tpassword=sc.next();
							
						  accounts= accountDAO.searchAccountByCustomerNamePassword(tcustomerName, tpassword);	
						  account = accountDAO.searchAccountByCustomerNamePassword_02(tcustomerName, tpassword);
							
						System.out.println("Before withdraw your balance is : " + account.getBalance());
																				
						wantRun = true;
						while (wantRun) {
							System.out.println("The amount you want withdrawn :");
							amount = sc.nextInt();
							// System.out.println("amount"+amount);

							if (amount <= account.getBalance() && amount >= 0) {

								wantRun = false;
							} else if(amount < 0)
								System.out.println("Amount cannot not be negative.");
							else
								System.out.println("Cannot withdraw more than current balance");
						}
						
						
						boolean isValidWithdraw = accountDAO.withdrawFromAccount(account, amount);
						
					
						

						if (!isValidWithdraw) {
							System.out.println("Withdraw was unsuccessful. Try again");
							
							System.out.println("Your account infomation is:");
							System.out.println(account);
							continue;
						}
						
						accounts= accountDAO.searchAccountByCustomerNamePassword(tcustomerName, tpassword);
						account = accountDAO.searchAccountByCustomerNamePassword_02(tcustomerName, tpassword);
						
						System.out.println("After withdraw your balance is : " + account.getBalance() );
						
						
						break;
					
					case 3: //3. Deposit to Personal Account 
						
						System.out.println("Welcome, please enter your name and password.");
						 System.out.print("Please enter your name : ");
						  tcustomerName=sc.next();
					
						 System.out.println("Please enter your password : ");
						tpassword=sc.next();
							
							
						accounts= accountDAO.searchAccountByCustomerNamePassword(tcustomerName, tpassword);
						
						account = accountDAO.searchAccountByCustomerNamePassword_02(tcustomerName, tpassword);
						
						System.out.println("Before Deposit your balance is : " + account.getBalance());
																	
						System.out.println("Deposit to Personal Account");
						System.out.println("The amount you want Deposit :");
						amount = sc.nextInt();
						
						boolean isValidDeposit = accountDAO.depositToAccount(account, amount);
						
						accounts= accountDAO.searchAccountByCustomerNamePassword(tcustomerName, tpassword);
						account = accountDAO.searchAccountByCustomerNamePassword_02(tcustomerName, tpassword);
						
						System.out.println("After deposit your balance is : " + account.getBalance() );
						
						break;
						
					case 4:
						System.out.println("Welcome, please enter your name and password.");
						  System.out.print("Please enter your name : ");
						  tcustomerName=sc.next();
					
						  System.out.println("Please enter your password : ");
						tpassword=sc.next();
						
						  accounts= accountDAO.searchAccountByCustomerNamePassword(tcustomerName, tpassword);							
						  account = accountDAO.searchAccountByCustomerNamePassword_02(tcustomerName, tpassword);
						
						System.out.print("##  The other account you want to transfer to : ");
						int otheraccountId= sc.nextInt();
						
						
						System.out.println("The amount you want to transfer : :");
						amount = sc.nextInt();
					    
						System.out.println("##  Before transfer your balance is : " + account.getBalance());
						
						boolean isValidTransfer1 = accountDAO.transferToOtherAccount(account, otheraccountId, amount);
						
						 accounts= accountDAO.searchAccountByCustomerNamePassword(tcustomerName, tpassword);							
						 account = accountDAO.searchAccountByCustomerNamePassword_02(tcustomerName, tpassword);
						
						 System.out.println("##  After transfer your balance is : " + account.getBalance());
						
						break;

					case 9:
						System.exit(0);
						break;
		 
					default: 
						System.out.println("Invalid Choise, Please enter (1-6) or 9 to Exit");
						break;	
				}		
			}
		}
	}
}
}
}
	
//public void printProductDetails(List<Product> products) 
//{
//		Iterator<Product> iterator = products.iterator();
//		while (iterator.hasNext()) {
//			Product temp = iterator.next();
//			System.out.println(temp);
//		}
// }
	
