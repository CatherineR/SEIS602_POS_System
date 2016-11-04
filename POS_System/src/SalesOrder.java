import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class SalesOrder {
	private UUID salesOrderID;
	private List<SalesOrderItem> itemList;
	private double totalPrice;
	private SalesOrderItem item;
	private int numItem;
	private Path saleTransactionFile= Paths.get("./././res/Sales.txt");
	
	
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
		return item.getorderQuantity();
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
	public double getTotalprice(){
		return this.totalPrice;
	}

	public void getItemList(List<SalesOrderItem> itemList) {
				
		this.itemList = itemList;
	}
	public void addItemList(SalesOrderItem item) {
		itemList.add(item);
		totalPrice = totalPrice + item.getSubTotal();
		numItem = numItem + item.getorderQuantity(); 
	}
	public void removeItemList(String name) {
		
		for(SalesOrderItem item : itemList)
		{if(item.getname().equals(name))
			{
			itemList.remove(item);
			totalPrice -= item.getSubTotal();
			}
		}
	
	}
	public void removeItemList(int itemNumber) {
		totalPrice -= itemList.get(itemNumber).getSubTotal();
		itemList.remove(itemNumber);
		
	}
	
	public void removeItemList(String name, int quantity) {
		
		for(SalesOrderItem item : itemList)
		{if(item.getname().equals(name))
			{
			totalPrice -= item.getSubTotal();		//remove previous price
			item.setOrderQuantity(quantity);
			totalPrice += item.getSubTotal();
			}
		}
	
	}

	
	public void cancelOrder(){
		setNumItem(0);
	}
	
	public SalesOrderItem getSalesOrderItem(int i){
		return itemList.get(i);
	}
	
	public void showReceipt(){
		for(int i =0; i<itemList.size(); i++){
			System.out.println((i+1)+".\t" +itemList.get(i).getname() + "\t"+ itemList.get(i).getorderQuantity() +"\t"+ itemList.get(i).getSubTotal());
		}
		System.out.println("\t\t\t" + "Total:" + this.getTotalprice());
	}
	public void commitSalesOrder(){
		for(int i =0; i<itemList.size(); i++){
			//System.out.println((i+1)+"\t" +itemList.get(i).getname() + "\t"+ itemList.get(i).getorderQuantity() +"\t"+ itemList.get(i).getprice());
			Inventory inventory = new Inventory();
			inventory.updateInventoryQuantity(itemList.get(i).getname(), -itemList.get(i).getorderQuantity());
		}
	}
	
	public void logTransaction(UUID registerSession){

			Charset charset = Charset.forName("US-ASCII");
			try(BufferedWriter writer = Files.newBufferedWriter(saleTransactionFile, charset, StandardOpenOption.APPEND)) {
				for (SalesOrderItem item: itemList){
					String salesInfo = this.salesOrderID +"|"+ registerSession + "|" +item.getname() + "|" +item.getorderQuantity() + "|" + item.getSubTotal();
					
					writer.append(salesInfo);
					writer.newLine();
				}
				writer.close();
			} catch (IOException e) {
				System.out.println("error with" + e.getMessage());
			}
	}

	public double returnSalesOrder(String salesOrderID, String itemName, int quantity){
		double returnAmount = 0.0;
		int validQuantity = 0;
		Charset charset = Charset.forName("US-ASCII");
		try(BufferedReader reader = Files.newBufferedReader(saleTransactionFile, charset)){
			String saleLog = null;
			String[] saleInfo;
			while ((saleLog = reader.readLine()) != null ){
				saleInfo=saleLog.split("[|]");
				//System.out.println(saleInfo[0]);
				if(saleInfo[0].equals(salesOrderID)){
					//System.out.println("here here " + saleInfo[2] + " " + saleInfo[4]);
					if (saleInfo[2].equals(itemName)){
						if(Integer.parseInt(saleInfo[3])<=quantity){
							Inventory inventoryReturn = new Inventory();
							returnAmount = (Double.parseDouble(saleInfo[4])/Integer.parseInt(saleInfo[3]))*quantity;
							//inventoryReturn.updateInventoryQuantity(itemName, -quantity);			//add the returned item back to inventory;
							System.out.println("You are getting back $" + returnAmount + "\n");
							break;
						}
						else
						{
							validQuantity = Integer.parseInt(saleInfo[3]);
							returnAmount =-1;
							break;
						}	
					}
				}
			}
			reader.close();
		}catch (IOException e){
			System.out.println(" return Sales Order encountered an error: " + e.getMessage());
		}
		
		if(returnAmount==0){
			System.out.println("Sorry, we can't locate records matching your inputs for salesOrderID " +salesOrderID);
		}
		if (returnAmount == -1)
		{
			System.out.println(quantity+ " is an invalid amount! Please try again with " +validQuantity  + " or less items.");
		}
		return returnAmount;
	}
}
