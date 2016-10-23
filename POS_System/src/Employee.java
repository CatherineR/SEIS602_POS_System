import java.io.*;
import java.util.Scanner;

public class Employee {
	private String userName = "not available";
	private String password ="not available";
	private String role = "not available";

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
	

}
