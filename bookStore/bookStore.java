package bookStore;

import java.util.Scanner;

public class bookStore {

	public static void main(String[] args) {
		String password = "password"; //default password
		Scanner keyIn = new Scanner(System.in); //declare Scanner
		
		//intro ------------------
		System.out.println("Author: Duc Nguyen");
		System.out.println("-------------------------------------");
		System.out.println("Welcome to book managing software! ");
		System.out.println("-------------------------------------");
		//---------------
		
		
		System.out.println("type in your maximum number of books can contain: ");//ask the maximum space
		int maxBooks = keyIn.nextInt();
		book[] inventory = new book[maxBooks];  
		//the array which contains all the books has length of maximum capacity of store
		
		int option;            // variable keeps the value coordinates with the option user enter 
		
		int failedOut = 0;     // variable keeps track of the time user enters password incorrectly in the main menu
		
		do {
			//-the menu of options for user
			String menu = "What do you want to do? \n"
					+          "  0. Change password \n"
					+          "  1. Enter new books (password required) \n"
					+          "  2. Change information of a book (password required) \n"
					+          "  3. Display all books by a specific author \n"
					+          "  4. Dispay all books under a certain price. \n"
					+          "  5. Quit \n" ;
			String goBack = "\n Directing back to main menu ... \n ";
			
			System.out.println(menu);
			
			System.out.print("Please enter your choice >"); // ask user for their option they want to perform
			option = keyIn.nextInt();
			
		    int failedIn = 0; // variable to keep track of  the time user enters password incorrectly inside 1 particular option
			//--------------------------------------------------
			
		    
			
			if (option == 1 || option == 2 || option == 0) // the first 3 cases when option requires users to check password to make changes
				{
				boolean pass = false; 
				
			do {
				System.out.println("Enter password: ");  // ask user to enter password
				String inputKey = keyIn.next();
				if (inputKey.equalsIgnoreCase(password)) {  //check if password user entered is correct or not
					pass = true;
				}
				else {
					failedIn++; //increase because user enter password incorrectly
				}
				
			} while (failedIn < 3 && pass == false); // user has 3 times to enter password 
			
			if (pass == true) { //if finally, user enter the right password
				switch(option) {
				case 0:  //change password
					keyIn.nextLine();
					System.out.println("Enter new password: ");
					String newPass = keyIn.nextLine();
					System.out.println("re-enter again: ");  // ask user to enter the password twice and check if they match
					String reEnter = keyIn.nextLine();
					if(reEnter.equals(newPass)) {
						password = newPass;
						System.out.println("\n Password changed ! \n"); //password was changed successfully
						System.out.println(goBack);
						break;
					} else {
						System.out.println("does not match !");
						System.out.println("please try again later"); //can not change password so going back to main menu
						System.out.println(goBack);
						break;
					}
					
				
				
				case 1:
					System.out.println("How many books do you want to enter?  ");
					
					int newEntries = keyIn.nextInt(); //keep the desired number of books user want to create now
					
					//get number of created books and calculate remained slots
					int createdBooks = book.findNumberOfCreatedBooks();
					int spaceRemain = maxBooks - createdBooks;
					//----
					
					if (newEntries <= spaceRemain) { // check if there is still slots for new books
						
						//processing to add the new books to inventory
						for (int i=0; i<newEntries; i++) {  // a loop for creating books as user wants
							System.out.println("Start Creating books....");
							int bookNumber = i+1;
							System.out.println("creating book " + bookNumber);
							System.out.println("Please start entering your desired book");
							
							keyIn.nextLine();
							//enter the details of book you want to create
							System.out.println("Enter author: ");
							String author = keyIn.nextLine();
							
							System.out.println("Enter title: ");
							String title = keyIn.nextLine();
							
							System.out.println("Enter ISBN: ");
							long ISBN = keyIn.nextLong();
							
							System.out.println("Enter price: ");
							double price = keyIn.nextDouble();

							 
							
							int currentSlot = book.findNumberOfCreatedBooks();
							inventory[currentSlot] = new book(author, title, ISBN, price); //create book by the information was given
							System.out.println("your book has been created successfully and has the details:\n"
									+ inventory[currentSlot]);
						
						}
						} else //when the number of books exceeds the remaining available space in inventory
							{
							System.out.println("the number of books you are requesting exceeds the remaining space of your inventory"
									+ "\nPlease try again\n");
						}
					break;
				case 2: //change information of a book
					boolean retry = false; 
					do {
					System.out.println("Which book do you want to change information? <entries start from 0>"); 
					int wantedBook = keyIn.nextInt();
					
					if ((wantedBook > book.findNumberOfCreatedBooks()) || (inventory[wantedBook] == null) || (wantedBook < 0)) //the number does not match any existing book in inventory
					{
					 System.out.println("Notice: The entered number does not match any existing book in the inventory.");
					 System.out.println("Do you want to re_enter the number of the book you want to change ? Y/N__");
					 String decision = keyIn.next(); // ask if user wants to enter the index of book they want to change again.
					 if (decision.equalsIgnoreCase("n")) {
						 retry = false;
						 System.out.println(goBack);
					 } else { retry = true;} // if retry = true then the loop continues again
						
					} else // the number entered matches an existing book in inventory
					{
						retry = false; //no retry required since the user is changing information in an EXISTING book object. 
						book target = inventory[wantedBook];
						System.out.println("Book # " + wantedBook); // print out information of the book user is wanting to change
						System.out.println(target + "\n");
						int choice = 5;
						do {
						System.out.print("What information would you like to change?\n"
								+"\t1.\tauthor\n"
								+"\t2.\ttitle\n"
								+"\t3.\tISBN\n"
								+"\t4.\tprice\n"
								+"\t5.\tQuit\n"
								+"Enter your choice >");
						choice = keyIn.nextInt();
						keyIn.nextLine();
						switch(choice) {
						case 1:
							System.out.println("Please enter a new author name: ");
							String updated_Author = keyIn.nextLine();
							target.setAuthor(updated_Author);
							break;
							
						case 2:
							System.out.println("Please enter a new title name: ");
							String updated_Title = keyIn.nextLine();
							target.setTitle(updated_Title);
							break;
							
						case 3:
							System.out.println("Please enter a new title name: ");
							long updated_ISBN = keyIn.nextLong();
							target.setISBN(updated_ISBN);
							break;
							
						case 4:
							System.out.println("Please enter a new price ");
							double updated_Price = keyIn.nextDouble();
							target.setPrice(updated_Price);
							break;
							
						default:
							break;
						}
						
						if ((choice == 1) ||(choice == 2) || (choice == 3) || (choice == 4) || (choice == 5)) {
							System.out.println("Here is the information of the book after updating:");
							System.out.println("\n" +target + "\n");
						}
						
						} while (choice != 5);
						System.out.println(goBack);
					}
				} while (retry == true);
					
			}  //end for nearest switch
				} else //when enter password incorrectly
			{
				if (option == 2) // if option is 2 then user has unlimited times to try again
				{failedOut = 0;} //reset variable keeps track of faulty password 
				else {failedOut++;}
			}
			
			} 
			else if(option == 3 || option == 4)  //if user chose option 3 or 4
			{
				switch(option) {
				case 3:
					System.out.println("Please enter the author you want to search for: ");
					String searchAuthor = keyIn.next();
					book.findBooksBy(inventory, searchAuthor);
					System.out.println(goBack);
					break;
					
				case 4:
					System.out.println("Please enter a certain price");
					double maxPrice = keyIn.nextDouble();
					book.findCheaperThan(inventory, maxPrice);
					System.out.println(goBack);
					break;
				}
			}
		} while (option != 5 && failedOut<4);
		
		if(failedOut == 4) { // when user enters 4 times incorrectly ( each 3 times in an option means 1 big times)
			System.out.println("Program detected suspicous activities and will terminate immediately! ");
			System.exit(0);
		}
		else if(option == 5) { // if user chose option 5. quit
			System.out.println("- Thank you for using our service -");
		} 
		keyIn.close();
	}
}
