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
	
	public UUID createOrder(String sName,String iName,int oQuantity, 
										double iPrice, Date orderDate)
	{
		InventoryOrderDAO invOrderDAO = new InventoryOrderDAO();
		UUID orderId = invOrderDAO.addOrder(sName, iName, oQuantity, iPrice, orderDate);
		
		//TODO - update Supplier file with lastOrderDate = orderDate
		return orderId;
	}
	public UUID getOrderId(){
		return orderId;
	}

	
}
