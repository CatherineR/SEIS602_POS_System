import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class Manager extends Cashier {
	
	public void updateQuantity(String itemName, int quantity){
		Inventory inv = new Inventory();
		inv.updateInventoryQuantity(itemName, quantity);
	}
	
	public void addInventoryItem(String itemName, int quantity, double price, 
									String supplier, double taxRate, int threshold ){
		Inventory inv = new Inventory();		
		inv.createInventoryItem(itemName, quantity, price, supplier, taxRate, threshold);
	}
	
	public void removeInventoryItem(String oldInventoryItemName){
		Inventory inv = new Inventory();	
		inv.deleteInventoryItem(oldInventoryItemName);
	}
	public void createOrders(){
		Inventory inv = new Inventory();
		List<InventoryItem> invList = inv.getInventoryList();
		//Go through inventory items
		for(int i=0; i<invList.size(); i++){
			InventoryItem item = invList.get(i);
			
			//if inventory is below threshold, create new order
			if(item.getInventoryQuantity() < item.getThreshold()){
				InventoryOrder invOrder = new InventoryOrder();
				Date orderDate = getCurrentDate();
				UUID orderId = invOrder.createOrder(item.getSupplier(), item.getName(), 100, item.getPrice(), orderDate);
				System.out.println("Added new order for item " + item.getName() + " with Order ID " + orderId +"\n");				
			}
		}
	}
	
	public void fullfillInventoryOrder(){
		System.out.println("incomplete method");
	}
	
	private Date getCurrentDate(){
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	    Date today = new Date();
	    Date formattedDate = new Date();
			try {
				formattedDate = formatter.parse(formatter.format(today));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	    return formattedDate;
	}
}