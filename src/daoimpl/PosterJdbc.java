package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dbconnection.ConnectionMaker;
import dbconnection.JdbcContext;
import dbconnection.StatementStrategy;
import table.Movie_Table;
import table.Poster_Table;

public class PosterJdbc {
	ConnectionMaker connectionMaker;
	JdbcContext context;
	
	public void setConnectionMaker(ConnectionMaker connectionMaker) {
		this.context = new JdbcContext();
		this.context.setConnectionMaker(connectionMaker);
		this.connectionMaker = connectionMaker;
	}
	
	public void add(Poster_Table poster_Table) {
		this.context.workWithStatementStrategy(
			new StatementStrategy() {
				public PreparedStatement makePreparedStatement(Connection con) throws SQLException {
					PreparedStatement pstmt = con.prepareStatement(
					"insert into poster_table("
											+ "package_Id, description, asset_Id, asset_Class, type, "
											+ "content_FileSize, content_CheckSum, value, image_Aspect_Ratio) "
											+ "values(?,?,?,?,?,?,?,?,?)") ;
					
					pstmt.setInt(1, poster_Table.getPackage_Id());
					pstmt.setString(2, poster_Table.getDescription());
					pstmt.setString(3, poster_Table.getAsset_Id());
					pstmt.setString(4, poster_Table.getAsset_Class());
					pstmt.setString(5, poster_Table.getType());
					pstmt.setString(6, poster_Table.getContent_FileSize());
					pstmt.setString(7, poster_Table.getContent_CheckSum());
					pstmt.setString(8, poster_Table.getValue());
					pstmt.setString(9, poster_Table.getImage_Aspect_Ratio());
					return pstmt;
			 }
		  }
	   );
	}
}
