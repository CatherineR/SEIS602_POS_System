import static org.junit.Assert.*;

import org.junit.Test;

public class RegisterTestCases {
	private Register register1 = new Register();
	@Test
	public void test() {

		//Check for log in. Result should show all user name and log in time.
		register1.login();
		
		//check for log out. It should show logout time and logged out user name.
		register1.logout();
		
	}
	
	

}
