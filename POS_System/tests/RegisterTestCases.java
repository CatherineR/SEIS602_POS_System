import static org.junit.Assert.*;

import org.junit.Test;

public class RegisterTestCases {
	
	@Test
	public void test() {
		Register register1 = new Register();
		//Check for log in. Result should show all user name and log in time.
		register1.login("olaniManager", "1234");
		
		//check for log out. It should show logout time and logged out user name.
		register1.logout();
		
		
		
	}
	
}
