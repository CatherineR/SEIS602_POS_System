import java.util.Date;
import java.util.Scanner;

public class Supplier {
	private String supplierName;
	private Date firstOrderDate;
	private Date lastOrderDate;
	private String address;
	private String phoneNumber;
	
	public Supplier(String supplierName, Date firstOrderDate, Date lastOrderDate,
					String address, String phoneNumber){
		this.supplierName = supplierName;
		this.firstOrderDate = firstOrderDate;
		this.lastOrderDate = lastOrderDate;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}
	
	public void addSupplierToFile(Supplier supplier){
		
	}

	public String getSupplierName() {
		return supplierName;
	}

	public Date getFirstOrderDate() {
		return firstOrderDate;
	}

	public Date getLastOrderDate() {
		return lastOrderDate;
	}

	public String getAddress() {
		return address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	
}
