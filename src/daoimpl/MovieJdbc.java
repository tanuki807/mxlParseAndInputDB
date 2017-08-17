package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dbconnection.ConnectionMaker;
import dbconnection.JdbcContext;
import dbconnection.StatementStrategy;
import table.Movie_Table;

public class MovieJdbc {
	ConnectionMaker connectionMaker;
	JdbcContext context;
	
	public void setConnectionMaker(ConnectionMaker connectionMaker) {
		this.context = new JdbcContext();
		this.context.setConnectionMaker(connectionMaker);
		this.connectionMaker = connectionMaker;
	}
	
	public void add(Movie_Table movie_Table) {
		this.context.workWithStatementStrategy(
			new StatementStrategy() {
				public PreparedStatement makePreparedStatement(Connection con) throws SQLException {
					PreparedStatement pstmt = con.prepareStatement(
					"insert into movie_table("
											+ "package_Id, description, asset_Id, asset_Class, type, "
											+ "encryption, audio_Type, screen_Format, languages, "
											+ "subtitle_Languages, dubbed_Languages, copy_Protection, "
											+ "copy_Protection_Verbose, analog_Protection_System, "
											+ "encryption_Mode_Indicator, constrained_Image_Trigger, "
											+ "cgms_A, HDContent, content_FileSize, content_CheckSum, value) "
											+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)") ;
					
					pstmt.setInt(1, movie_Table.getPackage_Id());
					pstmt.setString(2, movie_Table.getDescription());
					pstmt.setString(3, movie_Table.getAsset_Id());
					pstmt.setString(4, movie_Table.getAsset_Class());
					pstmt.setString(5, movie_Table.getType());
					pstmt.setString(6, movie_Table.getEncryption());
					pstmt.setString(7, movie_Table.getAudio_Type());
					pstmt.setString(8, movie_Table.getScreen_Format());
					pstmt.setString(9, movie_Table.getLanguages());
					pstmt.setString(10, movie_Table.getSubtitle_Languages());
					pstmt.setString(11, movie_Table.getDubbed_Languages());
					pstmt.setString(12, movie_Table.getCopy_Protection());
					pstmt.setString(13, movie_Table.getCopy_Protection_Verbose());
					pstmt.setInt(14, movie_Table.getAnalog_Protection_System());
					pstmt.setInt(15, movie_Table.getEncryption_Mode_Indicator());
					pstmt.setInt(16, movie_Table.getConstrained_Image_Trigger());
					pstmt.setInt(17, movie_Table.getCgms_A());
					pstmt.setString(18, movie_Table.getHDContent());
					pstmt.setString(19, movie_Table.getContent_FileSize());
					pstmt.setString(20, movie_Table.getContent_CheckSum());
					pstmt.setString(21, movie_Table.getValue());
					return pstmt;
			 }
		  }
	   );
	}
}
