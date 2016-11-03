import java.util.UUID;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Date;
import java.util.Scanner;

public class Register {
	private final String EmployeeFile = "./././res/EmployeeFile.txt";
	private Path registerSessionFile  = Paths.get("./././res/RegisterSession.txt");
	private int registerID;
	private UUID registerSession;
	private String userName;
	private String userRole;
	private boolean isLoggedIn;
	private Date sessionStartTime;
	private Date sessionEndTime;
	private Scanner scan;
	private SalesOrder receipt;
	private double sessionTotalSale;
	private int totalTransactions;
	
	public Register(int registerID){
		this.registerID = registerID;
	}
	public void login(String userName, String password){		
		String employeeRole = "1. not available";
		if(!isLoggedIn){
			
			employeeRole=checkEmployeeCredential(userName, password);
			
			if(employeeRole.toLowerCase().equals("manager") 
					|| employeeRole.toLowerCase().equals("cashier")){
				this.userName = userName;
				this.userRole = employeeRole;
				this.isLoggedIn = true;
				this.sessionStartTime = new Date();
				this.sessionEndTime = null;
				this.registerSession = UUID.randomUUID();
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
		String sessionUserName = this.userName;
		
		this.isLoggedIn = false;
		this.sessionEndTime = new Date();
		this.logRegisterSession();
		this.userName = null;
		this.registerSession = null;
		this.receipt = null;
		this.totalTransactions = 0;
		this.sessionTotalSale = 0.0;
		System.out.println(sessionUserName+ ": You have successfully logged out of the system." +
							"\nLogoff time: " + this.sessionEndTime);
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
	
	public boolean hasTransaction(){
		return (receipt != null);
	}
	
	public UUID beginTransaction(){
		if (hasTransaction() == false) {
			receipt = new SalesOrder();	
		}
		return registerSession;
	}
	
	public SalesOrder cancelTransaction(){
		receipt = null;
		return receipt;
	}
	
	public void commitTransaction(){
		 if(hasTransaction() == true){
			 sessionTotalSale = sessionTotalSale +receipt.getTotalprice();
			 totalTransactions ++;
			 receipt.commitSalesOrder();
			 receipt.showReceipt();
			 //receipt = null;
		 }
		 else{
			 System.out.println("Commit denied. Please begin transaction before attempting to commit.");
		 }
	}
	
	public void addItem(String name, int quantity){
		if(hasTransaction() == true){
			receipt.addItemList(new SalesOrderItem(name, quantity));
		}
		else{
			 System.out.println("Item not added. Please begin transaction before attempting to add item.");
		 }
	}
	
	public void removeItemList(int itemNumber){
		if(itemNumber>=0)
			receipt.removeItemList(itemNumber-1);
		else
			System.out.println(itemNumber + " is an invalid entry.\n");
	}
	
	public void returnSalesOrder(UUID salesOrderID){
		System.out.println("to be implemented");
	}
	
	public double totalSale(){
		System.out.println("not tested yet");
		return receipt.getTotalprice();
	}
	
	public double receivePayment(double payment){
		double change = payment - receipt.getTotalprice();
		return change;
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
	
	public void logRegisterSession(){
		int counter =0;
		while((isLoggedIn==false) && (sessionEndTime!=null) && counter<1){
			Charset charset = Charset.forName("US-ASCII");
			try(BufferedWriter writer = Files.newBufferedWriter(registerSessionFile, charset, StandardOpenOption.APPEND)) {
				String sessionInfo = registerSession + "|" + registerID+ "|" +userName + "|" + userRole + "|" +
									sessionStartTime + "|" + sessionEndTime + "|" + sessionTotalSale + "|" +totalTransactions;
				
				writer.append(sessionInfo);
				writer.newLine();
				writer.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
			counter++;
		}
	}
	
	public double getTotalTransactions(){
		return totalTransactions;
	}
	
	public double getSessionTotalSale(){
		return sessionTotalSale;
	}
	
	public void showReceipt(){
		if(this.hasTransaction())
			receipt.showReceipt();
		else
			System.out.println("No pending receipt to display.");
	}
}
