package xmlparsingtemp;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import dao.JdbcDao;
import daoimpl.MovieJdbcDaoImpl;
import daoimpl.PackageJdbcDaoImpl;
import daoimpl.PosterJdbcDaoImpl;
import daoimpl.TitleJdbcDaoImpl;
import factory.DaoFactory;
import readnode.AmsTag;
import readnode.App_DataTag;
import readnode.ContentTag;
import table.Actors_Table;
import table.Movie_Table;
import table.Package_Table;
import table.Poster_Table;
import table.Title_Table;

public class XmlParseTemp {
	
	//final static String filePath = "C:\\Users\\pandora\\Desktop\\xml";
	final static String filePath = "C:\\java_workspace\\xmlParse\\src\\xml";
	static DocumentBuilderFactory domFactoty;
	static DocumentBuilder domBuilder;
	static Document doc;
	static File file;
	static File[] files;
	private AmsTag amsTag;
	private List<AmsTag> amsList;
	private App_DataTag appTag;
	private ContentTag conTag;
	
	private JdbcDao actorsJdbcDao;
	private JdbcDao packageJdbcDao;
	private JdbcDao titleJdbcDao;
	private JdbcDao movieJdbcDao;
	private JdbcDao posterJdbcDao;
	
	private List<String> publication_RightValueList;
	private List<String> typeValueList;
	private List<String> actorValueList;
	private List<String> advisorieValueList;
	private List<String> content_FileSizeValueList;
	private List<String> content_CheckSumValueList;
	private List<String> content_ValueList;
	int count;
	
	public XmlParseTemp() {
		packageJdbcDao = new DaoFactory().packageJdbcDao();
		titleJdbcDao = new DaoFactory().titleJdbcDao();
		movieJdbcDao = new DaoFactory().movieJdbcDao();
		posterJdbcDao = new DaoFactory().posterJdbcDao();
		actorsJdbcDao = new DaoFactory().actorDao();
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
				publication_RightValueList = new ArrayList<String>();
				typeValueList = new ArrayList<String>();
				actorValueList = new ArrayList<String>();
				advisorieValueList = new ArrayList<String>();
				content_FileSizeValueList = new ArrayList<String>();
				content_CheckSumValueList = new ArrayList<String>();
				content_ValueList = new ArrayList<String>();
				appTag = new App_DataTag();
				conTag = new ContentTag();
				
				String fileName = files[i].getAbsolutePath();
				System.out.println();
				System.out.println("no."+ ++count +" "+fileName);
				doc = domBuilder.parse(fileName);
				doc.normalize();
				
				NodeList list = doc.getElementsByTagName("Metadata");
				NodeList contentList = doc.getElementsByTagName("Content");
				
				/*
				 * Content 노드 읽기.
				 */
				for(int x=0; x < contentList.getLength(); x++) {
					Node node = contentList.item(x);
					if(node.getNodeType() == Node.ELEMENT_NODE) {
						Element contentElement = (Element)node;
						content_ValueList.add(getTagValue("Value", contentElement));
					}
				}
				conTag.setVlaue(content_ValueList);
				
				/*
				 * AMS, App_Data 노드 읽기.
				 */
				for (int idx = 0; idx < list.getLength(); idx++) {
					NodeList childNode = list.item(idx).getChildNodes();
					for(int j=0; j < childNode.getLength(); j++) {
						Node node = childNode.item(j);
						if (node.getNodeType() == Node.ELEMENT_NODE) {
							
							if(node.getNodeName().equals("AMS")){
								amsTag = new AmsTag();
								
								Element amsElement = (Element) node;
								amsTag.setAsset_Class(getTagValue("Asset_Class", amsElement));
								amsTag.setAsset_Id(getTagValue("Asset_ID", amsElement));
								amsTag.setAsset_Name(getTagValue("Asset_Name", amsElement));
								amsTag.setCreation_Date(getTagValue("Creation_Date", amsElement));
								amsTag.setDescription(getTagValue("Description", amsElement));
								amsTag.setProduct(getTagValue("Product", amsElement));
								amsTag.setProvider(getTagValue("Provider", amsElement));
								amsTag.setProvider_ID(getTagValue("Provider_ID", amsElement));
								amsTag.setVersion_Major(Integer.parseInt(getTagValue("Version_Major", amsElement)));
								amsTag.setVersion_Minor(Integer.parseInt(getTagValue("Version_Minor", amsElement)));
								if(amsElement.getAttributeNode("Verb")!=null){
									amsTag.setVerb(getTagValue("Verb", amsElement));
								}
								amsList.add(amsTag); //amstag는 데이터가 4개 라서 리스트에 추가
								
							} else if(node.getNodeName().equals("App_Data")) {
								
								Element appElement = (Element) node;
								String name = getTagValue("Name", appElement);
								String value = getTagValue("Value", appElement);
								
								switch(name) {
								
								case "Provider_Content_Tier" :
									appTag.setProvider_Content_Tier(value);
									break;
								case "Metadata_Spec_Version" :
									appTag.setMetadata_Spec_Version(value);
									break;
								case "Publication_Right" :
									publication_RightValueList.add(value); //publication은 데이터가 여러개라 리스트에 추가.
									break;
								case "Type" :
									typeValueList.add(value); //type은 데이터가 여러개라 리스트에 추가.
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
									actorValueList.add(value); //actors는 데이터가 여러개라 리스트에 추가.
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
									advisorieValueList.add(value); //advisories는 데이터가 여러개라 리스트에 추가.
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
									content_FileSizeValueList.add(value); // content_filesize는 데이터가 여러개라 리스트에 추가.
									break;
								case "Content_CheckSum" :
									content_CheckSumValueList.add(value); // content_checksum은 데이터가 여러개라 리스트에 추가.
									break;
								case "Image_Aspect_Ratio" :
									appTag.setImage_Aspect_Ratio(value);
									break;
								}	
							}
						}	
					}
				}
				appTag.setPublication_Right(publication_RightValueList);
				appTag.setType(typeValueList);
				appTag.setActors(actorValueList);
				appTag.setAdvisories(advisorieValueList);
				appTag.setContent_FileSize(content_FileSizeValueList);
				appTag.setContent_CheckSum(content_CheckSumValueList);
				insertFile();
			}
		} catch(IOException e) {
			System.out.println("IOException!!"+e.getMessage());
			e.printStackTrace();
			System.exit(0);
		} catch(SAXException sae) {
			System.out.println("SAXException!!"+sae.getMessage());
			sae.printStackTrace();
			System.exit(0);
		} catch(ClassNotFoundException ce) {
			System.out.println("ClassNotFoundException!!"+ce.getMessage());
			ce.printStackTrace();
			System.exit(0);
		} catch(SQLException sqle) {
			System.out.println("SQLException!!"+sqle.getMessage());
			sqle.printStackTrace();
			System.exit(0);
		}
	}
	
	/*
	 * 테이블을 package, title, movie, poster 각각 분할해서 적재하기 위한 메서드.
	 */
	public void insertFile() throws SQLException, ClassNotFoundException {
//		this.actorsJdbcDao.deleteAll();;
//		this.packageJdbcDao.deleteAll();;
//		this.titleJdbcDao.deleteAll();;
//		this.movieJdbcDao.deleteAll();;
//		this.posterJdbcDao.deleteAll();;
//		int package_IdCount = this.packageJdbcDao.getCount();
//		System.out.println("package_Id :"+package_IdCount);
			
		//package_table
		AmsTag ams1 = amsList.get(0);
		Package_Table package_Table = new Package_Table();
		package_Table.setAsset_Class(ams1.getAsset_Class());
		package_Table.setAsset_Id(ams1.getAsset_Id());
		package_Table.setAsset_Name(ams1.getAsset_Name());
		package_Table.setCreation_Date(ams1.getCreation_Date());
		package_Table.setDescription(ams1.getDescription());
		package_Table.setProduct(ams1.getProduct());
		package_Table.setProvider(ams1.getProvider());
		package_Table.setProvider_Id(ams1.getProvider_ID());
		package_Table.setVerb(ams1.getVerb());
		package_Table.setVersion_Major(ams1.getVersion_Major());
		package_Table.setVersion_Minor(ams1.getVersion_Minor());
		package_Table.setProvider_Content_Tier(appTag.getProvider_Content_Tier());
		package_Table.setMetadata_Spec_Version(appTag.getMetadata_Spec_Version());
		if(publication_RightValueList.get(0)!=null){
			package_Table.setPublication_Right(publication_RightValueList.get(0));
		}
		packageJdbcDao.add(package_Table);
	
//		int package_Id = packageJdbcDao.getFindMaxPK();
//		//title_table
//		AmsTag ams2 = amsList.get(1);
//		Title_Table title_Table = new Title_Table();
//		title_Table.setPackage_Id(package_Id);
//		title_Table.setDescription(ams2.getDescription());
//		title_Table.setAsset_Id(ams2.getAsset_Id());
//		title_Table.setAsset_Class(ams2.getAsset_Class());
//		title_Table.setType(appTag.getType().get(0));
//		title_Table.setTitle(appTag.getTitle());
//		title_Table.setTitle_Brief(appTag.getTitle_Brief());
//		title_Table.setCategory(appTag.getCategory());
//		title_Table.setRating(appTag.getRating());
//		title_Table.setSummary_Short(appTag.getSummary_Short());
//		title_Table.setRun_Time(appTag.getRun_Time());
//		title_Table.setDisplay_Run_Time(appTag.getDisplay_Run_Time());
//		title_Table.setProvider_QA_Contact(appTag.getProvider_QA_Contact());
//		title_Table.setBilling_Id(appTag.getBilling_ID());
//		title_Table.setLicensing_Window_Start(appTag.getLicensing_Window_Start());
//		title_Table.setLicensing_Window_End(appTag.getLicensing_Window_End());
//		title_Table.setPreview_Period(Integer.parseInt(appTag.getPreview_Period()));
//		title_Table.setTitle_Sort_Name(appTag.getTitle_Sort_Name());
//		title_Table.setEpisode_Name(appTag.getEpisode_Name());
//		title_Table.setEpisode_Id(appTag.getEpisode_id());
//		title_Table.setSummary_Medium(appTag.getSummary_Medium());
//		title_Table.setSummary_Long(appTag.getSummary_Long());
//		title_Table.setActors_Display(appTag.getActors_Display());
//		title_Table.setChapter(appTag.getChapter());
//		title_Table.setStudio_Name(appTag.getStudio_Name());
//		title_Table.setStudio(appTag.getStudio());
//		title_Table.setClosed_Captioning(appTag.getClosed_Captioning());
//		title_Table.setSeason_Premiere(appTag.getSeason_Premiere());
//		title_Table.setSeason_Finale(appTag.getSeason_Finale());
//		title_Table.setDisplay_As_New(appTag.getDisplay_As_New());
//		title_Table.setDisplay_As_Last_Chance(appTag.getDisplay_As_Last_Chance());
//		title_Table.setSubscriber_View_Limit(appTag.getSubscriber_View_Limit());
//		title_Table.setYear(appTag.getYear());
//		title_Table.setCountry_of_Origin(appTag.getCountry_of_Origin());
//		title_Table.setGenre(appTag.getGenre());
//		title_Table.setMaximum_Viewing_Length(appTag.getMaximum_Viewing_Length());
//		title_Table.setSuggested_Price(Integer.parseInt(appTag.getSuggested_Price()));
//		title_Table.setPropagation_Priority(Integer.parseInt(appTag.getPropagation_Priority()));
//		title_Table.setLongTail_YN(appTag.getLongTail_YN());
//		title_Table.setStudio_Royalty_Percent(appTag.getStudio_Royalty_Percent());
//		title_Table.setStudio_Royalty_Minimum(appTag.getStudio_Royalty_Minimum());
//		title_Table.setStudio_Royalty_Flat_Rate(appTag.getStudio_Royalty_Flat_Rate());
//		title_Table.setDirector(appTag.getDirector());
//		title_Table.setWriter_Display(appTag.getWriter_Display());
//		title_Table.setProducer(appTag.getProducer());
//		title_Table.setHome_Video_Window(appTag.getHome_Video_Window());
//		title_Table.setContract_Name(appTag.getContract_Name());
//		title_Table.setDistributor_Royalty_Percent(appTag.getDistributor_Royalty_Percent());
//		title_Table.setDistributor_Royalty_Minimum(appTag.getDistributor_Royalty_Minimum());
//		title_Table.setDistributor_Royalty_Flat_Rate(appTag.getDistributor_Royalty_Flat_Rate());
//		title_Table.setDistributor_Name(appTag.getDistributor_Name());
//		titleJdbcDao.add(title_Table);
//		
//		//movie_table
//		AmsTag ams3 = amsList.get(2);
//		Movie_Table movie_Table = new Movie_Table();
//		movie_Table.setPackage_Id(package_Id);
//		movie_Table.setDescription(ams3.getDescription());
//		movie_Table.setAsset_Id(ams3.getAsset_Id());
//		movie_Table.setAsset_Class(ams3.getAsset_Class());
//		if(appTag.getType().get(1)!=null) {
//			movie_Table.setType(appTag.getType().get(1));
//		}
//		movie_Table.setEncryption(appTag.getEncryption());
//		movie_Table.setAudio_Type(appTag.getAudio_Type());
//		movie_Table.setScreen_Format(appTag.getScreen_Format());
//		movie_Table.setLanguages(appTag.getLanguages());
//		movie_Table.setSubtitle_Languages(appTag.getSubtitle_Languages());
//		movie_Table.setDubbed_Languages(appTag.getDubbed_Languages());
//		movie_Table.setCopy_Protection(appTag.getCopy_Protection());
//		if(appTag.getCopy_Protection_Verbose()!=null) {
//			movie_Table.setCopy_Protection_Verbose(appTag.getCopy_Protection_Verbose());
//		}
//		if(appTag.getAnalog_Protection_System()!=null) {
//			movie_Table.setAnalog_Protection_System(Integer.parseInt(appTag.getAnalog_Protection_System()));
//		}
//		if(appTag.getEncryption_Mode_Indicator()!=null) {
//			movie_Table.setEncryption_Mode_Indicator(Integer.parseInt(appTag.getEncryption_Mode_Indicator()));
//		}
//		if(appTag.getConstrained_Image_Trigger()!=null) {
//			movie_Table.setConstrained_Image_Trigger(Integer.parseInt(appTag.getConstrained_Image_Trigger()));
//		}
//		if(appTag.getCGMS_A()!=null) {
//			movie_Table.setCgms_A(Integer.parseInt(appTag.getCGMS_A()));
//		}
//		movie_Table.setHDContent(appTag.getHDContent());
//		if(appTag.getContent_FileSize().get(0)!=null) {
//			movie_Table.setContent_FileSize(appTag.getContent_FileSize().get(0));
//		}
//		if(appTag.getContent_CheckSum().get(0)!=null) {
//			movie_Table.setContent_CheckSum(appTag.getContent_CheckSum().get(0));
//		}
//		if(conTag.getVlaue().get(0)!=null) {
//			movie_Table.setValue(conTag.getVlaue().get(0));
//		}
//		if(publication_RightValueList.size() > 1) {
//			movie_Table.setPublication_Right(publication_RightValueList.get(1));
//		}
//		if(advisorieValueList.get(0)!=null) {
//			movie_Table.setAdvisories(advisorieValueList.get(0));
//		}
//		movieJdbcDao.add(movie_Table);
//		
//		//poster_table
//		AmsTag ams4 = amsList.get(3);
//		Poster_Table poster_Table = new Poster_Table();
//		poster_Table.setPackage_Id(package_Id);
//		poster_Table.setDescription(ams4.getDescription());
//		poster_Table.setAsset_Id(ams4.getAsset_Id());
//		poster_Table.setAsset_Class(ams4.getAsset_Class());
//		if(appTag.getType().get(2)!=null) {
//			poster_Table.setType(appTag.getType().get(2));
//		}
//		if(appTag.getContent_FileSize().get(1)!=null) {
//			poster_Table.setContent_FileSize(appTag.getContent_FileSize().get(1));
//		}
//		if(appTag.getContent_CheckSum().get(1)!=null) {
//			poster_Table.setContent_CheckSum(appTag.getContent_CheckSum().get(1));
//		}
//		if(conTag.getVlaue().get(1)!=null) {
//			poster_Table.setValue(conTag.getVlaue().get(1));
//		}
//		poster_Table.setImage_Aspect_Ratio(appTag.getImage_Aspect_Ratio());
//		if(publication_RightValueList.size() > 2) {
//			poster_Table.setPublication_Right(publication_RightValueList.get(2));
//		}
//		if(advisorieValueList.get(1)!=null) {
//			poster_Table.setAdvisories(advisorieValueList.get(1));
//		}
//		posterJdbcDao.add(poster_Table);
//		
//		//actors_table
//		Iterator<String> actorListIt = actorValueList.iterator();
//		while(actorListIt.hasNext()) {
//			Actors_Table actors_Table = new Actors_Table();
//			actors_Table.setPackage_Id(package_Id);
//			actors_Table.setActor(actorListIt.next());
//			actorsJdbcDao.add(actors_Table);
//		}
	}
	
	public  String getTagValue(String tag, Element element) {
		String list = element.getAttributeNode(tag).getTextContent();
		return list;
	}
	
	public static void main(String[] args) throws ParserConfigurationException,
	ClassNotFoundException, SQLException {
		XmlParseTemp xmlParse = new XmlParseTemp();
		xmlParse.tagReadWithPrint();
	}
}
