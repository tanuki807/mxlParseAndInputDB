package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbconnection.ConnectionMaker;
import dbconnection.JdbcContext;
import dbconnection.StatementStrategy;
import domain.Duplicated_Ams;

public class Duplicated_AmsDaoJdbc implements Duplicated_AmsDao {
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
			ps = c.prepareStatement("select count(*) from duplicated_ams");
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
	public void add(Duplicated_Ams dupAms) {
		this.jdbcContext.workWithStatementStrategy(
				new StatementStrategy() {
					public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
						PreparedStatement ps = c.prepareStatement(
						"insert into duplicated_ams("
										+ "title, asset_Name, creation_Date, product, provider,"
										+ "provider_Id, version_Major, version_Minor, verb) "
										+ "values(?,?,?,?,?,?,?,?,?)") ;
						
						ps.setString(1, dupAms.getTitle());
						ps.setString(2, dupAms.getAsset_Name());
						ps.setString(3, dupAms.getCreation_Date());
						ps.setString(4, dupAms.getProduct());
						ps.setString(5, dupAms.getProvider());
						ps.setString(6, dupAms.getProvider_Id());
						ps.setInt(7, dupAms.getVersion_Major());
						ps.setInt(8, dupAms.getVersion_Minor());
						ps.setString(9, dupAms.getVerb());
						return ps;
				 }
			  }
		   );
	}

	@Override
	public void deleteAll() {
		this.jdbcContext.executeSql("delete from duplicated_ams");
	}
}
