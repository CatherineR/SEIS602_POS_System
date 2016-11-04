public class SalesOrderItem {
	private String name;
	private int orderQuantity;
	private double itemPrice;
	private double subTotal;
	private double taxRate;
	private String itemID;
	

public SalesOrderItem(String name,int orderQuantity){
	Inventory item = new Inventory();
	InventoryItem itemDetails = item.getInventoryItem(name);	
	this.name=name;
	this.orderQuantity=orderQuantity;
	this.itemPrice = itemDetails.getPrice();
	this.taxRate = itemDetails.getTaxRate();
	this.subTotal = subTotalPrice();
	
}

public String setItemID(String itemID){
	   return this.itemID = itemID;
}
public String getItemID(){
	   return this.itemID;
}
public int getorderQuantity(){
	   return this.orderQuantity;
}
public String getname(){
	   return this.name;
}
public double gettaxRate(){
		Inventory inventory=new Inventory();
		return inventory.getInventoryItem(name).getTaxRate();
}

public double getprice(){
	   Inventory inventory=new Inventory();
	   return inventory.getInventoryItem(name).getPrice();
}

public double getSubTotal(){
	return this.subTotal;
}

public void setOrderQuantity(int orderQuantity){
	this.orderQuantity = orderQuantity;
	this.subTotal = subTotalPrice();
}

private double subTotalPrice(){
	return (1 + this.taxRate/100)*(this.itemPrice * this.orderQuantity);
}

}
