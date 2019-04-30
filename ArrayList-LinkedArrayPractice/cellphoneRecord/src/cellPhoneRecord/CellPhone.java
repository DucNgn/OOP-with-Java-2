/**
 * @author Duc Nguyen
 * COMP249
 * Assignment #4
 * 
 */

package cellPhoneRecord;
import java.util.Scanner;
import java.io.Serializable;

public class CellPhone implements Serializable {
	private long serialNum;
	private String brand;
	private int year;
	private double price;
	
	public long getSerialNum() {
		return serialNum;
	}
	public void setSerialNum(long serialNum) {
		this.serialNum = serialNum;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}

	public CellPhone(long serialNum, String brand, int year, double price) {
		this.serialNum = serialNum;
		this.brand = brand;
		this.year = year;
		this.price = price;
	}
	
	public CellPhone(CellPhone passed, long serialNum) {
		this.serialNum = serialNum;
		this.brand = passed.getBrand();
		this.year = passed.getYear();
		this.price = passed.getPrice();
	}
	
	public CellPhone clone() {
		Scanner keyIn = new Scanner(System.in);
		System.out.println("Enter a new serial number: ");
		long newSerial = keyIn.nextLong();
		keyIn.close();
		return new CellPhone(this, newSerial);
	}
	
	public String toString() {
		String m = "[" + serialNum + ": " + brand + "  " + price + "$ " + year + "]";
		return m;
	}
	
	public boolean equals(Object obj) {
		if((obj == null) || (obj.getClass() != getClass())) {
			return false;
		} else {
			CellPhone cellObj = (CellPhone)obj;
			if ((this.brand.equalsIgnoreCase(cellObj.getBrand())) && (this.getPrice() == cellObj.getPrice()) && (this.getYear() == cellObj.getYear())) {
				return true;
			} else {
				return false;
			}
		}
	}
	
	
}
