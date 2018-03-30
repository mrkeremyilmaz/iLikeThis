package group1c.ilikethis.model.cs102.group1c.ilikethis.model;

import java.sql.*;
import java.util.ArrayList;

/**
 * 
 * 
 * @author keremyilmaz
 * @version 12/04/2017
 */
public class DatabaseConnection {

	protected static Connection con;

	/**
	 * 
	 */
	public DatabaseConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");

			con = DriverManager.getConnection("jdbc:mysql://sql11.freemysqlhosting.net:3306/sql11168767", "sql11168767",
					"Kjw3ddic2l");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * 
	 */
	public static void connectToDatabase() {
		try {
			Class.forName("com.mysql.jdbc.Driver");

			con = DriverManager.getConnection("jdbc:mysql://sql11.freemysqlhosting.net:3306/sql11168767", "sql11168767",
					"Kjw3ddic2l");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * 
	 */
	public void closeConnection() {
		try {
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * 
	 * @param category
	 * @param searchTag
	 * @return
	 */
	public static ArrayList<ArrayList<String>> searchCategory(String category, String searchTag) {
		String result = "";
		ArrayList<ArrayList<String>> search = new ArrayList<>();
		ArrayList<String> item = new ArrayList<>(2);
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * from " + category + " WHERE title LIKE \"%" + searchTag + "%\"");
			while (rs.next()) {
				item = new ArrayList<>(2);
				item.add(rs.getString(2)); // title of the item
				item.add(rs.getString(1)); // id of the item
				search.add(item);
			}
			return search;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	public static ArrayList<String> getSearchTitles( ArrayList<ArrayList<String>> searchResult){
		ArrayList<String> titles = new ArrayList<String>();
		for (int i = 0; i < searchResult.size(); i++)
		{
			titles.add(searchResult.get(i).get(0));
		}
		return titles;
	}
	
//DatabaseConnection.getItemInfo("Book", var.get(i).get(1))
	
	public static ArrayList<String> getItemInfo(String category, String id) {
		String value;
		ArrayList<String> values = new ArrayList<>();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * from " + category + " WHERE id = " + id);
			ResultSetMetaData rsmd = rs.getMetaData();
			if (rs.next()) {
				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					value = "";
					value = rs.getString(i);
					values.add(value);
				}
			}
			return values;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;

	}

	/**
	 * 
	 * @param item
	 */
	public static void addItemToDb(Item item) {
		try {
			Statement st = con.createStatement();
			System.out.println("INSERT INTO " + item.getClass().getName().substring(30) + " " + item.propertiesForDb());
			st.executeUpdate("INSERT INTO " + item.getClass().getName().substring(30) + " " + item.propertiesForDb());
		} catch (Exception e) {
			System.out.println(e);
		}
		/**
		 * try{ System.out.println("INSERT INTO " + "\"" +
		 * item.getClass().getName() + "\"" + " " + item.propertiesForDb());
		 * PreparedStatement prs = con.prepareStatement("INSERT INTO " + "\"" +
		 * item.getClass().getName() + "\"" + " " + item.propertiesForDb());
		 * prs.executeUpdate(); } catch (Exception e) { System.out.println(e + "
		 * cause: " + e.getCause()); }
		 */
	}

	/**
	 * 
	 * @param category
	 * @return
	 */
	public static int createNewId(String category) {
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT MAX(id) from " + category);
			if (rs.next())
				return rs.getInt(1) + 1;
			return 0;
		} catch (Exception e) {
			System.out.println(e + " cause:" + e.getCause());
			return -1;
		}
	}

	/**
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public static boolean userLogin(String username, String password) {
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT password from `users`" + " WHERE username = \"" + username + "\"");
			if (rs.next())
				return password.equals(rs.getString(1));
			return false;
		} catch (Exception e) {
			System.out.println(e + " cause:" + e.getCause());
			return false;
		}
	}

	/**
	 * 
	 * @param category
	 * @return
	 */
	public static ResultSet printAll(String category) {
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * from " + category);
			return rs;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	/**
	 * 
	 * @param username
	 * @return
	 */
	public static String getEmail(String username) {
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT email from `users`" + " WHERE username = \"" + username + "\"");
			if (rs.next())
				return rs.getString(1);
			return "";
		} catch (Exception e) {
			System.out.println(e + " cause:" + e.getCause());
			return "";
		}
	}

	public static String getPwd(String username) {
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT password from `users`" + " WHERE username = \"" + username + "\"");
			if (rs.next())
				return rs.getString(1);
			return "";
		} catch (Exception e) {
			System.out.println(e + " cause:" + e.getCause());
			return "";
		}
	}

	public static int addUser(String username, String password, String email) {
		try {
			// check if username is used or not
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT username from `users`" + " WHERE username = \"" + username + "\"");
			if (rs.next())
				return 1;

			// check if email is used or not
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT email from `users`" + " WHERE email = \"" + email + "\"");
			if (rs.next())
				return 2;

			stmt = con.createStatement();
			stmt.executeUpdate("INSERT INTO users " + "(`id`, `username`, `password`, `email`) VALUES ( NULL, \""
					+ username + "\", \"" + password + "\", \"" + email + "\")");

			// check if push is successful
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT username from `users`" + " WHERE username = \"" + username + "\"");
			if (rs.next())
				return 0;
			return -2;
		} catch (Exception e) {
			System.out.println(e);
			return -1;
		}
	}
}
