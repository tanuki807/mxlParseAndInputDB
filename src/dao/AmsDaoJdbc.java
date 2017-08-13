package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbconnection.ConnectionMaker;
import dbconnection.JdbcContext;
import dbconnection.StatementStrategy;
import domain.Ams;


public class AmsDaoJdbc implements AmsDao{
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
			ps = c.prepareStatement("select count(*) from ams");
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
	public void deleteAll() throws ClassNotFoundException, SQLException {
		this.jdbcContext.executeSql("delete from ams");
	}
	
	@Override
	public void add(Ams ams) throws ClassNotFoundException, SQLException {
		this.jdbcContext.workWithStatementStrategy(
			new StatementStrategy() {
				public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
					PreparedStatement ps = c.prepareStatement(
					"insert into ams("
									+ "title, asset_Id, asset_Class, description, asset_Name, creation_Date, product, "
									+ "provider, provider_ID, version_Major, version_Minor, verb) "
									+ "values(?,?,?,?,?,?,?,?,?,?,?,?)") ;
					
					ps.setString(1, ams.getTitle());
					ps.setString(2, ams.getAsset_Id());
					ps.setString(3, ams.getAsset_Class());
					ps.setString(4, ams.getDescription());
					ps.setString(5, ams.getAsset_Name());
					ps.setString(6, ams.getCreation_Date());
					ps.setString(7, ams.getProduct());
					ps.setString(8, ams.getProvider());
					ps.setString(9, ams.getProvider_ID());
					ps.setInt(10, ams.getVersion_Major());
					ps.setInt(11, ams.getVersion_Minor());
					ps.setString(12, ams.getVerb());
					return ps;
			 }
		  }
	   );
	}
	
	
	public Ams get(String asset_Class) throws ClassNotFoundException, SQLException {
		Connection c = connectionMaker.makeConnection();
		PreparedStatement ps = c.prepareStatement("select *from asm where Asset_Class = ?");
		
		ps.setString(1, asset_Class);
		ResultSet rs = ps.executeQuery();
		rs.next();
		
		Ams ams = new Ams();
		ams.setAsset_Class(rs.getString("asset_Class"));
		ams.setAsset_Id(rs.getString("asset_Id"));
		ams.setAsset_Name(rs.getString("asset_Name"));
		ams.setCreation_Date(rs.getString("creation_Date"));
		ams.setDescription(rs.getString("description"));
		ams.setProduct(rs.getString("product"));
		ams.setProvider(rs.getString("provider"));
		ams.setProvider_ID(rs.getString("provider_Id"));
		ams.setVerb(rs.getString("verb"));
		ams.setVersion_Major(rs.getInt("version_Major"));
		ams.setVersion_Minor(rs.getInt("version_Minor"));
		return ams;
	}
}
