package group1c.ilikethis.model.cs102.group1c.ilikethis.model;

import java.util.ArrayList;

public class Place extends Item
{

  // properties
  private String address;
  private String workingHours;
  private String theme;

  // constructors

  /**
   * This initializes a place with id.
   * @param id is the id of the item.
   */
  public Place( int id)
  {
    super( id);
    ArrayList<String> values = DatabaseConnection.getItemInfo("Place", id + "");
    if( !values.isEmpty()){
	    setTitle( values.get(1));
	    setAddress(values.get(2));
	    setWorkingHours( values.get(3));
	    setTheme( values.get(4));
    }
  }

  /**
   * This initializes a place with title, address, workingHours and theme.
   * @param title is the title of the place.
   * @param address is the address of the place.
   * @param workingHours is the working hours of the place.
   * @param theme is the theme of the place.
   */
  public Place(String title, String address, String workingHours, String theme)
  {
  		super( "Place");
  		this.setTitle(title);
  		this.address = address;
  		this.workingHours = workingHours;
  		this.theme = theme;
  }

  // methods
  /**
   *
   * @return
   */
  public String propertiesForDb(){
    return "(`id`, `title`, `address`, `workingHours`, `theme`) VALUES ("
            + "NULL" + ", \"" + getTitle() + "\", \"" + getAddress() + "\", \"" + getWorkingHours()
            + "\", \"" + getTheme() + "\")";
  }

  /**
   * This method returns the address of the place.
   * @return the address of the place.
   */
  public String getAddress()
  {
    return address;
  }

  /**
   * This method sets the address of the place.
   * @param address, the address of the place.
   */
  public void setAddress( String address)
  {
    this.address = address;
  }

  /**
   * This method returns the working hours of the place.
   * @return the working hours of the place.
   */
  public String getWorkingHours()
  {
    return workingHours;
  }

  /**
   * This method sets the working hours of the place.
   * @param workingHours, the working hours of the place.
   */
  public void setWorkingHours( String workingHours)
  {
    this.workingHours = workingHours;
  }

  /**
   * This method returns the theme of the place.
   * @return theme of the place.
   */
  public String getTheme()
  {
    return theme;
  }

  /**
   * This method sets the theme of the place.
   * @param theme, the theme of the place.
   */
  public void setTheme( String theme)
  {
    this.theme = theme;
  }

}
