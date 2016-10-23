import java.util.UUID;
import java.io.File;
import java.util.Date;
import java.util.Scanner;

public class Register {
	private final String EmployeeFile = "/Users/olaniaga/Documents/POS_DB/EmployeeFile.txt";
	private int registerID;
	private UUID registerSession;
	private String userName;
	private String userRole;
	private boolean isLoggedIn;
	private Date sessionStartTime;
	private Date sessionEndTime;
	private Scanner scan;
	
	public void login(String userName, String password){
		System.out.println("Please insert user name followed by password");
		String employeeRole = "1. not available";
		if(!isLoggedIn){
			
			employeeRole=checkEmployeeCredential(userName, password);
			
			if(employeeRole.toLowerCase().equals("manager") 
					|| employeeRole.toLowerCase().equals("cashier")){
				this.userName = userName;
				this.userRole = employeeRole;
				this.isLoggedIn = true;
				this.sessionStartTime = new Date();
				System.out.println("Welcome to POS system,"+ this.userName + "!\n"+
									"You have been logged in as: " + employeeRole
									+"\nLogin time: " + this.sessionStartTime);
				
			}
			else
			{
				System.out.println("Access Denied.");
			}
			
			}
		
		}
	
	public void logout(){
		this.sessionEndTime = new Date();
		this.isLoggedIn = false;
		System.out.println(this.userName + ": You have successfully logged out of the system.\nLogoff time: " + this.sessionEndTime);
		this.userName = null;
	}
	
	
	//checks the employee credentials returns the role of the employee.
	public String checkEmployeeCredential(String userName, String password){
		openEmployeeFile();
		String employeeRole = "2. not available";
		
		while (scan.hasNext()) {
			String fileUserName = scan.next();
			String filePassword = scan.next();
			String fileRole = scan.next();
			
			//System.out.println(fileUserName + " " + filePassword + " " +fileRole);
			
			if (fileUserName.toLowerCase().concat(filePassword).equals(userName.toLowerCase().concat(password))){
				employeeRole = fileRole;
				//System.out.println(fileUserName + " " + filePassword + " " +fileRole);
				break;
			}
			
		}
		closeEmployeeFile();
		return employeeRole;
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
	//open the employee file.
	
	public void openEmployeeFile(){
		try{
			scan = new Scanner(new File(this.EmployeeFile));
		}
		catch (Exception e){
			System.out.println("could not find file");
		}
	}
	
	
	//close the employee file.
	public void closeEmployeeFile(){
		scan.close();
	}

	public boolean getIsLoggedIn(){
		return this.isLoggedIn;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public int getRegisterID() {
		return registerID;
	}

	public UUID getRegisterSession() {
		return registerSession;
	}

	public Date getSessionEndTime() {
		return sessionEndTime;
	}
	
	
}
