package readnode;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import dao.AmsDao;
import domain.Ams;
import domain.App;
import factory.DaoFactory;


public class AmsReadNode implements ReadNode {
	private AmsTag amsTag;
	private App app;
	private AmsDao amsDao;
	private Ams ams;
	private List<String> asset_IdList;
	private List<String> asset_ClassList;
	private List<String> descriptionList;
	
	
	
	//App_Data app;
	
	public AmsReadNode() {
		amsTag = new AmsTag();
		amsDao = new DaoFactory().amsDao();
		ams = new Ams();
		asset_IdList = new ArrayList();
		asset_ClassList = new ArrayList();
		descriptionList = new ArrayList();
	}
	
	public void setApp(App app) {
		this.app = app;
	}
	
	@Override
	public AmsTag readNode(NodeList list) {
		
		for (int i = 0; i < list.getLength(); i++) {
			Node node = list.item(i);
			
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;
				
				amsTag.setAsset_Id(getTagValue("Asset_ID", element));
				System.out.print(" | Asset_ID:" + amsTag.getAsset_Id());
				
				asset_IdList.add(getTagValue("Asset_ID", element));
				
				amsTag.setAsset_Class(getTagValue("Asset_Class", element));
				System.out.println("Asset_Class:" + amsTag.getAsset_Class());
				
				asset_ClassList.add(getTagValue("Asset_Class", element));
					
				amsTag.setDescription(getTagValue("Description", element));
				System.out.print(" | Description:" + amsTag.getDescription());
				
				descriptionList.add(getTagValue("Description", element));
				
				amsTag.setAsset_Name(getTagValue("Asset_Name", element));
				System.out.print(" | Asset_Name:" + amsTag.getAsset_Name());
				
				amsTag.setCreation_Date(getTagValue("Creation_Date", element));
				System.out.print(" | Creation_Date:" + amsTag.getCreation_Date());
				
				amsTag.setProduct(getTagValue("Product", element));
				System.out.print(" | Product:" + amsTag.getProduct());
				
				amsTag.setProvider(getTagValue("Provider", element));
				System.out.print(" | Provider:" + amsTag.getProvider());
				
				amsTag.setProvider_ID(getTagValue("Provider_ID", element));
				System.out.print(" | Provider_ID:" + amsTag.getProvider_ID());
				
				amsTag.setVersion_Major(Integer.parseInt(getTagValue("Version_Major", element)));
				System.out.print(" | Version_Major:" + amsTag.getVersion_Major());
				
				amsTag.setVersion_Minor(Integer.parseInt(getTagValue("Version_Minor", element)));
				System.out.println(" | Version_Minor:" + amsTag.getVersion_Minor());
				
				if(element.getAttributeNode("Verb")!=null){
					amsTag.setVerb(getTagValue("Verb", element));
					System.out.println(" | Verb:" + amsTag.getVerb());
				}
				amsTag.setTitle(app.getTitle());
				ams.setTitle(app.getTitle());
			}
		}
		ams.setAsset_Id(asset_IdList);
		ams.setAsset_Class(asset_ClassList);
		ams.setDescription(descriptionList);
		amsAdd(ams);
		return amsTag;
	}
	
	private void amsAdd(Ams ams) {
		Iterator<String> idListIt = ams.getAsset_Id().iterator();
		Iterator<String> classIt = ams.getAsset_Class().iterator();
		Iterator<String> descriptionIt = ams.getDescription().iterator();
		while(idListIt.hasNext()) {
			amsDao.add(ams.getTitle(), idListIt.next(), classIt.next(), descriptionIt.next());
		}
	}
	
	
	@Override
	public  String getTagValue(String tag, Element element) {
		String list = element.getAttributeNode(tag).getTextContent();
		return list;
	}
}
