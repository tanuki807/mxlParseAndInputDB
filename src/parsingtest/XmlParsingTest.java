package parsingtest;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlParsingTest {
	static DocumentBuilderFactory domFactoty;
	static DocumentBuilder domBuilder;
	static Document doc;
	
	public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException  {
		domFactoty = DocumentBuilderFactory.newInstance();
		domBuilder = domFactoty.newDocumentBuilder();
		doc = domBuilder.parse(new File("src/xml/M0000824LSV1831110-1482284097457.xml"));
		doc.normalize();
		
		System.out.println("Root element: "+doc.getDocumentElement().getNodeName());

		printNode("App_Data");
		
		printNode("AMS");
		
		printNode("Content");

	}
	
	private static void printNode(String tagName) {
		System.out.println();
		NodeList list = doc.getElementsByTagName(tagName);
		System.out.println(tagName+"³ëµå ¼ö :"+list.getLength());
		
		if(tagName.equals("AMS")) {
			
			for(int i=0; i < list.getLength(); i++) {
				Node node = list.item(i);
				if(node.getNodeType()==Node.ELEMENT_NODE) {
					Element element = (Element)node;
					System.out.print("Asset_Class:"+getTagValue("Asset_Class" , element));
					System.out.print(" | Asset_ID:"+getTagValue("Asset_ID", element));
					System.out.print(" | Asset_Name:"+getTagValue("Asset_Name", element));
					System.out.print(" | Creation_Date:"+getTagValue("Creation_Date", element));
					System.out.print(" | Description:"+getTagValue("Description", element));
					System.out.print(" | Product:"+getTagValue("Product", element));
					System.out.print(" | Provider:"+getTagValue("Provider", element));
					System.out.print(" | Provider_ID:"+getTagValue("Provider_ID", element));
					//System.out.println(" | Verb:"+getTagValue("Verb", element));
					System.out.print(" | Version_Major:"+getTagValue("Version_Major", element));
					System.out.println(" | Version_Minor:"+getTagValue("Version_Minor", element));
				}
			}
			
		} else if(tagName.equals("App_Data")) {
			
			for(int i=0; i < list.getLength(); i++) {
				Node node = list.item(i);
				if(node.getNodeType()==Node.ELEMENT_NODE) {
					Element element = (Element)node;
					System.out.print("Name:"+getTagValue("Name", element));
					System.out.println(" | Value:"+getTagValue("Value", element));
				}
			}
			
		} else {
			
			for(int i=0; i < list.getLength(); i++) {
				Node node = list.item(i);
				if(node.getNodeType()==Node.ELEMENT_NODE) {
					Element element = (Element)node;
					System.out.println("Value:"+getTagValue("Value", element));
				}
			}
		}	
	}
	
	private static String getTagValue(String tag, Element element) {
		String list = element.getAttributeNode(tag).getNodeValue();
		return list;
	}
}
