package readnode;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import domain.Duplicated_Ams;

public class Duplicated_AmsReadNode implements ReadNode {
	Duplicated_Ams dupAms;
	
	public Duplicated_AmsReadNode() {
		dupAms = new Duplicated_Ams();
	}

	@Override
	public Xml readNode(NodeList list) {
		Node node = list.item(0);
		
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			Element element = (Element) node;
			
			dupAms.setAsset_Name(getTagValue("Asset_Name", element));
			System.out.print(" | Asset_Name:" + dupAms.getAsset_Name());
			
			dupAms.setCreation_Date(getTagValue("Creation_Date", element));
			System.out.print(" | Creation_Date:" + dupAms.getCreation_Date());
		
			dupAms.setProduct(getTagValue("Product", element));
			System.out.print(" | Product:" + dupAms.getProduct());
			
			dupAms.setProvider(getTagValue("Provider", element));
			System.out.print(" | Provider:" + dupAms.getProvider());
			
			dupAms.setProvider_Id(getTagValue("Provider_ID", element));
			System.out.print(" | Provider_ID:" + dupAms.getProvider_Id());
			
			dupAms.setVersion_Major(Integer.parseInt(getTagValue("Version_Major", element)));
			System.out.print(" | Version_Major:" + dupAms.getVersion_Major());
			
			dupAms.setVersion_Minor(Integer.parseInt(getTagValue("Version_Minor", element)));
			System.out.println(" | Version_Minor:" + dupAms.getVersion_Minor());
			
			if(element.getAttributeNode("Verb")!=null){
				dupAms.setVerb(getTagValue("Verb", element));
				System.out.println(" | Verb:" + dupAms.getVerb());
			}
		}
			return dupAms;
	}

	@Override
	public String getTagValue(String tag, Element element) {
		String list = element.getAttributeNode(tag).getTextContent();
		return list;
	}
}
