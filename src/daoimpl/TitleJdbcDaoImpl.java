package daoimpl;

import dao.JdbcDao;
import dbconnection.ConnectionMaker;
import dbconnection.JdbcContext;
import table.Table;
import table.Title_Table;

public class TitleJdbcDaoImpl implements JdbcDao{
	private ConnectionMaker connectionMaker;
	private JdbcContext jdbcContext;
	private Title_Table title_Table;
	
	public void setConnectionMaker(ConnectionMaker connectionMaker) {
		this.jdbcContext = new JdbcContext();
		this.jdbcContext.setConnectionMaker(connectionMaker);
		this.connectionMaker = connectionMaker;
	}
	
	@Override
	public int getFindMaxPK() {
		return this.jdbcContext.queryForInt("select max(title_Id) from title_table");
	}
	
	@Override
	public int getCount() {
		return this.jdbcContext.queryForInt("select count(title_Id) from title_table");
	}
	
	@Override
	public void add(Table table) {
		if(table instanceof Title_Table) {
			title_Table = (Title_Table)table;
		}
		this.jdbcContext.insert("insert into title_table("
										+ "package_Id, description, asset_Id, asset_Class, type, "
										+ "title, title_Brief, category, rating, summary_Short, run_Time, display_Run_Time, "
										+ "provider_QA_Contact, billing_ID, licensing_Window_Start, licensing_Window_End, "
										+ "preview_Period, title_Sort_Name, episode_Name, episode_Id, summary_Medium, "
										+ "summary_Long, actors_Display, chapter, studio_Name, studio, closed_Captioning, "
										+ "season_Premiere, season_Finale, display_As_New, display_As_Last_Chance, "
										+ "subscriber_View_Limit, year, country_of_Origin, genre, maximum_Viewing_Length, "
										+ "suggested_Price, propagation_Priority, longTail_YN, studio_Royalty_Percent, "
										+ "studio_Royalty_Minimum, studio_Royalty_Flat_Rate, director, writer_Display, producer, "
										+ "home_Video_Window, contract_Name, distributor_Royalty_Percent, distributor_Royalty_Minimum, "
										+ "distributor_Royalty_Flat_Rate, distributor_Name) "
										+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
										+ "		  ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", 
										title_Table.getPackage_Id(), title_Table.getDescription(), title_Table.getAsset_Id(), 
										title_Table.getAsset_Class(), title_Table.getType(), title_Table.getTitle(), 
										title_Table.getTitle_Brief(), title_Table.getCategory(), title_Table.getRating(), 
										title_Table.getSummary_Short(), title_Table.getRun_Time(), title_Table.getDisplay_Run_Time(), 
										title_Table.getProvider_QA_Contact(), title_Table.getBilling_Id(), 
										title_Table.getLicensing_Window_Start(), title_Table.getLicensing_Window_End(), 
										title_Table.getPreview_Period(), title_Table.getTitle_Sort_Name(), title_Table.getEpisode_Name(), 
										title_Table.getEpisode_Id(), title_Table.getSummary_Medium(), title_Table.getSummary_Long(), 
										title_Table.getActors_Display(), title_Table.getChapter(), title_Table.getStudio_Name(), 
										title_Table.getStudio(), title_Table.getClosed_Captioning(), title_Table.getSeason_Premiere(), 
										title_Table.getSeason_Finale(), title_Table.getDisplay_As_New(), title_Table.getDisplay_As_Last_Chance(), 
										title_Table.getSubscriber_View_Limit(), title_Table.getYear(), title_Table.getCountry_of_Origin(), 
										title_Table.getGenre(), title_Table.getMaximum_Viewing_Length(), title_Table.getSuggested_Price(), 
										title_Table.getPropagation_Priority(), title_Table.getLongTail_YN(), title_Table.getStudio_Royalty_Percent(), 
										title_Table.getStudio_Royalty_Minimum(), title_Table.getStudio_Royalty_Flat_Rate(), 
										title_Table.getDirector(), title_Table.getWriter_Display(), title_Table.getProducer(), 
										title_Table.getHome_Video_Window(), title_Table.getContract_Name(), title_Table.getDistributor_Royalty_Percent(), 
										title_Table.getDistributor_Royalty_Minimum(), title_Table.getDistributor_Royalty_Flat_Rate(), 
										title_Table.getDistributor_Name());					
	}
	
	@Override
	public void deleteAll() {
		this.jdbcContext.executeSql("delete from title_table");
	}
}
