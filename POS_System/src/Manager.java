import java.util.Iterator;
import java.util.List;

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
	public void createOrder(){
		Inventory inv = new Inventory();
		List<InventoryItem> invList = inv.getInventoryList();
		for(int i=0; i<invList.size(); i++){
			InventoryItem item = invList.get(i);
			if(item.getInventoryQuantity() < item.getThreshold()){
				InventoryOrder invOrder = new InventoryOrder();
				invOrder.createOrder(item.getSupplier(), item.getName(), 100, item.getPrice());
				System.out.println("Added new order for: " + item.getName());
			}
		}
	}
	
	public void fullfillInventoryOrder(){
		System.out.println("incomplete method");
	}
}