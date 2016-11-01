import static org.junit.Assert.*;

import org.junit.Test;

public class ManagerTests {

	@Test
	public void test() {
		Manager m = new Manager();
		m.createOrders();
	}
	@Test
	public void testfulfillOrders() {
		Manager m = new Manager();
		m.fullfillInventoryOrder();
	}

}
