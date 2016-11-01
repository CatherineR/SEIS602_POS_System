import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Date;

import org.junit.Test;

public class SupplierFileDAOTestCases {

	@Test
	public void testDeleteSupplier() throws IOException {
		System.out.println("testDeleteSupplier: ");
		SupplierFileDAO supplierDAO = new SupplierFileDAO();
		supplierDAO.deleteSupplier("Supplier21");
		supplierDAO.commitUpdate();
		System.out.println();
	}
	
	@Test
	public void testAddSupplier() throws IOException {
		System.out.println("testAddSupplier: ");
		SupplierFileDAO supplierDAO3 = new SupplierFileDAO();
		supplierDAO3.addSupplier("Olani", new Date(),new Date(), "updated phone1", "updated address1");
		supplierDAO3.commitUpdate();
		System.out.println();
	}

	@Test
	public void testupdateLastOrderDate() throws IOException {
		System.out.println("testupdateLastOrderDate: ");
		SupplierFileDAO supplierDAO2 = new SupplierFileDAO();
		supplierDAO2.updateLastOrderDate("Suplier221221", new Date());
		supplierDAO2.commitUpdate();
		System.out.println();
	}
	
	@Test
	public void testupdateSupplier() throws IOException {
		System.out.println("testupdateSupplier: ");
		SupplierFileDAO supplierDAO3 = new SupplierFileDAO();
		supplierDAO3.updateSupplier("Messing around", "Olani", new Date(),new Date(), "updated phone1", "updated address1");
		supplierDAO3.commitUpdate();
		System.out.println();
	}

}
