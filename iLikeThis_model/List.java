package group1c.ilikethis.model.cs102.group1c.ilikethis.model;
import java.util.*;
// all the imports down below are for dom XML
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;

/**
@author kerem_yilmaz
@version 1.0
1.0 date : 11 April 2017
List is the class for creating lists.
Properties//
- items: ArrayList<List>
- selected: boolean
Methods//
+ addItem( item: Item): void
+ removeItem(index: int): void
+ getItem(index: int): Item
+ writeSelectedToXML(): void    ----- A LOT TO CHECK AND LEARN USE DOM Parser
http://www.mkyong.com/java/how-to-modify-xml-file-in-java-dom-parser/ source!!!
+ setSelected(selected: boolean): void
+ getSelected(): boolean
+ getIndexOf(item: Item): int  //CHANGE THIS TO BINARY SEARCH
+ compareListTo(list: List): int
*/
public class List
{

  //properties
  private ArrayList<Item> items;
  private boolean selected;
  private String title = "";

  /**
  *constructor
  *@param title title of this list
  */
  public List(String title)
  {
    items = new ArrayList<>();
    selected = false;
    this.title = title;
  }

  //Methods
  /**
   * 
   * @return
   */
  public String getTitle(){
	 return this.title;
  }
  
  /**
   * 
   * @return
   */
  public String toString(){
	 return items.toString();
  }
    /**
     * @return items in this list
     */
  public ArrayList<Item> getList()
  {
     return items;
  }
  
  
  /**
  *compares to lists and returns 0 ore -1
  */
  public int compareTo(List list)
  {
     Collections.sort(this.getList(), new Comparator<Item>() {
        @Override
        public int compare(Item it1, Item it2) {
           return it1.compareTo(it2);
        }});
     Collections.sort(list.getList(), new Comparator<Item>() {
        @Override
        public int compare(Item it1, Item it2) {
           return it1.compareTo(it2);
        }});
      if(this.getList().size() != list.getList().size())
        return -1;
      else
      {
        for(int i = 0; i < items.size(); i++)
        {
            if(this.getItem(i).compareTo(list.getItem(i)) != 0)
                return -1;
        }
        return 0;
      }
  }
  /**
  *method for adding item to the lists.
  *@param item the item which will be added to the list.
  */
  public void addItem(Item item) {
    items.add(item);
  }

  /**
  *method for removing item from the lists.
  *@param index index of the item which will be removed
  */
  public void removeItem(int index)
  {
    items.remove(index);
  }

  /**
  CHANGE THIS TO BINARY SEARCH LATER!!!!
  the method to find the index of the item in lists.
  @param item item which we want to learn index of
  @return the index of item if exists else -1
  */
  public int getIndexOf(Item item)
  {
    for(int i = 0; i < items.size(); i++)
    {
      if(item.compareTo(items.get(i)) == 0)
        return i;
    }
    return -1;
  }

  /**
  a method to get item from items arraylist using its index
  @param index index of the item to be returned
  @return the item at the index
  */
  public Item getItem(int index)
  {
    return items.get(index);
  }

  /**
  *a method to set the selected value
  *@param selected the selected value to be set
  */
  public void setSelected(boolean selected)
  {
    this.selected = selected;
  }

  /**
  a method to return the selected value of the lists.
  @return the selected value
  */
  public boolean getSelected()
  {
    return selected;
  }

  public void printItems() {
      System.out.println(items);
  }

  public int size() { return items.size(); }
  /**
  a method to update xml to the latest version of the list
  */
  /**
  public void writeSelectedToXML()
  {
    String filepath = "filepath/blablabla.xml";
    //Create a DocumentBuilder after importing the necessary packages
    DocumentBuilderFactory xmlFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder xmlBuilder = xmlFactory.newDocumentBuilder();

    //Create a Document from a file or stream
    Document xml = xmlBuilder.parse( filepath);
    //get the root element
    Node userName = xml.getFirstChild(); // i suppose it gets user name

    NodeList listNames = userName.getChildNodes();
    //loop for looping through the lists
    for(int i = 0; i < listNames.getLength(); i++)
    {
      NodeList itemsInList = listNames.item( i).getChildNodes();
      //check if list is selected, if so write it to xml then move on to next list
      if(listNames.item(i).getSelected() == true)
      {
        //    TO DO     TO DO     TO DO     TO DO     TO DO
        // write the whole list to the xml
      }
      //if not check for every item in list if they are selected, if so
      //write them to xml and move on
      else
      {
        //loop for looping through the items in the list
        for(int j = 0; j < itemsInList.getLength(); j++)
        {
          //check if item is selected
          if(itemsInList.item( j).getSelected() == true)
          {
            //    TO DO     TO DO     TO DO     TO DO     TO DO
            //write the item to the xml along !! dont forget to write the lists though(discuss)!!!
          }
        }
      }
    }
  */
    //OTHER INFO ABOUT XML WHICH CAN BE USEFUL

    // getElementsByTagName() to get it directly. IMPORTANT

    //I THINK TO CREATE A NEW XML (CODE DOWN BELOW)
    //StringBuilder xmlStringBuilder = new StringBuilder();
    //xmlStringBuilder.append("<?xml version="1.0"?> <class> </class>");
    //ByteArrayInputStream xmlInput =  new ByteArrayInputStream(xmlStringBuilder.toString().getBytes("UTF-8"));

    //Node listName = xml.getElementsByTagName("listName").item(0) //idk what item(0) does

    //uptading attributes of listName
    //NamedNodeMap attr = listName.getAttributes();
  //Node nodeAttr = attr.getNamedItem("The Name Of The Item Goes Here");
  //nodeAttr.setTextContent("Setting the attribute");

    //returns specific attribute
    //getAttribute("attributeName");
    //returns a Map (table) of names/values
    //getAttributes();
    //Examine sub-elements

    //returns a list of subelements of specified name
    //getElementsByTagName("subelementName");
    //returns a list of all child nodes
    //getChildNodes();

  //}
}