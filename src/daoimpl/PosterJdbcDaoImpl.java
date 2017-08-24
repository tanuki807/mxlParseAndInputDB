package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.JdbcDao;
import dbconnection.ConnectionMaker;
import dbconnection.JdbcContext;
import dbconnection.StatementStrategy;
import table.Movie_Table;
import table.Poster_Table;
import table.Table;

public class PosterJdbcDaoImpl implements JdbcDao {
	private ConnectionMaker connectionMaker;
	private JdbcContext jdbcContext;
	private Poster_Table poster_Table;
	
	public void setConnectionMaker(ConnectionMaker connectionMaker) {
		this.jdbcContext = new JdbcContext();
		this.jdbcContext.setConnectionMaker(connectionMaker);
		this.connectionMaker = connectionMaker;
	}
	
	@Override
	public int getFindMaxPK() {
		return this.jdbcContext.queryForInt("select max(poster_Id) from poster_table");
	}
	
	@Override
	public int getCount() {
		return this.jdbcContext.queryForInt("select count(poster_Id) from poster_table");
	}
	
	@Override
	public void add(Table table) {
		if(table instanceof Poster_Table) {
			poster_Table = (Poster_Table)table;
		}
		this.jdbcContext.workWithStatementStrategy(
			new StatementStrategy() {
				public PreparedStatement makePreparedStatement(Connection con) throws SQLException {
					PreparedStatement pstmt = con.prepareStatement(
					"insert into poster_table("
											+ "package_Id, description, asset_Id, asset_Class, type, "
											+ "content_FileSize, content_CheckSum, value, image_Aspect_Ratio, "
											+ "publication_Right, advisories) "
											+ "values(?,?,?,?,?,?,?,?,?,?,?)") ;
					
					pstmt.setInt(1, poster_Table.getPackage_Id());
					pstmt.setString(2, poster_Table.getDescription());
					pstmt.setString(3, poster_Table.getAsset_Id());
					pstmt.setString(4, poster_Table.getAsset_Class());
					pstmt.setString(5, poster_Table.getType());
					pstmt.setString(6, poster_Table.getContent_FileSize());
					pstmt.setString(7, poster_Table.getContent_CheckSum());
					pstmt.setString(8, poster_Table.getValue());
					pstmt.setString(9, poster_Table.getImage_Aspect_Ratio());
					pstmt.setString(10, poster_Table.getPublication_Right());
					pstmt.setString(11, poster_Table.getAdvisories());
					return pstmt;
			 }
		  }
	   );
	}
	
	@Override
	public void deleteAll() {
		this.jdbcContext.executeSql("delete from poster_table");
	}
}
