//package dbconnection;
//
//import java.sql.SQLException;
//
//import org.w3c.dom.Element;
//import org.w3c.dom.Node;
//import org.w3c.dom.NodeList;
//
//import dao.AmsDao;
//import dao.PackageDao;
//import domain.Ams;
//import domain.Package_Table;
//import domain.Xml;
//import factory.DaoFactory;
//
//public class TableContext {
//	
//	//AmsDao amsDao = new DaoFactory().amsDao();
//	PackageDao packageDao = new DaoFactory().packageDao();
//	
//	
//	public Xml packageNode(NodeList list, Package_Table table) throws ClassNotFoundException, SQLException {
//		workWithNodeStrategy(new TableStrategy() {
//			public Xml getTagValue(Element element, Xml xml) {
//				System.out.print(" | Asset_ID:" + getTag("Asset_ID", element));
//				table.setAsset_Id(getTag("Asset_ID", element));
//				System.out.println("Package_Table Asset_Id:"+table.getAsset_Id());
//				
//				System.out.print(" | Asset_Name:" + getTag("Asset_Name", element));
//				table.setAsset_Name(getTag("Asset_Name", element));
//				
//				System.out.println("Asset_Class:"+getTag("Asset_Class", element));
//				table.setAsset_Class(getTag("Asset_Class", element));
//
//				System.out.print(" | Creation_Date:" + getTag("Creation_Date", element));
//				table.setCreation_Date(getTag("Creation_Date", element));
//
//				System.out.print(" | Description:" + getTag("Description", element));
//				table.setDescription(getTag("Description", element));
//
//				System.out.print(" | Product:" + getTag("Product", element));
//				table.setProduct(getTag("Product", element));
//
//				System.out.print(" | Provider:" + getTag("Provider", element));
//				table.setProvider(getTag("Provider", element));
//
//				System.out.print(" | Provider_ID:" + getTag("Provider_ID", element));
//				table.setProvider_Id(getTag("Provider_ID", element));
//				
//				if(element.getAttributeNode("Verb")!=null){
//					System.out.println(" | Verb:"+getTag("Verb", element));
//					table.setVerb(getTag("Verb", element));
//				}
//				
//				System.out.print(" | Version_Major:" + getTag("Version_Major", element));
//				table.setVersion_Major(Integer.parseInt(getTag("Version_Major", element)));
//
//				System.out.println(" | Version_Minor:" + getTag("Version_Minor", element));
//				table.setVersion_Minor(Integer.parseInt(getTag("Version_Minor", element)));
//				
//				return table;
//			}
//		}, list, table);
//		return table;		
//	}
//	
//	public Xml workWithNodeStrategy(TableStrategy ns, NodeList list, Xml xml) 
//			throws SQLException, ClassNotFoundException {
//			for (int i = 0; i < list.getLength(); i++) {
//				Node node = list.item(i);
//				if (node.getNodeType() == Node.ELEMENT_NODE) {
//					Element element = (Element) node;
//					return ns.getTagValue(element, xml);
//				}
//			}
//			return null;
//	}
//	
//	private String getTag(String tag, Element element) {
//		String list = element.getAttributeNode(tag).getNodeValue();
//		return list;
//	}
//}
