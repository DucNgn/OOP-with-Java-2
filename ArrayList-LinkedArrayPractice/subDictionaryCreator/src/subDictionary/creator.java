/**
 * @author Duc Nguyen
 * COMP249
 * Assignment #4
 * 
 */


package subDictionary;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class creator {
	public static String extractWord(String input) {  //determine if a word is good to add to list. Truncate if need 
		/*/////////////////////////////REGEX/////////////
		String result = input.toUpperCase();
		if (result.matches(".*\\d.*") || result.matches("=") || result.matches(".*^[B-H]{1}$.*") 
		|| result.matches(".*^[J-Z0-9]{1}$.*")) {  //matches any digits or equal sign and any single character except A and I
			result = "-1";
			return result;
		} else if(result.matches(".*(\\?|\\:|\\,|\\;|\\!|\\.)$.*")) { //matches any ? : , ; ! . and eliminate these expression
			result = result.substring(0, result.length()-1);
		} 
		if(result.matches(".*(’M).*") || result.matches(".*(’S).*")) {  //matches any ’s or ’m for I’m or it’s
			result = result.substring(0, result.length()-2);
		}
		return result;
		*/
		
		//////////////////////////////MANUALLY EXTRACT//////////
		String result = input.toUpperCase();
		//handle the case with expression notation at the end of a word
		if(result.contains("?") || (result.contains(":")) || (result.contains(",")) || (result.contains(";")) || (result.contains("!")) || (result.contains(".")) ) {
			result = result.substring(0, result.length()-1);
		}
		//handle the case of I'm or it's
		if(result.contains("’S") || result.contains("’M")) {
			result = result.substring(0, result.length()-2);
		}
		if(result.length() == 1) {  //handle the case of a single char only
			int fLetter = (int)result.charAt(0);
			if(fLetter == 65 || fLetter == 73) {  //exception of char A <65> and I<73>
				return result;
			} else {
				result = "-1";
				return result;
			}
		} else {
			for(int i=0; i<result.length(); i++) {  //a loop through each char inside the word
				int letter = (int)result.charAt(i);
				if(letter >= 48 && letter <= 57) { // 48->57: digit
					result = "-1";
					return result;
				}
			}
		}
		return result;
	}
	
	public static void extractDictionary(ArrayList<String> input, PrintWriter pw) {  //process and extract sub-dictionary
	  String faulty = "-1";
	  for(int i=0; i<input.size(); i++) {
		  String temp = input.get(i);
		  int n = i+1;
		  for(;n<input.size(); n++) {
			  if(input.get(n).contentEquals(temp)) {
				  input.set(n, faulty);
			  }
		  }
	  }
	  while(input.contains(faulty)) {  //remove all the faulty object out of the article array
		  input.remove(faulty);  }
	  input.sort(null);  //sorting arrayList in order
	  int counter = input.size();
	//add title for a group of entries
	 for(int i =0; i<input.size(); i++) {
		 char first = input.get(i).charAt(0);
		 String title ="\n" + first + "\n==";
		 if(input.contains(title) == false) {
			 input.add(i, title);
		 }
	 }
	  String result = "The document produced this sub-dictionary, which includes " + counter + " entries.\n"; //initialize the string for result
	  for(String obj: input) {   // Write the ArrayList into a String to print to .txt
		  result = result + obj + "\n";
	  }
	  pw.println(result);	
	}
	
	public static void main(String[] args) { //main
		Scanner sc = null;  //Scanner to read file
		Scanner keyIn = new Scanner(System.in);  //read input
		System.out.println("Please enter the name of text file you want to create a sub-dictionary:   ");
		String fName = keyIn.nextLine();
		try {   //SCANNER TO READ FILE
			sc = new Scanner(new FileInputStream(fName), "cp1252");
		} catch(FileNotFoundException e) {
			System.out.println("Cannot find file");
			System.out.println("Terminating");
			System.exit(0);
		}
		String outputName = "SubDictionary.txt"; //CREATE AN OUTPUT DICTIONARY
		PrintWriter pw = null;   //PRINTWRITER
		try {
			pw = new PrintWriter(new FileOutputStream(outputName));
		} catch(FileNotFoundException e) {
			System.out.println("Cannot find file to write in");
			System.out.println("Terminating");
			System.exit(0);
		}
		ArrayList<String> article = new ArrayList<String>(1);
		while(sc.hasNext()) {
			String extracted = extractWord(sc.next());
			article.add(extracted);
		}
		if(article.isEmpty()) {
			System.out.println("The file is empty. No entries can be found");
		} else {
			extractDictionary(article, pw);	
			
		}	
		//close scanner and print writer
		pw.close();
		sc.close();
		keyIn.close();
		System.out.println("----------------------------------\nDone. Thank you for using our service");
	}
}