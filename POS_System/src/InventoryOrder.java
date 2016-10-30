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
			double iPrice){
		orderId = UUID.randomUUID();
		
		supplierName = sName;
		itemName = iName;
		orderQuantity = oQuantity;
		itemPrice = iPrice;
		fulfillmentDate = null;	
		status = "new";

	    itemPrice = iPrice;
	    subTotalPrice = iPrice * oQuantity;
	    
	    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	    Date today = new Date();
	    try {
			orderDate = formatter.parse(formatter.format(today));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void createOrder(String sName,String iName,int oQuantity, 
			double iPrice){
		InventoryOrderDAO invOrderDAO = new InventoryOrderDAO();
		invOrderDAO.addOrder(sName, iName, oQuantity, iPrice);
	}
	

	
}
