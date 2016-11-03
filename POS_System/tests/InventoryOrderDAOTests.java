import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.Test;


public class InventoryOrderDAOTests {

	@Test
	public void testGetInventoryOrders() {				
		InventoryOrderDAO invOrdDAO = new InventoryOrderDAO();
		List<InventoryOrder> list = invOrdDAO.getInventoryOrders();
		
		int count = 0;
		for(int i=0;i<list.size();i++){
			count++;
		}
		
		assertEquals(count, list.size());
	}
	
	public void testGetInventoryOrder() {				
		InventoryOrderDAO invOrdDAO = new InventoryOrderDAO();
		List<InventoryOrder> list = invOrdDAO.getInventoryOrders();
		InventoryOrder order = invOrdDAO.getInventoryOrder(list.get(0).getOrderId());
		
		assertEquals(order, list.get(0));
	}
	
	@Test
	public void getOutstandingOrders() {				
		InventoryOrderDAO invOrdDAO = new InventoryOrderDAO();
		List<InventoryOrder> list = invOrdDAO.getOutstandingOrders();
		
		assertEquals(list.get(0).getStatus(),"new");
	}
	@Test
	public void testMarkFulfilled() {
		InventoryOrderDAO invOrdDAO = new InventoryOrderDAO();
		Date testDate = new Date();
		UUID testID = invOrdDAO.addOrder("test", "supplier test", 100, 2.50,testDate);
		List<InventoryOrder> list = invOrdDAO.getOutstandingOrders();		
		invOrdDAO.markFulfilled(testID, "fulfilled");
		
		InventoryOrder testOrd = invOrdDAO.getInventoryOrder(testID);
				
		assertEquals(testOrd.getStatus(),"fulfilled");
	
	}
	@Test
	public void testAddOrder(){
		InventoryOrderDAO invOrdDAO = new InventoryOrderDAO();
		
		Date testDate = new Date();
		invOrdDAO.addOrder("test", "supplier test", 100, 2.50,testDate);
		List<InventoryOrder> list = invOrdDAO.getOutstandingOrders();
		
		assertEquals(list.get(list.size()-1).getItemName(), "test");
	}

}
