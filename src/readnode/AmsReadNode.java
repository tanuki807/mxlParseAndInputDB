package readnode;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import dao.AmsDao;
import dao.Duplicated_AmsDao;
import domain.Ams;
import domain.App;
import domain.Duplicated_Ams;
import factory.DaoFactory;


public class AmsReadNode implements ReadNode {
	private AmsTag amsTag;
	private App app;
	private AmsDao amsDao;
	private Duplicated_Ams dupAms;
	private Duplicated_AmsDao dupAmsDao;
	private Ams ams;
	private List<String> asset_IdList;
	private List<String> asset_ClassList;
	private List<String> descriptionList;
	
	
	
	//App_Data app;
	
	public AmsReadNode() {
		amsTag = new AmsTag();
		amsDao = new DaoFactory().amsDao();
		dupAms = new Duplicated_Ams();
		dupAmsDao = new DaoFactory().dupAmsDao();
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
				
				dupAms.setAsset_Name(getTagValue("Asset_Name", element));
				
				amsTag.setCreation_Date(getTagValue("Creation_Date", element));
				System.out.print(" | Creation_Date:" + amsTag.getCreation_Date());
				
				dupAms.setCreation_Date(getTagValue("Creation_Date", element));
			
				amsTag.setProduct(getTagValue("Product", element));
				System.out.print(" | Product:" + amsTag.getProduct());
				
				dupAms.setProduct(getTagValue("Product", element));
				
				amsTag.setProvider(getTagValue("Provider", element));
				System.out.print(" | Provider:" + amsTag.getProvider());
				
				dupAms.setProvider(getTagValue("Provider", element));
				
				amsTag.setProvider_ID(getTagValue("Provider_ID", element));
				System.out.print(" | Provider_ID:" + amsTag.getProvider_ID());
				
				dupAms.setProvider_Id(getTagValue("Provider_ID", element));
				
				amsTag.setVersion_Major(Integer.parseInt(getTagValue("Version_Major", element)));
				System.out.print(" | Version_Major:" + amsTag.getVersion_Major());
				
				dupAms.setVersion_Major(Integer.parseInt(getTagValue("Version_Major", element)));
				
				amsTag.setVersion_Minor(Integer.parseInt(getTagValue("Version_Minor", element)));
				System.out.println(" | Version_Minor:" + amsTag.getVersion_Minor());
				
				dupAms.setVersion_Minor(Integer.parseInt(getTagValue("Version_Minor", element)));
				
				if(element.getAttributeNode("Verb")!=null){
					amsTag.setVerb(getTagValue("Verb", element));
					System.out.println(" | Verb:" + amsTag.getVerb());
					
					dupAms.setVerb(getTagValue("Verb", element));
				}
				amsTag.setTitle(app.getTitle());
				dupAms.setTitle(app.getTitle());
				ams.setTitle(app.getTitle());
			}
		}
		ams.setAsset_Id(asset_IdList);
		ams.setAsset_Class(asset_ClassList);
		ams.setDescription(descriptionList);
		duplicated_AmsAdd(dupAms);
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
	
	private void duplicated_AmsAdd(Duplicated_Ams dupAms) {
		dupAmsDao.add(dupAms);
	}
	
	@Override
	public  String getTagValue(String tag, Element element) {
		String list = element.getAttributeNode(tag).getTextContent();
		return list;
	}
}
