package group1c.ilikethis.model.cs102.group1c.ilikethis.model;
import java.util.ArrayList;
import java.util.Arrays;

public class TVShow extends Item
{
   //properties
   private String director, country, genre;
   private int year, episodeDuration, seasons;
   private String[] cast;
   private ArrayList<ArrayList<Integer>> favoriteEpisodes;

   /**
    * This constructor get id as a parameter and pass it to the parent class to record
    *
    * @param id int identification value of the item
    */
   public TVShow( int id )
   {
      super( id );
      ArrayList<String> values = DatabaseConnection.getItemInfo("TVShow", id + "");
      if( !values.isEmpty()){
	      setTitle( values.get(1));
	      setDirector(values.get(2));
	      setCountry( values.get(3));
	      setGenre( values.get(4));
	      setYear( Integer.parseInt(values.get(5)));
	      setEpisodeDuration( Integer.parseInt(values.get(6)));
	      setSeasons(  Integer.parseInt(values.get(7)));
	      cast = new String[3];
	      cast[0] = values.get(8);
	      cast[1] = values.get(9);
	      cast[2] = values.get(10);
      }
   }


   /**
    * This constructor get all necessary variables from user and initializes class's variables and pass id and title to the parent class
    *
    * @param title String variable that indicates title of the Tv show
    * @param director String variable that indicates director of the Tv show
    * @param country String variable that indicates country of the Tv show
    * @param genre String variable that indicates genre of the Tv show
    * @param year int variable that indicates year of the Tv show
    * @param seasons int variable that indicates number of the seasons of the Tv show
    * @param episodeDuration int variable that indicates average duration of the episode of the Tv show
    */
   public TVShow(String title, String director, String country, String genre, int year, int seasons, int episodeDuration, String cast1, String cast2, String cast3)
   {
      super( "TVShow");
      this.setTitle(title);
      this.director = director;
      this.country = country;
      this.genre = genre;
      this.year = year;
      this.episodeDuration = episodeDuration;
      this.seasons = seasons;
      cast = new String[3];
      cast[0] = cast1;
      cast[1] = cast2;
      cast[2] = cast3;
      favoriteEpisodes = new ArrayList<ArrayList<Integer>>();
   }

   //methods
   public void setCast(int index, String castMember){
	   this.cast[index] = castMember;
   }
   
   /**
    *
    * @return
    */
   public String propertiesForDb(){
      return "(`id`, `title`, `director`, `country`, `genre`, `year`, `episodeDuration`, `seasons`, `cast1`, `cast2`, `cast3`) VALUES ("
              + "NULL" + ", \"" + getTitle() + "\", \"" + getDirector()
              + "\", \"" + getCountry() + "\", \"" + getGenre() + "\", " + getYear() + ", "
              + getEpisodeDuration() + ", " + getSeasons() + ", \"" + cast[0] + "\", \"" + cast[1] + "\", \"" + cast[2] + "\" )";
   }

   /**
    * returns the director of the TvShow
    * @return director of the TvShow
    */
   public String getDirector()
   {
      return director;
   }

   /**
    * sets the director of the TvShow
    * @param director director of the TvShow
    */
   public void setDirector( String director )
   {
      this.director = director;
   }

   /**
    * returns the country which the TvShow was made
    * @return country of TvShow
    */
   public String getCountry()
   {
      return country;
   }

   /**
    * sets the country in which the TvShow was made
    * @param country country of the TvShow
    */
   public void setCountry( String country )
   {
      this.country = country;
   }

   /**
    * returns the genre of the TvShow
    * @return genre of the TvShow
    */
   public String getGenre()
   {
      return genre;
   }

   /**
    * sets the genre of the TvShow
    * @param genre genre of the TvShow
    */
   public void setGenre( String genre )
   {
      this.genre = genre;
   }

   /**
    * returns the year in which the movie was TvShow
    * @return publication year of the TvShow
    */
   public int getYear()
   {
      return year;
   }

   /**
    * sets the year in which the TvShow was made
    * @param year year of the TvShow
    */
   public void setYear( int year )
   {
      this.year = year;
   }

   /**
    * returns the duration of the TvShow
    * @return duration of the TvShow
    */
   public int getEpisodeDuration()
   {
      return episodeDuration;
   }

   /**
    * sets the episode duration of the TvShow
    * @param episodeDuration episodeDuration of the TvShow
    */
   public void setEpisodeDuration( int episodeDuration )
   {
      this.episodeDuration = episodeDuration;
   }

   /**
    * returns the favourite cast members of the TvShow
    * @return cast of the TvShow
    */
   public String[] getCast()
   {
      return cast;
   }

   /**
    * adds a favourite cast member
    * @param name name of the cast member
    */
   public boolean addCastMember( String name ) {
      for (int i = 0; i < 3; i++){
         if (cast[i] == null && cast[i].length() == 0){
            cast[i] = name;
            return true;
         }
      }
      return false;
   }


   /**
    * removes the cast member given their name
    * @param name of the cast member
    */
   public boolean removeCastMember( String name )
   {
      for (int i = 0; i < 3; i++){
         if (cast[i].equals(name)){
            cast[i] = "";
            return true;
         }
      }
      return false;
   }

   /**
    * returns number of the seasons of the Tv Show
    * @return seasons int number of seasons of the tv show
    */
   public int getSeasons()
   {
      return seasons;
   }

   /**
    * sets the number  seasons of the Tv Show
    * @param seasons number ofthe  seasons of the tv show
    */
   public void setSeasons( int seasons)
   {
      this.seasons = seasons;
   }

   /**
    * returns list of favorite episodes
    * @return favoriteEpisodes array list of favorite episodes
    */
   public ArrayList<ArrayList<Integer>> getFavoriteEpisodes()
   {
      return favoriteEpisodes;
   }


    /**
    * adds favorite episode to the list of favorite episodes
    * @param season int index that indicates season of the fav. episode
    * @param episode int index of episode in the season
    */
   public void addFavoriteEpisode( int season, int episode)
   {
      favoriteEpisodes.get(season).add(episode);
   }

   /**
    * removes choosen favorite episode from the list of favorite episodes
    * @param season int index that indicates season of the fav. episode
    * @param episode int index of episode in the season
    */
   public void removeFavoriteEpisode( int season, int episode)
   {
      favoriteEpisodes.get(season).remove(episode);
   }
}
