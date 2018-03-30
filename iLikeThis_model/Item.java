package group1c.ilikethis.model.cs102.group1c.ilikethis.model;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
@author kerem_yilmaz
@version 1.0
1.0 date: 11 April 2017
The abstract class which will be extended by Category Classes such as "Books",
 "Movies", etc.
Properties//
- title: String
- additionalNotes: String
- selected: boolean
- id: int
Constructor//
+ Item(id: int, title: String, dateModified: String)
+ Item(id: int)
Methods//
+ setSelected( selected: boolean): void
+ getSelected(): boolean
+ setTitle( title: String): void
+ getTitle(): String
+ setAdditionalNotes(notes :String): void
+ getAdditionalNotes(): String
+ getID(): int
+ setID(id: int): void
+ compareTo(item: Item): int
+ setDateModified(dateModified: String): void
*/
public abstract class Item implements Selectable, Uploadable
{
  //Properties
  private String title = "";
  private String additionalNotes = "";
  private boolean selected;
  private int id;
  private String dateModified;

  //constructor
  public Item( int id)
  {
      this.id = id;
  }

  public Item(String category) {
      id = DatabaseConnection.createNewId( category);
  }
  //Methods
  /**
   * a method to set dateModified to current time
   */
  public void setDateModified()
  {
      LocalDate localDate = LocalDate.now();
      this.dateModified = DateTimeFormatter.ofPattern("yyy/MM/dd").format(localDate);
  }

  /**
   *
   * @return
   */
  public String getDateModified()
  {
    return dateModified;
  }
  
  /**
   * @return
   */
  public String toString(){
	  return this.title;
  }
  /**
  a method to return the id of the item
  @return the id of the item
  */
  public int getID()
  {
      return this.id;
  }

  /**
  a method to compare items according to
  their ID numbers.
  if they are same return 0, -1 if this should be first, 1 if item should be first, -2 if their classes are different
  @param item the item to be compared with
  @return 0 if they are same, -1 if this should be first, 1 if item should be first, -2 if their classes are different
  */
  public int compareTo(Item item)
  {
      if(this.getClass().getName() != item.getClass().getName())
        return -2;
      if(this.getID() == item.getID())
        return 0;
      else if(this.getID() < item.getID())
        return -1;
      else
        return 1;
  }

  /**
  a method to set selected value of item
  @param selected the boolean value of item to set selected value as
  */
  public void setSelected(boolean selected)
  {
    this.selected = selected;
  }

  /**
  a method to get selected value of item
  @return selected value of the item
  */
  public boolean getSelected()
  {
    return selected;
  }

  /**
  a method to set the title
  @param title the new title to be set as title
  */
  public void setTitle(String title)
  {
    this.title = title;
  }

  /**
  a method to return the title
  @return the title
  */
  public String getTitle()
  {
    return title;
  }

  /**
  a method to edit additionalNotes
  @param notes edited notes to be passed to additionalNotes
  */
  public void setAdditionalNotes(String notes)
  {
    additionalNotes = notes;
  }

  /**
  a method to get additionalNotes
  @return the additionalNotes
  */
  public String getAdditionalNotes()
  {
    return additionalNotes;
  }
}
