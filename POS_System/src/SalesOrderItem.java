public class SalesOrderItem {
	private String name;
	private int orderQuantity;
	private double itemPrice;
	private double subTotal;
	private double taxRate;
	private String itemID;
	

public SalesOrderItem(String name,int orderQuantity){
	   this.name=name;
	   this.orderQuantity=orderQuantity;
	   
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


}
