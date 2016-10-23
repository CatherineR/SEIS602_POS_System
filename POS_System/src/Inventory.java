
public class Inventory {
	public void deleteInventoryItem(String name){
		InventoryListDAO invListDAO = new InventoryListDAO();
		invListDAO.deleteItem(name.toLowerCase());
	}
	
	public void createInventoryItem(String itemName, int quantity, double price, String supplier, double taxRate, int threshold){
		InventoryListDAO invListDAO = new InventoryListDAO();
		invListDAO.addItem(itemName.toLowerCase(), quantity, price, supplier.toLowerCase(), taxRate, threshold);
	}
	
	public void updateInventoryQuantity(String itemName, int quantity){
		InventoryListDAO invListDAO = new InventoryListDAO();
		invListDAO.adjustItemQuantity(itemName.toLowerCase(), quantity);		
	}

}
