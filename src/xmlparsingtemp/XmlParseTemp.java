package xmlparsingtemp;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import dao.ActorsDao;
import dao.JdbcDao;
import daoimpl.MovieJdbc;
import daoimpl.PackageJdbcDaoImpl;
import daoimpl.PosterJdbc;
import daoimpl.SubTableJdbc;
import daoimpl.TitleJdbc;
import domain.Actors;
import factory.DaoFactory;
import readnode.AmsTag;
import readnode.App_DataTag;
import readnode.ContentTag;
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
	
	private PackageJdbcDaoImpl packageDao;
	private ActorsDao actorDao;
	private SubTableJdbc subTableJdbc;
	private TitleJdbc titleJdbc;
	private MovieJdbc movieJdbc;
	private PosterJdbc posterJdbc;
	
	private List<String> publication_RightValueList;
	private List<String> typeValueList;
	private List<String> actorValueList;
	private List<String> advisorieValueList;
	private List<String> content_FileSizeValueList;
	private List<String> content_CheckSumValueList;
	private List<String> content_ValueList;
	int count;
	
	public XmlParseTemp() {
		packageDao = new DaoFactory().packageJdbcDao();
		actorDao = new DaoFactory().actorDao();
		subTableJdbc = new DaoFactory().subTableJdbc();
		titleJdbc = new DaoFactory().titleJdbc();
		movieJdbc = new DaoFactory().movieJdbc();
		posterJdbc = new DaoFactory().posterJdbc();
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
				
				System.out.println("Root element: "+doc.getDocumentElement().getNodeName());
				
				System.out.println();
				NodeList list = doc.getElementsByTagName("Metadata");
				NodeList contentList = doc.getElementsByTagName("Content");
				System.out.println("Content 노드 수 :"+contentList.getLength());
				
				/*
				 * Content 노드 읽기.
				 */
				for(int x=0; x < contentList.getLength(); x++) {
					Node node = contentList.item(x);
					if(node.getNodeType() == Node.ELEMENT_NODE) {
						Element contentElement = (Element)node;
						System.out.println("Value:"+getTagValue("Value", contentElement));
						content_ValueList.add(getTagValue("Value", contentElement));
					}
				}
				conTag.setVlaue(content_ValueList);
				
				
				System.out.println("Metadata 노드 수 :"+list.getLength());
				
				for (int idx = 0; idx < list.getLength(); idx++) {
					NodeList childNode = list.item(idx).getChildNodes();
					System.out.println();
					System.out.println("idx:"+idx+"child:"+childNode.getLength());
					for(int j=0; j < childNode.getLength(); j++) {
						Node node = childNode.item(j);
						//System.out.println("childNode:"+node);
						if (node.getNodeType() == Node.ELEMENT_NODE) {
							//Element element = (Element) node;
							if(node.getNodeName().equals("AMS")){
								amsTag = new AmsTag();
								
								Element amsElement = (Element) node;
								System.out.print(" | Asset_Class:" + getTagValue("Asset_Class", amsElement));
								amsTag.setAsset_Class(getTagValue("Asset_Class", amsElement));
								System.out.print(" |Asset_ID:" + getTagValue("Asset_ID", amsElement));
								amsTag.setAsset_Id(getTagValue("Asset_ID", amsElement));
								System.out.print(" | Asset_Name:" + getTagValue("Asset_Name", amsElement));
								amsTag.setAsset_Name(getTagValue("Asset_Name", amsElement));
								System.out.print(" | Creation_Date:" + getTagValue("Creation_Date", amsElement));
								amsTag.setCreation_Date(getTagValue("Creation_Date", amsElement));
								System.out.print(" | Description:" + getTagValue("Description", amsElement));
								amsTag.setDescription(getTagValue("Description", amsElement));
								System.out.print(" | Product:" + getTagValue("Product", amsElement));
								amsTag.setProduct(getTagValue("Product", amsElement));
								System.out.print(" | Provider:" + getTagValue("Provider", amsElement));
								amsTag.setProvider(getTagValue("Provider", amsElement));
								System.out.print(" | Provider_ID:" + getTagValue("Provider_ID", amsElement));
								amsTag.setProvider_ID(getTagValue("Provider_ID", amsElement));
								System.out.print(" | Version_Major:" + getTagValue("Version_Major", amsElement));
								amsTag.setVersion_Major(Integer.parseInt(getTagValue("Version_Major", amsElement)));
								System.out.println(" | Version_Minor:" + getTagValue("Version_Minor", amsElement));
								amsTag.setVersion_Minor(Integer.parseInt(getTagValue("Version_Minor", amsElement)));
								if(amsElement.getAttributeNode("Verb")!=null){
									System.out.println(" | Verb:" + getTagValue("Verb", amsElement));
									amsTag.setVerb(getTagValue("Verb", amsElement));
								}
								amsList.add(amsTag); //amstag는 데이터가 4개 라서 리스트에 추가
							} else if(node.getNodeName().equals("App_Data")) {
								
								Element appElement = (Element) node;
								String name = getTagValue("Name", appElement);
								String value = getTagValue("Value", appElement);
								
								switch(name) {
								
								case "Provider_Content_Tier" :
									System.out.println("Provider_Content_Tier:" + value);
									appTag.setProvider_Content_Tier(value);
									break;
								case "Metadata_Spec_Version" :
									System.out.println("Metadata_Spec_Version:" + value);
									appTag.setMetadata_Spec_Version(value);
									break;
								case "Publication_Right" :
									System.out.println("Publication_Right:" + value);
									publication_RightValueList.add(value); //publication은 데이터가 여러개라 리스트에 추가.
									appTag.setPublication_Right(publication_RightValueList);
									break;
								case "Type" :
									System.out.println("Type:" + value);
									typeValueList.add(value); //type은 데이터가 여러개라 리스트에 추가.
									appTag.setType(typeValueList);
									break;
								case "Title" :
									System.out.println("Title:" + value);
									appTag.setTitle(value);
									break;
								case "Title_Brief" :
									System.out.println("Title_Brief:" + value);
									appTag.setTitle_Brief(value);
									break;
								case "Category" :
									System.out.println("Category:" + value);
									appTag.setCategory(value);
									break;
								case "Rating" :
									System.out.println("Rating:" + value);
									appTag.setRating(value);
									break;
								case "Summary_Short" :
									System.out.println("Summary_Short:" + value);
									appTag.setSummary_Short(value);
									break;
								case "Run_Time" :
									System.out.println("Run_Time:" + value);
									appTag.setRun_Time(value);
									break;
								case "Display_Run_Time" :
									System.out.println("Display_Run_Time:" + value);
									appTag.setDisplay_Run_Time(value);
									break;
								case "Provider_QA_Contact" :
									System.out.println("Provider_QA_Contact:" + value);
									appTag.setProvider_QA_Contact(value);
									break;
								case "Billing_ID" :
									System.out.println("Billing_ID:" + value);
									appTag.setBilling_ID(value);
									break;
								case "Licensing_Window_Start" :
									System.out.println("Licensing_Window_Start:" + value);
									appTag.setLicensing_Window_Start(value);
									break;
								case "Licensing_Window_End" :
									System.out.println("Licensing_Window_End:" + value);
									appTag.setLicensing_Window_End(value);
									break;
								case "Preview_Period" :
									System.out.println("Preview_Period:" + value);
									appTag.setPreview_Period(value);
									break;
								case "Title_Sort_Name" :
									System.out.println("Title_Sort_Name:" + value);
									appTag.setTitle_Sort_Name(value);
									break;
								case "Episode_Name" :
									System.out.println("Episode_Name:" + value);
									appTag.setEpisode_Name(value);
									break;
								case "Episode_id" :
									System.out.println("Episode_id:" + value);
									appTag.setEpisode_id(value);
									break;
								case "Summary_Medium" :
									System.out.println("Summary_Medium:" + value);
									appTag.setSummary_Medium(value);
									break;
								case "Summary_Long" :
									System.out.println("Summary_Long:" + value);
									appTag.setSummary_Long(value);
									break;
								case "Actors" :
									System.out.println("Actors:" + value);
									actorValueList.add(value); //actors는 데이터가 여러개라 리스트에 추가.
									appTag.setActors(actorValueList);
									break;
								case "Actors_Display" :
									System.out.println("Actors_Display:" + value);
									appTag.setActors_Display(value);
									break;
								case "Chapter" :
									System.out.println("Chapter:" + value);
									appTag.setChapter(value);
									break;
								case "Studio_Name" :
									System.out.println("Studio_Name:" + value);
									appTag.setStudio_Name(value);
									break;
								case "Studio" :
									System.out.println("Studio:" + value);
									appTag.setStudio(value);
									break;
								case "Advisories" :
									System.out.println("Advisories:" + value);
									advisorieValueList.add(value); //advisories는 데이터가 여러개라 리스트에 추가.
									appTag.setAdvisories(advisorieValueList);
									break;
								case "Closed_Captioning" :
									System.out.println("Closed_Captioning:" + value);
									appTag.setClosed_Captioning(value);
									break;
								case "Season_Premiere" :
									System.out.println("Season_Premiere:" + value);
									appTag.setSeason_Premiere(value);
									break;
								case "Season_Finale" :
									System.out.println("Season_Finale:" + value);
									appTag.setSeason_Finale(value);
									break;
								case "Display_As_New" :
									System.out.println("Display_As_New:" + value);
									appTag.setDisplay_As_New(value);
									break;
								case "Display_As_Last_Chance" :
									System.out.println("Display_As_Last_Chance:" + value);
									appTag.setDisplay_As_Last_Chance(value);
									break;
								case "Subscriber_View_Limit" :
									System.out.println("Subscriber_View_Limit:" + value);
									appTag.setSubscriber_View_Limit(value);
									break;
								case "Year" :
									System.out.println("Year:" + value);
									appTag.setYear(value);
									break;
								case "Country_of_Origin" :
									System.out.println("Country_of_Origin:" + value);
									appTag.setCountry_of_Origin(value);
									break;
								case "Genre" :
									System.out.println("Genre:" + value);
									appTag.setGenre(value);
									break;
								case "Maximum_Viewing_Length" :
									System.out.println("Maximum_Viewing_Length:" + value);
									appTag.setMaximum_Viewing_Length(value);
									break;
								case "Suggested_Price" :
									System.out.println("Suggested_Price:" + value);
									appTag.setSuggested_Price(value);
									break;
								case "Propagation_Priority" :
									System.out.println("Propagation_Priority:" + value);
									appTag.setPropagation_Priority(value);
									break;
								case "LongTail_YN" :
									System.out.println("LongTail_YN:" + value);
									appTag.setLongTail_YN(value);
									break;
								case "Studio_Royalty_Percent" :
									System.out.println("Studio_Royalty_Percent:" + value);
									appTag.setStudio_Royalty_Percent(value);
									break;
								case "Studio_Royalty_Minimum" :
									System.out.println("Studio_Royalty_Minimum:" + value);
									appTag.setStudio_Royalty_Minimum(value);
									break;
								case "Studio_Royalty_Flat_Rate" :
									System.out.println("Studio_Royalty_Flat_Rate:" + value);
									appTag.setStudio_Royalty_Flat_Rate(value);
									break;
								case "Director" :
									System.out.println("Director:" + value);
									appTag.setDirector(value);
									break;
								case "Writer_Display" :
									System.out.println("Writer_Display:" + value);
									appTag.setWriter_Display(value);
									break;
								case "Producer" :
									System.out.println("Producer:" + value);
									appTag.setProducer(value);
									break;
								case "Home_Video_Window" :
									System.out.println("Home_Video_Window:" + value);
									appTag.setHome_Video_Window(value);
									break;
								case "Contract_Name" :
									System.out.println("Contract_Name:" + value);
									appTag.setContract_Name(value);
									break;
								case "Distributor_Royalty_Percent" :
									System.out.println("Distributor_Royalty_Percent:" + value);
									appTag.setDistributor_Royalty_Percent(value);
									break;
								case "Distributor_Royalty_Minimum" :
									System.out.println("Distributor_Royalty_Minimum:" + value);
									appTag.setDistributor_Royalty_Minimum(value);
									break;
								case "Distributor_Royalty_Flat_Rate" :
									System.out.println("Distributor_Royalty_Flat_Rate:" + value);
									appTag.setDistributor_Royalty_Flat_Rate(value);
									break;
								case "Distributor_Name" :
									System.out.println("Distributor_Name:" + value);
									appTag.setDistributor_Name(value);
									break;
								case "Encryption" :
									System.out.println("Encryption:" + value);
									appTag.setEncryption(value);
									break;
								case "Audio_Type" :
									System.out.println("Audio_Type:" + value);
									appTag.setAudio_Type(value);
									break;
								case "Screen_Format" :
									System.out.println("Screen_Format:" + value);
									appTag.setScreen_Format(value);
									break;
								case "Languages" :
									System.out.println("Languages:" + value);
									appTag.setLanguages(value);
									break;
								case "Subtitle_Languages" :
									System.out.println("Subtitle_Languages:" + value);
									appTag.setSubtitle_Languages(value);
									break;
								case "Dubbed_Languages" :
									System.out.println("Dubbed_Languages:" + value);
									appTag.setDubbed_Languages(value);
									break;
								case "Copy_Protection" :
									System.out.println("Copy_Protection:" + value);
									appTag.setCopy_Protection(value);
									break;
								case "Copy_Protection_Verbose" :
									System.out.println("Copy_Protection_Verbose:" + value);
									appTag.setCopy_Protection_Verbose(value);
									break;
								case "Analog_Protection_System" :
									System.out.println("Analog_Protection_System:" + value);
									appTag.setAnalog_Protection_System(value);
									break;
								case "Encryption_Mode_Indicator" :
									System.out.println("Encryption_Mode_Indicator:" + value);
									appTag.setEncryption_Mode_Indicator(value);
									break;
								case "Constrained_Image_Trigger" :
									System.out.println("Constrained_Image_Trigger:" + value);
									appTag.setConstrained_Image_Trigger(value);
									break;
								case "CGMS_A" :
									System.out.println("CGMS_A:" + value);
									appTag.setCGMS_A(value);
									break;
								case "HDContent" :
									System.out.println("HDContent:" + value);
									appTag.setHDContent(value);
									break;
								case "Content_FileSize" :
									System.out.println("Content_FileSize:" + value);
									content_FileSizeValueList.add(value); // content_filesize는 데이터가 여러개라 리스트에 추가.
									appTag.setContent_FileSize(content_FileSizeValueList);
									break;
								case "Content_CheckSum" :
									System.out.println("Content_CheckSum:" + value);
									content_CheckSumValueList.add(value); // content_checksum은 데이터가 여러개라 리스트에 추가.
									appTag.setContent_CheckSum(content_CheckSumValueList);
									break;
								case "Image_Aspect_Ratio" :
									System.out.println("Image_Aspect_Ratio:" + value);
									appTag.setImage_Aspect_Ratio(value);
									break;
								}	
							}
						}	
					}
				}
				//inputFile();
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
		//package_table
//		AmsTag ams1 = amsList.get(0);
//		Package_Table package_Table = new Package_Table();
//		package_Table.setAsset_Class(ams1.getAsset_Class());
//		package_Table.setAsset_Id(ams1.getAsset_Id());
//		package_Table.setAsset_Name(ams1.getAsset_Name());
//		package_Table.setCreation_Date(ams1.getCreation_Date());
//		package_Table.setDescription(ams1.getDescription());
//		package_Table.setProduct(ams1.getProduct());
//		package_Table.setProvider(ams1.getProvider());
//		package_Table.setProvider_Id(ams1.getProvider_ID());
//		package_Table.setVerb(ams1.getVerb());
//		package_Table.setVersion_Major(ams1.getVersion_Major());
//		package_Table.setVersion_Minor(ams1.getVersion_Minor());
//		package_Table.setProvider_Content_Tier(appTag.getProvider_Content_Tier());
//		package_Table.setMetadata_Spec_Version(appTag.getMetadata_Spec_Version());
//		packageDao.add(package_Table);
		
		int package_Id = packageDao.getMaxPackage_Id();
		//title_table
		AmsTag ams2 = amsList.get(1);
		Title_Table title_Table = new Title_Table();
		title_Table.setPackage_Id(package_Id);
		title_Table.setDescription(ams2.getDescription());
		title_Table.setAsset_Id(ams2.getAsset_Id());
		title_Table.setAsset_Class(ams2.getAsset_Class());
		title_Table.setType(appTag.getType().get(0));
		title_Table.setTitle(appTag.getTitle());
		title_Table.setTitle_Brief(appTag.getTitle_Brief());
		title_Table.setCategory(appTag.getCategory());
		title_Table.setRating(appTag.getRating());
		title_Table.setSummary_Short(appTag.getSummary_Short());
		title_Table.setRun_Time(appTag.getRun_Time());
		title_Table.setDisplay_Run_Time(appTag.getDisplay_Run_Time());
		title_Table.setProvider_QA_Contact(appTag.getProvider_QA_Contact());
		title_Table.setBilling_Id(appTag.getBilling_ID());
		title_Table.setLicensing_Window_Start(appTag.getLicensing_Window_Start());
		title_Table.setLicensing_Window_End(appTag.getLicensing_Window_End());
		title_Table.setPreview_Period(Integer.parseInt(appTag.getPreview_Period()));
		title_Table.setTitle_Sort_Name(appTag.getTitle_Sort_Name());
		title_Table.setEpisode_Name(appTag.getEpisode_Name());
		title_Table.setEpisode_Id(appTag.getEpisode_id());
		title_Table.setSummary_Medium(appTag.getSummary_Medium());
		title_Table.setSummary_Long(appTag.getSummary_Long());
		title_Table.setActors_Display(appTag.getActors_Display());
		title_Table.setChapter(appTag.getChapter());
		title_Table.setStudio_Name(appTag.getStudio_Name());
		title_Table.setStudio(appTag.getStudio());
		title_Table.setClosed_Captioning(appTag.getClosed_Captioning());
		title_Table.setSeason_Premiere(appTag.getSeason_Premiere());
		title_Table.setSeason_Finale(appTag.getSeason_Finale());
		title_Table.setDisplay_As_New(appTag.getDisplay_As_New());
		title_Table.setDisplay_As_Last_Chance(appTag.getDisplay_As_Last_Chance());
		title_Table.setSubscriber_View_Limit(appTag.getSubscriber_View_Limit());
		title_Table.setYear(appTag.getYear());
		title_Table.setCountry_of_Origin(appTag.getCountry_of_Origin());
		title_Table.setGenre(appTag.getGenre());
		title_Table.setMaximum_Viewing_Length(appTag.getMaximum_Viewing_Length());
		title_Table.setSuggested_Price(Integer.parseInt(appTag.getSuggested_Price()));
		title_Table.setPropagation_Priority(Integer.parseInt(appTag.getPropagation_Priority()));
		title_Table.setLongTail_YN(appTag.getLongTail_YN());
		title_Table.setStudio_Royalty_Percent(appTag.getStudio_Royalty_Percent());
		title_Table.setStudio_Royalty_Minimum(appTag.getStudio_Royalty_Minimum());
		title_Table.setStudio_Royalty_Flat_Rate(appTag.getStudio_Royalty_Flat_Rate());
		title_Table.setDirector(appTag.getDirector());
		title_Table.setWriter_Display(appTag.getWriter_Display());
		title_Table.setProducer(appTag.getProducer());
		title_Table.setHome_Video_Window(appTag.getHome_Video_Window());
		title_Table.setContract_Name(appTag.getContract_Name());
		title_Table.setDistributor_Royalty_Percent(appTag.getDistributor_Royalty_Percent());
		title_Table.setDistributor_Royalty_Minimum(appTag.getDistributor_Royalty_Minimum());
		title_Table.setDistributor_Royalty_Flat_Rate(appTag.getDistributor_Royalty_Flat_Rate());
		title_Table.setDistributor_Name(appTag.getDistributor_Name());
		titleJdbc.add(title_Table);
		
		//movie_table
		AmsTag ams3 = amsList.get(2);
		Movie_Table movie_Table = new Movie_Table();
		movie_Table.setPackage_Id(package_Id);
		movie_Table.setDescription(ams3.getDescription());
		movie_Table.setAsset_Id(ams3.getAsset_Id());
		movie_Table.setAsset_Class(ams3.getAsset_Class());
		if(appTag.getType().get(1)!=null) {
			movie_Table.setType(appTag.getType().get(1));
		}
		movie_Table.setEncryption(appTag.getEncryption());
		movie_Table.setAudio_Type(appTag.getAudio_Type());
		movie_Table.setScreen_Format(appTag.getScreen_Format());
		movie_Table.setLanguages(appTag.getLanguages());
		movie_Table.setSubtitle_Languages(appTag.getSubtitle_Languages());
		movie_Table.setDubbed_Languages(appTag.getDubbed_Languages());
		movie_Table.setCopy_Protection(appTag.getCopy_Protection());
		movie_Table.setCopy_Protection_Verbose(appTag.getCopy_Protection_Verbose());
		movie_Table.setAnalog_Protection_System(Integer.parseInt(appTag.getAnalog_Protection_System()));
		movie_Table.setEncryption_Mode_Indicator(Integer.parseInt(appTag.getEncryption_Mode_Indicator()));
		movie_Table.setConstrained_Image_Trigger(Integer.parseInt(appTag.getConstrained_Image_Trigger()));
		movie_Table.setCgms_A(Integer.parseInt(appTag.getCGMS_A()));
		movie_Table.setHDContent(appTag.getHDContent());
		if(appTag.getContent_FileSize().get(0)!=null) {
			movie_Table.setContent_FileSize(appTag.getContent_FileSize().get(0));
		}
		if(appTag.getContent_CheckSum().get(0)!=null) {
			movie_Table.setContent_CheckSum(appTag.getContent_CheckSum().get(0));
		}
		if(conTag.getVlaue().get(0)!=null) {
			movie_Table.setValue(conTag.getVlaue().get(0));
		}
		movieJdbc.add(movie_Table);
		
		//poster_table
		AmsTag ams4 = amsList.get(3);
		Poster_Table poster_Table = new Poster_Table();
		poster_Table.setPackage_Id(package_Id);
		poster_Table.setDescription(ams4.getDescription());
		poster_Table.setAsset_Id(ams4.getAsset_Id());
		poster_Table.setAsset_Class(ams4.getAsset_Class());
		if(appTag.getType().get(2)!=null) {
			poster_Table.setType(appTag.getType().get(2));
		}
		if(appTag.getContent_FileSize().get(1)!=null) {
			poster_Table.setContent_FileSize(appTag.getContent_FileSize().get(1));
		}
		if(appTag.getContent_CheckSum().get(1)!=null) {
			poster_Table.setContent_CheckSum(appTag.getContent_CheckSum().get(1));
		}
		if(conTag.getVlaue().get(1)!=null) {
			poster_Table.setValue(conTag.getVlaue().get(1));
		}
		poster_Table.setImage_Aspect_Ratio(appTag.getImage_Aspect_Ratio());
		posterJdbc.add(poster_Table);
		
	}
	
	public void inputFile() throws SQLException, ClassNotFoundException {
//		AmsTag ams1 = amsList.get(0);
//		Package_Table package_Table = new Package_Table();
//		package_Table.setAsset_Class(ams1.getAsset_Class());
//		package_Table.setAsset_Id(ams1.getAsset_Id());
//		package_Table.setAsset_Name(ams1.getAsset_Name());
//		package_Table.setCreation_Date(ams1.getCreation_Date());
//		package_Table.setDescription(ams1.getDescription());
//		package_Table.setProduct(ams1.getProduct());
//		package_Table.setProvider(ams1.getProvider());
//		package_Table.setProvider_Id(ams1.getProvider_ID());
//		package_Table.setVerb(ams1.getVerb());
//		package_Table.setVersion_Major(ams1.getVersion_Major());
//		package_Table.setVersion_Minor(ams1.getVersion_Minor());
//		package_Table.setProvider_Content_Tier(appTag.getProvider_Content_Tier());
//		package_Table.setMetadata_Spec_Version(appTag.getMetadata_Spec_Version());
//		packageDao.add(package_Table);
//		
//		int package_Id = packageDao.getMaxPackage_Id();
//		
//		Actors actors = new Actors();
//		Iterator<String> actorIt = appTag.getActors().iterator();
//		while(actorIt.hasNext()) {
//			actorDao.add(package_Id, actorIt.next());
//		}
//		
//		
//		AmsTag ams2 = amsList.get(1);
//		Title_Table sub_TableTitle = new Title_Table();
//		sub_TableTitle.setPackage_Id(package_Id);
//		sub_TableTitle.setAsset_Class(ams2.getAsset_Class());
//		sub_TableTitle.setAsset_Id(ams2.getAsset_Id());
//		sub_TableTitle.setAsset_Name(ams2.getAsset_Name());
//		sub_TableTitle.setCreation_Date(ams2.getCreation_Date());
//		sub_TableTitle.setDescription(ams2.getDescription());
//		sub_TableTitle.setProduct(ams2.getProduct());
//		sub_TableTitle.setProvider(ams2.getProvider());
//		sub_TableTitle.setProvider_ID(ams2.getProvider_ID());
//		sub_TableTitle.setVersion_Major(ams2.getVersion_Major());
//		sub_TableTitle.setVersion_Minor(ams2.getVersion_Minor());
//		if(appTag.getPublication_Right().get(0)!=null) {
//			sub_TableTitle.setPublication_Right(appTag.getPublication_Right().get(0));
//		}
//		if(appTag.getType().get(0)!=null) {
//			sub_TableTitle.setType(appTag.getType().get(0));
//		}
//		sub_TableTitle.setTitle(appTag.getTitle());
//		sub_TableTitle.setTitle_Brief(appTag.getTitle_Brief());
//		sub_TableTitle.setCategory(appTag.getCategory());
//		sub_TableTitle.setRating(appTag.getRating());
//		sub_TableTitle.setSummary_Short(appTag.getSummary_Short());
//		sub_TableTitle.setRun_Time(appTag.getRun_Time());
//		sub_TableTitle.setDisplay_Run_Time(appTag.getDisplay_Run_Time());
//		sub_TableTitle.setProvider_QA_Contact(appTag.getProvider_QA_Contact());
//		sub_TableTitle.setBilling_Id(appTag.getBilling_ID());
//		sub_TableTitle.setLicensing_Window_Start(appTag.getLicensing_Window_Start());
//		sub_TableTitle.setLicensing_Window_End(appTag.getLicensing_Window_End());
//		sub_TableTitle.setPreview_Period(Integer.parseInt(appTag.getPreview_Period()));
//		sub_TableTitle.setTitle_Sort_Name(appTag.getTitle_Sort_Name());
//		sub_TableTitle.setEpisode_Name(appTag.getEpisode_Name());
//		sub_TableTitle.setEpisode_Id(appTag.getEpisode_id());
//		sub_TableTitle.setSummary_Medium(appTag.getSummary_Medium());
//		sub_TableTitle.setSummary_Long(appTag.getSummary_Long());
//		sub_TableTitle.setActors_Display(appTag.getActors_Display());
//		sub_TableTitle.setChapter(appTag.getChapter());
//		sub_TableTitle.setStudio_Name(appTag.getStudio_Name());
//		sub_TableTitle.setStudio(appTag.getStudio());
//		if(appTag.getAdvisories().get(0)!=null) {
//			sub_TableTitle.setAdvisories(appTag.getAdvisories().get(0));
//		}
//		sub_TableTitle.setClosed_Captioning(appTag.getClosed_Captioning());
//		sub_TableTitle.setSeason_Premiere(appTag.getSeason_Premiere());
//		sub_TableTitle.setSeason_Finale(appTag.getSeason_Finale());
//		sub_TableTitle.setDisplay_As_New(appTag.getDisplay_As_New());
//		sub_TableTitle.setDisplay_As_Last_Chance(appTag.getDisplay_As_Last_Chance());
//		sub_TableTitle.setSubscriber_View_Limit(appTag.getSubscriber_View_Limit());
//		sub_TableTitle.setYear(appTag.getYear());
//		sub_TableTitle.setCountry_of_Origin(appTag.getCountry_of_Origin());
//		sub_TableTitle.setGenre(appTag.getGenre());
//		sub_TableTitle.setMaximum_Viewing_Length(appTag.getMaximum_Viewing_Length());
//		sub_TableTitle.setSuggested_Price(Integer.parseInt(appTag.getSuggested_Price()));
//		sub_TableTitle.setPropagation_Priority(Integer.parseInt(appTag.getPropagation_Priority()));
//		sub_TableTitle.setLongTail_YN(appTag.getLongTail_YN());
//		sub_TableTitle.setStudio_Royalty_Percent(appTag.getStudio_Royalty_Percent());
//		sub_TableTitle.setStudio_Royalty_Minimum(appTag.getStudio_Royalty_Minimum());
//		sub_TableTitle.setStudio_Royalty_Flat_Rate(appTag.getStudio_Royalty_Flat_Rate());
//		sub_TableTitle.setDirector(appTag.getDirector());
//		sub_TableTitle.setWriter_Display(appTag.getWriter_Display());
//		sub_TableTitle.setProducer(appTag.getProducer());
//		sub_TableTitle.setHome_Video_Window(appTag.getHome_Video_Window());
//		sub_TableTitle.setContract_Name(appTag.getContract_Name());
//		sub_TableTitle.setDistributor_Royalty_Percent(appTag.getDistributor_Royalty_Percent());
//		sub_TableTitle.setDistributor_Royalty_Minimum(appTag.getDistributor_Royalty_Minimum());
//		sub_TableTitle.setDistributor_Royalty_Flat_Rate(appTag.getDistributor_Royalty_Flat_Rate());
//		sub_TableTitle.setDistributor_Name(appTag.getDistributor_Name());
//		subTableJdbc.add(sub_TableTitle);
//		
//		
//		AmsTag ams3 = amsList.get(2);
//		Title_Table sub_TableMovie = new Title_Table();
//		sub_TableMovie.setPackage_Id(package_Id);
//		sub_TableMovie.setAsset_Class(ams3.getAsset_Class());
//		sub_TableMovie.setAsset_Id(ams3.getAsset_Id());
//		sub_TableMovie.setAsset_Name(ams3.getAsset_Name());
//		sub_TableMovie.setCreation_Date(ams3.getCreation_Date());
//		sub_TableMovie.setDescription(ams3.getDescription());
//		sub_TableMovie.setProduct(ams3.getProduct());
//		sub_TableMovie.setProvider(ams3.getProvider());
//		sub_TableMovie.setProvider_ID(ams3.getProvider_ID());
//		sub_TableMovie.setVersion_Major(ams3.getVersion_Major());
//		sub_TableMovie.setVersion_Minor(ams3.getVersion_Minor());
//		if(appTag.getPublication_Right().get(1)!=null) {
//			sub_TableMovie.setPublication_Right(appTag.getPublication_Right().get(1));
//		}
//		if(appTag.getType().get(1)!=null) {
//			sub_TableMovie.setType(appTag.getType().get(1));
//		}
//		if(appTag.getAdvisories().get(1)!=null) {
//			sub_TableMovie.setAdvisories(appTag.getAdvisories().get(1));
//		}
//		sub_TableMovie.setEncryption(appTag.getEncryption());
//		sub_TableMovie.setAudio_Type(appTag.getAudio_Type());
//		sub_TableMovie.setScreen_Format(appTag.getScreen_Format());
//		sub_TableMovie.setLanguages(appTag.getLanguages());
//		sub_TableMovie.setSubtitle_Languages(appTag.getSubtitle_Languages());
//		sub_TableMovie.setDubbed_Languages(appTag.getDubbed_Languages());
//		sub_TableMovie.setCopy_Protection(appTag.getCopy_Protection());
//		sub_TableMovie.setCopy_Protection_Verbose(appTag.getCopy_Protection_Verbose());
//		sub_TableMovie.setAnalog_Protection_System(appTag.getAnalog_Protection_System());
//		sub_TableMovie.setEncryption_Mode_Indicator(appTag.getEncryption_Mode_Indicator());
//		sub_TableMovie.setConstrained_Image_Trigger(appTag.getConstrained_Image_Trigger());
//		sub_TableMovie.setcGMS_A(appTag.getCGMS_A());
//		sub_TableMovie.setHDContent(appTag.getHDContent());
//		if(appTag.getContent_FileSize().get(0)!=null) {
//			sub_TableMovie.setContent_FileSize(appTag.getContent_FileSize().get(0));
//		}
//		if(appTag.getContent_CheckSum().get(0)!=null) {
//			sub_TableMovie.setContent_CheckSum(appTag.getContent_CheckSum().get(0));
//		}
//		subTableJdbc.add(sub_TableMovie);
//		
//		
//		AmsTag ams4 = amsList.get(3);
//		Title_Table sub_TablePoster = new Title_Table();
//		sub_TablePoster.setPackage_Id(package_Id);
//		sub_TablePoster.setAsset_Class(ams4.getAsset_Class());
//		sub_TablePoster.setAsset_Id(ams4.getAsset_Id());
//		sub_TablePoster.setAsset_Name(ams4.getAsset_Name());
//		sub_TablePoster.setCreation_Date(ams4.getCreation_Date());
//		sub_TablePoster.setDescription(ams4.getDescription());
//		sub_TablePoster.setProduct(ams4.getProduct());
//		sub_TablePoster.setProvider(ams4.getProvider());
//		sub_TablePoster.setProvider_ID(ams4.getProvider_ID());
//		sub_TablePoster.setVersion_Major(ams4.getVersion_Major());
//		sub_TablePoster.setVersion_Minor(ams4.getVersion_Minor());
//		if(appTag.getPublication_Right().get(2)!=null) {
//			sub_TablePoster.setPublication_Right(appTag.getPublication_Right().get(2));
//		}
//		if(appTag.getType().get(2)!=null) {
//			sub_TablePoster.setType(appTag.getType().get(2));
//		}
//		sub_TablePoster.setImage_Aspect_Ratio(appTag.getImage_Aspect_Ratio());
//		if(appTag.getContent_FileSize().get(1)!=null) {
//			sub_TablePoster.setContent_FileSize(appTag.getContent_FileSize().get(1));
//		}
//		if(appTag.getContent_CheckSum().get(1)!=null) {
//			sub_TablePoster.setContent_CheckSum(appTag.getContent_CheckSum().get(1));
//		}
//		subTableJdbc.add(sub_TablePoster);
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
