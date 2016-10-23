import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Scanner;

public class SupplierFileDAO {
	private String supplierName;
	private Date firstOrderDate;
	private Date lastOrderDate;
	private String phoneNumber;
	private String address;
	private boolean validEntry= false;
	private String supplierFileLocation = "./././res/SupplierFile.txt";
	private File supplierFile= new File(supplierFileLocation);
	private FileWriter fileWriter; //= new FileWriter(supplierFile, true);
	private BufferedWriter buffer;
	PrintWriter printWriter;
	
	public SupplierFileDAO() throws IOException{
		
	}
	
	public SupplierFileDAO(Supplier supplier)throws IOException{
		this.supplierName = supplier.getSupplierName();
		this.firstOrderDate = supplier.getFirstOrderDate();
		this.lastOrderDate = supplier.getLastOrderDate();
		this.phoneNumber = supplier.getPhoneNumber();
		this.address = supplier.getAddress();
		this.validEntry = true;
	}
	
	public void addToSupplierFile() throws IOException{
		if (supplierFile.exists()==true && this.validEntry==true){
			System.out.println("File exists");
			fileWriter = new FileWriter(supplierFile, true);
			buffer = new BufferedWriter (fileWriter);
			printWriter = new PrintWriter(buffer);
			
			printWriter.println(this.supplierName +"|"+ this.firstOrderDate + "|"+ this.lastOrderDate + "|" + this.phoneNumber+ "|" +this.address);
			
			fileWriter.flush();
			printWriter.flush();
			fileWriter.close();
			printWriter.close();
		}
		else if (supplierFile.exists()==true && this.validEntry==false)
		{
			System.out.println("The file exists but the supplier information is not valid.") ;
		}
		else
		{
			System.out.println("File does NOT exist!");
		}
		

	}
}
