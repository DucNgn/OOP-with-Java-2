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


package Marine;

public class Ferry extends PublicTransport.PublicTransportation{
	private int buildYear;
	private String ship;
	
	public Ferry() {
		super();
		buildYear = 0;
		ship ="";
	}
	public Ferry(double price, int numOfStops, int buildYear, String ship) {
		super(price, numOfStops);
		this.buildYear = buildYear;
		this.ship = ship;
	}
	public Ferry(Ferry c) {
		super(c.getPrice(), c.getNumOfStops());
		this.buildYear = c.getBuildYear();
		this.ship = c.getShip();
	}
	
	
	public int getBuildYear() {
		return buildYear;
	}
	public void setBuildYear(int buildYear) {
		this.buildYear = buildYear;
	}
	public String getShip() {
		return ship;
	}
	public void setShip(String ship) {
		this.ship = ship;
	}
	
	
	
	public String toString() {
				String output = super.toString()
				+ "\nBuild year: " + buildYear
				+ "\nShip's name: " + ship;
		return output;
	}
	
	public boolean equals(Object obj) {
		if (obj == null) {return false;}
		//Even if user try to compare with a null object, this statement will give back false value instead of crashing the whole program
		else if (getClass() != obj.getClass()) {
			return false;
		} else {
			Ferry transport = (Ferry) obj;
			return (super.equals(transport) && (buildYear == transport.buildYear) && (ship.equalsIgnoreCase(transport.ship)));
	}
	}
	


}
