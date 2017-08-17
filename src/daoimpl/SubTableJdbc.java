package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dbconnection.ConnectionMaker;
import dbconnection.JdbcContext;
import dbconnection.StatementStrategy;
import domain.App;


public class SubTableJdbc {
	ConnectionMaker connectionMaker;
	JdbcContext context;
	
	public void setConnectionMaker(ConnectionMaker connectionMaker) {
		this.context = new JdbcContext();
		this.context.setConnectionMaker(connectionMaker);
		this.connectionMaker = connectionMaker;
	}
	
//	public void add(Sub_Table sub_Table) {
//		this.context.workWithStatementStrategy(
//				new StatementStrategy() {
//					public PreparedStatement makePreparedStatement(Connection con) throws SQLException {
//						PreparedStatement pstmt = con.prepareStatement(
//					"insert into sub_table("
//										+ "package_Id, asset_Class, asset_Id, asset_Name, creation_Date, description, product, "
//										+ "provider, provider_ID, version_Major, version_Minor, publication_Right, type, "
//										+ "title, title_Brief, category, rating, summary_Short, run_Time, display_Run_Time, "
//										+ "provider_QA_Contact, billing_ID, licensing_Window_Start, licensing_Window_End, "
//										+ "preview_Period, title_Sort_Name, episode_Name, episode_Id, summary_Medium, "
//										+ "summary_Long, actors_Display, chapter, studio_Name, studio, advisories, closed_Captioning, "
//										+ "season_Premiere, season_Finale, display_As_New, display_As_Last_Chance, "
//										+ "subscriber_View_Limit, year, country_of_Origin, genre, maximum_Viewing_Length, "
//										+ "suggested_Price, propagation_Priority, longTail_YN, studio_Royalty_Percent, "
//										+ "studio_Royalty_Minimum, studio_Royalty_Flat_Rate, director, writer_Display, producer, "
//										+ "home_Video_Window, contract_Name, distributor_Royalty_Percent, distributor_Royalty_Minimum, "
//										+ "distributor_Royalty_Flat_Rate, distributor_Name, "
//										+ "encryption, audio_Type, screen_Format, languages, subtitle_Languages, dubbed_Languages, "
//										+ "copy_Protection, copy_Protection_Verbose, analog_Protection_System, encryption_Mode_Indicator, "
//										+ "constrained_Image_Trigger, cGMS_A, HDContent, content_FileSize, content_CheckSum, "
//										+ "value, image_Aspect_Ratio) "
//										+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
//										+ "		  ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
//										+ "		  ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)") ;
//						
//						pstmt.setInt(1, sub_Table.getPackage_Id());
//						pstmt.setString(2, sub_Table.getAsset_Class());
//						pstmt.setString(3, sub_Table.getAsset_Id());
//						pstmt.setString(4, sub_Table.getAsset_Name());
//						pstmt.setString(5, sub_Table.getCreation_Date());
//						pstmt.setString(6, sub_Table.getDescription());
//						pstmt.setString(7, sub_Table.getProduct());
//						pstmt.setString(8, sub_Table.getProvider());
//						pstmt.setString(9, sub_Table.getProvider_ID());
//						pstmt.setInt(10, sub_Table.getVersion_Major());
//						pstmt.setInt(11, sub_Table.getVersion_Minor());
//						pstmt.setString(12, sub_Table.getPublication_Right());
//						pstmt.setString(13, sub_Table.getType());
//						pstmt.setString(14, sub_Table.getTitle());
//						pstmt.setString(15, sub_Table.getTitle_Brief());
//						pstmt.setString(16, sub_Table.getCategory());
//						pstmt.setString(17, sub_Table.getRating());
//						pstmt.setString(18, sub_Table.getSummary_Short());
//						pstmt.setString(19, sub_Table.getRun_Time());
//						pstmt.setString(20, sub_Table.getDisplay_Run_Time());
//						pstmt.setString(21, sub_Table.getProvider_QA_Contact());
//						pstmt.setString(22, sub_Table.getBilling_Id());
//						pstmt.setString(23, sub_Table.getLicensing_Window_Start());
//						pstmt.setString(24, sub_Table.getLicensing_Window_End());
//						pstmt.setInt(25, sub_Table.getPreview_Period());
//						pstmt.setString(26, sub_Table.getTitle_Sort_Name());
//						pstmt.setString(27, sub_Table.getEpisode_Name());
//						pstmt.setString(28, sub_Table.getEpisode_Id());
//						pstmt.setString(29, sub_Table.getSummary_Medium());
//						pstmt.setString(30, sub_Table.getSummary_Long());
//						pstmt.setString(31, sub_Table.getActors_Display());
//						pstmt.setString(32, sub_Table.getChapter());
//						pstmt.setString(33, sub_Table.getStudio_Name());
//						pstmt.setString(34, sub_Table.getStudio());
//						pstmt.setString(35, sub_Table.getAdvisories());
//						pstmt.setString(36, sub_Table.getClosed_Captioning());
//						pstmt.setString(37, sub_Table.getSeason_Premiere());
//						pstmt.setString(38, sub_Table.getSeason_Finale());
//						pstmt.setString(39, sub_Table.getDisplay_As_New());
//						pstmt.setString(40, sub_Table.getDisplay_As_Last_Chance());
//						pstmt.setString(41, sub_Table.getSubscriber_View_Limit());
//						pstmt.setString(42, sub_Table.getYear());
//						pstmt.setString(43, sub_Table.getCountry_of_Origin());
//						pstmt.setString(44, sub_Table.getGenre());
//						pstmt.setString(45, sub_Table.getMaximum_Viewing_Length());
//						pstmt.setInt(46, sub_Table.getSuggested_Price());
//						pstmt.setInt(47, sub_Table.getPropagation_Priority());
//						pstmt.setString(48, sub_Table.getLongTail_YN());
//						pstmt.setString(49, sub_Table.getStudio_Royalty_Percent());
//						pstmt.setString(50, sub_Table.getStudio_Royalty_Minimum());
//						pstmt.setString(51, sub_Table.getStudio_Royalty_Flat_Rate());
//						pstmt.setString(52, sub_Table.getDirector());
//						pstmt.setString(53, sub_Table.getWriter_Display());
//						pstmt.setString(54, sub_Table.getProducer());
//						pstmt.setString(55, sub_Table.getHome_Video_Window());
//						pstmt.setString(56, sub_Table.getContract_Name());
//						pstmt.setString(57, sub_Table.getDistributor_Royalty_Percent());
//						pstmt.setString(58, sub_Table.getDistributor_Royalty_Minimum());
//						pstmt.setString(59, sub_Table.getDistributor_Royalty_Flat_Rate());
//						pstmt.setString(60, sub_Table.getDistributor_Name());
//						
//						pstmt.setString(61, sub_Table.getEncryption());
//						pstmt.setString(62, sub_Table.getAudio_Type());
//						pstmt.setString(63, sub_Table.getScreen_Format());
//						pstmt.setString(64, sub_Table.getLanguages());
//						pstmt.setString(65, sub_Table.getSubtitle_Languages());
//						pstmt.setString(66, sub_Table.getDubbed_Languages());
//						pstmt.setString(67, sub_Table.getCopy_Protection());
//						pstmt.setString(68, sub_Table.getCopy_Protection_Verbose());
//						pstmt.setString(69, sub_Table.getAnalog_Protection_System());
//						pstmt.setString(70, sub_Table.getEncryption_Mode_Indicator());
//						pstmt.setString(71, sub_Table.getConstrained_Image_Trigger());
//						pstmt.setString(72, sub_Table.getcGMS_A());
//						pstmt.setString(73, sub_Table.getHDContent());
//						pstmt.setString(74, sub_Table.getContent_FileSize());
//						pstmt.setString(75, sub_Table.getContent_CheckSum());
//						pstmt.setString(76, sub_Table.getValue());
//						pstmt.setString(77, sub_Table.getImage_Aspect_Ratio());
//						return pstmt;	
//				 }
//			  }
//		   );
//	}
}
