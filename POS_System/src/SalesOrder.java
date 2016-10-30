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
	
	public SalesOrderItem setItem(SalesOrderItem item){
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
		return (item.getorderQuantity());
	}
	
	public String getname(){
		return (item.getname());
	}
	
	public double getprice(){
		return (item.getprice());
	}
	public double gettaxRate(){
		return(item.gettaxRate());
	}
	public double setTotalCprice(){
		return totalPrice=((item.getprice()*(1+item.gettaxRate())));
	}
	public double getTotalprice(){
		return this.totalPrice;
	}

	public void getItemList(List<SalesOrderItem> itemList) {
				
		this.itemList = itemList;
	}
	public void addItemList(SalesOrderItem item) {
		itemList.add(item);
		totalPrice += item.getprice();
	}
	public void removeItemList(SalesOrderItem item) {
		itemList.remove(item);
		totalPrice -= item.getprice();
	}
	
	public void cancelOrder(){
		setNumItem(0);
	}
	
	public void showReceipt(){
		for(int i =0; i<itemList.size(); i++){
			System.out.println((i+1)+"\t" +itemList.get(i).getname() + "\t"+ itemList.get(i).getorderQuantity() +"\t"+ itemList.get(i).getprice());
		}
	}
}