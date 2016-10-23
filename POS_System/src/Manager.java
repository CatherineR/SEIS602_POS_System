
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
		System.out.println("incomplete method");
	}
	
	public void fullfillInventoryOrder(){
		System.out.println("incomplete method");
	}
}