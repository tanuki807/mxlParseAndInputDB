package readnode;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.SystemPropertyUtils;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class AppReadNode implements ReadNode {
	App_Data app;
	List<String> publication_RightValue;
	List<String> typeValue;
	List<String> actorsValue;
	List<String> advisoriesValue;
	List<String> content_FileSizeValue;
	List<String> content_CheckSumValue;
	
	public AppReadNode() {
		app = new App_Data();
		publication_RightValue = new ArrayList();
		typeValue = new ArrayList();
		actorsValue = new ArrayList();
		advisoriesValue = new ArrayList();
		content_FileSizeValue = new ArrayList();
		content_CheckSumValue = new ArrayList();
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
						app.setProvider_Content_Tier(value);
						System.out.println("Provider_Content_Tier:" + app.getProvider_Content_Tier());
						//table.setProvider_Content_Tier(value);
						break;
					case "Metadata_Spec_Version" :
						app.setMetadata_Spec_Version(value);
						System.out.println("Metadata_Spec_Version:" + app.getMetadata_Spec_Version() );
						//table.setMetadata_Spec_Version(value);
						break;
					case "Publication_Right" :
						publication_RightValue.add(value);
						app.setPublication_Right(publication_RightValue);
						System.out.println("Publication_Right:" + app.getPublication_Right());
						break;
					case "Type" :
						typeValue.add(value);
						app.setType(typeValue);
						System.out.println("Type:" + app.getType());
						break;
					case "Title" :
						app.setTitle(value);
						System.out.println("Title:" + app.getTitle());
						break;
					case "Title_Brief" :
						app.setTitle_Brief(value);
						System.out.println("Title_Brief:" + app.getTitle_Brief());
						break;
					case "Category" :
						app.setCategory(value);
						System.out.println("Category:" + app.getCategory());
						break;
					case "Rating" :
						app.setRating(value);
						System.out.println("Rating:" + app.getRating());
						break;
					case "Summary_Short" :
						app.setSummary_Short(value);
						System.out.println("Summary_Short:" + app.getSummary_Short());
						break;
					case "Run_Time" :
						app.setRun_Time(value);
						System.out.println("Run_Time:" + app.getRun_Time());
						break;
					case "Display_Run_Time" :
						app.setDisplay_Run_Time(value);
						System.out.println("Display_Run_Time:" + app.getDisplay_Run_Time());
						break;
					case "Provider_QA_Contact" :
						app.setProvider_QA_Contact(value);
						System.out.println("Provider_QA_Contact:" + app.getProvider_QA_Contact());
						break;
					case "Billing_ID" :
						app.setBilling_ID(value);
						System.out.println("Billing_ID:" + app.getBilling_ID());
						break;
					case "Licensing_Window_Start" :
						app.setLicensing_Window_Start(value);
						System.out.println("Licensing_Window_Start:" + app.getLicensing_Window_Start());
						break;
					case "Licensing_Window_End" :
						app.setLicensing_Window_End(value);
						System.out.println("Licensing_Window_End:" + app.getLicensing_Window_End());
						break;
					case "Preview_Period" :
						app.setPreview_Period(value);
						System.out.println("Preview_Period:" + app.getPreview_Period());
						break;
					case "Title_Sort_Name" :
						app.setTitle_Sort_Name(value);
						System.out.println("Title_Sort_Name:" + app.getTitle_Sort_Name()); 
						break;
					case "Episode_Name" :
						app.setEpisode_Name(value);
						System.out.println("Episode_Name:" + app.getEpisode_Name());
						break;
					case "Episode_id" :
						app.setEpisode_id(value);
						System.out.println("Episode_id:" + app.getEpisode_id());
						break;
					case "Summary_Medium" :
						app.setSummary_Medium(value);
						System.out.println("Summary_Medium:" + app.getSummary_Medium());
						break;
					case "Summary_Long" :
						app.setSummary_Long(value);
						System.out.println("Summary_Long:" + app.getSummary_Long());
						break;
					case "Actors" :
						actorsValue.add(value);
						app.setActors(actorsValue);
						System.out.println("Actors:" + app.getActors());
						break;
					case "Actors_Display" :
						app.setActors_Display(value);
						System.out.println("Actors_Display:" + app.getActors_Display());
						break;
					case "Chapter" :
						app.setChapter(value);
						System.out.println("Chapter:" + app.getChapter());
						break;
					case "Studio_Name" :
						app.setStudio_Name(value);
						System.out.println("Studio_Name:" + app.getStudio_Name());
						break;
					case "Studio" :
						app.setStudio(value);
						System.out.println("Studio:" + app.getStudio());
						break;
					case "Advisories" :
						advisoriesValue.add(value);
						app.setAdvisories(advisoriesValue);
						System.out.println("Advisories:" + app.getAdvisories());
						break;
					case "Closed_Captioning" :
						app.setClosed_Captioning(value);
						System.out.println("Closed_Captioning:" + app.getClosed_Captioning());
						break;
					case "Season_Premiere" :
						app.setSeason_Premiere(value);
						System.out.println("Season_Premiere:" + app.getSeason_Premiere());
						break;
					case "Season_Finale" :
						app.setSeason_Finale(value);
						System.out.println("Season_Finale:" + app.getSeason_Finale());
						break;
					case "Display_As_New" :
						app.setDisplay_As_New(value);
						System.out.println("Display_As_New:" + app.getDisplay_As_New());
						break;
					case "Display_As_Last_Chance" :
						app.setDisplay_As_Last_Chance(value);
						System.out.println("Display_As_Last_Chance:" + app.getDisplay_As_Last_Chance());
						break;
					case "Subscriber_View_Limit" :
						app.setSubscriber_View_Limit(value);
						System.out.println("Subscriber_View_Limit:" + app.getSubscriber_View_Limit());
						break;
					case "Year" :
						app.setYear(value);
						System.out.println("Year:" + app.getYear());
						break;
					case "Country_of_Origin" :
						app.setCountry_of_Origin(value);
						System.out.println("Country_of_Origin:" + app.getCountry_of_Origin());
						break;
					case "Genre" :
						app.setGenre(value);
						System.out.println("Genre:" + app.getGenre());
						break;
					case "Maximum_Viewing_Length" :
						app.setMaximum_Viewing_Length(value);
						System.out.println("Maximum_Viewing_Length:" + app.getMaximum_Viewing_Length());
						break;
					case "Suggested_Price" :
						app.setSuggested_Price(value);
						System.out.println("Suggested_Price:" + app.getSuggested_Price());
						break;
					case "Propagation_Priority" :
						app.setPropagation_Priority(value);
						System.out.println("Propagation_Priority:" + app.getPropagation_Priority());
						break;
					case "LongTail_YN" :
						app.setLongTail_YN(value);
						System.out.println("LongTail_YN:" + app.getLongTail_YN());
						break;
					case "Studio_Royalty_Percent" :
						app.setStudio_Royalty_Percent(value);
						System.out.println("Studio_Royalty_Percent:" + app.getStudio_Royalty_Percent());
						break;
					case "Studio_Royalty_Minimum" :
						app.setStudio_Royalty_Minimum(value);
						System.out.println("Studio_Royalty_Minimum:" + app.getStudio_Royalty_Minimum());
						break;
					case "Studio_Royalty_Flat_Rate" :
						app.setStudio_Royalty_Flat_Rate(value);
						System.out.println("Studio_Royalty_Flat_Rate:" + app.getStudio_Royalty_Flat_Rate());
						break;
					case "Director" :
						app.setDirector(value);
						System.out.println("Director:" + app.getDirector());
						break;
					case "Writer_Display" :
						app.setWriter_Display(value);
						System.out.println("Writer_Display:" + app.getWriter_Display());
						break;
					case "Producer" :
						app.setProducer(value);
						System.out.println("Producer:" + app.getProducer());
						break;
					case "Home_Video_Window" :
						app.setHome_Video_Window(value);
						System.out.println("Home_Video_Window:" + app.getHome_Video_Window());
						break;
					case "Contract_Name" :
						app.setContract_Name(value);
						System.out.println("Contract_Name:" + app.getContract_Name());
						break;
					case "Distributor_Royalty_Percent" :
						app.setDistributor_Royalty_Percent(value);
						System.out.println("Distributor_Royalty_Percent:" + app.getDistributor_Royalty_Percent());
						break;
					case "Distributor_Royalty_Minimum" :
						app.setDistributor_Royalty_Minimum(value);
						System.out.println("Distributor_Royalty_Minimum:" + app.getDistributor_Royalty_Minimum());
						break;
					case "Distributor_Royalty_Flat_Rate" :
						app.setDistributor_Royalty_Flat_Rate(value);
						System.out.println("Distributor_Royalty_Flat_Rate:" + app.getDistributor_Royalty_Flat_Rate());
						break;
					case "Distributor_Name" :
						app.setDistributor_Name(value);
						System.out.println("Distributor_Name:" + app.getDistributor_Name());
						break;
					case "Encryption" :
						app.setEncryption(value);
						System.out.println("Encryption:" + app.getEncryption());
						break;
					case "Audio_Type" :
						app.setAudio_Type(value);
						System.out.println("Audio_Type:" + app.getAudio_Type());
						break;
					case "Screen_Format" :
						app.setScreen_Format(value);
						System.out.println("Screen_Format:" + app.getScreen_Format());
						break;
					case "Languages" :
						app.setLanguages(value);
						System.out.println("Languages:" + app.getLanguages());
						break;
					case "Subtitle_Languages" :
						app.setSubtitle_Languages(value);
						System.out.println("Subtitle_Languages:" + app.getSubtitle_Languages());
						break;
					case "Dubbed_Languages" :
						app.setDubbed_Languages(value);
						System.out.println("Dubbed_Languages:" + app.getDubbed_Languages());
						break;
					case "Copy_Protection" :
						app.setCopy_Protection(value);
						System.out.println("Copy_Protection:" + app.getCopy_Protection());
						break;
					case "Copy_Protection_Verbose" :
						app.setCopy_Protection_Verbose(value);
						System.out.println("Copy_Protection_Verbose:" + app.getCopy_Protection_Verbose());
						break;
					case "Analog_Protection_System" :
						app.setAnalog_Protection_System(value);
						System.out.println("Analog_Protection_System:" + app.getAnalog_Protection_System());
						break;
					case "Encryption_Mode_Indicator" :
						app.setEncryption_Mode_Indicator(value);
						System.out.println("Encryption_Mode_Indicator:" + app.getEncryption_Mode_Indicator());
						break;
					case "Constrained_Image_Trigger" :
						app.setConstrained_Image_Trigger(value);
						System.out.println("Constrained_Image_Trigger:" + app.getConstrained_Image_Trigger());
						break;
					case "CGMS_A" :
						app.setCGMS_A(value);
						System.out.println("CGMS_A:" + app.getCGMS_A());
						break;
					case "HDContent" :
						app.setHDContent(value);
						System.out.println("HDContent:" + app.getHDContent());
						break;
					case "Content_FileSize" :
						content_FileSizeValue.add(value);
						app.setContent_FileSize(content_FileSizeValue);
						System.out.println("Content_FileSize:" + app.getContent_FileSize());
						break;
					case "Content_CheckSum" :
						content_CheckSumValue.add(value);
						app.setContent_CheckSum(content_CheckSumValue);
						System.out.println("content_CheckSumValue:" + app.getContent_CheckSum());
						break;
					case "Image_Aspect_Ratio" :
						app.setImage_Aspect_Ratio(value);
						System.out.println("Image_Aspect_Ratio:" + app.getImage_Aspect_Ratio());
						break;
				}	
			}
		}
		return app;
	}
	
	@Override
	public  String getTagValue(String tag, Element element) {
		String list = element.getAttributeNode(tag).getTextContent();
		return list;
	}
}
