package daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import dao.JdbcDao;
import table.Ams;
import table.App_Data;
import table.Table;

public class App_DataJdbcDaoImpl implements JdbcDao {
	private App_Data app_Data;
	private JdbcTemplate jdbcTemplate;
	private RowMapper<App_Data> appMapper = 
			new RowMapper<App_Data>() {
				@Override
				public App_Data mapRow(ResultSet rs, int rowNum) throws SQLException {
					App_Data app_Data = new App_Data();
					app_Data.setPackage_ID(rs.getInt("package_ID"));
					app_Data.setProvider_Content_Tier(rs.getString("provider_Content_Tier"));
					app_Data.setMetadata_Spec_Version(rs.getString("metadata_Spec_Version"));
					app_Data.setTitle(rs.getString("title"));
					app_Data.setTitle_Brief(rs.getString("title_Brief"));
					app_Data.setCategory(rs.getString("category"));
					app_Data.setRating(rs.getString("rating"));
					app_Data.setSummary_Short(rs.getString("summary_Short"));
					app_Data.setRun_Time(rs.getString("run_Time"));
					app_Data.setDisplay_Run_Time(rs.getString("display_Run_Time"));
					app_Data.setProvider_QA_Contact(rs.getString("provider_QA_Contact"));
					app_Data.setBilling_Id(rs.getString("billing_ID"));
					app_Data.setLicensing_Window_Start(rs.getString("licensing_Window_Start"));
					app_Data.setLicensing_Window_End(rs.getString("licensing_Window_End"));
					app_Data.setPreview_Period(rs.getInt("preview_Period"));
					app_Data.setTitle_Sort_Name(rs.getString("title_Sort_Name"));
					app_Data.setEpisode_Name(rs.getString("episode_Name"));
					app_Data.setEpisode_Id("episode_Id");
					app_Data.setSummary_Medium(rs.getString("summary_Medium"));
					app_Data.setSummary_Long(rs.getString("summary_Long"));
					app_Data.setActors_Display(rs.getString("actors_Display"));
					app_Data.setChapter(rs.getString("chapter"));
					app_Data.setStudio_Name(rs.getString("studio_Name"));
					app_Data.setStudio(rs.getString("studio"));
					app_Data.setClosed_Captioning(rs.getString("closed_Captioning"));
					app_Data.setSeason_Premiere(rs.getString("season_Premiere"));
					app_Data.setSeason_Finale(rs.getString("season_Finale"));
					app_Data.setDisplay_As_New(rs.getString("display_As_New"));
					app_Data.setDisplay_As_Last_Chance(rs.getString("display_As_Last_Chance"));
					app_Data.setSubscriber_View_Limit(rs.getString("subscriber_View_Limit"));
					app_Data.setYear(rs.getString("year"));
					app_Data.setCountry_of_Origin(rs.getString("country_of_Origin"));
					app_Data.setGenre(rs.getString("genre"));
					app_Data.setMaximum_Viewing_Length(rs.getString("maximum_Viewing_Length"));
					app_Data.setSuggested_Price(rs.getInt("suggested_Price"));
					app_Data.setPropagation_Priority(rs.getInt("propagation_Priority"));
					app_Data.setLongTail_YN(rs.getString("longtail_YN"));
					app_Data.setStudio_Royalty_Percent(rs.getString("studio_Royalty_Percent"));
					app_Data.setStudio_Royalty_Minimum(rs.getString("studio_Royalty_Minimum"));
					app_Data.setStudio_Royalty_Flat_Rate(rs.getString("studio_Royalty_Flat_Rate"));
					app_Data.setDirector(rs.getString("director"));
					app_Data.setWriter_Display(rs.getString("writer_Display"));
					app_Data.setProducer(rs.getString("producer"));
					app_Data.setHome_Video_Window(rs.getString("home_Video_Window"));
					app_Data.setContract_Name(rs.getString("contract_Name"));
					app_Data.setDistributor_Royalty_Percent(rs.getString("distributor_Royalty_Percent"));
					app_Data.setDistributor_Royalty_Minimum(rs.getString("distributor_Royalty_Minimum"));
					app_Data.setDistributor_Royalty_Flat_Rate(rs.getString("distributor_Royalty_Flat_Rate"));
					app_Data.setDistributor_Name(rs.getString("distributor_Name"));
					app_Data.setEncryption(rs.getString("encryption"));
					app_Data.setAudio_Type(rs.getString("audio_Type"));
					app_Data.setScreen_Format(rs.getString("screen_Format"));
					app_Data.setLanguages(rs.getString("languages"));
					app_Data.setSubtitle_Languages(rs.getString("subtitle_Languages"));
					app_Data.setDubbed_Languages(rs.getString("dubbed_Languages"));
					app_Data.setCopy_Protection(rs.getString("copy_Protection"));
					app_Data.setCopy_Protection_Verbose(rs.getString("copy_Protection_Verbose"));
					app_Data.setAnalog_Protection_System(rs.getInt("analog_Protection_System"));
					app_Data.setEncryption_Mode_Indicator(rs.getInt("encryption_Mode_Indicator"));
					app_Data.setConstrained_Image_Trigger(rs.getInt("constrained_Image_Trigger"));
					app_Data.setCGMS_A(rs.getInt("cgms_A"));
					app_Data.setHDContent(rs.getString("hdContent"));
					app_Data.setImage_Aspect_Ratio(rs.getString("image_Aspect_Ratio"));
					return app_Data;
				}
			};
	
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public List<App_Data> getAll(int package_ID) {
		return this.jdbcTemplate.query("select package_ID, provider_Content_Tier, metadata_Spec_Version, "
				   + "title, title_Brief, category, rating, "
				   + "summary_Short, run_Time, display_Run_Time, provider_QA_Contact, "
				   + "billing_ID, licensing_Window_Start, licensing_Window_End, "
				   + "preview_Period, title_Sort_Name, episode_Name, episode_Id, "
				   + "summary_Medium, summary_Long, actors_Display, chapter, "
				   + "studio_Name, studio, closed_Captioning, season_Premiere, "
				   + "season_Finale, display_As_New, display_As_Last_Chance, "
				   + "subscriber_View_Limit, year, country_of_Origin, genre, "
				   + "maximum_Viewing_Length, suggested_Price, propagation_Priority, "
				   + "longTail_YN, studio_Royalty_Percent, studio_Royalty_Minimum, "
				   + "studio_Royalty_Flat_Rate, director, writer_Display, producer, "
				   + "home_Video_Window, contract_Name, distributor_Royalty_Percent, "
				   + "distributor_Royalty_Minimum, distributor_Royalty_Flat_Rate, "
				   + "distributor_Name, encryption, audio_Type, screen_Format, "
				   + "languages, subtitle_Languages, dubbed_Languages, copy_Protection, "
				   + "copy_Protection_Verbose, analog_Protection_System, "
				   + "encryption_Mode_Indicator, constrained_Image_Trigger, cgms_A, "
				   + "hdContent, image_Aspect_Ratio "
				 + "from app_data where package_ID = ?", 
				 new Object[] {package_ID}, appMapper);
	}
	
	@Override
	public App_Data get(int package_ID) {
		return this.jdbcTemplate.queryForObject("select package_ID, provider_Content_Tier, metadata_Spec_Version, "
												   + "title, title_Brief, category, rating, "
												   + "summary_Short, run_Time, display_Run_Time, provider_QA_Contact, "
												   + "billing_ID, licensing_Window_Start, licensing_Window_End, "
												   + "preview_Period, title_Sort_Name, episode_Name, episode_Id, "
												   + "summary_Medium, summary_Long, actors_Display, chapter, "
												   + "studio_Name, studio, closed_Captioning, season_Premiere, "
												   + "season_Finale, display_As_New, display_As_Last_Chance, "
												   + "subscriber_View_Limit, year, country_of_Origin, genre, "
												   + "maximum_Viewing_Length, suggested_Price, propagation_Priority, "
												   + "longTail_YN, studio_Royalty_Percent, studio_Royalty_Minimum, "
												   + "studio_Royalty_Flat_Rate, director, writer_Display, producer, "
												   + "home_Video_Window, contract_Name, distributor_Royalty_Percent, "
												   + "distributor_Royalty_Minimum, distributor_Royalty_Flat_Rate, "
												   + "distributor_Name, encryption, audio_Type, screen_Format, "
												   + "languages, subtitle_Languages, dubbed_Languages, copy_Protection, "
												   + "copy_Protection_Verbose, analog_Protection_System, "
												   + "encryption_Mode_Indicator, constrained_Image_Trigger, cgms_A, "
												   + "hdContent, image_Aspect_Ratio "
												 + "from app_data where package_ID = ?", 
								new Object[] {package_ID}, appMapper);
	}
	
	@Override
	public int getCount() {
		return this.jdbcTemplate.queryForInt("select count(app_ID) from app_data");
	}

	@Override
	public int getFindMaxPK() {
		return this.jdbcTemplate.queryForInt("select max(app_ID) from app_data");
	}

	@Override
	public void add(Table table) throws SQLException {
		if(table instanceof App_Data) {
			app_Data = (App_Data)table;
		}
		this.jdbcTemplate.update("insert into app_data(package_ID, provider_Content_Tier, metadata_Spec_Version, "
												   + "title, title_Brief, category, rating, "
												   + "summary_Short, run_Time, display_Run_Time, provider_QA_Contact, "
												   + "billing_ID, licensing_Window_Start, licensing_Window_End, "
												   + "preview_Period, title_Sort_Name, episode_Name, episode_Id, "
												   + "summary_Medium, summary_Long, actors_Display, chapter, "
												   + "studio_Name, studio, closed_Captioning, season_Premiere, "
												   + "season_Finale, display_As_New, display_As_Last_Chance, "
												   + "subscriber_View_Limit, year, country_of_Origin, genre, "
												   + "maximum_Viewing_Length, suggested_Price, propagation_Priority, "
												   + "longTail_YN, studio_Royalty_Percent, studio_Royalty_Minimum, "
												   + "studio_Royalty_Flat_Rate, director, writer_Display, producer, "
												   + "home_Video_Window, contract_Name, distributor_Royalty_Percent, "
												   + "distributor_Royalty_Minimum, distributor_Royalty_Flat_Rate, "
												   + "distributor_Name, encryption, audio_Type, screen_Format, "
												   + "languages, subtitle_Languages, dubbed_Languages, copy_Protection, "
												   + "copy_Protection_Verbose, analog_Protection_System, "
												   + "encryption_Mode_Indicator, constrained_Image_Trigger, cgms_A, "
												   + "hdContent, image_Aspect_Ratio) "
												   + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
												   + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
												   + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", 
								 app_Data.getPackage_ID(), app_Data.getProvider_Content_Tier(), app_Data.getMetadata_Spec_Version(), 
								 app_Data.getTitle(), app_Data.getTitle_Brief(), app_Data.getCategory(), app_Data.getRating(), 
								 app_Data.getSummary_Short(), app_Data.getRun_Time(), app_Data.getDisplay_Run_Time(), 
								 app_Data.getProvider_QA_Contact(), app_Data.getBilling_Id(), app_Data.getLicensing_Window_Start(), 
								 app_Data.getLicensing_Window_End(), app_Data.getPreview_Period(), app_Data.getTitle_Sort_Name(), 
								 app_Data.getEpisode_Name(), app_Data.getEpisode_Id(), app_Data.getSummary_Medium(), 
								 app_Data.getSummary_Long(), app_Data.getActors_Display(), app_Data.getChapter(), 
								 app_Data.getStudio_Name(), app_Data.getStudio(), app_Data.getClosed_Captioning(), 
								 app_Data.getSeason_Premiere(), app_Data.getSeason_Finale(), app_Data.getDisplay_As_New(), 
								 app_Data.getDisplay_As_Last_Chance(), app_Data.getSubscriber_View_Limit(), app_Data.getYear(), 
								 app_Data.getCountry_of_Origin(), app_Data.getGenre(), app_Data.getMaximum_Viewing_Length(), 
								 app_Data.getSuggested_Price(), app_Data.getPropagation_Priority(), app_Data.getLongTail_YN(), 
								 app_Data.getStudio_Royalty_Percent(), app_Data.getStudio_Royalty_Minimum(), app_Data.getStudio_Royalty_Flat_Rate(), 
								 app_Data.getDirector(), app_Data.getWriter_Display(), app_Data.getProducer(), 
								 app_Data.getHome_Video_Window(), app_Data.getContract_Name(), app_Data.getDistributor_Royalty_Percent(), 
								 app_Data.getDistributor_Royalty_Minimum(), app_Data.getDistributor_Royalty_Flat_Rate(), 
								 app_Data.getDistributor_Name(), app_Data.getEncryption(), app_Data.getAudio_Type(), 
								 app_Data.getScreen_Format(), app_Data.getLanguages(), app_Data.getSubtitle_Languages(), 
								 app_Data.getDubbed_Languages(), app_Data.getCopy_Protection(), app_Data.getCopy_Protection_Verbose(), 
								 app_Data.getAnalog_Protection_System(), app_Data.getEncryption_Mode_Indicator(), 
								 app_Data.getConstrained_Image_Trigger(), app_Data.getCGMS_A(), app_Data.getHDContent(), 
								 app_Data.getImage_Aspect_Ratio());
	}

	@Override
	public void deleteAll() throws SQLException {
		this.jdbcTemplate.update("delete from app_data");
	}
}
