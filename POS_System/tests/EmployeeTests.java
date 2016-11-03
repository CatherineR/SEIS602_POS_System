import static org.junit.Assert.*;

import org.junit.Test;

public class EmployeeTests {

	
	@Test
	public void testEmployee() {
		
		Employee emp1 = new Employee("olaniCashier", "1234");
		System.out.println("created a new object " + emp1.toString());
		Employee emp2 = new Employee("traineeCashier", "5678");
		System.out.println("created a new object " + emp2.toString());
		assertEquals("olaniCashier", emp1.getUserName());
		assertEquals("1234", emp1.getPassword());
		assertEquals("traineeCashier", emp2.getUserName());
		assertEquals("5678", emp2.getPassword());
	}

}
