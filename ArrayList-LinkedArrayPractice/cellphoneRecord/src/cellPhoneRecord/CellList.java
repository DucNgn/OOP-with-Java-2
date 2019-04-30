/**
 * @author Duc Nguyen
 * COMP249
 * Assignment #4
 * 
 */

package cellPhoneRecord;
import java.util.NoSuchElementException;
import java.io.Serializable;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;

 class CellList implements Serializable {
	
	private class CellNode implements Serializable {  //INNER CLASS
		private CellPhone cell;
		private CellNode next;
		
		public CellNode() {
			cell = null;
			next = null;
		}
		
		public CellNode(CellPhone cell, CellNode next) {
			this.cell = cell;
			this.next = next;
		}
		
		public CellNode(CellNode passed) {
			this.cell = passed.cell;
			this.next = passed.next;
		}
		
		public CellNode clone() { //deep clone of a node
			return new CellNode(this.cell.clone(), this.next);
		}

		public CellPhone getCell() { return cell;}

		public void setCell(CellPhone cell) { this.cell = cell; }

		public CellNode getNext() { return next; }

		public void setNext(CellNode next) { this.next = next; }
	} //end of inner class
	
	private CellNode head;
	private int size;
	public static int iteration;	

	public CellNode getHead() {
		return head;
	}

	public void setHead(CellNode head) {
		this.head = head;
	}
	
	public int getSize() {
		return size;
	}

	public CellList() {  //DEFAULT CONSTRUCTOR
		head = null;
		size =0;
	}
	
	public CellList(CellList passed) {  //copy constructor //WORKS //DEEP COPY
		//MANUALLY
		this.size = passed.size;
		if(passed.head == null) {
			head = null;
		} else {
			this.head = null;
			CellNode Cell1, Cell2, Cell3;
			Cell1 = passed.head;
			Cell2 = Cell3 = null;
			while(Cell1 != null) {
				if(head == null) {  //the first time 
					Cell2 = new CellNode(Cell1);
					head = Cell2;
				} else {
					Cell3 = new CellNode(Cell1);
					Cell2.next = Cell3;
					Cell2 = Cell3;
				}
				Cell1 = Cell1.next;
			}
			Cell2 = Cell3 = null; //Cell1 is already null
		}

		// this.head = passed.head;  //SHALLOW COPY	----should not use
		
		
		/*  ///////////////////////////////////	WRITING OBJECT TO A BINARY FILE THEN READ IT AGAIN//////
		 File ftemp = new File("temp.dat");
		
		try {
			ftemp.createNewFile();
		} catch (IOException e1) {
			System.out.println("Cannot create file");
		}
		ObjectOutputStream outS = null;
		try {
			outS = new ObjectOutputStream(new FileOutputStream("temp.dat"));
			outS.writeObject(passed);
			outS.close();
		} catch(FileNotFoundException e1) {
		    System.out.println("Cannot find file");
		    System.exit(0);
		} catch (IOException e) {
			System.out.println("Cannot write to file. Terminating");
			System.exit(0);
		} 
		
		CellList temp = new CellList();
		ObjectInputStream inS = null;
		try {
			inS = new ObjectInputStream(new FileInputStream("temp.dat"));
			temp= (CellList)inS.readObject();
			inS.close();
		} catch (IOException e) {
			System.out.println("Cannot read file");
			System.exit(0);
		} catch (ClassNotFoundException e1) {
			System.out.println("Cannot find the requested class");
			System.exit(0);
		}
		this.head = temp.head;
		ftemp.delete();
		*/
	}
	
	/*  DYNAMIC TIME-CHANGE SIZE <CLEANER CODE BUT NOT EFFICIENT>
	public int size() { //CHECKED: WORK
		int counter =0;
		CellNode index = head;
		while(index!= null) {
			counter++;
			index = index.next;
		}
		return counter;
	}
	*/
	
	public void addToStart(CellPhone obj) {  //CHECKED: WORK
		size++;
		head = new CellNode(obj, head);
	}
	
	public boolean insertAtIndex(CellPhone obj, int post) {  //CHECKED: WORK. NEED CHECK AGAIN FOR SIMPLICITY
		try {
			if(post > size || post <0) {
				throw new NoSuchElementException();
			} 
		} catch (NoSuchElementException e) {
			System.out.println("Invalid entered index");
			System.out.println("Terminating program...");
			System.exit(0);
		}
		
		if(head == null) {   //The list is empty
			this.addToStart(obj);
			size++;
			return true;
		} else if (post == size) {  //the requested position to add is at the end of the list
			CellNode index = head;
			while(index.next != null) {index = index.next;}
			index.next = new CellNode(obj, null);
			index = null;
			size++;
			return true;
		} else {  // the requested position is in the middle
			int current = 0;
			CellNode index = head;
			while(index != null) {
				if(current == post) {  //found the position
					index.next = new CellNode(obj, index.next);
					size++;
					return true;  //done
				} else {
					current++;
					index = index.next;
				}
			}
			return false; //never reach this line but still need to ensure return type is a boolean
		}
	}
	
	public boolean deleteFromIndex(int post) {  //CHECKED: WORK
		try {
			if(post > size - 1 || post < 0) {
				throw new NoSuchElementException();
			} 
		} catch (NoSuchElementException e) {
			System.out.println("Invalid entered index");
			System.out.println("Terminating program...");
			System.exit(0);
		}
		
		if(head == null) {
			return true;
		} else if (post == 0) {
			this.deleteFromStart();
			size--;
			return true;
		} else if(post == size-1) {
			CellNode index = head;
			while(index.next.next != null) {
				index = index.next;
			}
			index.next = null;  //done the job
			size--;
			index = null;
			return true;
		} else {
			int current = 0;
			CellNode index = head;
			while (index.next!= null) {
				if(current == post-1) {
					index.next = index.next.next;
					size--;
					index = null;
					return true;
				} else {
					index = index.next;
					current++;
				}
			}
			return false;
		}
	}
	
	public boolean deleteFromStart() {  //CHECKED: WORK
		if(head == null) {
			return false; //the array is already empty
		} else {
			head = head.next;
			size--;
			return true;
		}
	}
	
	public void replaceAtIndex(CellPhone obj, int post) {  //CHECKED: WORK. NEED SIMPLICITY
		if(post < 0|| post > size -1 || this.head == null) {
			return;
		}
		if (post == 0) {  //the first element
			this.deleteFromStart();
			this.addToStart(obj);
		} else if(post == size -1) {  //the last element
			CellNode index = head;
			while(index.next.next != null) {
				index = index.next;
			}
			index.next = new CellNode(obj, null);
			return;
		} else	{
			int ctr = 1; //ahead 1 node
			CellNode index = head;
			while (index.next != null) {
				if(ctr == post) {
					index.next = new CellNode(obj, index.next.next);
					return;
				} else {
					ctr++;
					index = index.next;
				}
			}
		}
	}
	
	public CellPhone find(long serialNum) {  //CHECKED: need check
		iteration = 0;
		CellNode index = head;
		while(index!= null) {
			iteration++;
			if(index.cell.getSerialNum() == serialNum) {
				return index.cell;
			}
			index = index.next;
		}
		return null; //reach this means the serialNum was not in the list
	}
	
	public boolean canAdd(CellPhone obj) {  //quite good. need check again for simplicity
		if(obj == null) {
			return false;
		}
		if(head == null) {
			return true;
		}
		CellNode index = head;
		long serialNum = obj.getSerialNum();
		if(this.contains(serialNum)) {
			return false;
		}
		while(index != null) {
			if(index.getCell().equals(obj)) {
				return false;
			} 	
			index = index.next;
		}
		return true;
	}
	
	public boolean contains(long serialNum) { //CHECKED: WORK
		if(this.find(serialNum) != null) { return true;
		} else {return false;}
	}
	
	public void showContents() {  //CHECKED: WORK
		System.out.println("The current size of the list is " + size + ". Here are the contents of the list\n"
				+          "=================================================================");
		if(size == 0) { System.out.println(" ---> x"); } 
		else {
			final int space = 3;
			int ctr =0;
			CellNode index = head;
			while(index!= null) {
				System.out.print(index.cell + " ----> ");
				index = index.next;
				ctr++;
				if(ctr == space) {
					System.out.println();
					ctr =0;
				}
			}
			System.out.println("x"); //last element	
		}
	}

	public boolean equals(CellList other) {  //good
		if(other.getSize() != this.size) {
			return false;
		}
		CellNode index = head;
		CellNode otherIndex = other.head;
		while(index != null) {
			if(index.cell.equals(otherIndex.cell) == false) {
				return false;
			}
			index = index.next;
			otherIndex = otherIndex.next;
		}
		return true;
	}
}
