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
		SalesOrderItem s1=new SalesOrderItem("bread",1);
		SalesOrderItem s2=new SalesOrderItem("cheese",1);
		s1.setItemID("1");
		s2.setItemID("2");
		s1.getItemID();
		
		//testing SalesOrderItem.getname/get quantity
		assertEquals("bread",s1.getname(),"bread");
		assertEquals(1,s1.getorderQuantity());
		assertEquals("cheese",s2.getname(),"cheese");
		assertEquals(1,s2.getorderQuantity());
		
		
		s1.getprice();
		s1.gettaxRate();
		
		//testing SalesOrderItem.getprice and taxrate from Inventory
		
		assertEquals(2.5,s2.getprice(),0.0);
		assertEquals(0.0,s2.gettaxRate(),0.0);
		// creat a new SalesOrder 
		
		SalesOrder receipt=new SalesOrder();
		receipt.addItemList(s1);
		receipt.addItemList(s2);
		receipt.removeItemList("bread");
		//s1has been removed from receipt
		receipt.showReceipt();
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		//assertEquals("name",receipt.getname(),"name");
		//assertEquals(1.0,receipt.getprice(),0);
		//assertEquals(0.1,receipt.gettaxRate(),0);
		//List<SalesOrderItem> itemlist=new ArrayList<SalesOrderItem>();
		//System.out.println(s1.getprice());		
				
				//receipt.addItemList(s2);
				//receipt.removeItemList(s1);
		//String a=receipt.get(0).getItemID();
	    //String b=receipt.get(1).getname();
	    //for(int i=0;i<(receipt.size());i++)
	    //{
	    //System.out.println("ITEM ID:"+" "+receipt.get(i).getItemID()+"\t"+"QUANTITY:"+" "+itemlist.get(i).getorderQuantity()+"\t"+
	    //	"ITEM NAME:"+" "+itemlist.get(i).getname()+"\t"+"ITEM PRICE:"+" "+itemlist.get(i).getprice()+"\t"+"ITEM TAX:"+" "+itemlist.get(i).gettaxRate());
	   //}
	  // System.out.println(receipt.getTotalprice());
	    
	    
	}
}


