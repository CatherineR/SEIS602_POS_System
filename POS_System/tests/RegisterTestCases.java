import static org.junit.Assert.*;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.Test;

public class RegisterTestCases {
	
	@Test
	public void test() {
		Register register1 = new Register(1);
		//Check for log in. Result should show all user name and log in time.
		register1.login("olaniManager", "1234");
		
		//check for log out. It should show logout time and logged out user name.
		register1.logout();
		
		
		
	}
	
	@Test
	public void testcommitSalesOrder(){
		Register register1 = new Register(23);
		
		register1.login("olaniCashier", "1234");
		
		assertEquals(false,register1.hasTransaction());
		register1.beginTransaction();
		register1.addItem("bread", 2);
		register1.addItem("cheese", 1);
		register1.commitTransaction();
		register1.beginTransaction();
		register1.addItem("bread", 2);
		register1.addItem("cheese", 10);
		System.out.println( "your change is:" + String.format("%.2f",register1.receivePayment(40)));
		
		register1.commitTransaction();
		assertTrue(register1.getRegisterSession() != null);
		assertEquals(true,register1.hasTransaction());
		
		System.out.println("Final transaction details: " + 
				register1.getUserName() + " "+ 
				register1.getTotalTransactions() + " "+ 
				register1.getSessionTotalSale());
		
		
		
		register1.logout();
		
		
	}
	
	@Test
	public void testcommitSalesOrder2(){
		Register register1 = new Register(23);
		
		register1.login("olaniCashier", "1234");
		
		assertEquals(false,register1.hasTransaction());
		register1.beginTransaction();
		assertEquals(true,register1.hasTransaction());
		register1.addItem("bread", 1);
		register1.addItem("cheese", 1);
		register1.cancelTransaction();
		assertNull(register1.cancelTransaction());
		assertEquals(false,register1.hasTransaction());
		
	}
	
	public void getRegisterTotalSale() throws NumberFormatException, IOException, ParseException{
		Register register1 = new Register(2);
			String path="./././res/RegisterSession.txt";
			register1.setUserName("olaniCashier");
			BufferedReader bufferLog = null;
			try {
			    bufferLog= new BufferedReader(new FileReader(path));
			    String singleLineLog = null;
			    double RegisterTotalfromLog=0.0;
			    singleLineLog=bufferLog.readLine();
			    
			    while ((singleLineLog = bufferLog.readLine()) != null) 
			      {
			    	String[] splited = singleLineLog.split("[|]");
			    	if(splited[2].equals(register1.getUserName()))
			    	{
			    		double sessionTotalformLog=Double.parseDouble(splited[6]);
			    		RegisterTotalfromLog=RegisterTotalfromLog+sessionTotalformLog;
			    	}
			       
			        
			     }
			    System.out.println(RegisterTotalfromLog);
			} catch (IOException e) {
			    System.out.println("There was a problem: " + e);
			    e.printStackTrace();
			} finally {
			    try {
			        bufferLog.close();
			    } catch (Exception e) {
			    }
			}
			
			
			
	
	}
}


	
	
	
	
	
	
	
