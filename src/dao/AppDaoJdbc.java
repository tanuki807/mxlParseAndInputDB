package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbconnection.ConnectionMaker;
import dbconnection.JdbcContext;
import dbconnection.StatementStrategy;
import domain.App;
import readnode.App_DataTag;

public class AppDaoJdbc implements AppDao {
	ConnectionMaker connectionMaker;
	JdbcContext context;
	
	public void setConnectionMaker(ConnectionMaker connectionMaker) {
		this.context = new JdbcContext();
		this.context.setConnectionMaker(connectionMaker);
		this.connectionMaker = connectionMaker;
	}
	
	
	@Override
	public int getCount() throws ClassNotFoundException, SQLException {
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			c = connectionMaker.makeConnection();
			ps = c.prepareStatement("select count(*) from app_data");
			rs = ps.executeQuery();
			rs.next();
			return rs.getInt(1);
		} catch(SQLException e) {
			throw e;
		} finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch(SQLException e) {
					
				}
			}
			if(ps!=null) {
				try {
					ps.close();
				} catch(SQLException e) {
					
				}
			}
			if(c!=null) {
				try {
					c.close();
				} catch(SQLException e) {
					
				}
			}
		}
	}
	
	//public void add(App_Data app) throws ClassNotFoundException, SQLException {
	
	@Override
	public void add(App app) {
		this.context.workWithStatementStrategy(
				new StatementStrategy() {
					public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
						PreparedStatement ps = c.prepareStatement(
					"insert into app_data("
										+ "title, billing_ID, provider_Content_Tier, metadata_Spec_Version, title_Brief, category, "
										+ "rating, summary_Short, run_Time, display_Run_Time, provider_QA_Contact, "
										+ "licensing_Window_Start, licensing_Window_End, preview_Period, title_Sort_Name, "
										+ "episode_Name, episode_id, summary_Medium, summary_Long, actors_Display, chapter, "
										+ "studio_Name, studio, closed_Captioning, season_Premiere, season_Finale, display_As_New, "
										+ "display_As_Last_Chance, subscriber_View_Limit, year, country_of_Origin, genre, "
										+ "Maximum_Viewing_Length, suggested_Price, propagation_Priority, longTail_YN, "
										+ "studio_Royalty_Percent, studio_Royalty_Minimum, studio_Royalty_Flat_Rate, director, "
										+ "writer_Display, producer, home_Video_Window, contract_Name, distributor_Royalty_Percent, "
										+ "distributor_Royalty_Minimum, distributor_Royalty_Flat_Rate, distributor_Name, encryption, "
										+ "audio_Type, screen_Format, languages, subtitle_Languages, dubbed_Languages, copy_Protection, "
										+ "copy_Protection_Verbose, analog_Protection_System, encryption_Mode_Indicator, "
										+ "constrained_Image_Trigger, cGMS_A, hDContent, image_Aspect_Ratio) "
										+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
										+ "		  ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)") ;
						
						ps.setString(1, app.getTitle());
						ps.setString(2, app.getBilling_ID());
						ps.setString(3, app.getProvider_Content_Tier());
						ps.setString(4, app.getMetadata_Spec_Version());
						ps.setString(5, app.getTitle_Brief());
						ps.setString(6, app.getCategory());
						ps.setString(7, app.getRating());
						ps.setString(8, app.getSummary_Short());
						ps.setString(9, app.getRun_Time());
						ps.setString(10, app.getDisplay_Run_Time());
						ps.setString(11, app.getProvider_QA_Contact());
						ps.setString(12, app.getLicensing_Window_Start());
						ps.setString(13, app.getLicensing_Window_End());
						ps.setInt(14, app.getPreview_Period());
						ps.setString(15, app.getTitle_Sort_Name());
						ps.setString(16, app.getEpisode_Name());
						ps.setString(17, app.getEpisode_id());
						ps.setString(18, app.getSummary_Medium());
						ps.setString(19, app.getSummary_Long());
						ps.setString(20, app.getActors_Display());
						ps.setString(21, app.getChapter());
						ps.setString(22, app.getStudio_Name());
						ps.setString(23, app.getStudio());
						ps.setString(24, app.getClosed_Captioning());
						ps.setString(25, app.getSeason_Premiere());
						ps.setString(26, app.getSeason_Finale());
						ps.setString(27, app.getDisplay_As_New());
						ps.setString(28, app.getDisplay_As_Last_Chance());
						ps.setString(29, app.getSubscriber_View_Limit());
						ps.setString(30, app.getYear());
						ps.setString(31, app.getCountry_of_Origin());
						ps.setString(32, app.getGenre());
						ps.setString(33, app.getMaximum_Viewing_Length());
						ps.setInt(34, app.getSuggested_Price());
						ps.setInt(35, app.getPropagation_Priority());
						ps.setString(36, app.getLongTail_YN());
						ps.setString(37, app.getStudio_Royalty_Percent());
						ps.setString(38, app.getStudio_Royalty_Minimum());
						ps.setString(39, app.getStudio_Royalty_Flat_Rate());
						ps.setString(40, app.getDirector());
						ps.setString(41, app.getWriter_Display());
						ps.setString(42, app.getProducer());
						ps.setString(43, app.getHome_Video_Window());
						ps.setString(44, app.getContract_Name());
						ps.setString(45, app.getDistributor_Royalty_Percent());
						ps.setString(46, app.getDistributor_Royalty_Minimum());
						ps.setString(47, app.getDistributor_Royalty_Flat_Rate());
						ps.setString(48, app.getDistributor_Name());
						ps.setString(49, app.getEncryption());
						ps.setString(50, app.getAudio_Type());
						ps.setString(51, app.getScreen_Format());
						ps.setString(52, app.getLanguages());
						ps.setString(53, app.getSubtitle_Languages());
						ps.setString(54, app.getDubbed_Languages());
						ps.setString(55, app.getCopy_Protection());
						ps.setString(56, app.getCopy_Protection_Verbose());
						ps.setString(57, app.getAnalog_Protection_System());
						ps.setString(58, app.getEncryption_Mode_Indicator());
						ps.setString(59, app.getConstrained_Image_Trigger());
						ps.setString(60, app.getcGMS_A());
						ps.setString(61, app.gethDContent());
						ps.setString(62, app.getImage_Aspect_Ratio());
						return ps;	
				 }
			  }
		   );
	}

	@Override
	public void deleteAll() {
		this.context.executeSql("delete from app_data");

	}

}
