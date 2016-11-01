import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
public class InventoryListDAO {
	
	private final String inventoryFile = "./././res/InventoryList.json";
	
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
	
	private List<InventoryItem> jsonToInvList(JSONArray json){
		//Convert JsonArray to List of Inventory Items
		List<InventoryItem> inventoryList = new ArrayList<InventoryItem>();	
		
		//Using gson library for conversion
		Type listOfTestObject = new TypeToken<List<InventoryItem>>(){}.getType();
		String s = new Gson().toJson(json, listOfTestObject);		
		inventoryList = new Gson().fromJson(s, listOfTestObject);	
		
		return inventoryList;
	}
	
	private JsonArray invListToJson(List<InventoryItem> list){
		//Using gson library for conversion
		Gson gson = new Gson();
		JsonElement element = gson.toJsonTree(list, new TypeToken<List<InventoryItem>>() {}.getType());
		JsonArray jsonArray = element.getAsJsonArray();	
		
		return jsonArray;
	}
	
	public List<InventoryItem> getInventoryList() {				
		JSONObject jsonObject = openFile();
		JSONArray inventoryItems = (JSONArray) jsonObject.get("inventoryItems");
				
		List<InventoryItem> inventoryList = jsonToInvList(inventoryItems); 					
			
		return inventoryList;
	}
	
	
	public void adjustItemQuantity(String name, int adjustmentAmount){
		List<InventoryItem> inventoryList = getInventoryList();
		for(InventoryItem item : inventoryList){
			if(item.getName().equals(name)){
				if((item.getInventoryQuantity()+adjustmentAmount) >= 0){
					int newQuantity = item.getInventoryQuantity() + adjustmentAmount;	
					item.setInventoryQuantity(newQuantity);	
					System.out.println(name + " has new quantity " + newQuantity);
				}
			}
		}		
		JSONObject jsonObject = openFile();
	
		JsonArray jsonArray = invListToJson(inventoryList);
		jsonObject.replace("inventoryItems",  jsonArray);
		writeToFile(jsonObject);		
	}
	
	public void addItem(String itemName, int quantity, double price, 
		String supplier, double taxRate, int threshold){
		List<InventoryItem> inventoryList = getInventoryList();
		InventoryItem newItem = new InventoryItem(itemName,quantity,price,supplier,taxRate,threshold);
		boolean itemExists = false;
		
		//Check if the item is already in inventory
		for(InventoryItem item : inventoryList){
			if(item.getName().equals(newItem.getName())){
				itemExists = true;
			}
		}
		
		//Only add if the item does not already exist
		if(!itemExists){
			inventoryList.add(newItem);	
			JSONObject jsonObject = openFile();			
			JsonArray jsonArray = invListToJson(inventoryList);
			jsonObject.replace("inventoryItems", jsonArray);
			writeToFile(jsonObject);
			System.out.println(newItem.getName() + " has been added to inventory.");
		}
		else{
			System.out.println(itemName + " already exists.");
		}
	}
	
	public void deleteItem(String name){
		List<InventoryItem> inventoryList = getInventoryList();
		InventoryItem itemToRemove = findInventoryItem(name);
		
		inventoryList.remove(itemToRemove);
		JSONObject jsonObject = openFile();			
		JsonArray jsonArray = invListToJson(inventoryList);
		jsonObject.replace("inventoryItems", jsonArray);
		writeToFile(jsonObject);
		
	}
	
	public InventoryItem findInventoryItem(String name) throws ItemNotFound{
		List<InventoryItem> inventoryList = getInventoryList();
		InventoryItem itemToReturn = new InventoryItem();
		boolean itemFound = false;
		
		//loop through inventory to find item
		for(InventoryItem item : inventoryList){
			if(item.getName().equals(name)){
				itemToReturn = item;
				itemFound = true;
			}
		}
		if(itemFound){
			return itemToReturn;
		}
		else{
			ItemNotFound e = new ItemNotFound("Item " + name + " does not exist in inventory");
			throw e;
		}
	}

	
}
