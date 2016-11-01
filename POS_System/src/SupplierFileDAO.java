import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;


public class SupplierFileDAO {
	private final String supplierFilePath = "./././res/SupplierFile.txt";
	private ArrayList<String> suppliers = new ArrayList<>();
	
	private Path supplierFile = Paths.get(supplierFilePath);
	private Charset charset = Charset.forName("US-ASCII");
	private boolean isSupplierFileImported = false;
	
	public SupplierFileDAO() throws IOException{
			
		}
	
	/**
	 * The addSupplier adds a supplier to the supplier file. 
	 * This method works if and only if the supplier is not in the file already.
	 * @param supplierName
	 * @param firstOrderDate
	 * @param lastOrderDate
	 * @param phoneNumber
	 * @param address
	 */
	public void addSupplier(String supplierName,Date firstOrderDate, 
							Date lastOrderDate, String phoneNumber, String address){
		//addSupplier, if it doesn't exist.
		if(locateSupplier(supplierName) ==-1){
			String newSupplier= supplierName +"|"+ firstOrderDate+ "|"
				+ lastOrderDate+ "|" +phoneNumber + "|" 
				+ address;
			suppliers.add(newSupplier);
			commitUpdate();
		}
		else{
			System.out.println("addSupplier: Supplier is already in database, please update instead.");
		}
		
	}
	/**
	 * The deleteSupplier method is used to delete supplier from the supplier file.
	 * Make sure to pass the supplierName as an argument. 
	 * If the supplier is not available. It will return -1. 
	 * if supplier is available it returns the location from which the supplier was removed.
	 * @param supplierName
	 * @return
	 */
	public int deleteSupplier(String supplierName){
		int oldSupplierPosition = locateSupplier(supplierName);
		if (oldSupplierPosition!=-1){
			System.out.println(oldSupplierPosition + " found items to delete.\n");
			suppliers.remove(oldSupplierPosition);
			System.out.println("deleteSupplier: Information pertaining to "+ supplierName + "will be deleted, upon next commit.");
		}
		else{
			System.out.println("deleteSupplier: No information was found for supplier named: " + supplierName);
		}
		return oldSupplierPosition;
	}
	
	/**
	 * The updateSupplier method is used to update all information about a supplier already in supplier file.
	 * The method will only update for suppliers that are already in the suppliers file.
	 * @param supplierName
	 * @param updatedSuplierName
	 * @param updatedFirstOrderDate
	 * @param updatedLastOrderDate
	 * @param updatedPhoneNumber
	 * @param updatedAddress
	 */
	public void updateSupplier(String supplierName, String updatedSuplierName,Date updatedFirstOrderDate, 
								Date updatedLastOrderDate, String updatedPhoneNumber, String updatedAddress) 
	{
		int oldSupplierPosition = locateSupplier(supplierName);
		String oldSupplierInfo = "There were no matching suppliers";
		String updatedSupplierInfo = "There were no matching suppliers";
		if (oldSupplierPosition!=-1){
			updatedSupplierInfo= updatedSuplierName +"|"+ updatedFirstOrderDate+ "|"
					+ updatedLastOrderDate+ "|" +updatedPhoneNumber + "|" 
					+ updatedAddress; 
			oldSupplierInfo=suppliers.get(oldSupplierPosition);
			suppliers.set(oldSupplierPosition,updatedSupplierInfo); //update information.
			
			
			System.out.println("Supplier information has been updated as follow.");
			System.out.println("Old info: \t"+ oldSupplierInfo);
			System.out.println("updated info: \t"+ updatedSupplierInfo +"\n");
		}
		else{
			System.out.println("updateSupplier: No information was found for supplier named: " + supplierName);
		}
	}
	
	/**
	 * The updateLastOrderDate(String supplierName, Date updatedLastOrderDate) method is used
	 * to update the last order date for a supplier already in the suppliers file.
	 * @param supplierName
	 * @param updatedLastOrderDate
	 */
	public void updateLastOrderDate(String supplierName, Date updatedLastOrderDate){
		int oldSupplierPosition = locateSupplier(supplierName);
		String oldSupplierInfo = "There were no matching suppliers";
		String updatedSupplierInfo = "There were no matching suppliers";
		
		if (oldSupplierPosition!=-1){
			oldSupplierInfo = suppliers.get(oldSupplierPosition);
			String[] properties = oldSupplierInfo.split("[|]");
			
			updatedSupplierInfo= properties[0] +"|"+ properties[1]+ "|"+ updatedLastOrderDate + "|" +properties[3]+ "|" + properties[4];
			suppliers.set(oldSupplierPosition, updatedSupplierInfo); //add new supplier row to suppliers list;
			
			System.out.println("Supplier information has been updated as follow.");
			System.out.println("Old info: \t"+ oldSupplierInfo);
			System.out.println("updated info: \t"+ updatedSupplierInfo +"\n");
		}
		else{
			System.out.println("updateLastOrderDate: No information was found for supplier named: " + supplierName + "\n");
		}
	}

	/**
	 * The locateSupplier method checks if a supplier exists in the database.
	 * If it exists, it returns the location of the supplier in the database. 
	 * If the supplier doesn't exist. It returns -1. This can be used to maintain data integrity.
	 * @param supplierName
	 * @return pointer (0 based index) to the location of the supplier in the database.
	 * Or -1, if the supplier doesn't exist.
	 */
	private int locateSupplier(String supplierName){
		int indexLocation = -1;
		importSuppliers();
		for(int i = 1; i<suppliers.size(); i++){
			String currentListSupplierName = suppliers.get(i).substring(0, suppliers.get(i).indexOf("|"));
			if(currentListSupplierName.equals(supplierName)){	
				indexLocation = i;
				break;
			}
		}
		return indexLocation;
	}
	
	/**
	 * Used to import all suppliers data from the suppliers file to supplier file data access object.
	 */
	private void importSuppliers(){
		//import occurs only once. This gives the user a chance to continue further updates.
		if (isSupplierFileImported==false){
			try (BufferedReader reader = Files.newBufferedReader(supplierFile, charset))
			{
				String line = null;
				while ((line = reader.readLine()) != null ){
					suppliers.add(line);
				}
				reader.close();
			}catch (IOException e){
				System.out.println(e.getMessage());
			}
			isSupplierFileImported=true;
		}
	}
	
	/**
	 * The commitUpdate will make commit to the supplier file.
	 * Commit will not happen if there are no pending changes.
	 * The method also resets the data access object,
	 * allowing the user to import latest supplier data.
	 */
	public void commitUpdate(){
		if(isSupplierFileImported==true){
			try (BufferedWriter writer = Files.newBufferedWriter(supplierFile, charset))
			{
				Iterator<String> iterator = suppliers.iterator();
				
				while(iterator.hasNext()){
					String s = iterator.next();
					writer.append(s, 0, s.length());
					writer.newLine();
				}
				writer.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		else{
			System.out.println("There are no pending changes to supplier table.");
		}
		
		isSupplierFileImported = false;
	}


}
