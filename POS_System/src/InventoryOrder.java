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

}
