package readnode;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.SystemPropertyUtils;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import domain.App;

public class AppReadNode implements ReadNode {
	//App_Data tag vo
	private App_Data app_Data;
	private List<String> publication_RightValue;
	private List<String> typeValue;
	private List<String> actorsValue;
	private List<String> advisoriesValue;
	private List<String> content_FileSizeValue;
	private List<String> content_CheckSumValue;
	
	//실제 table vo
	private App app;
	
	
	
	public AppReadNode() {
		app_Data = new App_Data();
		publication_RightValue = new ArrayList();
		typeValue = new ArrayList();
		actorsValue = new ArrayList();
		advisoriesValue = new ArrayList();
		content_FileSizeValue = new ArrayList();
		content_CheckSumValue = new ArrayList();
		app = new App();
	}
	
	@Override
	public App_Data readNode(NodeList list) {
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
						app_Data.setProvider_Content_Tier(value);
						System.out.println("Provider_Content_Tier:" + app_Data.getProvider_Content_Tier());
						
						app.setProvider_Content_Tier(value);
						break;
					case "Metadata_Spec_Version" :
						app_Data.setMetadata_Spec_Version(value);
						System.out.println("Metadata_Spec_Version:" + app_Data.getMetadata_Spec_Version() );
						
						app.setMetadata_Spec_Version(value);
						break;
					case "Publication_Right" :
						publication_RightValue.add(value);
						app_Data.setPublication_Right(publication_RightValue);
						System.out.println("Publication_Right:" + app_Data.getPublication_Right());
						break;
					case "Type" :
						typeValue.add(value);
						app_Data.setType(typeValue);
						System.out.println("Type:" + app_Data.getType());
						break;
					case "Title" :
						app_Data.setTitle(value);
						System.out.println("Title:" + app_Data.getTitle());
						
						app.setTitle(value);
						break;
					case "Title_Brief" :
						app_Data.setTitle_Brief(value);
						System.out.println("Title_Brief:" + app_Data.getTitle_Brief());
						
						app.setTitle_Brief(value);
						break;
					case "Category" :
						app_Data.setCategory(value);
						System.out.println("Category:" + app_Data.getCategory());
						
						app.setCategory(value);
						break;
					case "Rating" :
						app_Data.setRating(value);
						System.out.println("Rating:" + app_Data.getRating());
						
						app.setRating(value);
						break;
					case "Summary_Short" :
						app_Data.setSummary_Short(value);
						System.out.println("Summary_Short:" + app_Data.getSummary_Short());
						
						app.setSummary_Short(value);
						break;
					case "Run_Time" :
						app_Data.setRun_Time(value);
						System.out.println("Run_Time:" + app_Data.getRun_Time());
						
						app.setRun_Time(value);
						break;
					case "Display_Run_Time" :
						app_Data.setDisplay_Run_Time(value);
						System.out.println("Display_Run_Time:" + app_Data.getDisplay_Run_Time());
						
						app.setDisplay_Run_Time(value);
						break;
					case "Provider_QA_Contact" :
						app_Data.setProvider_QA_Contact(value);
						System.out.println("Provider_QA_Contact:" + app_Data.getProvider_QA_Contact());
						
						app.setProvider_QA_Contact(value);
						break;
					case "Billing_ID" :
						app_Data.setBilling_ID(value);
						System.out.println("Billing_ID:" + app_Data.getBilling_ID());
						
						app.setBilling_ID(value);
						break;
					case "Licensing_Window_Start" :
						app_Data.setLicensing_Window_Start(value);
						System.out.println("Licensing_Window_Start:" + app_Data.getLicensing_Window_Start());
						
						app.setLicensing_Window_Start(value);
						break;
					case "Licensing_Window_End" :
						app_Data.setLicensing_Window_End(value);
						System.out.println("Licensing_Window_End:" + app_Data.getLicensing_Window_End());
						
						app.setLicensing_Window_End(value);
						break;
					case "Preview_Period" :
						app_Data.setPreview_Period(value);
						System.out.println("Preview_Period:" + app_Data.getPreview_Period());
						
						app.setPreview_Period(Integer.parseInt(value));
						break;
					case "Title_Sort_Name" :
						app_Data.setTitle_Sort_Name(value);
						System.out.println("Title_Sort_Name:" + app_Data.getTitle_Sort_Name());
						
						app.setTitle_Sort_Name(value);
						break;
					case "Episode_Name" :
						app_Data.setEpisode_Name(value);
						System.out.println("Episode_Name:" + app_Data.getEpisode_Name());
						
						app.setEpisode_Name(value);
						break;
					case "Episode_id" :
						app_Data.setEpisode_id(value);
						System.out.println("Episode_id:" + app_Data.getEpisode_id());
						
						app.setEpisode_id(value);
						break;
					case "Summary_Medium" :
						app_Data.setSummary_Medium(value);
						System.out.println("Summary_Medium:" + app_Data.getSummary_Medium());
						
						app.setSummary_Medium(value);
						break;
					case "Summary_Long" :
						app_Data.setSummary_Long(value);
						System.out.println("Summary_Long:" + app_Data.getSummary_Long());
						
						app.setSummary_Long(value);
						break;
					case "Actors" :
						actorsValue.add(value);
						app_Data.setActors(actorsValue);
						System.out.println("Actors:" + app_Data.getActors());
						break;
					case "Actors_Display" :
						app_Data.setActors_Display(value);
						System.out.println("Actors_Display:" + app_Data.getActors_Display());
						
						app.setActors_Display(value);
						break;
					case "Chapter" :
						app_Data.setChapter(value);
						System.out.println("Chapter:" + app_Data.getChapter());
						
						app.setChapter(value);
						break;
					case "Studio_Name" :
						app_Data.setStudio_Name(value);
						System.out.println("Studio_Name:" + app_Data.getStudio_Name());
						
						app.setStudio_Name(value);
						break;
					case "Studio" :
						app_Data.setStudio(value);
						System.out.println("Studio:" + app_Data.getStudio());
						
						app.setStudio(value);
						break;
					case "Advisories" :
						advisoriesValue.add(value);
						app_Data.setAdvisories(advisoriesValue);
						System.out.println("Advisories:" + app_Data.getAdvisories());
						break;
					case "Closed_Captioning" :
						app_Data.setClosed_Captioning(value);
						System.out.println("Closed_Captioning:" + app_Data.getClosed_Captioning());
						
						app.setClosed_Captioning(value);
						break;
					case "Season_Premiere" :
						app_Data.setSeason_Premiere(value);
						System.out.println("Season_Premiere:" + app_Data.getSeason_Premiere());
						
						app.setSeason_Premiere(value);
						break;
					case "Season_Finale" :
						app_Data.setSeason_Finale(value);
						System.out.println("Season_Finale:" + app_Data.getSeason_Finale());
						
						app.setSeason_Finale(value);
						break;
					case "Display_As_New" :
						app_Data.setDisplay_As_New(value);
						System.out.println("Display_As_New:" + app_Data.getDisplay_As_New());
						
						app.setDisplay_As_New(value);
						break;
					case "Display_As_Last_Chance" :
						app_Data.setDisplay_As_Last_Chance(value);
						System.out.println("Display_As_Last_Chance:" + app_Data.getDisplay_As_Last_Chance());
						
						app.setDisplay_As_Last_Chance(value);
						break;
					case "Subscriber_View_Limit" :
						app_Data.setSubscriber_View_Limit(value);
						System.out.println("Subscriber_View_Limit:" + app_Data.getSubscriber_View_Limit());
						
						app.setSubscriber_View_Limit(value);
						break;
					case "Year" :
						app_Data.setYear(value);
						System.out.println("Year:" + app_Data.getYear());
						
						app.setYear(value);
						break;
					case "Country_of_Origin" :
						app_Data.setCountry_of_Origin(value);
						System.out.println("Country_of_Origin:" + app_Data.getCountry_of_Origin());
						
						app.setCountry_of_Origin(value);
						break;
					case "Genre" :
						app_Data.setGenre(value);
						System.out.println("Genre:" + app_Data.getGenre());
						
						app.setGenre(value);
						break;
					case "Maximum_Viewing_Length" :
						app_Data.setMaximum_Viewing_Length(value);
						System.out.println("Maximum_Viewing_Length:" + app_Data.getMaximum_Viewing_Length());
						
						app.setMaximum_Viewing_Length(value);
						break;
					case "Suggested_Price" :
						app_Data.setSuggested_Price(value);
						System.out.println("Suggested_Price:" + app_Data.getSuggested_Price());
						
						app.setSuggested_Price(Integer.parseInt(value));
						break;
					case "Propagation_Priority" :
						app_Data.setPropagation_Priority(value);
						System.out.println("Propagation_Priority:" + app_Data.getPropagation_Priority());
						
						app.setPropagation_Priority(Integer.parseInt(value));
						break;
					case "LongTail_YN" :
						app_Data.setLongTail_YN(value);
						System.out.println("LongTail_YN:" + app_Data.getLongTail_YN());
						
						app.setLongTail_YN(value);
						break;
					case "Studio_Royalty_Percent" :
						app_Data.setStudio_Royalty_Percent(value);
						System.out.println("Studio_Royalty_Percent:" + app_Data.getStudio_Royalty_Percent());
						
						app.setStudio_Royalty_Percent(value);
						break;
					case "Studio_Royalty_Minimum" :
						app_Data.setStudio_Royalty_Minimum(value);
						System.out.println("Studio_Royalty_Minimum:" + app_Data.getStudio_Royalty_Minimum());
						
						app.setStudio_Royalty_Minimum(value);
						break;
					case "Studio_Royalty_Flat_Rate" :
						app_Data.setStudio_Royalty_Flat_Rate(value);
						System.out.println("Studio_Royalty_Flat_Rate:" + app_Data.getStudio_Royalty_Flat_Rate());
						
						app.setStudio_Royalty_Flat_Rate(value);
						break;
					case "Director" :
						app_Data.setDirector(value);
						System.out.println("Director:" + app_Data.getDirector());
						
						app.setDirector(value);
						break;
					case "Writer_Display" :
						app_Data.setWriter_Display(value);
						System.out.println("Writer_Display:" + app_Data.getWriter_Display());
						
						app.setWriter_Display(value);
						break;
					case "Producer" :
						app_Data.setProducer(value);
						System.out.println("Producer:" + app_Data.getProducer());
						
						app.setProducer(value);
						break;
					case "Home_Video_Window" :
						app_Data.setHome_Video_Window(value);
						System.out.println("Home_Video_Window:" + app_Data.getHome_Video_Window());
						
						app.setHome_Video_Window(value);
						break;
					case "Contract_Name" :
						app_Data.setContract_Name(value);
						System.out.println("Contract_Name:" + app_Data.getContract_Name());
						
						app.setContract_Name(value);
						break;
					case "Distributor_Royalty_Percent" :
						app_Data.setDistributor_Royalty_Percent(value);
						System.out.println("Distributor_Royalty_Percent:" + app_Data.getDistributor_Royalty_Percent());
						
						app.setDistributor_Royalty_Percent(value);
						break;
					case "Distributor_Royalty_Minimum" :
						app_Data.setDistributor_Royalty_Minimum(value);
						System.out.println("Distributor_Royalty_Minimum:" + app_Data.getDistributor_Royalty_Minimum());
						
						app.setDistributor_Royalty_Minimum(value);
						break;
					case "Distributor_Royalty_Flat_Rate" :
						app_Data.setDistributor_Royalty_Flat_Rate(value);
						System.out.println("Distributor_Royalty_Flat_Rate:" + app_Data.getDistributor_Royalty_Flat_Rate());
						
						app.setDistributor_Royalty_Flat_Rate(value);
						break;
					case "Distributor_Name" :
						app_Data.setDistributor_Name(value);
						System.out.println("Distributor_Name:" + app_Data.getDistributor_Name());
						
						app.setDistributor_Name(value);
						break;
					case "Encryption" :
						app_Data.setEncryption(value);
						System.out.println("Encryption:" + app_Data.getEncryption());
						
						app.setEncryption(value);
						break;
					case "Audio_Type" :
						app_Data.setAudio_Type(value);
						System.out.println("Audio_Type:" + app_Data.getAudio_Type());
						
						app.setAudio_Type(value);
						break;
					case "Screen_Format" :
						app_Data.setScreen_Format(value);
						System.out.println("Screen_Format:" + app_Data.getScreen_Format());
						
						app.setScreen_Format(value);
						break;
					case "Languages" :
						app_Data.setLanguages(value);
						System.out.println("Languages:" + app_Data.getLanguages());
						
						app.setLanguages(value);
						break;
					case "Subtitle_Languages" :
						app_Data.setSubtitle_Languages(value);
						System.out.println("Subtitle_Languages:" + app_Data.getSubtitle_Languages());
						
						app.setSubtitle_Languages(value);
						break;
					case "Dubbed_Languages" :
						app_Data.setDubbed_Languages(value);
						System.out.println("Dubbed_Languages:" + app_Data.getDubbed_Languages());
						
						app.setDubbed_Languages(value);
						break;
					case "Copy_Protection" :
						app_Data.setCopy_Protection(value);
						System.out.println("Copy_Protection:" + app_Data.getCopy_Protection());
						
						app.setCopy_Protection(value);
						break;
					case "Copy_Protection_Verbose" :
						app_Data.setCopy_Protection_Verbose(value);
						System.out.println("Copy_Protection_Verbose:" + app_Data.getCopy_Protection_Verbose());
						
						app.setCopy_Protection_Verbose(value);
						break;
					case "Analog_Protection_System" :
						app_Data.setAnalog_Protection_System(value);
						System.out.println("Analog_Protection_System:" + app_Data.getAnalog_Protection_System());
						
						app.setAnalog_Protection_System(value);
						break;
					case "Encryption_Mode_Indicator" :
						app_Data.setEncryption_Mode_Indicator(value);
						System.out.println("Encryption_Mode_Indicator:" + app_Data.getEncryption_Mode_Indicator());
						
						app.setEncryption_Mode_Indicator(value);
						break;
					case "Constrained_Image_Trigger" :
						app_Data.setConstrained_Image_Trigger(value);
						System.out.println("Constrained_Image_Trigger:" + app_Data.getConstrained_Image_Trigger());
						
						app.setConstrained_Image_Trigger(value);
						break;
					case "CGMS_A" :
						app_Data.setCGMS_A(value);
						System.out.println("CGMS_A:" + app_Data.getCGMS_A());
						
						app.setcGMS_A(value);
						break;
					case "HDContent" :
						app_Data.setHDContent(value);
						System.out.println("HDContent:" + app_Data.getHDContent());
						
						app.sethDContent(value);
						break;
					case "Content_FileSize" :
						content_FileSizeValue.add(value);
						app_Data.setContent_FileSize(content_FileSizeValue);
						System.out.println("Content_FileSize:" + app_Data.getContent_FileSize());
						break;
					case "Content_CheckSum" :
						content_CheckSumValue.add(value);
						app_Data.setContent_CheckSum(content_CheckSumValue);
						System.out.println("content_CheckSumValue:" + app_Data.getContent_CheckSum());
						break;
					case "Image_Aspect_Ratio" :
						app_Data.setImage_Aspect_Ratio(value);
						System.out.println("Image_Aspect_Ratio:" + app_Data.getImage_Aspect_Ratio());
						
						app.setImage_Aspect_Ratio(value);
						break;
				}	
			}
		}
		return app_Data;
	}
	
	@Override
	public  String getTagValue(String tag, Element element) {
		String list = element.getAttributeNode(tag).getTextContent();
		return list;
	}
}
