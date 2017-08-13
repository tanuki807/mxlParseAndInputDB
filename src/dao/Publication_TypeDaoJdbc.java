package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbconnection.ConnectionMaker;
import dbconnection.JdbcContext;
import dbconnection.StatementStrategy;

public class Publication_TypeDaoJdbc implements Publication_TypeDao {
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
			ps = c.prepareStatement("select count(*) from pub_type");
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
	public void add(String publication, String type, String title) throws ClassNotFoundException, SQLException {
		this.jdbcContext.workWithStatementStrategy(
				new StatementStrategy() {
					public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
						PreparedStatement ps = c.prepareStatement(
						"insert into pub_type("
										+ "publication_Right, type, title) "
										+ "values(?,?,?)") ;
						
						ps.setString(1, publication);
						ps.setString(2, type);
						ps.setString(3, title);
						return ps;
				 }
			  }
		   );
	}

	@Override
	public void deleteAll() throws ClassNotFoundException, SQLException {
		this.jdbcContext.executeSql("delete from pub_type");
	}
}
