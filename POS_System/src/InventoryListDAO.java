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
	
	public List<InventoryItem> getInventoryList() {	
		List<InventoryItem> inventoryList = new ArrayList<InventoryItem>();				
		JSONObject jsonObject = openFile();
		JSONArray inventoryItems = (JSONArray) jsonObject.get("inventoryItems");
			
		Type listOfTestObject = new TypeToken<List<InventoryItem>>(){}.getType();
		String s = new Gson().toJson(inventoryItems, listOfTestObject);
		inventoryList = new Gson().fromJson(s, listOfTestObject);					
			
		return inventoryList;
	}
	
	
	public void adjustItemQuantity(String name, int adjustmentAmount){
		List<InventoryItem> inventoryList = getInventoryList();
		for(InventoryItem item : inventoryList){
			if(item.getName().equals(name)){
				int newQuantity = item.getInventoryQuantity() + adjustmentAmount;	
				item.setInventoryQuantity(newQuantity);	
			}
		}		
		JSONObject jsonObject = openFile();
		
		Gson gson = new Gson();
		JsonElement element = gson.toJsonTree(inventoryList, new TypeToken<List<InventoryItem>>() {}.getType());
		JsonArray jsonArray = element.getAsJsonArray();	
		jsonObject.replace("inventoryItems",  jsonArray);
		writeToFile(jsonObject);
		
	}
	
}
