
public class InventoryItem {
	private String name;
	private int inventoryQuantity;
	private double price;
	private String supplier;
	private double taxRate;
	private int threshold; 
	
	public InventoryItem(){	
	}
	public InventoryItem(String itemName, int quantity, double itemPrice, 
			String itemSupplier, double itemTax, int itemThreshold){	
		name = itemName;
		inventoryQuantity = quantity;
		price = itemPrice;
		supplier = 	itemSupplier;
		taxRate = itemTax;
		threshold = itemThreshold;							
	}
	
    public String getName() {
        return name;
    }
    public int getInventoryQuantity() {
        return inventoryQuantity;
    }
    public double getPrice() {
        return price;
    }
    public String getSupplier(){
    	return supplier;
    }
    
    public int getThreshold(){
    	return threshold;
    }
    
    public double getTaxRate(){
    	return taxRate;
    }
    
    public void setInventoryQuantity(int quantity) {
        inventoryQuantity=quantity;
    }
}
