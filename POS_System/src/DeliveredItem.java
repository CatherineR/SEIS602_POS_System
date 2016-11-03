import java.util.Date;
import java.util.UUID;

public class DeliveredItem {
	UUID orderID;
	
	public DeliveredItem(){
		
	}
	public DeliveredItem(UUID oID){
		orderID = oID;
	}
	public UUID getOrderID(){
		return orderID;
	}
}
