public class SalesOrderItem {
	private String name;
	private int orderQuantity;
	private double itemPrice;
	private double subTotal;
	private double taxRate;
	private String ItemID;
	


public void setItemID(String name,double itemPrice,double taxRate){
	   this.name=name;
	   this.itemPrice=itemPrice;
	   this.taxRate=taxRate;
}
public String getItemID(){
	   return this.ItemID;
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
