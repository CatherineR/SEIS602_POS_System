import static org.junit.Assert.*;
import org.junit.Test;

public class InventoryListDAOTests {

	@Test
	public void testIncrement() {
		InventoryListDAO invListDAO = new InventoryListDAO();		
		invListDAO.adjustItemQuantity("bread", 10);			
	}
	@Test
	public void testDecrement() {
		InventoryListDAO invListDAO = new InventoryListDAO();		
		invListDAO.adjustItemQuantity("bread", -10);			
	}
	@Test
	public void testAddInventoryItem() {
		InventoryListDAO invListDAO = new InventoryListDAO();		
		invListDAO.addItem("milk", 20, 2.50, "Kemps", 0, 10);			
	}
	
	@Test	
	public void testDeleteInventoryItem() {
		InventoryListDAO invListDAO = new InventoryListDAO();		
		invListDAO.deleteItem("milk");
			
	}
	
	
}
