package authorBibCreator;
/**
 * @author Duc Nguyen
 * COMP249
 * Assignment #3
 * 
 */
public class Article {

	private String[] author;
	private String keywords, journal, title, volume, pages, ISSN, month, doi, year, number;
	private static int countMatch = 0;
	public Article() {}

	
	public Article(String author, String journal, String title, String year, String volume, String number, String pages, String keywords, String doi, String ISSN, String month ) {

		this.author = author.split(" and ");
		this.journal = journal;
		this.title = title;
		this.year = year;
		this.volume = volume;
		this.number = number;
		this.pages = pages;
		this.keywords = keywords;
		this.doi = "DOI:https://doi.org/" + doi;
		this.ISSN = ISSN;
		this.month = month;
		countMatch++;
	}
	
	public Article(Article obj) {   //copy constructor
		this.journal = obj.getJournal();
		this.title = obj.getTitle();
		this.year = obj.getYear();
		this.volume = obj.getVolume();
		this.number = obj.getNumber();
		this.pages = obj.getPages();
		this.doi = obj.getDoi();
		this.author = obj.getAuthor();
		this.keywords = obj.getKeywords();
		this.ISSN = obj.getISSN();
		this.month = obj.getMonth();
	}
	//a method to add an article to an existing array of Article
	public static Article[] addArticle(Article[] origin, Article newAdd) {
		Article[] newAdded = new Article[countMatch];
		for(int i =0; i<origin.length; i++) {
			newAdded[i] = origin[i].clone(); //clone array origin
		}
		newAdded[countMatch-1] = newAdd.clone(); // add new Article to the end of the array
		return newAdded; 
	}
	
	public Article clone() {  //clone method
		return new Article(this);
	}

	public String[] getAuthor() {
		return author;
	}

	public String getKeywords() {
		return keywords;
	}

	public String getJournal() {
		return journal;
	}

	public String getTitle() {
		return title;
	}

	public String getVolume() {
		return volume;
	}

	public String getPages() {
		return pages;
	}

	public String getISSN() {
		return ISSN;
	}

	public String getMonth() {
		return month;
	}

	public String getDoi() {
		return doi;
	}

	public String getYear() {
		return year;
	}

	public String getNumber() {
		return number;
	}
}
