package bookStore;


/**
 * @author Duc Nguyen
 */

// This is the class book to define the object book inside the inventory

public class book {
	
	//declare attributes
	private String title;
	private String author;
	private long ISBN;
	private double price;
	
	//a variable to keep track number of books created
	public static int bookCount = 0;
	
	//a constructor to initialize the book object by setting its attributes
	public book(String author, String title, long ISBN, double price) {
		bookCount++;
		this.title = title;
		this.author = author;
		this.ISBN = ISBN;
		this.price = price;
		
	}
	
	// Mutator and Accessors for attributes
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public long getISBN() {
		return ISBN;
	}
	
	public void setISBN(long ISBN) {
		this.ISBN = ISBN;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	
	
	public String toString() { 
		//overriding the default toString method. 
		String output= ("Author: " + this.author +"\n"
		+"Title: "+ this.title + "\n" 
		+ "ISBN number: " + this.ISBN + "\n"
		+ "Price: $" + this.price + "\n");
		return output;
	}
	
	//a static method to get number of created books
	public static int findNumberOfCreatedBooks() {
		return bookCount;
	}
	
	//override the default equals method
	public boolean equals(book a) {
		if ((this.ISBN == a.getISBN()) && (this.price == a.getPrice())) {
			return true;
		} else {return false;}
	}
	
	// function to look for specific books satisfy the criteria of author
	public static void findBooksBy(book[] inventory, String author) {
		System.out.println("Here are the results match your search criteria: ");
		boolean availability = false;
		for (int i = 0; i<bookCount; i++) {
			if (inventory[i].getAuthor().equalsIgnoreCase(author)) {
				System.out.println(inventory[i] + "\n");
				availability = true;
			}
			if (availability == false) {
				System.out.println("\nUnfortunately, there is no book in inventory meets your criteria\n");
			}
		}
	}
	
	// function to look for specific books satisfy the criteria of price
	public static void findCheaperThan(book[] inventory, double price) {
		System.out.println("Here are the results match your search criteria: ");
		boolean availability = false;
		for (int i = 0; i<bookCount; i++) {
			if (inventory[i].getPrice() <= price) {
				System.out.println(inventory[i] + "\n");
				availability = true;
			}
		if (availability == false) {
			System.out.println("Unfortunately, there is no book in inventory meets your criteria");
		}
	}
}
}


