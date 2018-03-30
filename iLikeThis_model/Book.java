package group1c.ilikethis.model.cs102.group1c.ilikethis.model;

import java.util.ArrayList;
import java.util.Arrays;

public class Book extends Item
{
  // properties

  private String author;
  private int numOfPages;
  private String quotes;
  private String genre;
  private int yearPublished;
  private String language;

  // constructors

  /**
   * 
   * @param id
   */
  public Book( int id)
  {
    super( id);
	ArrayList<String> values = DatabaseConnection.getItemInfo("Book", id + "") ;
	if( !values.isEmpty()){
	    setTitle( values.get(1));
	    setAuthor(values.get(2));
	    setNumOfPages( Integer.parseInt(values.get(3)));
	    setQuotes( values.get(4));
	    setGenre( values.get(5));
	    setYearPublished( Integer.parseInt(values.get(6)));
	    setLanguage( values.get(7));
    }
  }

  /**
   * This initializes a book with title, author, number of pages, quotes, genre, published year and language.
   * @param title is the title of the book.
   * @param author is the author of the book.
   * @param numOfPages is the number of pages of the book.
   * @param quotes is the quotes of the book.
   * @param genre is the genre of the book.
   * @param yearPublished is the published year of the book.
   * @param language is the language of the book.
   */
  public Book( String title, String author, int numOfPages, String quotes, String genre, int yearPublished, String language, String additionalNotes)
  {
    super( "Book");
    this.setTitle(title);
    this.author = author;
    this.numOfPages = numOfPages;
    this.quotes = quotes;
    this.genre = genre;
    this.yearPublished = yearPublished;
    this.language = language;
    this.setAdditionalNotes( additionalNotes);
  }

  // methods

    /**
     *
     * @return
     */
  public String propertiesForDb(){
	  return "(`id`, `title`, `author`, `numOfPages`, `quotes`, `genre`, `yearPublished`, `language`)" + " VALUES ("
				 + "NULL" + ", \"" + getTitle() + "\", \"" + getAuthor() + "\", " + getNumofPages()
				 + ", \"" + getQuotes() + "\", \"" + getGenre() + "\", " + getYearPublished() + ", \"" + getLanguage() + "\")";
  }
  /**
   * This method returns the author of the book.
   * @return the author of the book.
   */
  public String getAuthor()
  {
    return author;
  }

  /**
   * This method sets the author of the book.
   * @param author, the author of the book.
   */
  public void setAuthor( String author)
  {
    this.author = author;
  }

  /**
   * This method returns the number of pages of the book.
   * @return the number of pages of the book.
   */
  public int getNumofPages()
  {
    return numOfPages;
  }

  /**
   * This method sets the number of pages of the book.
   * @param numOfPages, the number of pages of the book.
   */
  public void setNumOfPages( int numOfPages)
  {
    this.numOfPages = numOfPages;
  }

  /**
   * This method returns the quotes of the book.
   * @return quotes of the book.
   */
  public String getQuotes()
  {
    return quotes;
  }

  /**
   * This method sets the quotes of the book.
   * @param quotes, quotes of the book.
   */
  public void setQuotes( String quotes)
  {
    this.quotes = quotes;
  }

  /**
   * This method returns the genre of the book.
   * @return the genre of the book.
   */
  public String getGenre()
  {
    return genre;
  }

  /**
   * This method sets the genre of the book.
   * @param genre, the genre of the book.
   */
  public void setGenre( String genre)
  {
    this.genre = genre;
  }

  /**
   * This method returns the year published of the book.
   * @return the year published of the book.
   */
  public int getYearPublished()
  {
    return yearPublished;
  }

  /**
   * This method sets the year published of the book.
   * @param yearPublished, the year published of the book.
   */
  public void setYearPublished( int yearPublished)
  {
    this.yearPublished = yearPublished;
  }

  /**
   * This method returns the language of the book.
   * @return the language of the book.
   */
  public String getLanguage()
  {
    return language;
  }

  /**
   * This method sets the language of the book.
   * @param language, the language of the book.
   */
  public void setLanguage( String language)
  {
    this.language = language;
  }
}
