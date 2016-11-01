import java.util.Date;
import java.util.UUID;

public class DeliveredItem {
	UUID orderID;
	Date fulfillmentDate;
	
	public DeliveredItem(){
		
	}
	public DeliveredItem(UUID oID){
		orderID = oID;
	}
	public UUID getOrderID(){
		return orderID;
	}
}
