package xmlparse;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import dao.ActorsDao;
import dao.AppDao;
import dao.ContentDao;
import domain.App;
import domain.Content;
import factory.DaoFactory;
import readnode.AmsTag;
import readnode.AmsReadNode;
import readnode.AppReadNode;
import readnode.App_DataTag;
import readnode.ContentTag;
import readnode.ContentReadNode;
import readnode.ReadNode;

public class XmlParse {
	
	final static String filePath = "C:\\Users\\pandora\\Desktop\\xml";
	//final static String filePath = "C:\\java_workspace\\xmlParse\\src\\xml";
	static DocumentBuilderFactory domFactoty;
	static DocumentBuilder domBuilder;
	static Document doc;
	static File file;
	static File[] files;
	private AmsTag amsTag;
	//private App_Data app_Data;
	private App app;
	private ContentTag conTag;
	int count;
	
	public void addFile() throws ParserConfigurationException {
		
		domFactoty = DocumentBuilderFactory.newInstance();
		domBuilder = domFactoty.newDocumentBuilder();
		
		try {
			file = new File(filePath);
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
				
				AmsReadNode readAms = new AmsReadNode();
				AppReadNode readApp = new AppReadNode();
				ContentReadNode readContent = new ContentReadNode();
				readApp.setContentReadNode(readContent);
				
				inputWithPrintNode("App_Data" ,readApp);
				inputWithPrintNode("AMS", readAms);
				inputWithPrintNode("Content", readContent);
				
			}
		} catch(IOException e) {
			System.out.println("IOException!!"+e.getMessage());
			e.printStackTrace();
			System.exit(0);
		} catch(SAXException sae) {
			System.out.println("SAXException!!"+sae.getMessage());
			sae.printStackTrace();
			System.exit(0);
		} 
	}
		
	private  void inputWithPrintNode(String tagName, ReadNode node) {
		System.out.println();
		NodeList list = doc.getElementsByTagName(tagName);
		System.out.println(tagName+"노드 수 :"+list.getLength());
	
	  switch (tagName) {
	  
		 case "AMS":
			//table = this.tableContext.packageNode(list, table);
			//this.nodeContext.amsNode(list, ams);
			 
			if(node instanceof AmsReadNode) {
				AmsReadNode amsRead = (AmsReadNode)node;
				//amsRead.setApp(app_Data);
				amsRead.setApp(app);
				amsTag = amsRead.readNode(list);
			}
			break;

		case "App_Data":
			if(node instanceof AppReadNode) {
				AppReadNode appRead = (AppReadNode) node;
				//app_Data = appRead.readNode(list);
				app = appRead.readNode(list);
			}
			break;

		case "Content":
			if(node instanceof ContentReadNode) {
				ContentReadNode contentRead = (ContentReadNode)node;
				conTag = contentRead.readNode(list);
			}
		}
	}
}
