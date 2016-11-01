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

public class DeliveredItemsDAO {
	private final String deliveryFile = "./././res/DeliveryList.json";
	
	private JSONObject openFile(){
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = new JSONObject();
		try {
			Object obj = parser.parse(new FileReader(deliveryFile));
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
			FileWriter writer = new FileWriter(deliveryFile);
			writer.write(json.toJSONString());
		    writer.flush();
		    writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	private List<DeliveredItem> jsonToDelList(JSONArray json){
		//Convert JsonArray to List of Inventory Items
		List<DeliveredItem> delList = new ArrayList<DeliveredItem>();	
		
		//Using gson library for conversion
		Type listOfTestObject = new TypeToken<List<DeliveredItem>>(){}.getType();
		String s = new Gson().toJson(json, listOfTestObject);		
		delList = new Gson().fromJson(s, listOfTestObject);	
		
		return delList;
	}
	
	private JsonArray delListToJson(List<DeliveredItem> list){
		//Using gson library for conversion
		Gson gson = new Gson();
		JsonElement element = gson.toJsonTree(list, new TypeToken<List<DeliveredItem>>() {}.getType());
		JsonArray jsonArray = element.getAsJsonArray();	
		
		return jsonArray;
	}
	public List<DeliveredItem> getDelList() {				
		JSONObject jsonObject = openFile();
		JSONArray deliveredItems = (JSONArray) jsonObject.get("DeliveredItems");				
		List<DeliveredItem> deliveredList = jsonToDelList(deliveredItems); 					
			
		return deliveredList;
	}
}
