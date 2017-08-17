package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.ActorsDao;
import dbconnection.ConnectionMaker;
import dbconnection.JdbcContext;
import dbconnection.StatementStrategy;
import readnode.App_DataTag;

public class ActorsDaoJdbc implements ActorsDao {
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
			ps = c.prepareStatement("select count(*) from actors");
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
	public void add(int package_Id, String actor) {
		this.jdbcContext.workWithStatementStrategy(
				new StatementStrategy() {
					public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
						PreparedStatement ps = c.prepareStatement(
						"insert into actors("
										+ "package_Id, actors) "
										+ "values(?,?)") ;
						
						ps.setInt(1, package_Id);
						ps.setString(2, actor);
						return ps;
				 }
			  }
		   );
	}

	@Override
	public void deleteAll() {
		this.jdbcContext.executeSql("delete from actors");
	}
}
