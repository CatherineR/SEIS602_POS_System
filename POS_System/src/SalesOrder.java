import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class SalesOrder {
	private UUID salesOrderID;
	private List<SalesOrderItem> itemList;
	private double totalPrice;
	private SalesOrderItem item;
	private int numItem;
	
	
	public SalesOrder(){
		salesOrderID = UUID.randomUUID();
		numItem =0;
		totalPrice = 0.0;
		itemList = new ArrayList<SalesOrderItem>();
	}
	
	public SalesOrderItem getItem(){
		return this.item;
	}
	
	public void setSaleorderID(UUID salesOrderID){
		this.salesOrderID=salesOrderID;
	}
	
	public UUID getSaleorderID(){
		return salesOrderID;
	}
	public void setNumItem(int numItem){
		this.numItem=numItem;
	}
	public int getNumItem(){
		return this.numItem;
	}
	public int getQuantity(){
		return (getItem().getorderQuantity());
	}
	
	public String getname(){
		return (getItem().getname());
	}
	
	public double getprice(){
		return (getItem().getprice());
	}
	public double gettaxRate(){
		return(getItem().gettaxRate());
	}
	public double setTotalCprice(){
		return totalPrice=((item.getprice()*(1+item.gettaxRate())*getNumItem()));
	}
	public double getTotalprice(){
		return this.totalPrice;
	}

	public void setItemList(List<SalesOrderItem> itemList) {
				
		this.itemList = itemList;
	}
	public List<SalesOrderItem> getItemList() {
		return itemList;
	}
	
	public void cancelOrder(){
		setNumItem(0);
	}
}
