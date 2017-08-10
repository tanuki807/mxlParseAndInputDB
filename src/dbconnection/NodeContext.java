package dbconnection;

import java.sql.SQLException;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import dao.AmsDao;
import domain.Ams;
import domain.Xml;
import factory.DaoFactory;

public class NodeContext {
	
	AmsDao dao = new DaoFactory().amsDao();
	
	
	public void amsNode(NodeList list, Ams ams) throws ClassNotFoundException, SQLException {
		workWithNodeStrategy(new NodeStrategy() {
			public Xml getTagValue(Element element, Xml xml) {
				System.out.println("Asset_Class:"+getTag("Asset_Class", element));
				ams.setAsset_Class(getTag("Asset_Class", element));

				System.out.print(" | Asset_ID:" + getTag("Asset_ID", element));
				ams.setAsset_Id(getTag("Asset_ID", element));

				System.out.print(" | Asset_Name:" + getTag("Asset_Name", element));
				ams.setAsset_Name(getTag("Asset_Name", element));

				System.out.print(" | Creation_Date:" + getTag("Creation_Date", element));
				ams.setCreation_Date(getTag("Creation_Date", element));

				System.out.print(" | Description:" + getTag("Description", element));
				ams.setDescription(getTag("Description", element));

				System.out.print(" | Product:" + getTag("Product", element));
				ams.setProduct(getTag("Product", element));

				System.out.print(" | Provider:" + getTag("Provider", element));
				ams.setProvider(getTag("Provider", element));

				System.out.print(" | Provider_ID:" + getTag("Provider_ID", element));
				ams.setProvider_Id(getTag("Provider_ID", element));

				// System.out.println(" | Verb:"+getTagValue("Verb", element));
				ams.setVerb("");

				System.out.print(" | Version_Major:" + getTag("Version_Major", element));
				ams.setVersion_Major(Integer.parseInt(getTag("Version_Major", element)));

				System.out.println(" | Version_Minor:" + getTag("Version_Minor", element));
				ams.setVersion_Minor(Integer.parseInt(getTag("Version_Minor", element)));
				
				return ams;
			}
		}, list, ams);
				
	}
	
	public void workWithNodeStrategy(NodeStrategy ns, NodeList list, Xml xml) 
			throws SQLException, ClassNotFoundException {
			for (int i = 0; i < list.getLength(); i++) {
				Node node = list.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;
					dao.add(ns.getTagValue(element, xml));
				}
			}
	}
	
	private String getTag(String tag, Element element) {
		String list = element.getAttributeNode(tag).getNodeValue();
		return list;
	}
}
