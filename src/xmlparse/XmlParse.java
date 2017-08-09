package xmlparse;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import dao.XmlDao;
import domain.Xml;
import factory.DaoFactory;

public class XmlParse {
	final static String filePath = "c:\\java_workspace\\xmlParse\\src\\xml";
	static DocumentBuilderFactory domFactoty;
	static DocumentBuilder domBuilder;
	static Document doc;
	static File file;
	static File[] files;
	
	public void addFile() throws IOException, SAXException, ParserConfigurationException {
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
			
			printNode("App_Data");
			printNode("AMS");
			printNode("Content");
		}
	}
	
	private  void printNode(String tagName) {
		NodeList list = doc.getElementsByTagName(tagName);
		System.out.println(tagName+"³ëµå ¼ö :"+list.getLength());
		Xml xml = new Xml();
		XmlDao dao = new DaoFactory().xmlDao();
		
	  switch (tagName) {
		case "AMS":
			for (int i = 0; i < list.getLength(); i++) {
				Node node = list.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;
					
					System.out.print("Asset_Class:" + getTagValue("Asset_Class", element));
					xml.setAsset_Class(getTagValue("Asset_Class", element));

					System.out.print(" | Asset_ID:" + getTagValue("Asset_ID", element));
					xml.setAsset_Id(getTagValue("Asset_ID", element));

					System.out.print(" | Asset_Name:" + getTagValue("Asset_Name", element));
					xml.setAsset_Name(getTagValue("Asset_Name", element));

					System.out.print(" | Creation_Date:" + getTagValue("Creation_Date", element));
					xml.setCreation_Date(getTagValue("Creation_Date", element));

					System.out.print(" | Description:" + getTagValue("Description", element));
					xml.setDescription(getTagValue("Description", element));

					System.out.print(" | Product:" + getTagValue("Product", element));
					xml.setProduct(getTagValue("Product", element));

					System.out.print(" | Provider:" + getTagValue("Provider", element));
					xml.setProvider(getTagValue("Provider", element));

					System.out.print(" | Provider_ID:" + getTagValue("Provider_ID", element));
					xml.setProvider_Id(getTagValue("Provider_ID", element));

					// System.out.println(" | Verb:"+getTagValue("Verb",
					// element));
					xml.setVerb("");

					System.out.print(" | Version_Major:" + getTagValue("Version_Major", element));
					xml.setVersion_Major(Integer.parseInt(getTagValue("Version_Major", element)));

					System.out.println(" | Version_Minor:" + getTagValue("Version_Minor", element));
					xml.setVersion_Minor(Integer.parseInt(getTagValue("Version_Minor", element)));

					try {
						dao.add(xml);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
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
