import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.Test;

public class DeliveredItemTests {

	@Test
	public void getOrderID(){
		UUID randomID = UUID.randomUUID();
		DeliveredItem item = new DeliveredItem(randomID);
		assertEquals(item.getOrderID(), randomID);
	}

}
