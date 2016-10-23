public class SalesOrderItem {
	private String name;
	private int orderQuantity;
	private double itemPrice;
	private double subTotal;
	private double taxRate;
	private String itemID;
	

public SalesOrderItem(int orderQuantity,String name,double itemPrice,double taxRate){
	   this.name=name;
	   this.itemPrice=itemPrice;
	   this.taxRate=taxRate;
	   this.orderQuantity=orderQuantity;
}
public String setItemID(String itemId){
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
	   return this.taxRate;
}
public void setprice(double price){
	   this.itemPrice=itemPrice;
}
public double getprice(){
	   return this.itemPrice;
}


}
