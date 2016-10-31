import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

public class InventoryOrderTests {

	@Test
	public void testIncrement() {
		InventoryOrderDAO invOrderDAO= new InventoryOrderDAO();		
		Date currentDate = new Date();
		invOrderDAO.addOrder("Orange Juice", "Doles", 20, 2.50, currentDate);		
	}

}
