import static org.junit.Assert.*;

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
		
		register1.beginTransaction();
		register1.addItem("bread", 2);
		register1.addItem("cheese", 1);
		register1.commitTransaction();
		register1.beginTransaction();
		register1.addItem("bread", 2);
		register1.addItem("cheese", 10);
		System.out.println( "your change is:" + String.format("%.2f",register1.receivePayment(40)));
		register1.commitTransaction();
		
		System.out.println("Final transaction details: " + 
				register1.getUserName() + " "+ 
				register1.getTotalTransactions() + " "+ 
				register1.getSessionTotalSale());
		register1.logout();
		
		
	}
	
}
