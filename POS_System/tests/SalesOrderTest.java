import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class SalesOrderTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		SalesOrderItem s1=new SalesOrderItem(1,"name",1.0,0.1);
		SalesOrderItem s2=new SalesOrderItem(2,"name2",2.0,0.2);
		s1.setItemID("1");
		s2.setItemID("2");
		s1.getItemID();
		assertEquals(1,s1.getorderQuantity());
		assertEquals("name",s1.getname());
		assertEquals(1.0,s1.getprice(),0);
		assertEquals(0.1,s1.gettaxRate(),0);
		
		SalesOrder receipt=new SalesOrder();
		//assertEquals(1,receipt.getQuantity());
		//assertEquals("name",receipt.getname(),"name");
		//assertEquals(1.0,receipt.getprice(),0);
		//assertEquals(0.1,receipt.gettaxRate(),0);
		//List<SalesOrderItem> itemlist=new ArrayList<SalesOrderItem>();
				receipt.addItemList(s1);
				receipt.addItemList(s2);
				receipt.removeItemList(s1);
		//String a=receipt.get(0).getItemID();
	    //String b=receipt.get(1).getname();
	    //for(int i=0;i<receipt.size();i++)
	    //{
	    //System.out.println("ITEM ID:"+" "+receipt.get(i).getItemID()+"\t"+"QUANTITY:"+" "+itemlist.get(i).getorderQuantity()+"\t"+
	    //	"ITEM NAME:"+" "+itemlist.get(i).getname()+"\t"+"ITEM PRICE:"+" "+itemlist.get(i).getprice()+"\t"+"ITEM TAX:"+" "+itemlist.get(i).gettaxRate());
	   // }
	    System.out.println(receipt.getTotalprice());
	    
	    receipt.showReceipt();
	}
}


