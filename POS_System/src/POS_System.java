import java.util.Scanner;

public class POS_System {
	
	
	Register register2 = new Register();
	
	public static void main(String[] args){
		Register register1 = new Register();
	    Scanner input = new Scanner(System.in);
	    
		System.out.println("Welcome to the Point-Of-Sale Registration System" + "\n");
		String inputText;
		do{
			System.out.println("Type in '1' to login");
			System.out.println("Type in 'Quit' to exit");
			inputText = input.nextLine();
			if(inputText.equals("1")){
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
							
						}					
					}while(!command.equals("0"));//end Cashier menu
				}
				register1.logout();
			}
		while(!inputText.equals("Quit"));
		System.out.println("Goodbye");
	 
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
			
			register1.removeItem(name, quantity);
		}
		if(choise==3){
			//exit to main menu
		}
	}
	private static void doScan(){
		System.out.println("************Scanning begins************");
				Register register1=new Register();
				register1.beginTransaction();
				Scanner input = new Scanner(System.in);
				do
				{
					System.out.println("Input the item's name  then press enter");
					String name=input.next();
					System.out.println("Input the item's quantity then press enter");
				    int quantity=input.nextInt();
					System.out.println("Enter 'Y' to continue or enter 'N' to check out ");
					if(input.next().equals("Y"))
					{
					register1.addItem(name, quantity);
					
							                           }
					else if(input.next().equals("N"))
							
					{	
						doCheckOut();
						}
				}
				while(true);
				
	}
	private static void doCheckOut(){
			System.out.println("*************Check out ************");
			//print salesorder(receipt) and total price
			Register register1=new Register();
			register1.commitTransaction();
			System.out.println("Cashier No.");
			System.out.println("Total price:");
			//tracks payment 
			register1.receivePayment(register1.totalSale());
			
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