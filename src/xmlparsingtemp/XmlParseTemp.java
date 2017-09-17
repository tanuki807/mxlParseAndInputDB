package xmlparsingtemp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.GroupLayout.Alignment;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import dao.JdbcDao;
import daoimpl.ActorJdbcDaoImpl;
import daoimpl.AmsJdbcDaoImpl;
import daoimpl.ContentJdbcDaoImpl;
import daoimpl.M_PackageJdbcDaoImpl;
import daoimpl.TypeJdbcDaoImpl;
import factory.DaoFactory;
import readnode.AmsTag;
import readnode.App_DataTag;
import readnode.ContentTag;
import table.Actor;
import table.Ams;
import table.App_Data;
import table.Content;
import table.M_Package;
import table.Type;

public class XmlParseTemp {
	
	final static String filePath = "C:\\Users\\pandora\\Desktop\\xml";
	//final static String filePath = "C:\\java_workspace\\xmlParse\\src\\xml";
	static DocumentBuilderFactory domFactoty;
	static DocumentBuilder domBuilder;
	static Document doc;
	static File file;
	static File[] files;
	private AmsTag amsTag;
	private List<AmsTag> amsList;
	private App_DataTag appTag;
	private List<String> publication_RightValueList;
	private List<String> typeValueList;
	private List<String> advisorieValueList;
	private List<String> actorValueList;
	private List<String> content_FileSizeValueList;
	private List<String> content_CheckSumValueList;
	private List<String> content_ValueList;
	private ContentTag conTag;
	M_Package m_Package;
	Ams ams;
	Actor actor;
	Type type;
	Content content;
	App_Data appData;
	
	private M_PackageJdbcDaoImpl m_PackageDao;
	private AmsJdbcDaoImpl amsDao;
	private ActorJdbcDaoImpl actorDao;
	private TypeJdbcDaoImpl typeDao;
	private ContentJdbcDaoImpl contentDao;
	private JdbcDao appDao;
	
	String printFileName;
	int count;
	
	public XmlParseTemp() {
		m_PackageDao = (M_PackageJdbcDaoImpl) new DaoFactory().m_PackageDao();
		amsDao = (AmsJdbcDaoImpl) new DaoFactory().amsDao();
		actorDao = (ActorJdbcDaoImpl) new DaoFactory().actorDao();
		typeDao = (TypeJdbcDaoImpl) new DaoFactory().typeDao();
		contentDao = (ContentJdbcDaoImpl) new DaoFactory().contentDao();
		appDao = new DaoFactory().appDao();
	}
	
	public void readAms(String tagName) {
		NodeList nList = doc.getElementsByTagName(tagName);
		
		for (int idx = 0; idx < nList.getLength(); idx++) {
			
			Node node = nList.item(idx);

			if (node.getNodeType() == Node.ELEMENT_NODE) {
				amsTag = new AmsTag();

				Element element = (Element) node;
				amsTag.setAsset_Class(getTagValue("Asset_Class", element));
				amsTag.setAsset_Id(getTagValue("Asset_ID", element));
				amsTag.setAsset_Name(getTagValue("Asset_Name", element));
				amsTag.setCreation_Date(getTagValue("Creation_Date", element));
				amsTag.setDescription(getTagValue("Description", element));
				amsTag.setProduct(getTagValue("Product", element));
				amsTag.setProvider(getTagValue("Provider", element));
				amsTag.setProvider_ID(getTagValue("Provider_ID", element));
				amsTag.setVersion_Major(Integer.parseInt(getTagValue("Version_Major", element)));
				amsTag.setVersion_Minor(Integer.parseInt(getTagValue("Version_Minor", element)));
				if (element.getAttributeNode("Verb") != null) {
					amsTag.setVerb(getTagValue("Verb", element));
				}
			}
			amsList.add(amsTag);
		}
	}
	
	public void readAppData(String tagName) {
		NodeList nList = doc.getElementsByTagName(tagName);
		
		for(int idx = 0; idx < nList.getLength(); idx++) {
			Node node = nList.item(idx);
			
			if(node.getNodeType() == Node.ELEMENT_NODE) {
				
				Element element = (Element)node;
				String name = getTagValue("Name", element);
				String value = getTagValue("Value", element);
				
				switch(name) {
				
				case "Provider_Content_Tier" :
					appTag.setProvider_Content_Tier(value);
					break;
				case "Metadata_Spec_Version" :
					appTag.setMetadata_Spec_Version(value);
					break;
				case "Publication_Right" :
					publication_RightValueList.add(value); 
					break;
				case "Type" :
					typeValueList.add(value); 
					break;
				case "Title" :
					appTag.setTitle(value);
					break;
				case "Title_Brief" :
					appTag.setTitle_Brief(value);
					break;
				case "Category" :
					appTag.setCategory(value);
					break;
				case "Rating" :
					appTag.setRating(value);
					break;
				case "Summary_Short" :
					appTag.setSummary_Short(value);
					break;
				case "Run_Time" :
					appTag.setRun_Time(value);
					break;
				case "Display_Run_Time" :
					appTag.setDisplay_Run_Time(value);
					break;
				case "Provider_QA_Contact" :
					appTag.setProvider_QA_Contact(value);
					break;
				case "Billing_ID" :
					appTag.setBilling_ID(value);
					break;
				case "Licensing_Window_Start" :
					appTag.setLicensing_Window_Start(value);
					break;
				case "Licensing_Window_End" :
					appTag.setLicensing_Window_End(value);
					break;
				case "Preview_Period" :
					appTag.setPreview_Period(value);
					break;
				case "Title_Sort_Name" :
					appTag.setTitle_Sort_Name(value);
					break;
				case "Episode_Name" :
					appTag.setEpisode_Name(value);
					break;
				case "Episode_id" :
					appTag.setEpisode_id(value);
					break;
				case "Summary_Medium" :
					appTag.setSummary_Medium(value);
					break;
				case "Summary_Long" :
					appTag.setSummary_Long(value);
					break;
				case "Actors" :
					actorValueList.add(value); 
					break;
				case "Actors_Display" :
					appTag.setActors_Display(value);
					break;
				case "Chapter" :
					appTag.setChapter(value);
					break;
				case "Studio_Name" :
					appTag.setStudio_Name(value);
					break;
				case "Studio" :
					appTag.setStudio(value);
					break;
				case "Advisories" :
					advisorieValueList.add(value); 
					break;
				case "Closed_Captioning" :
					appTag.setClosed_Captioning(value);
					break;
				case "Season_Premiere" :
					appTag.setSeason_Premiere(value);
					break;
				case "Season_Finale" :
					appTag.setSeason_Finale(value);
					break;
				case "Display_As_New" :
					appTag.setDisplay_As_New(value);
					break;
				case "Display_As_Last_Chance" :
					appTag.setDisplay_As_Last_Chance(value);
					break;
				case "Subscriber_View_Limit" :
					appTag.setSubscriber_View_Limit(value);
					break;
				case "Year" :
					appTag.setYear(value);
					break;
				case "Country_of_Origin" :
					appTag.setCountry_of_Origin(value);
					break;
				case "Genre" :
					appTag.setGenre(value);
					break;
				case "Maximum_Viewing_Length" :
					appTag.setMaximum_Viewing_Length(value);
					break;
				case "Suggested_Price" :
					appTag.setSuggested_Price(value);
					break;
				case "Propagation_Priority" :
					appTag.setPropagation_Priority(value);
					break;
				case "LongTail_YN" :
					appTag.setLongTail_YN(value);
					break;
				case "Studio_Royalty_Percent" :
					appTag.setStudio_Royalty_Percent(value);
					break;
				case "Studio_Royalty_Minimum" :
					appTag.setStudio_Royalty_Minimum(value);
					break;
				case "Studio_Royalty_Flat_Rate" :
					appTag.setStudio_Royalty_Flat_Rate(value);
					break;
				case "Director" :
					appTag.setDirector(value);
					break;
				case "Writer_Display" :
					appTag.setWriter_Display(value);
					break;
				case "Producer" :
					appTag.setProducer(value);
					break;
				case "Home_Video_Window" :
					appTag.setHome_Video_Window(value);
					break;
				case "Contract_Name" :
					appTag.setContract_Name(value);
					break;
				case "Distributor_Royalty_Percent" :
					appTag.setDistributor_Royalty_Percent(value);
					break;
				case "Distributor_Royalty_Minimum" :
					appTag.setDistributor_Royalty_Minimum(value);
					break;
				case "Distributor_Royalty_Flat_Rate" :
					appTag.setDistributor_Royalty_Flat_Rate(value);
					break;
				case "Distributor_Name" :
					appTag.setDistributor_Name(value);
					break;
				case "Encryption" :
					appTag.setEncryption(value);
					break;
				case "Audio_Type" :
					appTag.setAudio_Type(value);
					break;
				case "Screen_Format" :
					appTag.setScreen_Format(value);
					break;
				case "Languages" :
					appTag.setLanguages(value);
					break;
				case "Subtitle_Languages" :
					appTag.setSubtitle_Languages(value);
					break;
				case "Dubbed_Languages" :
					appTag.setDubbed_Languages(value);
					break;
				case "Copy_Protection" :
					appTag.setCopy_Protection(value);
					break;
				case "Copy_Protection_Verbose" :
					appTag.setCopy_Protection_Verbose(value);
					break;
				case "Analog_Protection_System" :
					appTag.setAnalog_Protection_System(value);
					break;
				case "Encryption_Mode_Indicator" :
					appTag.setEncryption_Mode_Indicator(value);
					break;
				case "Constrained_Image_Trigger" :
					appTag.setConstrained_Image_Trigger(value);
					break;
				case "CGMS_A" :
					appTag.setCGMS_A(value);
					break;
				case "HDContent" :
					appTag.setHDContent(value);
					break;
				case "Content_FileSize" :
					content_FileSizeValueList.add(value); 
					break;
				case "Content_CheckSum" :
					content_CheckSumValueList.add(value); 
					break;
				case "Image_Aspect_Ratio" :
					appTag.setImage_Aspect_Ratio(value);
					break;
				}
			}
		}
	}
	
	public void readContent(String tagName) {
		NodeList nList = doc.getElementsByTagName(tagName);
		
		for(int idx = 0; idx < nList.getLength(); idx++) {
			Node node = nList.item(idx);
			
			if(node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element)node;
				content_ValueList.add(getTagValue("Value", element));
			}
		}
	}
	
	public void tagReadWithPrint() throws ParserConfigurationException {

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
				amsList = new ArrayList<AmsTag>();
				appTag = new App_DataTag();
				publication_RightValueList = new ArrayList<String>();
				typeValueList = new ArrayList<String>();
				advisorieValueList = new ArrayList<String>();
				actorValueList = new ArrayList<String>();
				content_FileSizeValueList = new ArrayList<String>();
				content_CheckSumValueList = new ArrayList<String>();
				content_ValueList = new ArrayList<String>();
				conTag = new ContentTag();
				
				String fileName = files[i].getAbsolutePath();
				printFileName = "no."+ ++count +" "+fileName;
				System.out.println();
				System.out.println(printFileName);
				doc = domBuilder.parse(fileName);
				doc.normalize();
				
				readAms("AMS");
				readAppData("App_Data");
				readContent("Content");
			
				appTag.setActors(actorValueList);
				appTag.setType(typeValueList);
				appTag.setPublication_Right(publication_RightValueList);
				appTag.setContent_FileSize(content_FileSizeValueList);
				appTag.setContent_CheckSum(content_CheckSumValueList);
				appTag.setAdvisories(advisorieValueList);
				conTag.setVlaue(content_ValueList);
				
				insertFile();
				//정상적으로 인서트 작업이 완료되면 파일 번호와 이름을 남긴다.
				try(BufferedWriter out = new BufferedWriter(
						 new FileWriter("C:\\Users\\pandora\\Desktop\\success\\xmlSuccess.txt", true))) {
					
					out.write(printFileName);
					out.newLine();
					out.write("SUCCESS!!");
					out.newLine();
				} catch(IOException ioe) {
					ioe.printStackTrace();
				}
			}
		} catch(Exception e) {
			System.out.println("Exception!!"+e.getMessage());
			String exception = e.getMessage();
			e.printStackTrace();
			//익셉션이 발생하면 등록 실패한 파일 번호와 이름을 파일로 생성.
			try(BufferedWriter out = new BufferedWriter(
										 new FileWriter("C:\\Users\\pandora\\Desktop\\exception\\xmlException.txt", true))) {
				out.write(printFileName);
				out.newLine();
				out.write(exception);
				out.newLine();
			} catch(IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}
	
	public void insertFile() throws SQLException, ClassNotFoundException {
//		m_PackageDao.deleteAll();
//		amsDao.deleteAll();
//		actorDao.deleteAll();
//		typeDao.deleteAll();
//		contentDao.deleteAll();
//		appDao.deleteAll();
		
		//m_package
		AmsTag ams1 = amsList.get(0);
		m_Package = new M_Package();
		m_Package.setAsset_Name(ams1.getAsset_Name());
		m_Package.setCreation_Date(ams1.getCreation_Date());
		m_Package.setProduct(ams1.getProduct());
		m_Package.setProvider(ams1.getProvider());
		m_Package.setProvider_ID(ams1.getProvider_ID());
		m_Package.setVerb(ams1.getVerb());
		m_Package.setVersion_Major(ams1.getVersion_Major());
		m_Package.setVersion_Minor(ams1.getVersion_Minor());
		m_PackageDao.add(m_Package);
		
		//package_ID
		int package_ID = m_PackageDao.getFindMaxPK();
		
		
		//ams
		for(int i=0; i < amsList.size(); i++) {
			ams = new Ams();
			AmsTag tmp = amsList.get(i);
			ams.setPackage_ID(package_ID); // 마스터 테이블 패키지 아이디 필요
			ams.setAsset_Class(tmp.getAsset_Class());
			ams.setAsset_ID(tmp.getAsset_Id());
			ams.setDescription(tmp.getDescription());
			amsDao.add(ams);
		}
		
		//actor
		for(int i=0; i < appTag.getActors().size(); i++) {
			actor = new Actor();
			actor.setPackage_ID(package_ID); // 마스터 테이블 패키지 아이디 필요
			actor.setActor(appTag.getActors().get(i));
			actorDao.add(actor);
		}
		
		//type
		Iterator<String> typeIt = typeValueList.iterator();
		Iterator<String> publicationIt = publication_RightValueList.iterator();
		while(publicationIt.hasNext() || typeIt.hasNext()) {
			type = new Type();
			type.setPackage_ID(package_ID); //마스터 테이블 패키지 아이디 필요
			if(!publicationIt.hasNext()) {
				type.setPublication_Right("");
			} else {
				type.setPublication_Right(publicationIt.next());
			}
			type.setType(typeIt.next());
			typeDao.add(type);
		}
		
		//content
		for(int i=0; i < appTag.getContent_FileSize().size(); i++) {
			content = new Content();
			content.setPackage_ID(package_ID); //마스터 테이블...
			content.setContent_FileSize(appTag.getContent_FileSize().get(i));
			content.setContent_CheckSum(appTag.getContent_CheckSum().get(i));
			content.setValue(conTag.getVlaue().get(i));
			if(appTag.getAdvisories().get(i) != null) {
				content.setAdvisories(appTag.getAdvisories().get(i));
			}
			contentDao.add(content);
		}
		
		//app_data
		appData = new App_Data();
		appData.setPackage_ID(package_ID); //마스터..
		appData.setProvider_Content_Tier(appTag.getProvider_Content_Tier());
		appData.setMetadata_Spec_Version(appTag.getMetadata_Spec_Version());
		appData.setTitle(appTag.getTitle());
		appData.setTitle_Brief(appTag.getTitle_Brief());
		appData.setCategory(appTag.getCategory());
		appData.setRating(appTag.getRating());
		appData.setSummary_Short(appTag.getSummary_Short());
		appData.setRun_Time(appTag.getRun_Time());
		appData.setDisplay_Run_Time(appTag.getDisplay_Run_Time());
		appData.setProvider_QA_Contact(appTag.getProvider_QA_Contact());
		appData.setBilling_Id(appTag.getBilling_ID());
		appData.setLicensing_Window_Start(appTag.getLicensing_Window_Start());
		appData.setLicensing_Window_End(appTag.getLicensing_Window_End());
		appData.setPreview_Period(Integer.parseInt(appTag.getPreview_Period()));
		appData.setTitle_Sort_Name(appTag.getTitle_Sort_Name());
		appData.setEpisode_Name(appTag.getEpisode_Name());
		appData.setEpisode_Id(appTag.getEpisode_id());
		appData.setSummary_Medium(appTag.getSummary_Medium());
		appData.setSummary_Long(appTag.getSummary_Long());
		appData.setActors_Display(appTag.getActors_Display());
		appData.setChapter(appTag.getChapter());
		appData.setStudio_Name(appTag.getStudio_Name());
		appData.setStudio(appTag.getStudio());
		appData.setClosed_Captioning(appTag.getClosed_Captioning());
		appData.setSeason_Premiere(appTag.getSeason_Premiere());
		appData.setSeason_Finale(appTag.getSeason_Finale());
		appData.setDisplay_As_New(appTag.getDisplay_As_New());
		appData.setDisplay_As_Last_Chance(appTag.getDisplay_As_Last_Chance());
		appData.setSubscriber_View_Limit(appTag.getSubscriber_View_Limit());
		appData.setYear(appTag.getYear());
		appData.setCountry_of_Origin(appTag.getCountry_of_Origin());
		appData.setGenre(appTag.getGenre());
		appData.setMaximum_Viewing_Length(appTag.getMaximum_Viewing_Length());
		appData.setSuggested_Price(Integer.parseInt(appTag.getSuggested_Price()));
		appData.setPropagation_Priority(Integer.parseInt(appTag.getPropagation_Priority()));
		appData.setLongTail_YN(appTag.getLongTail_YN());
		appData.setStudio_Royalty_Percent(appTag.getStudio_Royalty_Percent());
		appData.setStudio_Royalty_Minimum(appTag.getStudio_Royalty_Minimum());
		appData.setStudio_Royalty_Flat_Rate(appTag.getStudio_Royalty_Flat_Rate());
		appData.setDirector(appTag.getDirector());
		appData.setWriter_Display(appTag.getWriter_Display());
		appData.setProducer(appTag.getProducer());
		appData.setHome_Video_Window(appTag.getHome_Video_Window());
		appData.setContract_Name(appTag.getContract_Name());
		appData.setDistributor_Royalty_Percent(appTag.getDistributor_Royalty_Percent());
		appData.setDistributor_Royalty_Minimum(appTag.getDistributor_Royalty_Minimum());
		appData.setDistributor_Royalty_Flat_Rate(appTag.getDistributor_Royalty_Flat_Rate());
		appData.setDistributor_Name(appTag.getDistributor_Name());
		appData.setEncryption(appTag.getEncryption());
		appData.setAudio_Type(appTag.getAudio_Type());
		appData.setScreen_Format(appTag.getScreen_Format());
		appData.setLanguages(appTag.getLanguages());
		appData.setSubtitle_Languages(appTag.getSubtitle_Languages());
		appData.setDubbed_Languages(appTag.getDubbed_Languages());
		appData.setCopy_Protection(appTag.getCopy_Protection());
		appData.setCopy_Protection_Verbose(appTag.getCopy_Protection_Verbose());
		if(appTag.getAnalog_Protection_System() != null) {
			appData.setAnalog_Protection_System(Integer.parseInt(appTag.getAnalog_Protection_System()));
		}
		if(appTag.getEncryption_Mode_Indicator() != null) {
			appData.setEncryption_Mode_Indicator(Integer.parseInt(appTag.getEncryption_Mode_Indicator()));
		}
		if(appTag.getConstrained_Image_Trigger() != null) {
			appData.setConstrained_Image_Trigger(Integer.parseInt(appTag.getConstrained_Image_Trigger()));
		}
		if(appTag.getCGMS_A() != null) {
			appData.setCGMS_A(Integer.parseInt(appTag.getCGMS_A()));
		}
		appData.setHDContent(appTag.getHDContent());
		appData.setImage_Aspect_Ratio(appTag.getImage_Aspect_Ratio());
		appDao.add(appData);
	}
	
	public  String getTagValue(String tag, Element element) {
		String list = element.getAttributeNode(tag).getTextContent();
		return list;
	}
	
	public void printExcel() {
		XSSFWorkbook workBook;
		String fileName;
	
		//ams tag
		String[] amsCol = {"package_ID", "asset_Name", "creation_Date", "product", "provider", 
						   "provider_ID", "verb", "version_Major", "version_Minor", 
						   "asset_Class", "asset_ID", "description"};
		
		//app tag
		String[] app_DataCol = {"package_ID", "provider_Content_Tier", "metadata_Spec_Version", 
				   				"title", "title_Brief", "category", "rating", 
				   				"summary_Short", "run_Time", "display_Run_Time", "provider_QA_Contact",
				   				"billing_ID", "licensing_Window_Start", "licensing_Window_End",
				   				"preview_Period", "title_Sort_Name", "episode_Name", "episode_Id",
				   				"summary_Medium", "summary_Long", "actors_Display", "chapter",
				   				"studio_Name", "studio", "closed_Captioning", "season_Premiere",
				   				"season_Finale", "display_As_New", "display_As_Last_Chance",
				   				"subscriber_View_Limit", "year", "country_of_Origin", "genre",
				   				"maximum_Viewing_Length", "suggested_Price", "propagation_Priority",
				   				"longTail_YN", "studio_Royalty_Percent", "studio_Royalty_Minimum",
				   				"studio_Royalty_Flat_Rate", "director", "writer_Display", "producer",
				   				"home_Video_Window", "contract_Name", "distributor_Royalty_Percent",
				   				"distributor_Royalty_Minimum", "distributor_Royalty_Flat_Rate",
				   				"distributor_Name", "encryption", "audio_Type", "screen_Format",
				   				"languages", "subtitle_Languages", "dubbed_Languages", "copy_Protection",
				   				"copy_Protection_Verbose", "analog_Protection_System",
				   				"encryption_Mode_Indicator", "constrained_Image_Trigger", "cgms_A",
				   				"hdContent", "image_Aspect_Ratio"};
		
		//m_package table
		List<M_Package> m_Package = m_PackageDao.getAll();
		
		File dir = new File("C:\\Users\\pandora\\Desktop\\xmlParse\\excel");
		
		if(!dir.exists()) {
			dir.mkdir();
		}
		
		for(int idx=0; idx < m_Package.size(); idx++) {
			
			//ams table
			List<Ams> ams = amsDao.getAms(m_Package.get(idx).getPackage_ID());

			String fileNameTemp = m_Package.get(idx).getAsset_Name();
			
			if(fileNameTemp.length() > 20) {
				fileName = fileNameTemp.substring(0, 21)+ "..(" + idx + ").xlsx";
			} else {
				fileName = m_Package.get(idx).getAsset_Name()+ "(" + idx + ").xlsx";
			}
			
			File excelFile = new File(dir, fileName);
			
			workBook = new XSSFWorkbook();
			Sheet sheet1 = workBook.createSheet("noName");
			
			sheet1.setColumnWidth(0, 10*256);
			sheet1.setColumnWidth(1, 10*256);
			sheet1.setColumnWidth(2, 13*256);
			sheet1.setColumnWidth(3, 7*256);
			sheet1.setColumnWidth(4, 8*256);
			sheet1.setColumnWidth(5, 11*256);
			sheet1.setColumnWidth(6, 4*256);
			sheet1.setColumnWidth(7, 13*256);
			sheet1.setColumnWidth(8, 13*256);
			
			Row row0 = sheet1.createRow(0);
			for(int i=0; i < amsCol.length; i++) {
				Cell amsCell = row0.createCell(i);
				amsCell.setCellValue(amsCol[i]);
			}
			
			for(int i=1; i < 5; i++) {
				Row row1 = sheet1.createRow(i);
				Cell idCell = row1.createCell(0);
				idCell.setCellValue(m_Package.get(idx).getPackage_ID());
				Cell nameCell = row1.createCell(1);
				nameCell.setCellValue(m_Package.get(idx).getAsset_Name());
				Cell dateCell = row1.createCell(2);
				dateCell.setCellValue(m_Package.get(idx).getCreation_Date());
				Cell productCell = row1.createCell(3);
				productCell.setCellValue(m_Package.get(idx).getProduct());
				Cell providerCell = row1.createCell(4);
				providerCell.setCellValue(m_Package.get(idx).getProvider());
				Cell pIDCell = row1.createCell(5);
				pIDCell.setCellValue(m_Package.get(idx).getProvider_ID());
				Cell verbCell = row1.createCell(6);
				verbCell.setCellValue(m_Package.get(idx).getVerb());
				Cell majorCell = row1.createCell(7);
				majorCell.setCellValue(m_Package.get(idx).getVersion_Major());
				Cell minorCell = row1.createCell(8);
				minorCell.setCellValue(m_Package.get(idx).getVersion_Minor());
				Cell asClassCell = row1.createCell(9);
				asClassCell.setCellValue(ams.get(i-1).getAsset_Class());
				Cell asIdCell = row1.createCell(10);
				asIdCell.setCellValue(ams.get(i-1).getAsset_ID());
				Cell descriptionCell = row1.createCell(11);
				descriptionCell.setCellValue(ams.get(i-1).getDescription());
			}
			
			
			//app_data table
			App_Data app_Data = (App_Data) appDao.get(m_Package.get(idx).getPackage_ID());
			
			Row row6 = sheet1.createRow(6);
			Row row9 = sheet1.createRow(9);
			Row row12 = sheet1.createRow(12);
			Row row15 = sheet1.createRow(15);
			int firstIdx = 0;
			int secondIdx = 0;
			int thirdIdx = 0;
			for(int i=0; i < app_DataCol.length; i++) {
				if(i >= 57) {
					Cell appThirdLineCell = row15.createCell(thirdIdx++);
					appThirdLineCell.setCellValue(app_DataCol[i]);
				} else if(i >= 38) {
					Cell appSecondLineCell = row12.createCell(secondIdx++);
					appSecondLineCell.setCellValue(app_DataCol[i]);
				} else if(i >= 19) {
					Cell appFirstLineCell = row9.createCell(firstIdx++);
					appFirstLineCell.setCellValue(app_DataCol[i]);
				} else if(i >= 0) {
					Cell appCell = row6.createCell(i);
					appCell.setCellValue(app_DataCol[i]);
				}  
			}
			
			//app_data value
			// app_data 1st data
			Row row7 = sheet1.createRow(7); 
			Cell idCell = row7.createCell(0);
			idCell.setCellValue(app_Data.getPackage_ID());
			Cell tierCell = row7.createCell(1);
			tierCell.setCellValue(app_Data.getProvider_Content_Tier());
			Cell metaCell = row7.createCell(2);
			metaCell.setCellValue(app_Data.getMetadata_Spec_Version());
			Cell titleCell = row7.createCell(3);
			titleCell.setCellValue(app_Data.getTitle());
			Cell titleBriefCell = row7.createCell(4);
			titleBriefCell.setCellValue(app_Data.getTitle_Brief());
			Cell categoryCell = row7.createCell(5);
			categoryCell.setCellValue(app_Data.getCategory());
			Cell ratingCell = row7.createCell(6);
			ratingCell.setCellValue(app_Data.getRating());
			Cell sumShortCell = row7.createCell(7);
			sumShortCell.setCellValue(app_Data.getSummary_Short());
			Cell runtimeCell = row7.createCell(8);
			runtimeCell.setCellValue(app_Data.getRun_Time());
			Cell disRunTimeCell = row7.createCell(9);
			disRunTimeCell.setCellValue(app_Data.getDisplay_Run_Time());
			Cell qaCell = row7.createCell(10);
			qaCell.setCellValue(app_Data.getProvider_QA_Contact());
			Cell billCell = row7.createCell(11);
			billCell.setCellValue(app_Data.getBilling_Id());
			Cell winStartCell = row7.createCell(12);
			winStartCell.setCellValue(app_Data.getLicensing_Window_Start());
			Cell winEndCell = row7.createCell(13);
			winEndCell.setCellValue(app_Data.getLicensing_Window_End());
			Cell prevPeriodCell = row7.createCell(14);
			prevPeriodCell.setCellValue(app_Data.getPreview_Period());
			Cell titleSortNameCell = row7.createCell(15);
			titleSortNameCell.setCellValue(app_Data.getTitle_Sort_Name());
			Cell epiNameCell = row7.createCell(16);
			epiNameCell.setCellValue(app_Data.getEpisode_Name());
			Cell epiIdCell = row7.createCell(17);
			epiIdCell.setCellValue(app_Data.getEpisode_Id());
			Cell sumMediumCell = row7.createCell(18);
			sumMediumCell.setCellValue(app_Data.getSummary_Medium());
			
			// app_data 2nd data
			Row row10 = sheet1.createRow(10); 
			Cell sumLong = row10.createCell(0);
			sumLong.setCellValue(app_Data.getSummary_Long());
			Cell actorDisplayCell = row10.createCell(1);
			actorDisplayCell.setCellValue(app_Data.getActors_Display());
			Cell chapterCell = row10.createCell(2);
			chapterCell.setCellValue(app_Data.getChapter());
			Cell stuNameCell = row10.createCell(3);
			stuNameCell.setCellValue(app_Data.getStudio_Name());
			Cell studioCell = row10.createCell(4);
			studioCell.setCellValue(app_Data.getStudio());
			Cell closCaptionCell = row10.createCell(5);
			closCaptionCell.setCellValue(app_Data.getClosed_Captioning());
			Cell premiereCell = row10.createCell(6);
			premiereCell.setCellValue(app_Data.getSeason_Premiere());
			Cell finaleCell = row10.createCell(7);
			finaleCell.setCellValue(app_Data.getSeason_Finale());
			Cell disNewCell = row10.createCell(8);
			disNewCell.setCellValue(app_Data.getDisplay_As_New());
			Cell disLastChance = row10.createCell(9);
			disLastChance.setCellValue(app_Data.getDisplay_As_Last_Chance());
			Cell subsViewCell = row10.createCell(10);
			subsViewCell.setCellValue(app_Data.getSubscriber_View_Limit());
			Cell yearCell = row10.createCell(11);
			yearCell.setCellValue(app_Data.getYear());
			Cell countOrignCell = row10.createCell(12);
			countOrignCell.setCellValue(app_Data.getCountry_of_Origin());
			Cell genereCell = row10.createCell(13);
			genereCell.setCellValue(app_Data.getGenre());
			Cell maxViewLenCell = row10.createCell(14);
			maxViewLenCell.setCellValue(app_Data.getMaximum_Viewing_Length());
			Cell suggestCell = row10.createCell(15);
			suggestCell.setCellValue(app_Data.getSuggested_Price());
			Cell propagationCell = row10.createCell(16);
			propagationCell.setCellValue(app_Data.getPropagation_Priority());
			Cell longTailCell = row10.createCell(17);
			longTailCell.setCellValue(app_Data.getLongTail_YN());
			Cell stuRoyPerCell = row10.createCell(18);
			stuRoyPerCell.setCellValue(app_Data.getStudio_Royalty_Percent());
			
			// app_data 3rd data
			Row row13 = sheet1.createRow(13); 
			Cell stuRoyMinCell = row13.createCell(0);
			stuRoyMinCell.setCellValue(app_Data.getStudio_Royalty_Minimum());
			Cell stuRoyFlatCell = row13.createCell(1);
			stuRoyFlatCell.setCellValue(app_Data.getStudio_Royalty_Flat_Rate());
			Cell directorCell = row13.createCell(2);
			directorCell.setCellValue(app_Data.getDirector());
			Cell writerDisCell = row13.createCell(3);
			writerDisCell.setCellValue(app_Data.getWriter_Display());
			Cell producerCell = row13.createCell(4);
			producerCell.setCellValue(app_Data.getProducer());
			Cell homeWindowCell = row13.createCell(5);
			homeWindowCell.setCellValue(app_Data.getHome_Video_Window());
			Cell contractNameCell = row13.createCell(6);
			contractNameCell.setCellValue(app_Data.getContract_Name());
			Cell disRoyPerCell = row13.createCell(7);
			disRoyPerCell.setCellValue(app_Data.getDistributor_Royalty_Percent());
			Cell disRoyMinCell = row13.createCell(8);
			disRoyMinCell.setCellValue(app_Data.getDistributor_Royalty_Minimum());
			Cell disRoyFlatCell = row13.createCell(9);
			disRoyFlatCell.setCellValue(app_Data.getDistributor_Royalty_Flat_Rate());
			Cell disNameCell = row13.createCell(10);
			disNameCell.setCellValue(app_Data.getDistributor_Name());
			Cell encryptionCell = row13.createCell(11);
			encryptionCell.setCellValue(app_Data.getEncryption());
			Cell audioTypeCell = row13.createCell(12);
			audioTypeCell.setCellValue(app_Data.getAudio_Type());
			Cell screenFormCell = row13.createCell(13);
			screenFormCell.setCellValue(app_Data.getScreen_Format());
			Cell languageCell = row13.createCell(14);
			languageCell.setCellValue(app_Data.getLanguages());
			Cell subLanguageCell = row13.createCell(15);
			subLanguageCell.setCellValue(app_Data.getSubtitle_Languages());
			Cell dubLanguageCell = row13.createCell(16);
			dubLanguageCell.setCellValue(app_Data.getDubbed_Languages());
			Cell copyProtecCell = row13.createCell(17);
			copyProtecCell.setCellValue(app_Data.getCopy_Protection());
			Cell copyProtecVerbCell = row13.createCell(18);
			copyProtecVerbCell.setCellValue(app_Data.getCopy_Protection_Verbose());
			
			// app_data 4th data
			Row row16 = sheet1.createRow(16); 
			Cell analProtecSysCell = row16.createCell(0);
			analProtecSysCell.setCellValue(app_Data.getAnalog_Protection_System());
			Cell encrypModIndicateCell = row16.createCell(1);
			encrypModIndicateCell.setCellValue(app_Data.getEncryption_Mode_Indicator());
			Cell consImageTriggerCell = row16.createCell(2);
			consImageTriggerCell.setCellValue(app_Data.getConstrained_Image_Trigger());
			Cell cgmsCell = row16.createCell(3);
			cgmsCell.setCellValue(app_Data.getCGMS_A());
			Cell hdConCell = row16.createCell(4);
			hdConCell.setCellValue(app_Data.getHDContent());
			Cell imageRatioCell = row16.createCell(5);
			imageRatioCell.setCellValue(app_Data.getImage_Aspect_Ratio());
			
			// actor table
			List<Actor> actor =  actorDao.getActor(m_Package.get(idx).getPackage_ID());
			
			Row row18 = sheet1.createRow(18);
			Cell actPakIdColCell = row18.createCell(0);
			actPakIdColCell.setCellValue("package_ID");
			Cell actorColCell = row18.createCell(1);
			actorColCell.setCellValue("actor");
			Row row19;
			
			for(int i=19, j=0; j < actor.size(); i++, j++) {
				row19 = sheet1.createRow(i);
				Cell actorPakIdCell = row19.createCell(0);
				actorPakIdCell.setCellValue(actor.get(j).getPackage_ID());
				Cell actorCell = row19.createCell(1);
				actorCell.setCellValue(actor.get(j).getActor());
			}
			if(actor.size()==1) {
				row19 = sheet1.createRow(20);
				row19 = sheet1.createRow(21);
			} else if(actor.size()==2) {
				row19 = sheet1.createRow(21);
			}
			
			// type table
			List<Type> type = typeDao.getType(m_Package.get(idx).getPackage_ID());
			
			Cell typePakIdColCell = row18.createCell(4);
			typePakIdColCell.setCellValue("package_ID");
			Cell typeColCell = row18.createCell(5);
			typeColCell.setCellValue("type");
			Cell pubColCell = row18.createCell(6);
			pubColCell.setCellValue("publication_Right");
			
			for(int i=19, j=0; j < type.size(); i++, j++) {
				row19 = sheet1.getRow(i);
				Cell typePakIdCell = row19.createCell(4);
				typePakIdCell.setCellValue(type.get(j).getPackage_ID());
				Cell typeCell = row19.createCell(5);
				typeCell.setCellValue(type.get(j).getType());
				Cell pubCell = row19.createCell(6);
				pubCell.setCellValue(type.get(j).getPublication_Right());
			}
			
			// content table
			List<Content> content = contentDao.getContent(m_Package.get(idx).getPackage_ID());
			
			Cell contentPakIdColCell = row18.createCell(9);
			contentPakIdColCell.setCellValue("package_ID");
			Cell conFileColCell = row18.createCell(10);
			conFileColCell.setCellValue("content_FileSize");
			Cell conCheckColCell = row18.createCell(11);
			conCheckColCell.setCellValue("content_CheckSum");
			Cell conValueColCell = row18.createCell(12);
			conValueColCell.setCellValue("value");
			Cell advisorColCell = row18.createCell(13);
			advisorColCell.setCellValue("advisories");
			
			for(int i=19, j=0; j < content.size(); i++, j++) {
				row19 = sheet1.getRow(i);
				Cell conPakIdCell = row19.createCell(9);
				conPakIdCell.setCellValue(content.get(j).getPackage_ID());
				Cell conFileCell = row19.createCell(10);
				conFileCell.setCellValue(content.get(j).getContent_FileSize());
				Cell conCheckCell = row19.createCell(11);
				conCheckCell.setCellValue(content.get(j).getContent_CheckSum());
				Cell conValueCell = row19.createCell(12);
				conValueCell.setCellValue(content.get(j).getValue());
				Cell advisorCell = row19.createCell(13);
				advisorCell.setCellValue(content.get(j).getAdvisories());
			}
			
			
			//excel file create
			try(FileOutputStream fileOut = new FileOutputStream(excelFile)) {
				workBook.write(fileOut);
				
				File successDir = new File("C:\\Users\\pandora\\Desktop\\success");
				if(!successDir.exists()) {
					successDir.mkdir();
				}
				try(BufferedWriter out = new BufferedWriter(
						 new FileWriter(new File(successDir, "excelSuccess.txt"), true))) {
					out.write(fileName);
					out.newLine();
					out.write("SUCCESS!!");
					out.newLine();
				} catch(IOException ie) {
					ie.printStackTrace();
				}
				System.out.println(excelFile+"종료!!");
				
			} catch(IOException ioe) {
				ioe.printStackTrace();
				File exceptionDir = new File("C:\\Users\\pandora\\Desktop\\exception");
				if(!exceptionDir.exists()) {
					exceptionDir.mkdir();
				}
				
				try(BufferedWriter out = new BufferedWriter(
						 new FileWriter(new File(exceptionDir, "excelException.txt"), true))) {
					out.write(fileName);
					out.newLine();
					out.write(ioe.getMessage());
					out.newLine();
				
				} catch(IOException ie) {
					ie.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) throws ParserConfigurationException {
		XmlParseTemp xmlParse = new XmlParseTemp();
		//xmlParse.tagReadWithPrint();
		xmlParse.printExcel();
	}
}
