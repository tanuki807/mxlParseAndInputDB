package dbconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcContext {
	private ConnectionMaker connectionMaker;
	
	public void setConnectionMaker(ConnectionMaker connectionMaker) {
		this.connectionMaker = connectionMaker;
	}
	
	public void executeSql(final String query) throws SQLException, ClassNotFoundException {
		workWithStatementStrategy(
				new StatementStrategy() {
					public PreparedStatement makePreparedStatement(Connection c) 
								throws SQLException {
							return c.prepareStatement(query);
					}
				}
		);
	}
	
		
	
	public void workWithStatementStrategy(StatementStrategy stmt) throws SQLException, ClassNotFoundException {
		Connection c = null;
		PreparedStatement ps = null;
		
		try {
			c = connectionMaker.makeConnection();
			ps = stmt.makePreparedStatement(c);
			ps.executeUpdate();
		} catch(SQLException e) {
			throw e;
		} finally {
			if(ps!=null) {
				try {
					ps.close();
				} catch(SQLException e) {}
			}
			
			if(c!=null) {
				try {
					c.close();
				} catch(SQLException e) {}
			}
		}
	}
}
