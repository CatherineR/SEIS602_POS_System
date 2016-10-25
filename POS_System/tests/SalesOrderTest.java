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
		
		SalesOrder ss=new SalesOrder(s1);
		assertEquals(1,ss.getQuantity());
		assertEquals("name",ss.getname(),"name");
		assertEquals(1.0,ss.getprice(),0);
		assertEquals(0.1,ss.gettaxRate(),0);
		List<SalesOrderItem> itemlist=new ArrayList<SalesOrderItem>();
				itemlist.add(s1);
				itemlist.add(s2);
		String a=itemlist.get(0).getItemID();
	    String b=itemlist.get(1).getname();
	    for(int i=0;i<itemlist.size();i++)
	    {
	    System.out.println("ITEM ID:"+" "+itemlist.get(i).getItemID()+"\t"+"QUANTITY:"+" "+itemlist.get(i).getorderQuantity()+"\t"+
	    		"ITEM NAME:"+" "+itemlist.get(i).getname()+"\t"+"ITEM PRICE:"+" "+itemlist.get(i).getprice()+"\t"+"ITEM TAX:"+" "+itemlist.get(i).gettaxRate());
	    }
	    
	}
}


