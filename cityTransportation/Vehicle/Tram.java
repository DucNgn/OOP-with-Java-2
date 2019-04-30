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

public class Tram extends CityBus{
	private int maxSpeed;
	
	public Tram() {
		super();
		maxSpeed = 0;
	}
	public Tram(double price, int numOfStops, long routeNum, int operateYear, String line, String driver,
			int maxSpeed) {
		super(price, numOfStops, routeNum, operateYear,line, driver);
		this.maxSpeed = maxSpeed;
	}
	
	public Tram(Tram c) {
		super(c.getPrice(), c.getNumOfStops(), c.getRouteNum(), c.getOperateYear(), c.getLine(), c.getDriver());
		this.maxSpeed = c.getMaxSpeed();
	}

	public int getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	

	public String toString() {
		String output = super.toString()
				+ "\nMax speed of this vehicle: " + maxSpeed + " km/h";
		return output;
	}
		
	public boolean equals(Object obj) {
		if (obj == null) {return false;}
		//Even if user try to compare with a null object, this statement will give back false value instead of crashing the whole program
		else if (getClass() != obj.getClass()) {
			return false;
		} else {
			Tram transport = (Tram) obj;
			return (super.equals(transport) && (maxSpeed == transport.maxSpeed));
			}
		}
	
	
}
