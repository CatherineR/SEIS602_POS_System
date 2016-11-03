import static org.junit.Assert.*;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Test;

public class DeliveredItemsDAOTests {

	@Test
	public void getDelList() {				
		DeliveredItemsDAO delItemDAO = new DeliveredItemsDAO();
		List<DeliveredItem> itemList = delItemDAO.getDelList();
				
		int count = 0;
		for(int i=0;i<itemList.size();i++){
			count++;
		}
		
		assertEquals(count, itemList.size());
	}

}
