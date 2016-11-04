import java.util.UUID;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
	private double UserTotalfromLog=0.0;
	private double UserShiftTotalfromLog=0.0;
	private double RegisterTotalfromLog=0.0;
	
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
			 receipt.logTransaction(registerSession);
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
		
	public void getSaleAmount() throws NumberFormatException, IOException, ParseException{
		
		String path="./././res/RegisterSession.txt";
		
		BufferedReader bufferLog = null;
		try {
		    bufferLog= new BufferedReader(new FileReader(path));
		    String singleLineLog = null;
		    
		    singleLineLog=bufferLog.readLine();
		    
		    while ((singleLineLog = bufferLog.readLine()) != null) 
		      {
		    	String[] splited = singleLineLog.split("[|]");
		    	String WeekLogin=splited[4].substring(0,3);
		    	String MonthLogin=splited[4].substring(4, 7);
		    	String DayLogin=splited[4].substring(8, 10);
		    	String WeekLogoff=splited[5].substring(0,3);
		    	String MonthLogoff=splited[5].substring(4, 7); 
		    	String DayLogoff=splited[5].substring(8, 10);
		    	System.out.println(splited[1]);
		    	double sessionTotalfromLog=Double.parseDouble(splited[6]);
		    	if(splited[2].equals(getUserName()))
		    		{
		    		
		    		if(WeekLogin.equals(WeekLogoff)&&MonthLogin.equals(MonthLogoff)&&DayLogin.equals(DayLogoff))
		    			{
		    			
		    			UserShiftTotalfromLog=UserShiftTotalfromLog+sessionTotalfromLog;
		    			UserTotalfromLog=UserTotalfromLog+sessionTotalfromLog;
		    			}
		    		}
		    	if(splited[1].equals(String.valueOf((getRegisterID()))))
		    	{
		    		RegisterTotalfromLog=RegisterTotalfromLog+sessionTotalfromLog;
		    	}
		    	UserTotalfromLog=UserTotalfromLog+sessionTotalfromLog;
		     }
		    System.out.println(UserShiftTotalfromLog);
		    System.out.println(UserTotalfromLog);
		    System.out.println(RegisterTotalfromLog);
		    
		   
		} catch (IOException e) {
		    System.out.println("There was a problem: " + e);
		    e.printStackTrace();
		} finally {
		    try {
		        bufferLog.close();
		    } catch (Exception e) {
		    }
		}
	
	}
		
	public double getUserShiftTotal(){
		return UserShiftTotalfromLog;
	}
	public double getUserTotalSale(){
		return UserTotalfromLog;
		
	}
	public double getRegisterTotal(){
		return RegisterTotalfromLog;
	}
	
}
