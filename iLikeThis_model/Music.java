package group1c.ilikethis.model.cs102.group1c.ilikethis.model;
import java.util.ArrayList;

public class Music extends Item
{
   //properties
   private String album, artist, genre, language;
   private int duration;

   //constructors
   public Music( int id)
   {
      super( id);
      ArrayList<String> values = DatabaseConnection.getItemInfo("Music", id + "");
      if( !values.isEmpty()){
	      setTitle( values.get(1));
	      setAlbum( values.get(2));
	      setArtist( values.get(3));
	      setGenre( values.get(4));
	      setLanguage( values.get(5));
	      setDuration( Integer.parseInt(values.get(6)));
      }
   }

   public Music(String title, String album, String artist, String genre, String language, int duration )
   {
      super( "Music");
      this.setTitle(title);
      this.album = album;
      this.artist = artist;
      this.genre = genre;
      this.language = language;
      this.duration = duration;
   }

   //methods

   /**
    *
    * @return
    */
   public String propertiesForDb(){
      return "(`id`, `title`, `album`, `artist`, `genre`, `language`, `duration`) VALUES ("
              + "NULL" + ", \"" + getTitle() + "\", \"" + getAlbum() + "\", \"" + getArtist()
              + "\", \"" + getGenre() + "\", \"" + getLanguage() + "\", " + getDuration() + ")";
   }

   /**
    * returns the name of the album
    * @return album name
    */
   public String getAlbum()
   {
      return album;
   }

   /**
    * sets the album which the song belongs
    * @param album album which contains the song
    */
   public void setAlbum( String album )
   {
      this.album = album;
   }

   /**
    * returns the name of the artist
    * @return artist name
    */
   public String getArtist()
   {
      return artist;
   }

   /**
    * sets the artist of the song
    * @param artist artist the song belongs to
    */
   public void setArtist( String artist )
   {
      this.artist = artist;
   }

   /**
    * returns the genre of the song
    * @return genre
    */
   public String getGenre()
   {
      return genre;
   }

   /**
    * sets the genre of the song
    * @param genre genre of the song
    */
   public void setGenre( String genre )
   {
      this.genre = genre;
   }

   /**
    * returns the language of the song
    * @return language
    */
   public String getLanguage()
   {
      return language;
   }

   /**
    * sets the language of the song
    * @param language language of the song
    */
   public void setLanguage( String language )
   {
      this.language = language;
   }

   /**
    * returns the duration of the song
    * @return duration
    */
   public int getDuration()
   {
      return duration;
   }

   /**
    * sets the duration of the song
    * @param duration duration of the song
    */
   public void setDuration( int duration )
   {
      this.duration = duration;
   }
}
