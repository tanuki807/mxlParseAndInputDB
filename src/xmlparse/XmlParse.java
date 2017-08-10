package xmlparse;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.sql.SQLException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import dao.AmsDao;
import dbconnection.NodeContext;
import domain.Ams;
import factory.DaoFactory;

public class XmlParse {
	final static String filePath = "c:\\java_workspace\\xmlParse\\src\\xml";
	static DocumentBuilderFactory domFactoty;
	static DocumentBuilder domBuilder;
	static Document doc;
	static File file;
	static File[] files;
	
	private NodeContext nodeContext;
	
	public XmlParse() {
		this.nodeContext = new NodeContext();
	}
	
	public void addFile() throws IOException, SAXException, ParserConfigurationException, 
	SQLException, ClassNotFoundException {
		domFactoty = DocumentBuilderFactory.newInstance();
		domBuilder = domFactoty.newDocumentBuilder();
		
		file = new File(filePath);
		if(!(file.exists() || file.isDirectory())){
			System.out.println("file is not Directory");
			System.exit(0);
		}
		
		files = file.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.lastIndexOf("xml") != -1;
			}
		});
		
		int count = 0;
		for(int i=0; i < files.length; i++) {
			String fileName = files[i].getAbsolutePath();
			System.out.println();
			System.out.println("no."+ ++count +" "+fileName);
			doc = domBuilder.parse(fileName);
			doc.normalize();
			
			System.out.println("Root element: "+doc.getDocumentElement().getNodeName());
			
			inputWithPrintNode("App_Data");
			inputWithPrintNode("AMS");
			inputWithPrintNode("Content");
		}
	}
	
	
	private  void inputWithPrintNode(String tagName) throws SQLException, ClassNotFoundException {
		//String[] nodeArr = {"App_Data", "AMS", "Content"};
//		for(int i=0; i < nodeArr.length; i++) {
//			
//		}
		NodeList list = doc.getElementsByTagName(tagName);
		System.out.println(tagName+"³ëµå ¼ö :"+list.getLength());
		Ams ams = new Ams();
		AmsDao dao = new DaoFactory().amsDao();
		
	  switch (tagName) {
	  
		case "AMS":
			this.nodeContext.amsNode(list, ams);
		break;

		case "App_Data":
			for (int i = 0; i < list.getLength(); i++) {
				Node node = list.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;
					System.out.print("Name:" + getTagValue("Name", element));
					System.out.println(" | Value:" + getTagValue("Value", element));
				}
			}
			break;

		case "Content":
			for (int i = 0; i < list.getLength(); i++) {
				Node node = list.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;
					System.out.println("Value:" + getTagValue("Value", element));
				}
			}
		} 
	}
	
	private  String getTagValue(String tag, Element element) {
		String list = element.getAttributeNode(tag).getNodeValue();
		return list;
	}
}
