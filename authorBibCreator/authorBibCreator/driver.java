package authorBibCreator;

/**
 * @author Duc Nguyen
 * COMP249
 * Assignment #3
 * 
 */

import java.util.StringTokenizer;
import java.util.Scanner;
import java.util.NoSuchElementException;
import java.util.StringJoiner;
import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

public class driver {
	
	//SUB METHOD:
	public static String sub(String a) {   // a static method to take in a string and substring it to the only content
		StringTokenizer b = new StringTokenizer(a, "{"); //the string has only 2 parts.
		b.nextToken();
		String result = null;
		try {
			result = b.nextToken();
		} catch (NoSuchElementException e) {
			result = "";  //if the field is empty
		}
		return result;
	}
	
	public static void closeScanner(Scanner[] target) {  //close an array of Scanner
		for(int i=0; i<target.length; i++) {
			target[i].close();
		}
	}
	
	 public static boolean detectLabel(String line, String label) {  // a method detects which field a line belongs to
         String[] lineArray = line.split("=");
         lineArray[0].replaceAll(" ", "");
         return(lineArray[0].contains(label));	
   }
	
	public static void toIEEE(Article[] matched, PrintWriter pw) throws IOException {
		for(int i =0; i<matched.length; i++) {	
			StringJoiner sj = new StringJoiner(", ", "", ". ");
			String[] authorArray = matched[i].getAuthor();
			for(int n=0; n<authorArray.length; n++) {  //add every single author in the array author of article together
				sj.add(authorArray[n]);
			}
			String author = sj.toString();
			String extract = author+ "\"" + matched[i].getTitle() + "\", " + matched[i].getJournal()
					+ ", vol. " + matched[i].getVolume() + ", no. " + matched[i].getNumber() + ", p. " 
					+ matched[i].getPages() + ", " + matched[i].getMonth() + " " + matched[i].getYear() + ".";			
			pw.println(extract);
		}
		
	}
	
    public static void toACM(Article[] matched, PrintWriter pw) throws IOException {
		for(int i =0; i<matched.length; i++) {
			int count = i+ 1;
			String[] author = matched[i].getAuthor();
			String extract = "[" + count + "]" + "\t" + author[0] + " et al. " + matched[i].getYear() + ". " + matched[i].getTitle()
					+ ". " + matched[i].getJournal() +". " + matched[i].getVolume() + ", " + matched[i].getNumber() + " (" + matched[i].getYear() + "), "
					+ matched[i].getPages() + ". "
					+ matched[i].getDoi() + ".";
		pw.println(extract);
		}
		
	}
    
    public static void toNJ(Article[] matched, PrintWriter pw) throws IOException {
    	for(int i =0; i<matched.length; i++) {
    		StringJoiner sj = new StringJoiner(" & ", "", ". ");
			String[] authorArray = matched[i].getAuthor();
			for(int n=0; n<authorArray.length; n++) {
				sj.add(authorArray[n]);
			}
			String author = sj.toString();
			String extract = author + matched[i].getTitle() + ". " + matched[i].getJournal() + ". " + matched[i].getVolume() + ", "
					+ matched[i].getPages() + "(" + matched[i].getYear() + ").";
			pw.println(extract);
    	}
		
	}
    
    //MAIN CORE METHOD TO PROCESS PROBLEM
	public static void processBibFiles(Scanner[] bibFiles, String targetName) { 
		Article[] result = new Article[0];  //initialize an array of article which contains the result we will get later on
		for(int i =0; i <bibFiles.length; i++) {  // a for loop through all the opened file 
			String latex = "";     // add all the articles in a file into 1 string
			while(bibFiles[i].hasNextLine()) {       
				latex = latex + bibFiles[i].nextLine();    //read all the line in  a file and assign to a string latex
			}
			String[] article= latex.split("@ARTICLE"); // separate all article in a file and assign to an array
			
			for(int n = 1; n<article.length; n++) {   //loop through all article in a file
				String[] field = article[n].split("},");   //assign all field in an article to an array for searching
				boolean match = false;   //keep track if one article matches the target search
				
				//initialize field at the beginning
				String author ="";
				String journal ="";
				String title ="";
				String year = "";
				String volume ="";
				String number ="";
				String pages = "";
				String keywords ="";
				String doi ="";
				String ISSN = "";
				String month = "";

				for(int h=0; h<field.length; h++) {  //loop through all the field
					if(detectLabel(field[h], "author")) { //if a field is detected as a String containing author
						author = sub(field[h]);
					} else if(detectLabel(field[h], "journal")) {
						journal = sub(field[h]);
					} else if (detectLabel(field[h], "title")) {
						title = sub(field[h]);
					} else if (detectLabel(field[h], "year")) {
						year = sub(field[h]);
					} else if(detectLabel(field[h], "volume")) {
						volume = sub(field[h]);
					} else if(detectLabel(field[h], "number")) {
						number = sub(field[h]);
					} else if(detectLabel(field[h], "pages")) {
						pages = sub(field[h]);
					} else if(detectLabel(field[h], "keywords")) {
						keywords = sub(field[h]);
					} else if(detectLabel(field[h], "doi")) {
						doi = sub(field[h]);
					} else if(detectLabel(field[h], "ISSN")) {
						ISSN = sub(field[h]);
					} else if(detectLabel(field[h], "month")) {
						month = sub(field[h]);
					}
				}
				
				String[] authorS = author.split(" and "); //split the name of each author to an array
			    for(int j=0; j<authorS.length; j++) {  //loop through the array containing name of author in a specific article
			    	StringTokenizer partName = new StringTokenizer(authorS[j], ". "); //separate a specific name to tokens
			    	while(partName.hasMoreTokens()) {
			    	if(partName.nextToken().equalsIgnoreCase(targetName)) {   //IMPORTANT: search for the target part name here
			    		match = true;  //mark as a result
			    		break;
			    	}
			    	}
			    }
				
				if(match == true) {
					Article newResult = new Article(author,journal, title, year, volume, number, pages,keywords, doi, ISSN, month);
					result = Article.addArticle(result, newResult);  //update the array result
				}
				
			}
		}
				
		// process with file after getting result
		if(result.length == 0) {  //if cannot find any result.
			System.out.println("No records were found for author(s) with name: " + targetName);
			System.out.println("No files has been created");
		} else {
			String IEEEname = targetName + "-IEEE.json";  File fIEEE = new File(IEEEname);
			String ACMname = targetName + "-ACM.json";  File fACM = new File(ACMname);
			String NJname = targetName + "-NJ.json";   File fNJ = new File(NJname);
			String buIEEEname = targetName + "-IEEE-BU.json";  File fIEEEbu = new File(buIEEEname);
			String buACMname = targetName + "-ACM-BU.json";  File fACMbu = new File(buACMname);
			String buNJname = targetName + "-NJ-BU.json";   File fNJbu = new File(buNJname);
			
			/*
			 * CHECK IF FILE IS ALREADY EXISTED OR NOT
			 * if file is existed => check if BU files are already existed, if yes => delete
			 *  change the name of file to -BU <back up file>
			 * if none of the file is existed then skip these statements.
			 */
			
			if (fIEEE.exists() || fACM.exists() || fNJ.exists()) {
				
				try {
					  if(fIEEE.exists()) {
						  throw new FileExistException();                      
					  }
					} 
				catch (FileExistException e) {  //HANDLING WHEN FILE IS ALREADY CREATED
						String msg = e.getMessage();
						System.out.println("A file already existed with the name: " + IEEEname);
						System.out.println(msg);  //display warning message 
					}
				
				try {
					  if(fACM.exists()) {
						  throw new FileExistException();
					  }
					} 
				catch (FileExistException e) {  //HANDLING WHEN FILE IS ALREADY CREATED
						String msg = e.getMessage();
						System.out.println("A file already existed with the name: " + ACMname);
						System.out.println(msg);  //display warning message 
					}
				
				try {
					  if(fNJ.exists()) {
						  throw new FileExistException();
					  }
					} 
				catch (FileExistException e) {  //HANDLING WHEN FILE IS ALREADY CREATED
						String msg = e.getMessage();
						System.out.println("A file already existed with the name: " + NJname);
						System.out.println(msg);  //display warning message 
					}
				
				boolean done = false;
				while (done == false ) {
					if(fIEEEbu.exists() || fACMbu.exists() || fNJbu.exists()) {
						try {
							fIEEEbu.delete();
							fACMbu.delete();
							fNJbu.delete();
						} catch (SecurityException e1) {
							System.out.println("Cannot delete file due to access permission");
							System.out.println("Program terminates now");
							closeScanner(bibFiles);
							System.exit(0);
							} 
						catch (Exception e2) {
							System.out.println("Cannot delete file !");
							System.out.println("Program terminates now");
							closeScanner(bibFiles);
							System.exit(0);
						}
					} else {
						try {
							fIEEE.renameTo(fIEEEbu);
							fACM.renameTo(fACMbu);
							fNJ.renameTo(fNJbu);
							done = true;
						} catch(SecurityException e1) {
							System.out.println("Cannot rename file due to access permission");
							System.out.println("Program terminates now");
							closeScanner(bibFiles);
							System.exit(0);
						}
					}
				}  //end of while loop
	
			}
			
			//CREATING NEW FILE
			try {
				fIEEE.createNewFile();
				fACM.createNewFile();
				fNJ.createNewFile();
			} catch (IOException e) {
				System.out.println("Cannot process new file as requested");
				System.out.println("Program terminates now");
				closeScanner(bibFiles);
				System.exit(0);
			}
			
			//WRITING TO new FILE
			PrintWriter pwIEEE = null;
			PrintWriter pwACM = null;
			PrintWriter pwNJ = null;
			
			try {
				pwIEEE = new PrintWriter(new FileOutputStream(IEEEname));
				pwACM = new PrintWriter(new FileOutputStream(ACMname));
				pwNJ = new PrintWriter(new FileOutputStream(NJname));
				toIEEE(result, pwIEEE);
				toACM(result, pwACM);
				toNJ(result, pwNJ);
				System.out.println("Files " + IEEEname + ", " + ACMname + ", and " + NJname + " have been created!");
			} 
			catch(FileNotFoundException e) { 
				System.out.println("Cannot find files");
				System.out.println("Program terminates now");
				closeScanner(bibFiles);
				System.exit(0);
			}
			catch(IOException e1) {
				System.out.println("Cannot process matched search to file");
				System.out.println("Program terminates now");
				closeScanner(bibFiles);
				System.exit(0);
			}
			finally {
				closeScanner(bibFiles);
				pwIEEE.close();  // close all PrintWriter
				pwACM.close();
				pwNJ.close();
			}
			System.out.println("A total of " + result.length + " records were found for author(s) with name: " + targetName);	
		
		} //close for if-else statement to manipulating files
	}  //close for the whole method
	
	//MAIN DRIVER
	public static void main(String[] args) {
		Scanner keyIn = new Scanner(System.in);
		System.out.println("Welcome to BibAuthor!\n");
		System.out.print("Please enter the author name you are targeting: ");
		String authorName = keyIn.next();
		
	    //OPENNING FILE
		Scanner[] Searchlatex = new Scanner[10]; // an array stores the information to read all the files in library.
		
		for(int i =0; i < 10; i++) { // a loop to open all the file and close if there is one file cannot be opened
			int index = 1 + i;
			String nameFile = "Latex" + index + ".bib";
			try {
				Searchlatex[i] = new Scanner(new FileInputStream(nameFile));
			} catch (FileNotFoundException e) {
				System.out.println("Could not open input file " + nameFile + " for reading \n");
				System.out.println("Please check if file exist! Program will terminate after closing any opened files.");
				
				for (int opened = 0; opened < i; opened++) {
				  Searchlatex[opened].close();
				}
				System.exit(0);
			}
		}
		System.out.println();
		processBibFiles(Searchlatex, authorName);
		
		
		//closing Scanner and terminate program
		System.out.println("\nGoodbye! Hope you have enjoyed creating the needed files using AuthorBibCreator_");
		keyIn.close();
		closeScanner(Searchlatex);
		System.exit(0);
	}

}
