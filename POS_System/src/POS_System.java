import java.util.Scanner;

public class POS_System {
	

	Register register2 = new Register();
	public static void main(String[] args){
		Register register1 = new Register();
	    Scanner input = new Scanner(System.in);

		System.out.println("Welcome to the Point-Of-Sale Registration System" + "\n");
		while(!register1.getIsLoggedIn()){
			System.out.print("Please enter your username: ");			
			String userName = input.nextLine();
			
			System.out.print("Please enter your password: ");
			String userPassword = input.nextLine();
			
			register1.login(userName, userPassword);
		}		
			String command;
			if(register1.getUserRole().equals("manager")){
				//Begin Manager menu				
				do{
					System.out.println("Type in '1' if you would like to start a Sales Order");
					System.out.println("Type in '2' if you would like to update Inventory");
					System.out.println("Type in '0' to log out");
					command = input.nextLine();
					if(command.equals("1")){
						startSalesMenu();
					}					
					
					if(command.equals("2")){
						startInventoryManagerMenu();
					}
				}while(!command.equals("0"));//end Manager menu
										
			}
			else{
				//Begin Cashier
				do{
					System.out.println("Type in '1' if you would like to start a Sales Order");
					System.out.println("Type in '0' to log out");
					command = input.nextLine();
					if(command.equals("1")){
						startSalesMenu();
					}					
				}while(!command.equals("0"));//end Cashier menu
			}
			register1.logout();
	 
	}
	private static void startSalesMenu(){
		//TODO Sales Order Menu and commands go here
	}
	private static void startInventoryManagerMenu(){
		Manager manager = new Manager();
		String choice;
		do{
		System.out.println("----------");	
		System.out.println("Enter '1' to update inventory quantity");
		System.out.println("Enter '2' to add inventory items");
		System.out.println("Enter '3' to create inventory orders");
		System.out.println("Enter '4' to fulfill inventory orders");
		System.out.println("Enter '0' to exit");
		Scanner input = new Scanner(System.in);
		
			choice = input.nextLine();
			if(choice.equals("1")){
				System.out.println("What is the name of the item to update? ");
				String itemName = input.nextLine();
				System.out.println("What is the amount you would like to adjust it by?  ");
				String quantity = input.nextLine();
				manager.updateQuantity(itemName, Integer.parseInt(quantity));
			}
			if(choice.equals("2")){
				System.out.println("What is the name of the item to add? ");
				String itemName = input.nextLine();
				System.out.println("What is the current quantity of the item to add? ");
				int quantity = Integer.parseInt(input.nextLine());
				System.out.println("What is the price of the item to add? ");
				double price = Double.parseDouble(input.nextLine());
				System.out.println("What is the name of the supplier?");
				String supplier = input.nextLine();
				System.out.println("What is the tax rate?");
				double taxRate = Double.parseDouble(input.nextLine());
				System.out.println("What is the threshold?");
				int threshold = Integer.parseInt(input.nextLine());
				manager.addInventoryItem(itemName, quantity, price, supplier, taxRate, threshold);
				
			}
			if(choice.equals("3")){
				manager.createOrders();
			}
			if(choice.equals("4")){
				manager.fullfillInventoryOrder();
			}
			
		}while(!choice.equals("0"));//End Inventory menu
	}
		
}
