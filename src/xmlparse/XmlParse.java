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
import dao.Publication_TypeDao;
import domain.Ams;
import domain.App;
import factory.DaoFactory;
import readnode.AmsReadNode;
import readnode.AppReadNode;
import readnode.App_Data;
import readnode.Content;
import readnode.ContentReadNode;
import readnode.ReadNode;

public class XmlParse {
	
	final static String filePath = "C:\\Users\\pandora\\Desktop\\xml";
	static DocumentBuilderFactory domFactoty;
	static DocumentBuilder domBuilder;
	static Document doc;
	static File file;
	static File[] files;
	private Ams ams;
	private App_Data app_Data;
	private App app;
	private Content con;
	int count;
	
	
	public void addFile() throws IOException, SAXException, ParserConfigurationException, 
	SQLException, ClassNotFoundException {
		
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
			
			
			AppDao appDao = new DaoFactory().appDao();
			appDao.add(app_Data);

			ActorsDao actorDao = new DaoFactory().actorDao();
			Publication_TypeDao pubDao = new DaoFactory().pubDao();
			
			String title = app_Data.getTitle();
			List actorsList = app_Data.getActors();
			Iterator<String> it = actorsList.iterator();
			while(it.hasNext()) {
				actorDao.add(it.next(), title);
			}
			
			List pubList = app_Data.getPublication_Right();
			List typeList = app_Data.getType();
			Iterator<String> typeIterator = typeList.iterator();
			it = pubList.iterator();
			
			while(it.hasNext()) {
				pubDao.add(it.next(), typeIterator.next(), title);
			}
			
			ContentDao contentDao = new DaoFactory().contentDao();
			List advisoriesList = app_Data.getAdvisories();
			List content_FileSizeList = app_Data.getContent_FileSize();
			List content_CheckSumList = app_Data.getContent_CheckSum();
			List valueList = con.getVlaue();
			Iterator<String> advisorIterator = advisoriesList.iterator();
			Iterator<String> fileSizeIterator = content_FileSizeList.iterator();
			Iterator<String> checkSumListIterator = content_CheckSumList.iterator();
			Iterator<String> valueIterator = valueList.iterator();
			while(advisorIterator.hasNext()) {
				contentDao.add(
						title, advisorIterator.next(), fileSizeIterator.next(), 
						checkSumListIterator.next(), valueIterator.next());
			}
		}
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
				amsRead.setApp(app_Data);
				ams = amsRead.readNode(list);
			}
			break;

		case "App_Data":
			if(node instanceof AppReadNode) {
				AppReadNode appRead = (AppReadNode) node;
				app_Data = appRead.readNode(list);
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
