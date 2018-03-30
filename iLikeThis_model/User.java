package group1c.ilikethis.model.cs102.group1c.ilikethis.model;
import java.security.Security;
import java.util.*;
import java.util.List;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * User class. A user has lists of their items, username and password.
 * Created by Salih on 2/5/2017.
 * User can now sendEmailToDevs.
 * print method added for testing.
 * addItem method added
 */
public class User {

    //constants for ArrayList indexes. not used for now?


    public static final int LIST_BOOKS = 0;
    public static final int LIST_MOVIES = 1;
    public static final int LIST_TV_SHOWS= 2;
    public static final int LIST_PLACES = 3;
    public static final int LIST_MUSIC = 4;
    public static final int LIST_VIDEO_GAMES = 5;
    public static final int LIST_WEBSITES = 6;
    public static final int LIST_NOTES = 7;

    //properties
    private ArrayList<ArrayList<java.util.List>> categories;
    private String username;
    private String password;

    //constructor
    public User( String username)
    {
    	this.username = username;
        categories = new ArrayList<>(8);         
        for ( int i = 0; i < 8; i++) {
            categories.add(new ArrayList<java.util.List>());        // to do
        }
    }
    
    public User( String username, String password) {
    	DatabaseConnection.connectToDatabase();
    	if (DatabaseConnection.userLogin(username,password)) {
            this.username = username;
            this.password = password;
            categories = new ArrayList<>(8);         
            for ( int i = 0; i < 8; i++) {
                categories.add(new ArrayList<java.util.List>());        // to do
            }
        }
    }

    //methods

    /**
     *@return username
     */
    public String getUsername() { return username; }

    /**
     * This method sends the message in the paramater to application's developers.
     * @param messageToDevs user's message to devs
     */
    public void sendEmailToDevs( String messageToDevs ) {

        final String mailUsername = "dev.ilikethis@gmail.com";
        final String mailPassword = "ilikethispassword";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        
        //create new session.
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(mailUsername, mailPassword);
                    }
                });
        try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress("dev.ilikethis@gmail.com"));

            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("dev.ilikethis@gmail.com"));

            // Set Subject: header field
            message.setSubject("iLikeThis" + "from user : " + username);

            // Now set the actual message
            message.setText(messageToDevs);

            // Send message
            Transport.send(message);

            System.out.println("Sent message successfully...");

        } catch (MessagingException e) {
            System.out.println(e);
        }
    }

    public void addList(int index, java.util.List list) {
        categories.get(index).add(list);
    }

    //for testing purposes?
    public void addItem(java.util.List list, Item item) {
         if ( list != null)
             list.addItem( item);
    }

    /**
     *
     *
     */
    public ArrayList<ArrayList<List>> getCategories() {
        return categories;
    }

    public int getCategoryCount() { return categories.size(); }

    public int getListCount(int index) { return categories.get(index).size(); }

    public int getItemCount(int index) {
        int count = 0;
        for (int i = 0; i < categories.get(index).size(); i++) {
            count += categories.get(index).get(i).size();
        }
        return count;
    }

    public static void requestPassword( String username ) {
    	try {
        final String mailUsername = "dev.ilikethis@gmail.com";
        final String mailPassword = "ilikethispassword";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        //create new session.
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(mailUsername, mailPassword);
                    }
                });
        
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress("dev.ilikethis@gmail.com"));

            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(DatabaseConnection.getEmail(username)));

            // Set Subject: header field
            message.setSubject("iLikeThis password recovery");

            // Now set the actual message
            message.setText("Password : " + DatabaseConnection.getPwd(username));

            // Send message
            Transport.send(message);

            System.out.println("Sent message successfully...");

        } catch (MessagingException e) {
            System.out.println(e);
        }
    }

}
