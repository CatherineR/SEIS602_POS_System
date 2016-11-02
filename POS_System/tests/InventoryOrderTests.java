import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.Test;

public class InventoryOrderTests {

	@Test
	public void testCreateOrder() {
		InventoryOrderDAO invOrderDAO= new InventoryOrderDAO();		
		Date currentDate = new Date();
		UUID testID = invOrderDAO.addOrder("Orange Juice", "Doles", 20, 2.50, currentDate);	
		List<InventoryOrder> orders = invOrderDAO.getInventoryOrders();	
		
		assertEquals(testID,orders.get(orders.size()-1).getOrderId() );
	}
	
	@Test
	public void testGetOutstandingOrders() {
		InventoryOrderDAO invOrderDAO= new InventoryOrderDAO();		
		List<InventoryOrder> outstandingOrders = invOrderDAO.getOutstandingOrders();	
		int count = 0;
		for(InventoryOrder order : outstandingOrders){
			count++;
		}
		assertEquals(count,outstandingOrders.size());
		
	}

	@Test
	public void testCreateOrders(){
		InventoryOrder invOrder = new InventoryOrder();
		//invOrder.createOrder("", iName, oQuantity, iPrice, orderDate)
	}
	
	@Test
	public void testGetOrderId(){		
		InventoryOrderDAO invOrderDAO = new InventoryOrderDAO();	
		List<InventoryOrder> orders = invOrderDAO.getInventoryOrders();
		InventoryOrder testOrder = orders.get(0);
		
		UUID testID = testOrder.getOrderId();

		assertEquals(invOrderDAO.getInventoryOrder(testID).getItemName(),orders.get(0).getItemName());
	}
	
	@Test
	public void testGetStatus(){		
		InventoryOrderDAO invOrderDAO = new InventoryOrderDAO();	
		List<InventoryOrder> orders = invOrderDAO.getInventoryOrders();
		InventoryOrder testOrder = orders.get(0);
		
		String testStatus = testOrder.getStatus();

		assertEquals(testStatus,orders.get(0).getStatus());
	}
	
	@Test
	public void testGetFulfillmentDate(){
		InventoryOrderDAO invOrderDAO = new InventoryOrderDAO();	
		List<InventoryOrder> orders = invOrderDAO.getInventoryOrders();
		InventoryOrder testOrder = orders.get(0);
		Date testDate = testOrder.getFulfillmentDate();

		assertEquals(testDate,orders.get(0).getFulfillmentDate());
	}
	
	@Test
	public void testSetStatus(){
		InventoryOrderDAO invOrderDAO = new InventoryOrderDAO();	
		List<InventoryOrder> orders = invOrderDAO.getInventoryOrders();
		InventoryOrder testOrder = orders.get(0);
		
		String newStatus = "test status";
		testOrder.setStatus(newStatus);
		assertEquals(newStatus,testOrder.getStatus());
	}
	
	@Test
	public void testSetFulfillmentDate(){
		InventoryOrderDAO invOrderDAO = new InventoryOrderDAO();	
		List<InventoryOrder> orders = invOrderDAO.getInventoryOrders();
		InventoryOrder testOrder = orders.get(0);
		
		Date newDate = new Date();
		testOrder.setFulfillmentDate(newDate);
		assertEquals(newDate,testOrder.getFulfillmentDate());
	}
	
	@Test
	public void testGetItemName(){
		InventoryOrderDAO invOrderDAO = new InventoryOrderDAO();	
		List<InventoryOrder> orders = invOrderDAO.getInventoryOrders();
		InventoryOrder testOrder = orders.get(0);
		
		String testName = testOrder.getItemName();

		assertEquals(testName,orders.get(0).getItemName());
	}
	
	@Test 
	public void testGetSupplierName(){
		InventoryOrderDAO invOrderDAO = new InventoryOrderDAO();	
		List<InventoryOrder> orders = invOrderDAO.getInventoryOrders();
		InventoryOrder testOrder = orders.get(0);
		
		String testName = testOrder.getSupplierName();

		assertEquals(testName,orders.get(0).getSupplierName());
	}
	
	@Test 
	public void testGetQuantity(){
		InventoryOrderDAO invOrderDAO = new InventoryOrderDAO();	
		List<InventoryOrder> orders = invOrderDAO.getInventoryOrders();
		InventoryOrder testOrder = orders.get(0);
		
		int testQuantity = testOrder.getQuantity();

		assertEquals(testQuantity,orders.get(0).getQuantity());		
	}

}
