package group1c.ilikethis.model.cs102.group1c.ilikethis.model;
/**
 * Note class
 * @author Muhammed Salih Altun
 * @version 1.0, 11.04.2017
 * update : 01.05.2017 - Constructor changes added.
 */
public class Note extends Item
{
   //properties

   private String note;

   //constructors

   /**
    * Constructs a note object with the String given in the paramater, empty title.
    * @param note given String
    */
   public Note( String title )
   {
      super( "Note");
      this.setTitle(title);
   }

   /**
    * Constructs a note object with the String given and title in the parameter.
    */
   public Note( int id)
   {
      super(id);
      this.note = note;
   }

   //methods

   public String propertiesForDb(){
      return "";
   }
   /**
    * Returns this note.
    */
   public String getNote()
   {
      return note;
   }

   /**
    * Changes this note to given String.
    * @param input given string.
    */
   public void setNote( String input )
   {
      note = input;
   }

}//end of class Note
