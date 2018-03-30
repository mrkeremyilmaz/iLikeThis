package group1c.ilikethis.model.cs102.group1c.ilikethis.model;
import java.util.ArrayList;

public class VideoGame extends Item
{
   //properties
   private String developer, genre, info;
   private int year;

   //constructors

   /**
    * This constructor get id as a parameter and pass it to the parent class to record
    *
    * @param id int identification value of the item
    */
   public VideoGame( int id)
   {
      super( id);
      ArrayList<String> values = DatabaseConnection.getItemInfo("VideoGame", id + "") ;
      if( !values.isEmpty()){
	      setTitle( values.get(1));
	      setDeveloper(values.get(2));
	      setGenre( values.get(3));
	      setInfo( values.get(4));
	      setYear( Integer.parseInt(values.get(5)));
      }
   }

  /**
    * This constructor get all necessary variables from user and initializes class's variables and pass id and title to the parent class
    *
    * @param title String variable that indicates title of the video game
    * @param developer String variable that indicates developer of the video game
    * @param genre String variable that indicates genre of the video game
    * @param info String variable that indicates whether the game is able to be played online, multiplayer, or single
    * @param year int variable that indicates year of the video game
    */
   public VideoGame( String title, String developer, String genre, String info, int year)
   {
      super( "VideoGame");
      this.setTitle(title);
      this.developer = developer;
      this.genre = genre;
      this.year = year;
      this.info = info;
   }

   //methods
   /**
    *
    * @return
    */
   public String propertiesForDb(){
      return "(`id`, `title`, `developer`, `genre`, `info`, `year`) VALUES ("
              + "NULL" + ", \"" + getTitle() + "\", \"" + getDeveloper() + "\", \""
              + getGenre() + "\", \"" + getInfo() + "\", " + getYear() + ")";
   }

   /**
    * returns the name of developer of the Videogame
    * @return developer String variable that indicates name of the developer
    */
   public String getDeveloper()
   {
      return developer;
   }


   /**
    * sets the name of developer of the Videogame
    * @param developer String variable that indicates name of the developer
    */
   public void setDeveloper( String developer )
   {
      this.developer = developer;
   }


   /**
    * returns the genre of the Videogame
    * @return genre of the Videogame
    */
   public String getGenre()
   {
      return genre;
   }

   /**
    * sets the genre of the VideoGame
    * @param genre genre of the videogame
    */
   public void setGenre( String genre )
   {
      this.genre = genre;
   }

   /**
    * returns the year in which the movie was Videogame
    * @return publication year of the Videogame
    */
   public int getYear()
   {
      return year;
   }

   /**
    * sets the year in which the Videogame was made
    * @param year year of the Videogame
    */
   public void setYear( int year )
   {
      this.year = year;
   }

   /**
    * returns the info(multiplayer/coop) of the Videogame
    * @return info(multiplayer/coop) of the Videogame
    */
   public String getInfo()
   {
      return info;
   }

   /**
    * sets info(multiplayer/coop) of the Videogame
    * @param info(multiplayer/coop) of the Videogame
    */
   public void setInfo( String info )
   {
      this.info = info;
   }
}
