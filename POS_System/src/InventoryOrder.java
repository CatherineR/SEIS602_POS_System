import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class InventoryOrder {
	private UUID orderId;
	private Date orderDate;
	private String supplierName;
	private String itemName;
	private int orderQuantity;
	private double itemPrice;
	private double subTotalPrice;
	private Date fulfillmentDate;
	private String status;

	public InventoryOrder(){
		
	}
	
	public InventoryOrder(String sName,String iName,int oQuantity, 
			double iPrice, Date oDate){
		orderId = UUID.randomUUID();
		
		supplierName = sName;
		itemName = iName;
		orderQuantity = oQuantity;
		itemPrice = iPrice;
		fulfillmentDate = null;	
		status = "new";
		orderDate = oDate;
	    itemPrice = iPrice;
	    subTotalPrice = iPrice * oQuantity;
	    
	    
	}
	
	public UUID createOrder (String iName,String sName,int oQuantity, 
										double iPrice, Date orderDate)
	{
		InventoryOrderDAO invOrderDAO = new InventoryOrderDAO();
		UUID orderId = invOrderDAO.addOrder( iName, sName, oQuantity, iPrice, orderDate);
		
		//update supplier's last order date 
		SupplierFileDAO supplier;
		try {
			supplier = new SupplierFileDAO();
			supplier.updateLastOrderDate(sName, orderDate);
		} catch (IOException e) {
			
			e.printStackTrace();
		}		
		
		return orderId;
	}
	public UUID getOrderId(){
		return orderId;
	}
	
	public String getStatus(){
		return status;
	}
	public void setStatus(String newStatus){
		status = newStatus;
	}
	public void setFulfillmentDate(Date newDate){
		fulfillmentDate = newDate;
	}
	public Date getFulfillmentDate(){
		return fulfillmentDate;
	}
	public String getItemName(){
		return itemName;
	}
	public String getSupplierName(){
		return supplierName;
	}
	public int getQuantity(){
		return orderQuantity;
	}
}
