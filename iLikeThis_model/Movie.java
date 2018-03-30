package group1c.ilikethis.model.cs102.group1c.ilikethis.model;
import java.util.ArrayList;

public class Movie extends Item
{
   //properties
   private String director, country, genre;
   private int year, duration;
   private ArrayList<String> cast;

   //constructors
   public Movie( int id)
   {
      super( id );
      ArrayList<String> values = DatabaseConnection.getItemInfo("Movie", id + "") ;
      if( !values.isEmpty()){
	      setTitle( values.get(1));
	      setDirector(values.get(2));
	      setCountry( values.get(3));
	      setGenre( values.get(4));
	      setYear( Integer.parseInt(values.get(5)));
	      setDuration( Integer.parseInt(values.get(6)));
	      cast = new ArrayList<>();
	      cast.add(values.get(7));
	      cast.add(values.get(8));
	      cast.add(values.get(9));
      }
      
   }

   public Movie(String title, String director, String country, String genre, int year, int duration )
   {
      super( "Movie");
      this.setTitle(title);
      this.director = director;
      this.country = country;
      this.genre = genre;
      this.year = year;
      this.duration = duration;
      cast = new ArrayList<>(3);
      cast.add(0, "");
      cast.add(1, "");
      cast.add(2, "");
   }

   /**
    *
    * @return
    */
   public String propertiesForDb(){
      return "(`id`, `title`, `director`, `country`, `genre`, `year`, `duration`, `cast1`, `cast2`, `cast3`) VALUES ("
              + "NULL" + ", \"" + getTitle() + "\", \"" + getDirector() + "\", \"" + getCountry()
              + "\", \"" + getGenre() + "\", " + getYear() + ", " + getDuration() + ", \"" + 
              cast.get(0) + "\", \"" + cast.get(1) + "\", \"" + cast.get(2) +"\")";
   }



   /**
    * returns the director of the movie
    * @return director of the movie
    */
   public String getDirector()
   {
      return director;
   }

   /**
    * sets the director of the movie
    * @param director director of the movie
    */
   public void setDirector( String director )
   {
      this.director = director;
   }

   /**
    * returns the country which the movie was made
    * @return country of the movie
    */
   public String getCountry()
   {
      return country;
   }

   /**
    * sets the country in which the movie was made
    * @param country country of the movie
    */
   public void setCountry( String country )
   {
      this.country = country;
   }

   /**
    * returns the genre of the movie
    * @return genre of the movie
    */
   public String getGenre()
   {
      return genre;
   }

   /**
    * sets the genre of the movie
    * @param genre genre of the movie
    */
   public void setGenre( String genre )
   {
      this.genre = genre;
   }

   /**
    * returns the year in which the movie was made
    * @return publication year of the movie
    */
   public int getYear()
   {
      return year;
   }

   /**
    * sets the year in which the movie was made
    * @param year year of the movie
    */
   public void setYear( int year )
   {
      this.year = year;
   }

   /**
    * returns the duration of the movie
    * @return duration of the movie
    */
   public int getDuration()
   {
      return duration;
   }

   /**
    * sets the duration of the movie
    * @param duration duration of the movie
    */
   public void setDuration( int duration )
   {
      this.duration = duration;
   }

   /**
    * returns the favourite casr members of the movie
    * @return cast of the movie
    */
   public ArrayList<String> getCast()
   {
      return cast;
   }

   /**
    * adds a favourite cast member
    * @param name name of the cast member
    */
   public void addCastMember( String name )
   {
      cast.add( name );
   }

   /**
    * removes the cast member given their name
    * @param name of the cast member
    */
   public void removeCastMember( String name )
   {
      cast.remove( cast.indexOf( name ) );
   }
}
