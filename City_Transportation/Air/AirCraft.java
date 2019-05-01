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

package Air;
public class AirCraft extends PublicTransport.PublicTransportation{
	
	
	public enum air {
		HELICOPTER, AIRLINE, GLIDER, BALLOON
	}
	
	public enum maintenance {
		WEEKLY, MONTHLY, YEARLY
	}
	
	private air type;
	private maintenance mnt;
	
	public AirCraft() {  //default constructor
		super();
		type = air.AIRLINE;
		mnt = maintenance.YEARLY;
	}
	
	
    public AirCraft(double price, int numOfStops, air type, maintenance time) { //parameter
		super(price, numOfStops);
		this.type = type;
		this.mnt = time;
	}
    
    public AirCraft(AirCraft c) { //copy constructor
    	super(c.price, c.numOfStops);
    	this.type = c.getType();
    	this.mnt = c.getMnt();
    }
	
	public air getType() {
		return type;
	}

	public void setType(air type) {
		this.type = type;
	}

	public maintenance getMnt() {
		return mnt;
	}

	public void setMnt(maintenance mnt) {
		this.mnt = mnt;
	}
	
	
		public String toString() {
			String output = super.toString()
					+ "\nIt is of type " + type
					+ " and need to be maintenance " + mnt;
			return output;
	}
		
		public boolean equals(Object obj) {
			if (obj == null ) { return false;}
			//Even if user try to compare with a null object, this statement will give back false value instead of crashing the whole program
			else if (obj.getClass() != AirCraft.class ) {
				return false;
			} else {
				AirCraft transport = (AirCraft) obj;
				return (super.equals(transport)
						&& (type == transport.type) && (mnt == transport.mnt));
			}
		}

	
	
	

}
