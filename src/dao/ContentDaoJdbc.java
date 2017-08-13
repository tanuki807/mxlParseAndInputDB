package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbconnection.ConnectionMaker;
import dbconnection.JdbcContext;
import dbconnection.StatementStrategy;

public class ContentDaoJdbc implements ContentDao {
	private ConnectionMaker connectionMaker;
	private JdbcContext jdbcContext;
	
	public void setConnectionMaker(ConnectionMaker connectionMaker) {
		this.jdbcContext = new JdbcContext();
		this.jdbcContext.setConnectionMaker(connectionMaker);
		this.connectionMaker = connectionMaker;
	}
	
	@Override
	public int getCount() throws ClassNotFoundException, SQLException {
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			c = connectionMaker.makeConnection();
			ps = c.prepareStatement("select count(*) from content");
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

	@Override
	public void add(String title, String advisories, String content_FileSize, String content_CheckSum, String value) 
			throws ClassNotFoundException, SQLException {
		this.jdbcContext.workWithStatementStrategy(
				new StatementStrategy() {
					public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
						PreparedStatement ps = c.prepareStatement(
						"insert into content("
										+ "title, advisories, content_FileSize, content_CheckSum, value) "
										+ "values(?,?,?,?,?)") ;
						
						ps.setString(1, title);
						ps.setString(2, advisories);
						ps.setString(3, content_FileSize);
						ps.setString(4, content_CheckSum);
						ps.setString(5, value);
						return ps;
				 }
			  }
		   );
	}

	@Override
	public void deleteAll() throws ClassNotFoundException, SQLException {
		this.jdbcContext.executeSql("delete from content");
	}
}
