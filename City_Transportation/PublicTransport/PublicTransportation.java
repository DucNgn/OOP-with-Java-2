/**@Author: Duc Nguyen
*@ID: 40064649
*@COMP249
*@Assignment #2
*@Due_Date: February 24, 2019
*/
// -----------------------------------------------------
// Assignment 2
// Part: 1
// Written by: Duc Nguyen. ID: 40064649
// -----------------------------------------------------


package PublicTransport;

public class PublicTransportation {
	protected double price;
	protected int numOfStops;
	public PublicTransportation() {  //default constructor
		price = 0;
		numOfStops = 0;
	}
	public PublicTransportation(PublicTransportation c) { //copy constructor
		this.price = c.getPrice();
		this.numOfStops = c.getNumOfStops();
	}
	
	public PublicTransportation(double ticket_price, int numOfStops) { //parameter constructor
		
		super();
		this.price = ticket_price;
		this.numOfStops = numOfStops;
	}
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getNumOfStops() {
		return numOfStops;
	}
	public void setNumOfStops(int numOfStops) {
		this.numOfStops = numOfStops;
	}
	

	public String toString() {
		String className = this.getClass().getSimpleName();
		String output = "This " + className + " has ticket price: " + price + " $"
				+ ", and number of stops is: " + numOfStops + " stops";  
		return output;
	}
	
	public boolean equals(Object obj) {
		
		if (obj == null) {return false;} 
		//Even if user try to compare with a null object, this statement will give back false value instead of crashing the whole program
		else if (getClass() != obj.getClass()) {
			return false;
		} else {
			PublicTransportation transport = (PublicTransportation) obj;
			return ((price == transport.price) && (numOfStops == transport.numOfStops));
	}
	}
	
	public static void getCheapest(PublicTransportation[] a) {
		int indexCheapest = 0;
		for(int i =0; i<a.length; i++) {
			if (a[indexCheapest].getPrice() > a[i].getPrice()) {
				indexCheapest = i;
			}
		}
		System.out.println("Here are the information of cheapest means of transportation in the system:"); 
		for(int i=0; i<a.length; i++) {
			if (a[i].price == a[indexCheapest].price) {
				System.out.println(a[i] + "\nFounded at position " + i + " in the array. \n");
			}
		}
	}
	
	public static void getMostExpensive(PublicTransportation[] a) {
		int indexExpensive = 0;
		for(int i =0; i<a.length; i++) {
			if (a[indexExpensive].price <= a[i].price) {
				indexExpensive = i;
			}
		}
		System.out.println("Here are the information of most expensive means of transportation in the system:");
		for(int i = 0; i<a.length; i++) {
			if (a[indexExpensive].price == a[i].price) {
				System.out.println(a[i] + "\nFounded at position " + i + " in the array. \n");
			}
		}
	}
	
}
