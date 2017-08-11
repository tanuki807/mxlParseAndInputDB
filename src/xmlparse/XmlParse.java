package xmlparse;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.sql.SQLException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import dao.AmsDao;
import dao.PackageDao;


import domain.Package_Table;
import factory.DaoFactory;
import readnode.Ams;
import readnode.AmsReadNode;
import readnode.AppReadNode;
import readnode.App_Data;
import readnode.Content;
import readnode.ContentReadNode;
import readnode.ReadNode;

public class XmlParse {
	final static String filePath = "c:\\java_workspace\\xmlParse\\src\\xml";
	static DocumentBuilderFactory domFactoty;
	static DocumentBuilder domBuilder;
	static Document doc;
	static File file;
	static File[] files;
	private Package_Table table;
	Ams ams;
	App_Data app;
	Content con;
	int count;
	
	
	//private TableContext tableContext;
	
	public XmlParse() {
		//this.tableContext = new TableContext();
	}
	
	public void addFile() throws IOException, SAXException, ParserConfigurationException, 
	SQLException, ClassNotFoundException {
		this.table = new Package_Table();
		
		
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
			
			AmsReadNode readAms = new AmsReadNode();
			AppReadNode readApp = new AppReadNode();
			ContentReadNode readContent = new ContentReadNode();
			
			inputWithPrintNode("App_Data" ,readApp);
			inputWithPrintNode("AMS", readAms);
			inputWithPrintNode("Content", readContent);
			
		}
//		PackageDao packageDao = new DaoFactory().packageDao();
//		packageDao.add(table);
	}
	
	
	private  void inputWithPrintNode(String tagName, ReadNode node) throws SQLException, ClassNotFoundException {
		System.out.println();
		NodeList list = doc.getElementsByTagName(tagName);
		System.out.println(tagName+"노드 수 :"+list.getLength());
	
		
	  switch (tagName) {
	  
		 case "AMS":
			//table = this.tableContext.packageNode(list, table);
			//this.nodeContext.amsNode(list, ams);
			if(node instanceof AmsReadNode) {
				AmsReadNode amsRead = (AmsReadNode)node;
				ams = amsRead.readNode(list);
			}
			break;

		case "App_Data":
			if(node instanceof AppReadNode) {
				AppReadNode appRead = (AppReadNode) node;
				app = appRead.readNode(list);
			}
			break;

		case "Content":
			if(node instanceof ContentReadNode) {
				ContentReadNode contentRead = (ContentReadNode)node;
				con = contentRead.readNode(list);
			}
		}
	}
	
}
