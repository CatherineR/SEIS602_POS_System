import java.util.UUID;
import java.util.Date;
import java.util.Scanner;

public class Register {
	private final int allowedAttempts = 3;
	private int registerID;
	private UUID registerSession;
	private String userName;
	private boolean isLoggedIn;
	private Date sessionStartTime;
	private Date sessionEndTime;
	private Scanner scan;
	private int loginAttempts = 1;
	
	public void login(){
		this.scan = new Scanner(System.in);
		System.out.println("Please insert user name followed by password");
		String userName = scan.next();
		String password = scan.next();
		String employeeRole = "NA";
		if(!isLoggedIn){
		Employee currnetEmployee = new Employee(userName, password);
		employeeRole = currnetEmployee.login(userName, password);
		
		}
		if (employeeRole.toLowerCase().equals("manager") || employeeRole.toLowerCase().equals("cashier")){
			this.isLoggedIn = true;
			this.userName = userName;
			this.loginAttempts++;
			this.sessionStartTime = new Date();
			System.out.println("Welcome to POS system,"+ this.userName + "!\n"+
								"You have been logged in as: " + employeeRole
								+"\nLog in time: " + this.sessionStartTime);
			
		}
		else if(this.loginAttempts<this.allowedAttempts)
		{
			System.out.println("Access Denied. You only have total of "+ this.allowedAttempts+" tries. Please try again. You have used " + loginAttempts + " tiers so far.");
			this.loginAttempts++;
			this.login();
		}else
		{
			System.out.println("Access Denied. You have exahusted all "+ this.allowedAttempts+" chances. Please talk to your manager.");
		}
		scan.close();
	}
	
	public void logout(){
		this.sessionEndTime = new Date();
		this.isLoggedIn = false;
		System.out.println(this.userName + ": You have successfully logged out of the system.\nLogoff time: " + this.sessionEndTime);
		this.userName = null;
	}
	
	public void beginTransaction(){
		System.out.println("to be implemented");
	}
	
	public void cancelTransaction(){
		System.out.println("to be implemented");
	}
	
	public void commitTransaction(){
		System.out.println("to be implemented");
	}
	
	public void addItem(String name, int quantity){
		System.out.println("to be implemented");
	}
	
	public void removeItem(String name, int quantity){
		System.out.println("to be implemented");
	}
	
	public void returnSalesOrder(UUID salesOrderID){
		System.out.println("to be implemented");
	}
	
	public double totalSale(){
		System.out.println("to be implemented, returning 0.0 for now");
		return 0.0;
	}
	
	public void receivePayment(double payment){
		System.out.println("to be implemented");
	}
}
