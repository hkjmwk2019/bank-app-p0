package com.training.pms.product_app_alstrom;

import java.io.IOException;
import java.util.InputMismatchException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
        BankProductApp bankproductApp = new BankProductApp();
        
		try {
			bankproductApp.startBankApp();
		} 
		catch (InputMismatchException e) 
		{
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Please enter number only");
		}
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
    }  
    
}
