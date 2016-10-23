import static org.junit.Assert.*;

import org.junit.Test;

public class EmployeTestCases {

	@Test
	public void test1() {
		String userName = "olaniCashier";
		String password = "1234";
		Employee emp1 = new Employee(userName, password);
		
		assertEquals("cashier",emp1.login(userName, password).toLowerCase(), "cashier");
		

	}
	
	@Test
	public void test2() {
		String userName = "olaniManager";
		String password = "1234";
		Employee emp2 = new Employee(userName, password);
		//System.out.println(emp1.login(userName, password));
		
		assertEquals("manager",emp2.login(userName, password).toLowerCase(), "manager");
		
	}
	
	@Test
	public void test3() {
		String userName = "olaniManager1";
		String password = "1234";
		Employee emp2 = new Employee(userName, password);
		//System.out.println(emp1.login(userName, password));
		
		assertEquals("not available",emp2.login(userName, password).toLowerCase(), "not available");
		
	}
	
	@Test
	public void test4() {
		String userName = "olaniManager";
		String password = "1235";
		Employee emp2 = new Employee(userName, password);
		//System.out.println(emp1.login(userName, password));
		
		assertEquals("not available",emp2.login(userName, password).toLowerCase(), "not available");
		
	}

}
