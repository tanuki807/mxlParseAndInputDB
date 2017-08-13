package readnode;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import dao.AmsDao;
import domain.Ams;
import factory.DaoFactory;


public class AmsReadNode implements ReadNode {
	Ams ams;
	App_Data app;
	AmsDao amsDao;
	
	public AmsReadNode() {
		ams = new Ams();
		amsDao = new DaoFactory().amsDao();
	}
	
	public void setApp(App_Data app) {
		this.app = app;
	}
	
	@Override
	public Ams readNode(NodeList list) {
		
		for (int i = 0; i < list.getLength(); i++) {
			Node node = list.item(i);
			
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;
				
				ams.setAsset_Id(getTagValue("Asset_ID", element));
				System.out.print(" | Asset_ID:" + ams.getAsset_Id());
				
				ams.setAsset_Class(getTagValue("Asset_Class", element));
				System.out.println("Asset_Class:" + ams.getAsset_Class());
					
				ams.setDescription(getTagValue("Description", element));
				System.out.print(" | Description:" + ams.getDescription());
				
				ams.setAsset_Name(getTagValue("Asset_Name", element));
				System.out.print(" | Asset_Name:" + ams.getAsset_Name());
				
				ams.setCreation_Date(getTagValue("Creation_Date", element));
				System.out.print(" | Creation_Date:" + ams.getCreation_Date());
			
				ams.setProduct(getTagValue("Product", element));
				System.out.print(" | Product:" + ams.getProduct());
				
				ams.setProvider(getTagValue("Provider", element));
				System.out.print(" | Provider:" + ams.getProvider());
				
				ams.setProvider_ID(getTagValue("Provider_ID", element));
				System.out.print(" | Provider_ID:" + ams.getProvider_ID());
				
				ams.setVersion_Major(Integer.parseInt(getTagValue("Version_Major", element)));
				System.out.print(" | Version_Major:" + ams.getVersion_Major());
				
				ams.setVersion_Minor(Integer.parseInt(getTagValue("Version_Minor", element)));
				System.out.println(" | Version_Minor:" + ams.getVersion_Minor());
				
				if(element.getAttributeNode("Verb")!=null){
					ams.setVerb(getTagValue("Verb", element));
					System.out.println(" | Verb:" + ams.getVerb());
				}
				
				ams.setTitle(app.getTitle());
			}
			try {
				amsDao.add(ams);
			} catch(Exception e) {
				e.printStackTrace();
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
