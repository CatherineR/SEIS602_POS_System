import static org.junit.Assert.*;

import java.util.List;

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
	
	@Test(expected=ItemNotFound.class)	
	public void testFindInventoryItemNoneExist(){
		InventoryListDAO invListDAO = new InventoryListDAO();		
		invListDAO.findInventoryItem("shoe");			
	}
	
	@Test
	public void testFindInventoryItemDoesExist(){
		InventoryListDAO invListDAO = new InventoryListDAO();		
		invListDAO.findInventoryItem("milk");			
	}
	
	@Test
	public void testGetInventoryList(){
		InventoryListDAO invListDAO = new InventoryListDAO();		
		List<InventoryItem> orders = invListDAO.getInventoryList();	
		int count = 0;
		for(int i=0;i<orders.size();i++){
			count++;
		}
		assertEquals(orders.size(),count);
		
	}
}
