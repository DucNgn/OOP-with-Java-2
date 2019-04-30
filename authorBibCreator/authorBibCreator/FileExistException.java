package authorBibCreator;

/**
 * @author Duc Nguyen
 * COMP249
 * Assignment #3
 * 
 */
public class FileExistException extends Exception {
	public FileExistException() {
		super("Exception: There is already an existing file for that author. File will be renamed as BU, and old BUs files will be deleted! \n");
	}
	
	public FileExistException(String msg) {
		super(msg);
	}

}
