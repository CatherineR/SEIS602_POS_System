import java.util.List;
import java.util.UUID;

public class SalesOrder {
	private UUID salesOrderID;
	private List<SalesOrderItem> itemList;
	private double totalPrice;
	
	public double getTotal(){
		return this.totalPrice;
	}
}
