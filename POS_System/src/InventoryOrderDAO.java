import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

public class InventoryOrderDAO {

	private final String inventoryFile = "./././res/InventoryOrderList.json";
	
	private JSONObject openFile(){
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = new JSONObject();
		try {
			Object obj = parser.parse(new FileReader(inventoryFile));
			jsonObject = (JSONObject) obj;
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return jsonObject;			
	}
	private void writeToFile(JSONObject json){

		try {
			FileWriter writer = new FileWriter(inventoryFile);
			writer.write(json.toJSONString());
		    writer.flush();
		    writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	private List<InventoryOrder> jsonToInvOrder(JSONArray json){
		//Convert JsonArray to List of Inventory Items
		List<InventoryOrder> inventoryOrder = new ArrayList<InventoryOrder>();	
		
		//Using gson library for conversion
		Type listOfTestObject = new TypeToken<List<InventoryOrder>>(){}.getType();
		String s = new Gson().toJson(json, listOfTestObject);		
		inventoryOrder = new Gson().fromJson(s, listOfTestObject);	
		
		return inventoryOrder;
	}
	
	private JsonArray invOrderToJson(List<InventoryOrder> list){
		//Using gson library for conversion
		Gson gson = new Gson();
		JsonElement element = gson.toJsonTree(list, new TypeToken<List<InventoryOrder>>() {}.getType());
		JsonArray jsonArray = element.getAsJsonArray();	
		
		return jsonArray;
	}
	
	public List<InventoryOrder> getInventoryOrder() {				
		JSONObject jsonObject = openFile();
		JSONArray inventoryOrders = (JSONArray) jsonObject.get("inventoryOrders");
				
		List<InventoryOrder> inventoryOrder = jsonToInvOrder(inventoryOrders); 					
			
		return inventoryOrder;
	}
	
	public void addOrder(String sName,String iName,int oQuantity, 
			double iPrice){
		List<InventoryOrder> inventoryOrder = getInventoryOrder();
		InventoryOrder newOrder = new InventoryOrder( sName.toLowerCase(), iName.toLowerCase(), oQuantity, 
				 iPrice);
		
		
		inventoryOrder.add(newOrder);	
		JSONObject jsonObject = openFile();			
		JsonArray jsonArray = invOrderToJson(inventoryOrder);
		jsonObject.replace("inventoryOrders", jsonArray);
		writeToFile(jsonObject);
	
	}
}
