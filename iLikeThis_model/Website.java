package group1c.ilikethis.model.cs102.group1c.ilikethis.model;

import java.util.ArrayList;

/**
 * Website class.
 * @author Muhammed Salih Altun
 * @version 1.0, 11.04.2017
 * update: 01.05.2017 - Constructor changes added.
 */
public class Website extends Item
{

   //properties

   private String useOfSite = "";

   //constructors

   /**
    * Constructs website object with given title and useOfSite.
    * @param title given title
    * @param useOfSite
    */
   public Website( String title, String useOfSite )
   {
      super( "Website");
      this.setTitle(title);
      this.useOfSite = useOfSite;
   }

   /**
    * Constructs a website object with the given id
    * @param id id of this object in the database
    */
   public Website( int id )
   {
      super(id);
      ArrayList<String> values = DatabaseConnection.getItemInfo("Website", id + "") ;
      if( !values.isEmpty()){
	      setTitle( values.get(1));
	      setUseOfSite(values.get(2));
      }
   }

   /**
    *
    * @return
    */
   public String propertiesForDb(){
      return "(`id`, `title`, `useOfSite`) VALUES (" + "NULL" + ", \"" + getTitle()
              + "\", \"" + getUseOfSite() + "\")";
   }

   /**
    * Returns the use of this site
    * @return use of this site
    */
   public String getUseOfSite()
   {
      return useOfSite;
   }

   /**
    * Sets use of this site to given string
    * @param givenString given string
    */
   public void setUseOfSite( String givenString )
   {
      useOfSite = givenString;
   }

}//end of class Website
