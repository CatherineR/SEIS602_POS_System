import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class ManagerTests {

	@Test
	public void tetsUpdateQuantityAdd(){
		Manager m = new Manager();
		Inventory inv = new Inventory() ;
		int invQuantityBefore = inv.getInventoryItem("cheese").getInventoryQuantity();
		m.updateQuantity("cheese", 10);
		int invQuantityAfter = inv.getInventoryItem("cheese").getInventoryQuantity();
		
		
		assertEquals(invQuantityBefore+10,invQuantityAfter );
	}
	@Test
	public void testUpdateQuantitySubtract(){
		Manager m = new Manager();
		Inventory inv = new Inventory() ;
		int invQuantityBefore = inv.getInventoryItem("cheese").getInventoryQuantity();
		m.updateQuantity("cheese", -10);
		int invQuantityAfter = inv.getInventoryItem("cheese").getInventoryQuantity();
		
		
		assertEquals(invQuantityBefore-10,invQuantityAfter );
	}
	@Test
	public void addInventoryItem(){
		Manager m = new Manager();
		m.addInventoryItem("test_2", 100, 2.50, "test_2 supplier", .1, 90);
		Inventory inv = new Inventory() ;
		List <InventoryItem> testList = inv.getInventoryList();
		assertEquals(inv.getInventoryItem("test_2").getName(), testList.get(testList.size()-1).getName());
	}
	@Test
	public void removeInventoryItem(){
		Manager m = new Manager();
		m.removeInventoryItem("test_2");
	}
	
	@Test
	public void testCreateOrders() {
		Manager m = new Manager();
		m.createOrders();
	}
	@Test
	public void testfulfillOrders() {
		Manager m = new Manager();
		m.fullfillInventoryOrder();
				
	}

}
