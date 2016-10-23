import java.io.*;
import java.util.Scanner;

public class Employee {
	private String userName = "not available";
	private String password ="not available";
	private String role = "not available";
	private Scanner scan;

	//default constructor
	public Employee(){

	}
	
	public Employee(String userName, String password){
		this.userName = userName;
		this.password = password;
	}
	
	public String getUserName(){
		return this.userName;
	}
	
	public String getPassword(){
		return this.password;
	}
	
	//create a user user name and 
	public String login(String userName, String password){
		//Employee currnetEmployee = new Employee(userName, password);
		String userRole=checkEmployeeCredential(userName, password);
		if(userRole.toLowerCase().equals("manager") 
				|| userRole.toLowerCase().equals("cashier")){
			this.role = userRole;
			//System.out.println("Log in has been authorized. userName: "+ userName +"\t userRole: " + userRole);
		}
		
		return this.role;
	}
	
	
	//checks the employee credentials returns the role of the employee.
	public String checkEmployeeCredential(String userName, String password){
		openEmployeeFile();
		String employeeRole = "not available";
		
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
	
	//open the employee file.
	public void openEmployeeFile(){
		try{
			scan = new Scanner(new File("/Users/olaniaga/Documents/POS_DB/EmployeeFile.txt"));
		}
		catch (Exception e){
			System.out.println("could not find file");
		}
	}
	
	
	//close the employee file.
	public void closeEmployeeFile(){
		scan.close();
	}
	
}
