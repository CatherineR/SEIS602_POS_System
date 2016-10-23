import static org.junit.Assert.*;
import org.junit.Test;

public class InventoryListDAOTests {

	@Test
	public void test() {
		InventoryListDAO invListDAO = new InventoryListDAO();
		
		invListDAO.adjustItemQuantity("bread", 10);
		
		
	}
	
	
	
}
