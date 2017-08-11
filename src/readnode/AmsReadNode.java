package readnode;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class AmsReadNode implements ReadNode {
	Ams ams;
	
	public AmsReadNode() {
		ams = new Ams();
	}
	
	@Override
	public Ams readNode(NodeList list) {
		
		for (int i = 0; i < list.getLength(); i++) {
			Node node = list.item(i);
			
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;
				
				
				ams.setAsset_Class(getTagValue("Asset_Class", element));
				System.out.println("Asset_Class:" + ams.getAsset_Class());
				//table.setAsset_Class(getTagValue("Asset_Class", element));
				
				ams.setAsset_Id(getTagValue("Asset_ID", element));
				System.out.print(" | Asset_ID:" + ams.getAsset_Id());
				//table.setAsset_Id(getTagValue("Asset_ID", element));
				
				ams.setAsset_Name(getTagValue("Asset_Name", element));
				System.out.print(" | Asset_Name:" + ams.getAsset_Name());
				//table.setAsset_Name(getTagValue("Asset_Name", element));
				
				ams.setCreation_Date(getTagValue("Creation_Date", element));
				System.out.print(" | Creation_Date:" + ams.getCreation_Date());
				//table.setCreation_Date(getTagValue("Creation_Date", element));

				ams.setDescription(getTagValue("Description", element));
				System.out.print(" | Description:" + ams.getDescription());
				//table.setDescription(getTagValue("Description", element));

				ams.setProduct(getTagValue("Product", element));
				System.out.print(" | Product:" + ams.getProduct());
				//table.setProduct(getTagValue("Product", element));

				ams.setProvider(getTagValue("Provider", element));
				System.out.print(" | Provider:" + ams.getProvider());
				//table.setProvider(getTagValue("Provider", element));

				
				ams.setProvider_Id(getTagValue("Provider_ID", element));
				System.out.print(" | Provider_ID:" + ams.getProvider_Id());
				//table.setProvider_Id(getTagValue("Provider_ID", element));
				
				if(element.getAttributeNode("Verb")!=null){
					ams.setVerb(getTagValue("Verb", element));
					System.out.println(" | Verb:" + ams.getVerb());
					//table.setVerb(getTagValue("Verb", element));
				}
				
				ams.setVersion_Major(Integer.parseInt(getTagValue("Version_Major", element)));
				System.out.print(" | Version_Major:" + ams.getVersion_Major());
				//table.setVersion_Major(Integer.parseInt(getTagValue("Version_Major", element)));

				ams.setVersion_Minor(Integer.parseInt(getTagValue("Version_Minor", element)));
				System.out.println(" | Version_Minor:" + ams.getVersion_Minor());
				//table.setVersion_Minor(Integer.parseInt(getTagValue("Version_Minor", element)));
			}
		}
		return ams;
	}
	
	@Override
	public  String getTagValue(String tag, Element element) {
		String list = element.getAttributeNode(tag).getTextContent();
		return list;
	}
}
