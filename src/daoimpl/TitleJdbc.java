package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dbconnection.ConnectionMaker;
import dbconnection.JdbcContext;
import dbconnection.StatementStrategy;
import table.Title_Table;

public class TitleJdbc {
	ConnectionMaker connectionMaker;
	JdbcContext context;
	
	public void setConnectionMaker(ConnectionMaker connectionMaker) {
		this.context = new JdbcContext();
		this.context.setConnectionMaker(connectionMaker);
		this.connectionMaker = connectionMaker;
	}
	
	public void add(Title_Table title_Table) {
		this.context.workWithStatementStrategy(
				new StatementStrategy() {
					public PreparedStatement makePreparedStatement(Connection con) throws SQLException {
						PreparedStatement pstmt = con.prepareStatement(
				"insert into title_table("
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
										+ "		  ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)") ;
						
						pstmt.setInt(1, title_Table.getPackage_Id());
						pstmt.setString(2, title_Table.getDescription());
						pstmt.setString(3, title_Table.getAsset_Id());
						pstmt.setString(4, title_Table.getAsset_Class());
						pstmt.setString(5, title_Table.getType());
						pstmt.setString(6, title_Table.getTitle());
						pstmt.setString(7, title_Table.getTitle_Brief());
						pstmt.setString(8, title_Table.getCategory());
						pstmt.setString(9, title_Table.getRating());
						pstmt.setString(10, title_Table.getSummary_Short());
						pstmt.setString(11, title_Table.getRun_Time());
						pstmt.setString(12, title_Table.getDisplay_Run_Time());
						pstmt.setString(13, title_Table.getProvider_QA_Contact());
						pstmt.setString(14, title_Table.getBilling_Id());
						pstmt.setString(15, title_Table.getLicensing_Window_Start());
						pstmt.setString(16, title_Table.getLicensing_Window_End());
						pstmt.setInt(17, title_Table.getPreview_Period());
						pstmt.setString(18, title_Table.getTitle_Sort_Name());
						pstmt.setString(19, title_Table.getEpisode_Name());
						pstmt.setString(20, title_Table.getEpisode_Id());
						pstmt.setString(21, title_Table.getSummary_Medium());
						pstmt.setString(22, title_Table.getSummary_Long());
						pstmt.setString(23, title_Table.getActors_Display());
						pstmt.setString(24, title_Table.getChapter());
						pstmt.setString(25, title_Table.getStudio_Name());
						pstmt.setString(26, title_Table.getStudio());
						pstmt.setString(27, title_Table.getClosed_Captioning());
						pstmt.setString(28, title_Table.getSeason_Premiere());
						pstmt.setString(29, title_Table.getSeason_Finale());
						pstmt.setString(30, title_Table.getDisplay_As_New());
						pstmt.setString(31, title_Table.getDisplay_As_Last_Chance());
						pstmt.setString(32, title_Table.getSubscriber_View_Limit());
						pstmt.setString(33, title_Table.getYear());
						pstmt.setString(34, title_Table.getCountry_of_Origin());
						pstmt.setString(35, title_Table.getGenre());
						pstmt.setString(36, title_Table.getMaximum_Viewing_Length());
						pstmt.setInt(37, title_Table.getSuggested_Price());
						pstmt.setInt(38, title_Table.getPropagation_Priority());
						pstmt.setString(39, title_Table.getLongTail_YN());
						pstmt.setString(40, title_Table.getStudio_Royalty_Percent());
						pstmt.setString(41, title_Table.getStudio_Royalty_Minimum());
						pstmt.setString(42, title_Table.getStudio_Royalty_Flat_Rate());
						pstmt.setString(43, title_Table.getDirector());
						pstmt.setString(44, title_Table.getWriter_Display());
						pstmt.setString(45, title_Table.getProducer());
						pstmt.setString(46, title_Table.getHome_Video_Window());
						pstmt.setString(47, title_Table.getContract_Name());
						pstmt.setString(48, title_Table.getDistributor_Royalty_Percent());
						pstmt.setString(49, title_Table.getDistributor_Royalty_Minimum());
						pstmt.setString(50, title_Table.getDistributor_Royalty_Flat_Rate());
						pstmt.setString(51, title_Table.getDistributor_Name());
						return pstmt;	
				 }
			  }
		   );
	}
}
