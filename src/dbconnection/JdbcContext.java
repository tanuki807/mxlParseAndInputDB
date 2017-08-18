package dbconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcContext {
	private ConnectionMaker connectionMaker;
	
	public void setConnectionMaker(ConnectionMaker connectionMaker) {
		this.connectionMaker = connectionMaker;
	}
	
	public void executeSql(final String query) {
		workWithStatementStrategy(
				new StatementStrategy() {
					public PreparedStatement makePreparedStatement(Connection con) 
								throws SQLException {
							return con.prepareStatement(query);
					}
				}
		);
	}
	
		
	
	public void workWithStatementStrategy(StatementStrategy stmt) {
		
		try (Connection con = connectionMaker.makeConnection();
			 PreparedStatement pstmt = stmt.makePreparedStatement(con)) {
			
			pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("SQLException!!"+e.getMessage());
			System.exit(0);
		} catch(ClassNotFoundException ce) {
			ce.printStackTrace();
			System.out.println("ClassNotFoundException!!"+ce.getMessage());
			System.exit(0);
		}
	}
}
