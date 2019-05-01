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


package main;
import PublicTransport.*;
import Air.*;
import Air.AirCraft.air;
import Air.AirCraft.maintenance;
import Marine.*;
import Vehicle.*;

public class driver {

	public static void main(String[] args) {
		System.out.println("------------------------------------------------------------------------------"
				+          "\n                 WELCOME TO PUBLIC-TRANSPORTATION_MANAGEMENT_SYSTEM"
				+          "\n VER. 1 "
				+          "\n------------------------------------------------------------------------------");
		
		//testing objects
		System.out.println("CREATING OBJECTS...");
		PublicTransportation a = new PublicTransportation(20, 5);
		CityBus b = new CityBus(3.5, 35, 20, 2002, "green", "David");
		Tram c = new Tram(2, 20, 40, 2005, "red", "Robert", 20);
		Tram g = new Tram(2, 20, 40, 2005, "red", "Robert", 20);
		Metro d = new Metro(3.5, 30, 20, 2010, "orange", "Mike", 12, "Montreal");
		Ferry e = new Ferry(2, 17, 2005, "hope"); 
		CityBus f =new CityBus(3.5, 35, 20, 2002, "green", "David");
		
		
		//testing toString methods
		System.out.println("TESTING TOSTRING METHODS");
		System.out.println(a + "\n");
		System.out.println(b + "\n");
		System.out.println(c + "\n");
		System.out.println(d + "\n");
		System.out.println(e + "\n");
		System.out.println(f + "\n");
		System.out.println(g + "\n");
		System.out.println("END OF TESTING TOSTRING METHOD ----------------\n");
		
		System.out.println("START TESTING EQUALS METHOD \n");
		//testing equals method
		if(a.equals(b)) {
			System.out.println("Two objects are equivalent\n");
		} else {
			System.out.println("Two objects are NOT equivalent \n");
		}    // should result in not equivalent
		
		if(c.equals(g)) {
			System.out.println("Two objects are equivalent\n");
		} else {
			System.out.println("Two objects are NOT equivalent \n");
		}    // should result in equivalent
		
		if(a.equals(c)) {
			System.out.println("Two objects are equivalent\n");
		} else {
			System.out.println("Two objects are NOT equivalent \n");
		}   //should result in not equivalent
		
		if(c.equals(f)) {
			System.out.println("Two objects are equivalent\n");
		} else {
			System.out.println("Two objects are NOT equivalent \n");
		}   //should result in not equivalent
		
		
		//end of testing
		System.out.println("END OF TESTING EQUALS METHOD ----------------------");
		
		System.out.println("STARTING PART 1: GET THE CHEAPEST AND THE MOST EXPENSIVE: \n");
		//an array of 15 objects
		PublicTransportation[] system = new PublicTransportation[15];
		system[0] = new PublicTransportation(20, 5);
		system[1] = new CityBus(3.5, 35, 20, 2002, "green", "David");
		system[2] = new Tram(2, 20, 40, 2005, "red", "Robert", 20);
		system[3] = new Metro(3.5, 30, 20, 2010, "orange", "Mike", 12, "Montreal");
		system[4] = new Ferry(2, 17, 2005, "hope");
		system[5] = new AirCraft(100, 2, air.HELICOPTER, maintenance.MONTHLY);
		system[6] = new PublicTransportation(20, 5);
		system[7] = new CityBus(2, 50, 19, 2000, "red", "Michale");
		system[8] = new Tram(2, 20, 40, 2005, "red", "Robert", 20);
		system[9] = new Metro(3.5, 20, 40, 2012, "yellow", "John", 12, "Toronto");
		system[10] = new Ferry(2, 17, 2005, "hope");
		system[11] = new AirCraft(100, 1, air.AIRLINE, maintenance.YEARLY);
		system[12] = new PublicTransportation(20, 5);
		system[13] = new CityBus(1, 70, 20, 2003, "downtown", "William");
		system[14] = new Tram(2, 20, 40, 2015, "Davidville", "Rosie", 35);
		
		
		//display the cheapest and the most expensive means of transportation
		PublicTransportation.getCheapest(system);
		System.out.println("-----------------");
		PublicTransportation.getMostExpensive(system);
		System.out.println("-----------------");
		System.out.println("END OF TEST FOR THE CHEAPEST, MOST EXPENSIVE MEANS OF TRANSPORTATION \n");
		
		System.out.println("exiting .....");
		System.out.println("------------------------------------------------------------------------------"
				+          "\n                 THANK YOU FOR USING OUR SERVICE."
				+          "\n------------------------------------------------------------------------------");	
	}

}
