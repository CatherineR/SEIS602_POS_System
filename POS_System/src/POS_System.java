import java.util.Scanner;

public class POS_System {
	
	
	private static Register register1;
	
	public static void main(String[] args){
		
	    Scanner input = new Scanner(System.in);
	    
		System.out.println("Welcome to the Point-Of-Sale Registration System" + "\n");
		String inputText;
		do{
			System.out.println("Type in '1' to login");
			System.out.println("Type in 'Quit' to exit");
			inputText = input.nextLine();
			if(inputText.equals("1")){
				System.out.println("Type in '1' if you would like to use Register 1");
				System.out.println("Type in '2' if you would like to yse Register 2");
				String registerChoice =  input.nextLine();
				if(registerChoice.equals("1")){
					register1 = new Register(1);
				}
				else{
					register1 = new Register(2);
				}
				
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
		}while(!inputText.equals("Quit"));
		System.out.println("Goodbye");
		System.exit(0);
	}
	private static void startSalesMenu(){
		System.out.println("Type in '1' if you would like to begin scanning items");
		//System.out.println("Type in '2' if you would like to check out");
		System.out.println("Type in '2' if you would like to return items");	
		System.out.println("Type in '3' if you would like to go back to main menu");	
		Scanner input=new Scanner(System.in);
		int choise=input.nextInt();
		if(choise==1)
		{
			doScan();
		}

		if(choise==2)
		{
		//Register.removeItem();
			
			//register1.removeItem(name, quantity);
		}
		if(choise==3){
			//exit to main menu
		}
	}
	private static void doScan(){
		System.out.println("************Scanning begins************");				
		register1.beginTransaction();
		Scanner input = new Scanner(System.in);
		String scanInput;
		do{
			System.out.println("Input the item's name then press enter");
			String name=input.next();
			System.out.println("Input the item's quantity then press enter");
		    int quantity=input.nextInt();
		    try{
		    	register1.addItem(name, quantity);
		    }
		    catch(ItemNotFound e){
		    	System.out.println(e);
		    }
		    
		
			System.out.println("Enter 'Y' to continue or enter 'N' to check out or enter 'R' to remove item ");
			scanInput = input.next();
			if(scanInput.equals("R"))
			{
				doRemover();
			}
		}
		while(!scanInput.equals("N"));
		doCheckOut();
				
				
				
	}
	private static void doCheckOut(){
			System.out.println("*************Check out **************");
			
			//print salesorder(receipt) and total price
			System.out.println("User:"+register1.getUserName());
			register1.commitTransaction();
			
			System.out.println("Payment amount received:");
			Scanner input = new Scanner(System.in);
			double payment=input.nextDouble();
			System.out.println("Received payments:"+payment);
			System.out.println("Change is:"+register1.receivePayment(payment));
			if(register1.hasTransaction())
			{ 
				register1.cancelTransaction();	
			}	
			
			
			
	}
		
	private static void doRemover(){
		Scanner input=new Scanner(System.in);	
		do{
		System.out.println("**************Input item's number you want to remove**********");
			register1.removeItemList(input.nextInt());
		System.out.println("Items have been removed from order successfully");
		System.out.println("Enter '1' to continue removing or enter any number go back to scan");
		  }
		while(input.nextInt()==1);
	}
				
			
			
		
	
	private static void startInventoryManagerMenu(){
		Manager manager = new Manager();
		String choice;
		do{
		System.out.println("----------");	
		System.out.println("Enter '1' to update inventory quantity");
		System.out.println("Enter '2' to add an inventory item");
		System.out.println("Enter '3' to remove an inventory item");
		System.out.println("Enter '4' to create inventory orders");
		System.out.println("Enter '5' to fulfill inventory orders");
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
				System.out.println("What is the name of the item to remove from inventory? ");
				String oldInventoryItemName = input.nextLine();
				manager.removeInventoryItem(oldInventoryItemName);
			}
			if(choice.equals("4")){
				manager.createOrders();
			}
			if(choice.equals("5")){
				manager.fullfillInventoryOrder();
			}
			
		}while(!choice.equals("0"));//End Inventory menu
	}
		
}
