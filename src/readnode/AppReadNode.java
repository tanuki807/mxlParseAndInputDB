package readnode;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.util.SystemPropertyUtils;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import dao.ActorsDao;
import dao.AppDao;
import domain.Actors;
import domain.App;
import domain.Content;
import factory.DaoFactory;
import xmlparse.XmlParse;

public class AppReadNode implements ReadNode {
	//App_Data tag vo
	private App_DataTag app_DataTag;
	private List<String> publication_RightValue;
	private List<String> typeValue;
	private List<String> actorsValue;
	private List<String> advisoriesValue;
	private List<String> content_FileSizeValue;
	private List<String> content_CheckSumValue;
	
	
	//실제 table vo
	private App app;
	private AppDao appDao;
	private Actors actors;
	private ActorsDao actorDao;
	private Content content;
	private ContentReadNode conReadNode;
	
	
	public AppReadNode() {
		app_DataTag = new App_DataTag();
		publication_RightValue = new ArrayList();
		typeValue = new ArrayList();
		actorsValue = new ArrayList();
		advisoriesValue = new ArrayList();
		content_FileSizeValue = new ArrayList();
		content_CheckSumValue = new ArrayList();
		
		
		app = new App();
		appDao = new DaoFactory().appDao();
		actors = new Actors();
		actorDao = new DaoFactory().actorDao();
		content = new Content();
	}
	
	public void setContentReadNode(ContentReadNode conReadNode) {
		this.conReadNode = conReadNode;
	}
	
	@Override
	//public App_Data readNode(NodeList list) throws ClassNotFoundException, SQLException {
	public App readNode(NodeList list) {
		for (int i = 0; i < list.getLength(); i++) {
			Node node = list.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;
				String name = getTagValue("Name", element);
				//System.out.print("Name:" + name);
				
				String value = getTagValue("Value", element);
				//System.out.println(" | Value:" + value);
				
				switch(name) {
					
					case "Provider_Content_Tier" :
						app_DataTag.setProvider_Content_Tier(value);
						System.out.println("Provider_Content_Tier:" + app_DataTag.getProvider_Content_Tier());
						
						app.setProvider_Content_Tier(value);
						break;
					case "Metadata_Spec_Version" :
						app_DataTag.setMetadata_Spec_Version(value);
						System.out.println("Metadata_Spec_Version:" + app_DataTag.getMetadata_Spec_Version() );
						
						app.setMetadata_Spec_Version(value);
						break;
					case "Publication_Right" :
						publication_RightValue.add(value);
						app_DataTag.setPublication_Right(publication_RightValue);
						System.out.println("Publication_Right:" + app_DataTag.getPublication_Right());
						
						break;
					case "Type" :
						typeValue.add(value);
						app_DataTag.setType(typeValue);
						System.out.println("Type:" + app_DataTag.getType());
						
						break;
					case "Title" :
						app_DataTag.setTitle(value);
						System.out.println("Title:" + app_DataTag.getTitle());
						
						app.setTitle(value);
						//actors.setTitle(value);
						content.setTitle(value);
						break;
					case "Title_Brief" :
						app_DataTag.setTitle_Brief(value);
						System.out.println("Title_Brief:" + app_DataTag.getTitle_Brief());
						
						app.setTitle_Brief(value);
						break;
					case "Category" :
						app_DataTag.setCategory(value);
						System.out.println("Category:" + app_DataTag.getCategory());
						
						app.setCategory(value);
						break;
					case "Rating" :
						app_DataTag.setRating(value);
						System.out.println("Rating:" + app_DataTag.getRating());
						
						app.setRating(value);
						break;
					case "Summary_Short" :
						app_DataTag.setSummary_Short(value);
						System.out.println("Summary_Short:" + app_DataTag.getSummary_Short());
						
						app.setSummary_Short(value);
						break;
					case "Run_Time" :
						app_DataTag.setRun_Time(value);
						System.out.println("Run_Time:" + app_DataTag.getRun_Time());
						
						app.setRun_Time(value);
						break;
					case "Display_Run_Time" :
						app_DataTag.setDisplay_Run_Time(value);
						System.out.println("Display_Run_Time:" + app_DataTag.getDisplay_Run_Time());
						
						app.setDisplay_Run_Time(value);
						break;
					case "Provider_QA_Contact" :
						app_DataTag.setProvider_QA_Contact(value);
						System.out.println("Provider_QA_Contact:" + app_DataTag.getProvider_QA_Contact());
						
						app.setProvider_QA_Contact(value);
						break;
					case "Billing_ID" :
						app_DataTag.setBilling_ID(value);
						System.out.println("Billing_ID:" + app_DataTag.getBilling_ID());
						
						app.setBilling_ID(value);
						break;
					case "Licensing_Window_Start" :
						app_DataTag.setLicensing_Window_Start(value);
						System.out.println("Licensing_Window_Start:" + app_DataTag.getLicensing_Window_Start());
						
						app.setLicensing_Window_Start(value);
						break;
					case "Licensing_Window_End" :
						app_DataTag.setLicensing_Window_End(value);
						System.out.println("Licensing_Window_End:" + app_DataTag.getLicensing_Window_End());
						
						app.setLicensing_Window_End(value);
						break;
					case "Preview_Period" :
						app_DataTag.setPreview_Period(value);
						System.out.println("Preview_Period:" + app_DataTag.getPreview_Period());
						
						app.setPreview_Period(Integer.parseInt(value));
						break;
					case "Title_Sort_Name" :
						app_DataTag.setTitle_Sort_Name(value);
						System.out.println("Title_Sort_Name:" + app_DataTag.getTitle_Sort_Name());
						
						app.setTitle_Sort_Name(value);
						break;
					case "Episode_Name" :
						app_DataTag.setEpisode_Name(value);
						System.out.println("Episode_Name:" + app_DataTag.getEpisode_Name());
						
						app.setEpisode_Name(value);
						break;
					case "Episode_id" :
						app_DataTag.setEpisode_id(value);
						System.out.println("Episode_id:" + app_DataTag.getEpisode_id());
						
						app.setEpisode_id(value);
						break;
					case "Summary_Medium" :
						app_DataTag.setSummary_Medium(value);
						System.out.println("Summary_Medium:" + app_DataTag.getSummary_Medium());
						
						app.setSummary_Medium(value);
						break;
					case "Summary_Long" :
						app_DataTag.setSummary_Long(value);
						System.out.println("Summary_Long:" + app_DataTag.getSummary_Long());
						
						app.setSummary_Long(value);
						break;
					case "Actors" :
						actorsValue.add(value);
						app_DataTag.setActors(actorsValue);
						System.out.println("Actors:" + app_DataTag.getActors());
						
						break;
					case "Actors_Display" :
						app_DataTag.setActors_Display(value);
						System.out.println("Actors_Display:" + app_DataTag.getActors_Display());
						
						app.setActors_Display(value);
						break;
					case "Chapter" :
						app_DataTag.setChapter(value);
						System.out.println("Chapter:" + app_DataTag.getChapter());
						
						app.setChapter(value);
						break;
					case "Studio_Name" :
						app_DataTag.setStudio_Name(value);
						System.out.println("Studio_Name:" + app_DataTag.getStudio_Name());
						
						app.setStudio_Name(value);
						break;
					case "Studio" :
						app_DataTag.setStudio(value);
						System.out.println("Studio:" + app_DataTag.getStudio());
						
						app.setStudio(value);
						break;
					case "Advisories" :
						advisoriesValue.add(value);
						app_DataTag.setAdvisories(advisoriesValue);
						System.out.println("Advisories:" + app_DataTag.getAdvisories());
						
						break;
					case "Closed_Captioning" :
						app_DataTag.setClosed_Captioning(value);
						System.out.println("Closed_Captioning:" + app_DataTag.getClosed_Captioning());
						
						app.setClosed_Captioning(value);
						break;
					case "Season_Premiere" :
						app_DataTag.setSeason_Premiere(value);
						System.out.println("Season_Premiere:" + app_DataTag.getSeason_Premiere());
						
						app.setSeason_Premiere(value);
						break;
					case "Season_Finale" :
						app_DataTag.setSeason_Finale(value);
						System.out.println("Season_Finale:" + app_DataTag.getSeason_Finale());
						
						app.setSeason_Finale(value);
						break;
					case "Display_As_New" :
						app_DataTag.setDisplay_As_New(value);
						System.out.println("Display_As_New:" + app_DataTag.getDisplay_As_New());
						
						app.setDisplay_As_New(value);
						break;
					case "Display_As_Last_Chance" :
						app_DataTag.setDisplay_As_Last_Chance(value);
						System.out.println("Display_As_Last_Chance:" + app_DataTag.getDisplay_As_Last_Chance());
						
						app.setDisplay_As_Last_Chance(value);
						break;
					case "Subscriber_View_Limit" :
						app_DataTag.setSubscriber_View_Limit(value);
						System.out.println("Subscriber_View_Limit:" + app_DataTag.getSubscriber_View_Limit());
						
						app.setSubscriber_View_Limit(value);
						break;
					case "Year" :
						app_DataTag.setYear(value);
						System.out.println("Year:" + app_DataTag.getYear());
						
						app.setYear(value);
						break;
					case "Country_of_Origin" :
						app_DataTag.setCountry_of_Origin(value);
						System.out.println("Country_of_Origin:" + app_DataTag.getCountry_of_Origin());
						
						app.setCountry_of_Origin(value);
						break;
					case "Genre" :
						app_DataTag.setGenre(value);
						System.out.println("Genre:" + app_DataTag.getGenre());
						
						app.setGenre(value);
						break;
					case "Maximum_Viewing_Length" :
						app_DataTag.setMaximum_Viewing_Length(value);
						System.out.println("Maximum_Viewing_Length:" + app_DataTag.getMaximum_Viewing_Length());
						
						app.setMaximum_Viewing_Length(value);
						break;
					case "Suggested_Price" :
						app_DataTag.setSuggested_Price(value);
						System.out.println("Suggested_Price:" + app_DataTag.getSuggested_Price());
						
						app.setSuggested_Price(Integer.parseInt(value));
						break;
					case "Propagation_Priority" :
						app_DataTag.setPropagation_Priority(value);
						System.out.println("Propagation_Priority:" + app_DataTag.getPropagation_Priority());
						
						app.setPropagation_Priority(Integer.parseInt(value));
						break;
					case "LongTail_YN" :
						app_DataTag.setLongTail_YN(value);
						System.out.println("LongTail_YN:" + app_DataTag.getLongTail_YN());
						
						app.setLongTail_YN(value);
						break;
					case "Studio_Royalty_Percent" :
						app_DataTag.setStudio_Royalty_Percent(value);
						System.out.println("Studio_Royalty_Percent:" + app_DataTag.getStudio_Royalty_Percent());
						
						app.setStudio_Royalty_Percent(value);
						break;
					case "Studio_Royalty_Minimum" :
						app_DataTag.setStudio_Royalty_Minimum(value);
						System.out.println("Studio_Royalty_Minimum:" + app_DataTag.getStudio_Royalty_Minimum());
						
						app.setStudio_Royalty_Minimum(value);
						break;
					case "Studio_Royalty_Flat_Rate" :
						app_DataTag.setStudio_Royalty_Flat_Rate(value);
						System.out.println("Studio_Royalty_Flat_Rate:" + app_DataTag.getStudio_Royalty_Flat_Rate());
						
						app.setStudio_Royalty_Flat_Rate(value);
						break;
					case "Director" :
						app_DataTag.setDirector(value);
						System.out.println("Director:" + app_DataTag.getDirector());
						
						app.setDirector(value);
						break;
					case "Writer_Display" :
						app_DataTag.setWriter_Display(value);
						System.out.println("Writer_Display:" + app_DataTag.getWriter_Display());
						
						app.setWriter_Display(value);
						break;
					case "Producer" :
						app_DataTag.setProducer(value);
						System.out.println("Producer:" + app_DataTag.getProducer());
						
						app.setProducer(value);
						break;
					case "Home_Video_Window" :
						app_DataTag.setHome_Video_Window(value);
						System.out.println("Home_Video_Window:" + app_DataTag.getHome_Video_Window());
						
						app.setHome_Video_Window(value);
						break;
					case "Contract_Name" :
						app_DataTag.setContract_Name(value);
						System.out.println("Contract_Name:" + app_DataTag.getContract_Name());
						
						app.setContract_Name(value);
						break;
					case "Distributor_Royalty_Percent" :
						app_DataTag.setDistributor_Royalty_Percent(value);
						System.out.println("Distributor_Royalty_Percent:" + app_DataTag.getDistributor_Royalty_Percent());
						
						app.setDistributor_Royalty_Percent(value);
						break;
					case "Distributor_Royalty_Minimum" :
						app_DataTag.setDistributor_Royalty_Minimum(value);
						System.out.println("Distributor_Royalty_Minimum:" + app_DataTag.getDistributor_Royalty_Minimum());
						
						app.setDistributor_Royalty_Minimum(value);
						break;
					case "Distributor_Royalty_Flat_Rate" :
						app_DataTag.setDistributor_Royalty_Flat_Rate(value);
						System.out.println("Distributor_Royalty_Flat_Rate:" + app_DataTag.getDistributor_Royalty_Flat_Rate());
						
						app.setDistributor_Royalty_Flat_Rate(value);
						break;
					case "Distributor_Name" :
						app_DataTag.setDistributor_Name(value);
						System.out.println("Distributor_Name:" + app_DataTag.getDistributor_Name());
						
						app.setDistributor_Name(value);
						break;
					case "Encryption" :
						app_DataTag.setEncryption(value);
						System.out.println("Encryption:" + app_DataTag.getEncryption());
						
						app.setEncryption(value);
						break;
					case "Audio_Type" :
						app_DataTag.setAudio_Type(value);
						System.out.println("Audio_Type:" + app_DataTag.getAudio_Type());
						
						app.setAudio_Type(value);
						break;
					case "Screen_Format" :
						app_DataTag.setScreen_Format(value);
						System.out.println("Screen_Format:" + app_DataTag.getScreen_Format());
						
						app.setScreen_Format(value);
						break;
					case "Languages" :
						app_DataTag.setLanguages(value);
						System.out.println("Languages:" + app_DataTag.getLanguages());
						
						app.setLanguages(value);
						break;
					case "Subtitle_Languages" :
						app_DataTag.setSubtitle_Languages(value);
						System.out.println("Subtitle_Languages:" + app_DataTag.getSubtitle_Languages());
						
						app.setSubtitle_Languages(value);
						break;
					case "Dubbed_Languages" :
						app_DataTag.setDubbed_Languages(value);
						System.out.println("Dubbed_Languages:" + app_DataTag.getDubbed_Languages());
						
						app.setDubbed_Languages(value);
						break;
					case "Copy_Protection" :
						app_DataTag.setCopy_Protection(value);
						System.out.println("Copy_Protection:" + app_DataTag.getCopy_Protection());
						
						app.setCopy_Protection(value);
						break;
					case "Copy_Protection_Verbose" :
						app_DataTag.setCopy_Protection_Verbose(value);
						System.out.println("Copy_Protection_Verbose:" + app_DataTag.getCopy_Protection_Verbose());
						
						app.setCopy_Protection_Verbose(value);
						break;
					case "Analog_Protection_System" :
						app_DataTag.setAnalog_Protection_System(value);
						System.out.println("Analog_Protection_System:" + app_DataTag.getAnalog_Protection_System());
						
						app.setAnalog_Protection_System(value);
						break;
					case "Encryption_Mode_Indicator" :
						app_DataTag.setEncryption_Mode_Indicator(value);
						System.out.println("Encryption_Mode_Indicator:" + app_DataTag.getEncryption_Mode_Indicator());
						
						app.setEncryption_Mode_Indicator(value);
						break;
					case "Constrained_Image_Trigger" :
						app_DataTag.setConstrained_Image_Trigger(value);
						System.out.println("Constrained_Image_Trigger:" + app_DataTag.getConstrained_Image_Trigger());
						
						app.setConstrained_Image_Trigger(value);
						break;
					case "CGMS_A" :
						app_DataTag.setCGMS_A(value);
						System.out.println("CGMS_A:" + app_DataTag.getCGMS_A());
						
						app.setcGMS_A(value);
						break;
					case "HDContent" :
						app_DataTag.setHDContent(value);
						System.out.println("HDContent:" + app_DataTag.getHDContent());
						
						app.sethDContent(value);
						break;
					case "Content_FileSize" :
						content_FileSizeValue.add(value);
						app_DataTag.setContent_FileSize(content_FileSizeValue);
						System.out.println("Content_FileSize:" + app_DataTag.getContent_FileSize());
						
						break;
					case "Content_CheckSum" :
						content_CheckSumValue.add(value);
						app_DataTag.setContent_CheckSum(content_CheckSumValue);
						System.out.println("content_CheckSumValue:" + app_DataTag.getContent_CheckSum());
						
						break;
					case "Image_Aspect_Ratio" :
						app_DataTag.setImage_Aspect_Ratio(value);
						System.out.println("Image_Aspect_Ratio:" + app_DataTag.getImage_Aspect_Ratio());
						
						app.setImage_Aspect_Ratio(value);
						break;
				}	
			}
		}
		actors.setActors(actorsValue);
		content.setAdvisories(advisoriesValue);
		content.setContent_FileSize(content_FileSizeValue);
		content.setContent_CheckSum(content_CheckSumValue);
		conReadNode.setContent(content);
		app_DataAdd(app);
		//actorsAdd(actors);
		//return app_Data;
		return app;
	}
	
	@Override
	public  String getTagValue(String tag, Element element) {
		String list = element.getAttributeNode(tag).getTextContent();
		return list;
	}
	
	//app_data table add
	private void app_DataAdd(App app) {
		appDao.add(app);
	}
	
	//actors table add
//	private void actorsAdd(Actors actor) {
//		String title = actor.getTitle();
//		Iterator<String> actorIt = actor.getActors().iterator();
//		while(actorIt.hasNext()) {
//			actorDao.add(title, actorIt.next());
//		}
//	}
}
