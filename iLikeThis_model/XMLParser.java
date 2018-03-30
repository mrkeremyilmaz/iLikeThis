package group1c.ilikethis.model.cs102.group1c.ilikethis.model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.w3c.dom.Node;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class XMLParser {
	// properties
	User user;
	File file;
	String directory;
	User sharedUser;

	// constructor
	public XMLParser(String directory) {
		this.directory = directory;

		file = new File(directory + "/shared_with.xml");
		if (!file.exists()) {
			try {
				file.getParentFile().mkdirs();
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public XMLParser(User user, String directory) {
		this.user = user;
		this.directory = directory;
		file = new File(directory + "/" + user.getUsername() + ".xml");
		if (!file.exists()) {
			try {
				file.getParentFile().mkdirs();
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// methods
	public void appendShared(String xml) {
		try {
			file.delete();
			file = new File(directory + "/shared_with.xml");
			FileWriter fw = new FileWriter(file, true); // the true will
																// append the
																// new data
			fw.write(xml);// appends the string to the file
			fw.close();
		} catch (IOException ioe) {
			System.err.println("IOException: " + ioe.getMessage());
		}
	}

	public String sharedUsername(String xml) {
		int index = 0;
		String xmlxml = xml.substring(39);
		String s = "";
		while (xmlxml.charAt(index) != '>') {
			s += xmlxml.charAt(index);
			index++;
		}
		return s;
	}

	public User getShared(String username) {

		User sharedUser = new User(username);

		try {

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(file);

			NodeList userNodes = doc.getElementsByTagName(username);
			System.out.println(userNodes.item(0));
			Node userNode = userNodes.item(0);

			NodeList categoryNodes = userNode.getChildNodes();
			for (int categoryConstant = 0; categoryConstant < 8; categoryConstant++) {
				Node catNode = categoryNodes.item(categoryConstant);
				Element catElement = (Element) catNode;
				NodeList listNodes = catElement.getElementsByTagName("list");

				for (int listCount = 0; listCount < listNodes.getLength(); listCount++) {
					Node listNode = listNodes.item(listCount);
					NodeList itemNodes = listNode.getChildNodes();
					if (listNode.getNodeType() == Node.ELEMENT_NODE) {
						Element listElement = (Element) listNode;

						List list = new List(listElement.getAttribute("title"));
						for (int itemCount = 0; itemCount < itemNodes.getLength(); itemCount++) {
							Element itemElement = (Element) itemNodes.item(itemCount);
							if (categoryConstant == 0) {
								list.addItem(new Book(Integer.parseInt(itemElement.getAttribute("id"))));
							} else if (categoryConstant == 1) {
								list.addItem(new Movie(Integer.parseInt(itemElement.getAttribute("id"))));
							} else if (categoryConstant == 2) {
								list.addItem(new TVShow(Integer.parseInt(itemElement.getAttribute("id"))));
							} else if (categoryConstant == 3) {
								list.addItem(new Place(Integer.parseInt(itemElement.getAttribute("id"))));
							} else if (categoryConstant == 4) {
								list.addItem(new Music(Integer.parseInt(itemElement.getAttribute("id"))));
							} else if (categoryConstant == 5) {
								list.addItem(new VideoGame(Integer.parseInt(itemElement.getAttribute("id"))));
							} else if (categoryConstant == 6) {
								list.addItem(new Website(Integer.parseInt(itemElement.getAttribute("id"))));
							} else if (categoryConstant == 7) {
								Note note = new Note(itemElement.getAttribute("title"));
								note.setNote(itemElement.getAttribute("note"));
								list.addItem(note);
							}
						}
						sharedUser.addList(categoryConstant, list);
					}
				}
			}

		} catch (ParserConfigurationException e) {
			System.out.println(e);
		} catch (SAXException e) {
			System.out.println(e);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println(e);
			e.printStackTrace();
		}
		this.sharedUser = sharedUser;
		return sharedUser;
	}

	public void toArrayList() {

		if (user != null) {
			try {
				File xmlFile = new File(directory + "/" + user.getUsername() + ".xml");
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(xmlFile);

				NodeList categoryNodes = doc.getElementsByTagName("category");
				for (int categoryConstant = 0; categoryConstant < 8; categoryConstant++) {
					Node catNode = categoryNodes.item(categoryConstant);
					Element catElement = (Element) catNode;
					NodeList listNodes = catElement.getElementsByTagName("list");

					for (int listCount = 0; listCount < listNodes.getLength(); listCount++) {
						Node listNode = listNodes.item(listCount);
						NodeList itemNodes = listNode.getChildNodes();
						if (listNode.getNodeType() == Node.ELEMENT_NODE) {
							Element listElement = (Element) listNode;

							List list = new List(listElement.getAttribute("title"));
							for (int itemCount = 0; itemCount < itemNodes.getLength(); itemCount++) {
								Element itemElement = (Element) itemNodes.item(itemCount);
								if (categoryConstant == 0) {
									list.addItem(new Book(Integer.parseInt(itemElement.getAttribute("id"))));
								} else if (categoryConstant == 1) {
									list.addItem(new Movie(Integer.parseInt(itemElement.getAttribute("id"))));
								} else if (categoryConstant == 2) {
									list.addItem(new TVShow(Integer.parseInt(itemElement.getAttribute("id"))));
								} else if (categoryConstant == 3) {
									list.addItem(new Place(Integer.parseInt(itemElement.getAttribute("id"))));
								} else if (categoryConstant == 4) {
									list.addItem(new Music(Integer.parseInt(itemElement.getAttribute("id"))));
								} else if (categoryConstant == 5) {
									list.addItem(new VideoGame(Integer.parseInt(itemElement.getAttribute("id"))));
								} else if (categoryConstant == 6) {
									list.addItem(new Website(Integer.parseInt(itemElement.getAttribute("id"))));
								} else if (categoryConstant == 7) {
									Note note = new Note(itemElement.getAttribute("title"));
									note.setNote(itemElement.getAttribute("note"));
									list.addItem(note);
								}
							}
							user.addList(categoryConstant, list);
						}
					}
				}

			} catch (ParserConfigurationException e) {
				System.out.println(e);
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		else{
			try {
				File xmlFile = new File(directory + "/shared_with.xml");
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(xmlFile);

				NodeList categoryNodes = doc.getElementsByTagName("category");
				for (int categoryConstant = 0; categoryConstant < 8; categoryConstant++) {
					Node catNode = categoryNodes.item(categoryConstant);
					Element catElement = (Element) catNode;
					NodeList listNodes = catElement.getElementsByTagName("list");

					for (int listCount = 0; listCount < listNodes.getLength(); listCount++) {
						Node listNode = listNodes.item(listCount);
						NodeList itemNodes = listNode.getChildNodes();
						if (listNode.getNodeType() == Node.ELEMENT_NODE) {
							Element listElement = (Element) listNode;

							List list = new List(listElement.getAttribute("title"));
							for (int itemCount = 0; itemCount < itemNodes.getLength(); itemCount++) {
								Element itemElement = (Element) itemNodes.item(itemCount);
								if (categoryConstant == 0) {
									list.addItem(new Book(Integer.parseInt(itemElement.getAttribute("id"))));
								} else if (categoryConstant == 1) {
									list.addItem(new Movie(Integer.parseInt(itemElement.getAttribute("id"))));
								} else if (categoryConstant == 2) {
									list.addItem(new TVShow(Integer.parseInt(itemElement.getAttribute("id"))));
								} else if (categoryConstant == 3) {
									list.addItem(new Place(Integer.parseInt(itemElement.getAttribute("id"))));
								} else if (categoryConstant == 4) {
									list.addItem(new Music(Integer.parseInt(itemElement.getAttribute("id"))));
								} else if (categoryConstant == 5) {
									list.addItem(new VideoGame(Integer.parseInt(itemElement.getAttribute("id"))));
								} else if (categoryConstant == 6) {
									list.addItem(new Website(Integer.parseInt(itemElement.getAttribute("id"))));
								} else if (categoryConstant == 7) {
									Note note = new Note(itemElement.getAttribute("title"));
									note.setNote(itemElement.getAttribute("note"));
									list.addItem(note);
								}
							}
							sharedUser.addList(categoryConstant, list);
						}
					}
				}

			} catch (ParserConfigurationException e) {
				System.out.println(e);
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	

	public void toXml() {
		try {
			// create DocumentBuilderFactory and DocumentBuilder
			DocumentBuilderFactory xmlFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder xmlBuilder = xmlFactory.newDocumentBuilder();

			// root elements
			Document xml = xmlBuilder.newDocument();
			Element rootElement = xml.createElement(user.getUsername()); // enter
																			// username
																			// here
			xml.appendChild(rootElement);

			// category elements
			Element catElement;
			for (int categoryConstant = 0; categoryConstant < 8; categoryConstant++) {
				catElement = xml.createElement("category"); // enter category
															// constant
				rootElement.appendChild(catElement);

				// setting constant of the category as attribute
				Attr constant = xml.createAttribute("constant");
				constant.setValue(categoryConstant + "");
				catElement.setAttributeNode(constant);

				// list elements
				ArrayList<List> lists = user.getCategories().get(categoryConstant);
				for (int listCount = 0; listCount < lists.size(); listCount++) {
					List list = lists.get(listCount);

					Element listElement = xml.createElement("list"); // enter
																		// list
																		// name
					catElement.appendChild(listElement);

					// setting title of the list as attribute
					Attr title = xml.createAttribute("title");
					title.setValue(lists.get(listCount).getTitle());
					listElement.setAttributeNode(title);

					for (int itemCount = 0; itemCount < list.size(); itemCount++) {
						// item elements

						Element itemElement = xml.createElement("item"); // enter
																			// item
																			// id
						listElement.appendChild(itemElement);

						// setting id of the item as attribute
						Attr id = xml.createAttribute("id");
						id.setValue(list.getItem(itemCount).getID() + "");
						itemElement.setAttributeNode(id);
					}
				}
			}

			TransformerFactory xmlTransFac = TransformerFactory.newInstance();
			Transformer xmlTrans = xmlTransFac.newTransformer();
			DOMSource xmlSource = new DOMSource(xml);
			File newFile = new File(directory + "/" + user.getUsername() + ".xml");
			file.delete();
			StreamResult xmlResult = new StreamResult(new File(directory + "/" + user.getUsername() + ".xml"));

			xmlTrans.transform(xmlSource, xmlResult);

			System.out.println("File saved!");
		} catch (Exception e) {
			System.out.println(e + " cause: " + e.getCause());
		}
	}

	public String shareSelected() {
		try {
			// create DocumentBuilderFactory and DocumentBuilder
			DocumentBuilderFactory xmlFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder xmlBuilder = xmlFactory.newDocumentBuilder();

			// root elements
			Document xml = xmlBuilder.newDocument();
			Element rootElement = xml.createElement(user.getUsername()); // enter
			xml.appendChild(rootElement);

			// category elements
			Element catElement;
			for (int categoryConstant = 0; categoryConstant < 8; categoryConstant++) {
				catElement = xml.createElement("category"); // enter category
															// constant
				rootElement.appendChild(catElement);

				// setting constant of the category as attribute
				Attr constant = xml.createAttribute("constant");
				constant.setValue(categoryConstant + "");
				catElement.setAttributeNode(constant);

				// list elements
				ArrayList<List> lists = user.getCategories().get(categoryConstant);
				for (int listCount = 0; listCount < lists.size(); listCount++) {
					List list = lists.get(listCount);

					if (list.getSelected()) {
						Element listElement = xml.createElement("list");
						catElement.appendChild(listElement);

						// setting title of the list as attribute
						Attr title = xml.createAttribute("title");
						title.setValue(lists.get(listCount).getTitle());
						listElement.setAttributeNode(title);

						for (int itemCount = 0; itemCount < list.size(); itemCount++) {
							// item elements

							Element itemElement = xml.createElement("item");
							listElement.appendChild(itemElement);

							// setting id of the item as attribute
							Attr id = xml.createAttribute("id");
							id.setValue(list.getItem(itemCount).getID() + "");
							itemElement.setAttributeNode(id);
						}
					} else if (!list.getSelected()) {
						Element listElement = xml.createElement("list");
						catElement.appendChild(listElement);
						int count = 0;
						// setting title of the list as attribute
						Attr title = xml.createAttribute("title");
						title.setValue(lists.get(listCount).getTitle());
						listElement.setAttributeNode(title);

						for (int itemCount = 0; itemCount < list.size(); itemCount++) {
							// item elements
							if (list.getItem(itemCount).getSelected()) {
								Element itemElement = xml.createElement("item"); // enter
																					// item
																					// id
								listElement.appendChild(itemElement);
								count++;
								// setting id of the item as attribute
								Attr id = xml.createAttribute("id");
								id.setValue(list.getItem(itemCount).getID() + "");
								itemElement.setAttributeNode(id);
							}
						}

						if (count == 0)
							catElement.removeChild(listElement);
					}
				}

				TransformerFactory xmlTransFac = TransformerFactory.newInstance();
				Transformer xmlTrans = xmlTransFac.newTransformer();
				DOMSource xmlSource = new DOMSource(xml);
				StreamResult xmlResult = new StreamResult(
						new File(directory + "/share_" + user.getUsername() + ".xml"));

				xmlTrans.transform(xmlSource, xmlResult);

				System.out.println("File saved!");
			}
			return readFile(directory + "/share_" + user.getUsername() + ".xml");
		} catch (Exception e) {
			System.out.println(e + " cause: " + e.getCause());
		}
		return "not successful";
	}

	public static String readFile(String path) throws IOException {
		StringBuilder sb = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			String currentLine = "";
			while (currentLine != null) {
				sb.append(currentLine);
				currentLine = br.readLine();
			}
		}
		return sb.toString();
	}
}