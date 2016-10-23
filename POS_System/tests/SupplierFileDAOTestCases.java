import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Date;

import org.junit.Test;

public class SupplierFileDAOTestCases {

	@Test
	public void test() throws IOException {

		SupplierFileDAO sfdao = new SupplierFileDAO(new Supplier("Suplier2", new Date(), new Date(), "address 2", "phone number 2"));
		
		sfdao.addToSupplierFile();
		
		//fail("Not yet implemented");
	}

}
