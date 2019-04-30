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



package Vehicle;

public class Metro extends CityBus{
	private int numOfVehicles;
	private String city;
	
	public Metro() {
		super();
		numOfVehicles = 0;
		city = "";
	}
	
	public Metro(double price, int numOfStops, long routeNum, int operateYear, String line, String driver,
			int numOfVehicles, String city) {
		super(price, numOfStops, routeNum, operateYear, line, driver);
		this.numOfVehicles = numOfVehicles;
		this.city = city;
	}
	
	public Metro(Metro c) {
		super(c.getPrice(), c.getNumOfStops(), c.getRouteNum(), c.getOperateYear(), c.getLine(), c.getDriver());
		this.numOfVehicles = c.getNumOfVehicles();
		this.city = c.getCity();
	}
	
	
	
	public int getNumOfVehicles() {
		return numOfVehicles;
	}
	public void setNumOfVehicles(int numOfVehicles) {
		this.numOfVehicles = numOfVehicles;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}

	
	
	public String toString() {
		String output = super.toString()
				+ "\nIt has " + numOfVehicles + " vehicles in the system."
				+ "\nOperating city: " + city; 
		return output;
	}
	
	public boolean equals(Object obj) {
		if (obj == null) {return false;}
		//Even if user try to compare with a null object, this statement will give back false value instead of crashing the whole program
		else if (getClass() != obj.getClass()) {
			return false;
		} else {
			Metro transport = (Metro) obj;
			return (super.equals(transport)	&& (numOfVehicles == transport.numOfVehicles) && (city == transport.city));
	}
	}
	

}
