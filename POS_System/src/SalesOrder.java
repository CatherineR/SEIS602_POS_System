import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class SalesOrder {
	private UUID salesOrderID;
	private List<SalesOrderItem> itemList;
	private double totalPrice;
	private SalesOrderItem item;
	private int numItem;
	
	public double getTotal(){
		return this.totalPrice;
	}
	 
	public void SalesOrderItem(SalesOrderItem item){
			setItem(item);
			//setItem(item);
		     setNumItem(1);
		}
		protected void setItem(SalesOrderItem item){
			this.item=item;
		}
		public SalesOrderItem getItem(){
			return this.item;
		}
		
		public String getItemID(){
			return (getItem().getItemID());
		}
		public void setSaleorderID(){
			this.salesOrderID=salesOrderID;
		}
		public UUID getSaleorderID(){
			return salesOrderID;
		}
		public String getname(){
			return (getItem().getname());
		}
		
		
		public double getprice(){
			return (getItem().getprice());
		}
		public void setNumItem(int numItem){
			this.numItem=numItem;
		}
		public int getNumItem(){
			return this.numItem;
		}
		public double getTotalCprice(){
			return totalPrice=((item.getprice()*(1+item.gettaxRate())*getNumItem()));
		}
		//public void incermentNum(){
		//	setNumItem(+1);
		//}
		public List<SalesOrderItem> getItemList() {
			return itemList;
		}
		public void setItemList(List<SalesOrderItem> itemList) {
			this.itemList = itemList;
		}
		
		public void cancelOrder(){
			setNumItem(0);
		}
	}
