import static org.junit.Assert.*;
import org.junit.Test;
import java.util.List;

public class InventoryTest {

	@Test
	public void createInventoryItem(){
		Inventory inv = new Inventory();
		int sizeBefore = inv.getInventoryList().size();
		inv.createInventoryItem("test", 100, 1.50, "test supplier", 0, 90);
		int sizeAfter = inv.getInventoryList().size();
		assertEquals(sizeBefore+1, sizeAfter);
		
	}
	@Test
	public void testupdateInventoryQuantity(){
		Inventory inv = new Inventory();	
		int quantityBefore = inv.getInventoryItem("milk").getInventoryQuantity();
		inv.updateInventoryQuantity("milk", 1);
		int quantityAfter = inv.getInventoryItem("milk").getInventoryQuantity();
		assertEquals(quantityBefore+1,quantityAfter);
	}	
	@Test
	public void testupdateInventoryQuantityNegative(){
		Inventory inv = new Inventory();	
		int quantityBefore = inv.getInventoryItem("milk").getInventoryQuantity();
		inv.updateInventoryQuantity("milk", -1);
		int quantityAfter = inv.getInventoryItem("milk").getInventoryQuantity();
		assertEquals(quantityBefore-1,quantityAfter);
	}	
	
	@Test
	public void testgetInventoryItem(){
		Inventory inv = new Inventory();	
		InventoryItem testItem = inv.getInventoryItem("milk");
		
		assertEquals(testItem.getName(),"milk");
	}
	@Test
	public void testgetInventoryList(){
		Inventory inv = new Inventory();
		List<InventoryItem> testList = inv.getInventoryList();
		
		assertEquals(testList.get(testList.size()-1).getName(), inv.getInventoryItem(testList.get(testList.size()-1).getName()).getName());
	}
	
	@Test
	public void testDeleteInventoryItem(){
		Inventory inv = new Inventory();
		int sizeBefore = inv.getInventoryList().size();
		inv.deleteInventoryItem("test");
		int sizeAfter = inv.getInventoryList().size();
		assertEquals(sizeBefore-1, sizeAfter);
	}



}
