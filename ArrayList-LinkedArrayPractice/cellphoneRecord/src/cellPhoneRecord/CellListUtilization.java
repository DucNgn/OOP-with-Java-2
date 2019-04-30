/**
 * @author Duc Nguyen
 * COMP249
 * Assignment #4
 * 
 */

package cellPhoneRecord;
import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;
public class CellListUtilization {
	
	public static boolean test(int choice, CellList list, Scanner keyIn) {
		if (choice == 11) {
			System.out.println("Exiting....");
			return true;
		}
		System.out.println("==================TEST BEGINNING============");
		
		switch(choice) {
		case 1:
			System.out.println("\nCreating a fake list by copy constructor");
			CellList fake = new CellList(list);
			System.out.println("\nDisplaying the original list");
			list.showContents();
			System.out.println("\nDisplaying the fake list");
			fake.showContents();
			System.out.println("\n================test with fake list============\n");
			System.out.println("\nDeleting one element at the beginning of fake list");
			fake.deleteFromStart();
			System.out.println("\nDisplaying again the original list");
			list.showContents();
			System.out.println("\nDisplaying again the new fake list");
			fake.showContents();
			break;
			
		case 2:  //ADDTOSTART
			CellPhone test = new CellPhone(9999999, "CONCORDIA", 2019, 1500);
			System.out.println("JUST CREATED A NEW CELL PHONE: " + test);
			System.out.println("adding to the first position of the list");
			list.addToStart(test);
			System.out.println("Displaying the list with new cellphone added");
			list.showContents();
			break;
			
		case 3: //"3. Method insertAtIndex" 
			
			int auto = -1;
			int index = -1;
			boolean match = false;
			
			while (match == false) {
				match = true;
				try {
					System.out.println("Please enter an index to insert the new Cell Phone");
					index = keyIn.nextInt();
					System.out.println("Do you want to create a new object to insert by yourself or let the system generate an object automatically? 1 or 0: ");
					auto = keyIn.nextInt();
				} catch(InputMismatchException e) {
					keyIn.nextLine();
					System.out.println("A problem has occured with your choice. Please try again");
					match = false;
				}
			}
			
			CellPhone demo = new CellPhone(9999999, "CONCORDIA", 2019, 1500);
			if(auto == 0) {
				System.out.println("JUST CREATED A NEW CELL PHONE WITH THE INFORMATION: " + demo.toString());
			} else {
				match = false;
				while (match == false) {
					match = true;
					try {
						System.out.println("\nCREATING NEW CELL PHONE");
						System.out.println("Enter the serial number for the new Cell phone you want to create:");
						demo.setSerialNum(keyIn.nextLong());
						keyIn.nextLine();
						System.out.println("Enter the brand for the new Cell phone you want to create:");
						demo.setBrand(keyIn.nextLine());
						System.out.println("Enter the year for the new Cell phone you want to create:");
						demo.setYear(keyIn.nextInt());
						System.out.println("Enter the price for the new Cell phone you want to create:");
						demo.setPrice(keyIn.nextDouble());
					} catch (InputMismatchException e) {
						keyIn.nextLine();
						System.out.println("A problem has occured with your input while creating your cell phone. Please try again");
						match = false;
					}
				}
				System.out.println("You created a new cellphone: " + demo + " \n");
				
			}
			System.out.println("Inserting the new cell phone " + demo + " to index " + index);
			list.insertAtIndex(demo, index);
			System.out.println("Inserted the requested cell phone. Displaying the new list:");
			list.showContents();
			break;
			
		case 4:  //Delete from index
			int deletePost = -1;
			boolean validate = false;
			while (validate == false) {
				validate = true;
				System.out.println("Please enter an index to delete");
				try {
					deletePost = keyIn.nextInt();
				} catch(InputMismatchException e) {
					keyIn.nextLine();
					System.out.println("Invalid index. Please try again");
					validate = false;
				}
			}
			System.out.println("Deleting the requested object");
			list.deleteFromIndex(deletePost);
			System.out.println("Deleted the requested cell phone. Displaying the new list:");
			list.showContents();
			break;
			
		case 5: //Delete from start
			System.out.println("Deleting to the first object of the list");
			list.deleteFromStart();
			System.out.println("Displaying the list after deleting\n");
			list.showContents();
			break;
			
		case 6: // Replace at Index
			CellPhone replace = new CellPhone(9999999, "CONCORDIA", 2019, 1500);
			System.out.println("JUST CREATED A NEW CELL PHONE FOR TESTING WITH THE INFORMATION: " + replace);
			int replacePost = -1;
			boolean valid = false;
			while (valid == false) {
				valid = true;
				System.out.println("Please enter index of the object you want to delete");
				try {
					replacePost = keyIn.nextInt();
				} catch(InputMismatchException e) {
					keyIn.nextLine();
					System.out.println("Invalid index. Please try again");
					valid = false;
				}
			}
			System.out.println("Replacing the object at index " + replacePost + " with the new cellphone " + replace);
			list.replaceAtIndex(replace, replacePost);
			System.out.println("Displaying the new list after replacing \n");
			list.showContents();
			break;
			
		case 7:
			System.out.println("---------------------------------------------");
			boolean validType = false;
		    while(validType == false) {
		    	validType = true;
		    	try {
		    		System.out.println("Please input the serial number of the cellphone you are looking for: ");
		    		CellPhone result = list.find(keyIn.nextLong());
		    		if(result == null) {
		    			System.out.println("The cell phone you are looking for is not in the list");
		    		} else {
		    			System.out.println("Here is the CellPhone you are looking for: " + result + " after performing " + CellList.iteration + " iterations\n");
		    		}
		    	} catch (InputMismatchException e) {
		    		keyIn.nextLine();
		    		System.out.println("Invalid type of input. Please try again");
		    		validType = false;
		    	}	
		    }
			break;
			
		case 8: //Contains
		    boolean matchType = false;
		    while(matchType == false) {
		    	matchType = true;
		    	try {
		    		System.out.println("Please enter the serial number you want to search for: ");
			    	if(list.contains(keyIn.nextLong())) {
			    		System.out.println("This list DOES contains the cellphone you are looking for.");
			    	} else {
			    		System.out.println("This list DOESNOT contain the cellphone you are looking for.");
			    	}
		    	} catch (InputMismatchException e) {
		    		keyIn.nextLine();
		    		System.out.println("Invalid type of input. Please try again");
		    		matchType = false;
		    	}	
		    }
		    break;
			
		case 9: //showContents
			System.out.println("Showing all the content inside the list");
			list.showContents();
			break;
	
		case 10: //equals\n
			System.out.println("\nCreating a fake list");
			CellList faker = new CellList(list);
			System.out.println("\nDisplaying the fake list");
			faker.showContents();
			System.out.println("Comparing fake list with the original. Result of equals is " + list.equals(faker));
			
			System.out.println("\n=========================Making changes in the fake list by deleting objects ");
			faker.deleteFromStart();
			faker.deleteFromStart();
			faker.deleteFromStart();
			faker.deleteFromStart();
			
			System.out.println("\nDisplaying the fake list after making changes");
			faker.showContents();
			System.out.println("Comparing fake list now with the original. Result of equals is " + list.equals(faker));
			break;
			
		default: 
			System.out.println("A problem has occured. Going back ...");
			return false;
		}
	    System.out.println("\n==================TEST FINISHED============");
	    System.out.println("GOING BACK TO MAIN MENU");
		return false;
	}

	public static void main(String[] args) {
		System.out.println("=================PROGRAM IS STARTING===========");
		System.out.println("openning file");
		Scanner sc = null;
		String fName = "Cell_Info.txt";
		try {
			sc = new Scanner(new FileInputStream(fName));
		} catch(FileNotFoundException e) {
			System.out.println("Couldn't find file. Terminating");
			System.exit(0);
		}
		
		CellList list = new CellList();
		while(sc.hasNextLine()) {
			long serialNum = sc.nextLong();
			String brand = sc.next();
			double price = sc.nextDouble();
			int year = sc.nextInt();
			CellPhone newCell = new CellPhone(serialNum, brand, year, price);
			if(list.canAdd(newCell)) { list.addToStart(newCell); }	
		}
		
		System.out.println("Showing the content of the list: ");
		list.showContents();
		sc.close();
		Scanner keyIn = new Scanner(System.in);
		System.out.println("TESTING METHODS. PLEASE SELECT ONE OPTION BELOW");
		String option = "\nMAIN MENU\n" + "1. A copy constructor\n" 
				+ "2. Method addToStart\n" 
				+ "3. Method insertAtIndex\n" 
				+ "4. Delete from index\n" 
				+ "5. Delete from start\n" 
				+ "6. Replace at Index\n" 
				+ "7. Find\n" 
				+ "8. Contains\n"
				+ "9. showContents\n"
				+ "10. equals\n"
				+ "11. EXIT\n";
		boolean pass = false;
		while(pass == false) {
			System.out.println(option);
			pass = true;
			try {
			    int choice = keyIn.nextInt();
				if(choice > 11 || choice< 1) {
					throw new InputMismatchException();
				} else {
					CellList fake = new CellList(list);
					pass = test(choice, fake, keyIn);
				}
			}
			catch(InputMismatchException e1) {
				pass = false;
				keyIn.nextLine();  //clear input line
				System.out.println("Invalid input. Try again");
			}
		} 
		keyIn.close();
		System.out.println("==============THANK YOU FOR USING OUR SERVICE============");
	}

}
