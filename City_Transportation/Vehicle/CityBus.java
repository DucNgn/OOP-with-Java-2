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
public class CityBus extends PublicTransport.PublicTransportation{
	protected long routeNum;
	protected int operateYear;
	protected String line;
	protected String driver;
	
	public CityBus() { //default constructor
		super();
		routeNum = 0;
		operateYear = 0;
		line ="";
		driver = "";
	}
	
	public CityBus(double price, int numOfStops, long routeNum, int operateYear, String line, String driver) {
		super(price, numOfStops);
		this.routeNum = routeNum;
		this.operateYear = operateYear;
		this.line = line;
		this.driver = driver;
	}
	
	public CityBus(CityBus c) {
		super(c.getPrice(), c.getNumOfStops());
		this.routeNum = c.getRouteNum();
		this.operateYear = c.getOperateYear();
		this.line = c.getLine();
		this.driver = c.getDriver();
	}
	
	public long getRouteNum() {
		return routeNum;
	}
	public void setRouteNum(long routeNum) {
		this.routeNum = routeNum;
	}
	public int getOperateYear() {
		return operateYear;
	}
	public void setOperateYear(int operateYear) {
		this.operateYear = operateYear;
	}
	public String getLine() {
		return line;
	}
	public void setLine(String line) {
		this.line = line;
	}
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	
	
	public String toString() {
		String output = super.toString()
				+ "\nRoute number is " + routeNum + ", begin operation year: " + operateYear
				+ "\nLine's name: " + line + "\nDriver's name: " + driver;  
		return output;
	}
	
	public boolean equals(Object obj) {
		if (obj == null) {return false;}
		//Even if user try to compare with a null object, this statement will give back false value instead of crashing the whole program
		else if (getClass() != obj.getClass()) {
			return false;
		} else {
			CityBus transport = (CityBus) obj;
			return ((super.equals(transport))
					&& (routeNum == transport.routeNum) && (operateYear == transport.operateYear)
					&& (line.equalsIgnoreCase(transport.line)) && (driver.equalsIgnoreCase(transport.driver)));
	}
	}

}
