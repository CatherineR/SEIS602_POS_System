import static org.junit.Assert.*;

import org.junit.Test;

public class InventoryItemTests {

	@Test
	public void testGetName() {
		InventoryItem item = new InventoryItem("corn", 100, 2.50,  "green giant", .1, 25 );
		assertEquals(item.getName(), "corn");
	}
	@Test
    public void testGetInventoryQuantity() {
		InventoryItem item = new InventoryItem("corn", 100, 2.50,  "green giant", .1, 25 );
		assertEquals(item.getInventoryQuantity(), 100);
    }
	@Test	
    public void testGetPrice() {
		InventoryItem item = new InventoryItem("corn", 100, 2.50,  "green giant", .1, 25 );
		assertEquals(item.getPrice(), 2.50, .2);
    }
	@Test	
    public void testGetSupplier(){
		InventoryItem item = new InventoryItem("corn", 100, 2.50,  "green giant", .1, 25 );
		assertEquals(item.getSupplier(), "green giant");
    }
	@Test    
    public void testGetThreshold(){
		InventoryItem item = new InventoryItem("corn", 100, 2.50,  "green giant", .1, 25 );
		assertEquals(item.getThreshold(), 25);
    }
	@Test    
    public void testGetTaxRate(){
		InventoryItem item = new InventoryItem("corn", 100, 2.50,  "green giant", .1, 25 );
		assertEquals(item.getTaxRate(), .1, .3);
    }
	@Test   
    public void testSetInventoryQuantity() {
		InventoryItem item = new InventoryItem("corn", 100, 2.50,  "green giant", .1, 25 );
		item.setInventoryQuantity(20);
		assertEquals(item.getInventoryQuantity(), 20);
    }

}
