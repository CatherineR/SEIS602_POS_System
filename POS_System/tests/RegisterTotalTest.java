import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

public class RegisterTotalTest {
	private double UserTotalfromLog=0.0;
	private double UserShiftTotalfromLog=0.0;
	private double RegisterTotalfromLog=0.0;
	Register register1=new Register(23);
	@Test
	public void test() {
		String path="./././res/RegisterSession.txt";
		register1.setUserName("olaniCashier");
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
		    	if(splited[2].equals(register1.getUserName()))
		    		{
		    		
		    		if(WeekLogin.equals(WeekLogoff)&&MonthLogin.equals(MonthLogoff)&&DayLogin.equals(DayLogoff))
		    			{
		    			
		    			UserShiftTotalfromLog=UserShiftTotalfromLog+sessionTotalfromLog;
		    			UserTotalfromLog=UserTotalfromLog+sessionTotalfromLog;
		    			}
		    		}
		    	if(splited[1].equals(String.valueOf((register1.getRegisterID()))))
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

}
