import java.util.UUID;
import java.util.Date;

public class Register {
	private int registerID;
	private UUID registerSession;
	private String userName;
	private boolean isLoggedIn;
	private Date sessionStartTime;
	private Date sessionEndTime;
	
	public void login(String userName, String password){
		System.out.println("to be impletemented");
	}
	
	public void logout(String userName){
		System.out.println("to be impletemented");
	}
	
	public void beginTransaction(){
		System.out.println("to be impletemented");
	}
	
	public void cancelTransaction(){
		System.out.println("to be impletemented");
	}
	
	public void commitTransaction(){
		System.out.println("to be impletemented");
	}
	
	public void addItem(String name, int quantity){
		System.out.println("to be impletemented");
	}
	
	public void removeItem(String name, int quantity){
		System.out.println("to be impletemented");
	}
	
	public void returnSalesOrder(UUID salesOrderID){
		System.out.println("to be impletemented");
	}
	
	public double totalSale(){
		System.out.println("to be impletemented, returning 0.0 for now");
		return 0.0;
	}
	
	public void receivePayment(double payment){
		System.out.println("to be impletemented");
	}
}
