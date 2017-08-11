package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbconnection.ConnectionMaker;
import dbconnection.JdbcContext;
import dbconnection.StatementStrategy;
import domain.Package_Table;


public class PackageDao {
	private ConnectionMaker connectionMaker;
	private JdbcContext jdbcContext;
	Package_Table pac;
	
	public void setConnectionMaker(ConnectionMaker connectionMaker) {
		this.jdbcContext = new JdbcContext();
		this.jdbcContext.setConnectionMaker(connectionMaker);
		this.connectionMaker = connectionMaker;
	}
	
	public int getCount() throws ClassNotFoundException, SQLException {
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			c = connectionMaker.makeConnection();
			ps = c.prepareStatement("select count(*) from package_table");
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
	
	public void deleteAll() throws ClassNotFoundException, SQLException {
		this.jdbcContext.executeSql("delete from package_table");
	}
	
	public void add(final Package_Table pac) throws ClassNotFoundException, SQLException {
		
		this.jdbcContext.workWithStatementStrategy(
			new StatementStrategy() {
				public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
					PreparedStatement ps = c.prepareStatement(
					"insert into package_table("
									+ "asset_Id, asset_Name, asset_Class, creation_Date, description, product, "
									+ "provider, provider_Id, verb, version_Major, version_Minor, "
									+ "provider_Content_Tier, metadata_Spec_Version) "
									+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?)") ;
					
					
					ps.setString(1, pac.getAsset_Id());
					ps.setString(2, pac.getAsset_Name());
					ps.setString(3, pac.getAsset_Class());
					ps.setString(4, pac.getCreation_Date());
					ps.setString(5, pac.getDescription());
					ps.setString(6, pac.getProduct());
					ps.setString(7, pac.getProvider());
					ps.setString(8, pac.getProvider_Id());
					ps.setString(9, pac.getVerb());
					ps.setInt(10, pac.getVersion_Major());
					ps.setInt(11, pac.getVersion_Minor());
					ps.setString(12, pac.getProvider_Content_Tier());
					ps.setString(13, pac.getMetadata_Spec_Version());
					return ps;
			 }
		  }
	   );
	}
	
	public Package_Table get(String asset_Id) throws ClassNotFoundException, SQLException {
		Connection c = connectionMaker.makeConnection();
		PreparedStatement ps = c.prepareStatement("select *from package_table where Asset_Id = ?");
		
		ps.setString(1, asset_Id);
		ResultSet rs = ps.executeQuery();
		rs.next();
		
		Package_Table table = new Package_Table();
		table.setAsset_Id(rs.getString("asset_Id"));
		table.setAsset_Name(rs.getString("asset_Name"));
		table.setAsset_Class(rs.getString("asset_Class"));
		table.setCreation_Date(rs.getString("creation_Date"));
		table.setDescription(rs.getString("description"));
		table.setProduct(rs.getString("product"));
		table.setProvider(rs.getString("provider"));
		table.setProvider_Id(rs.getString("provider_Id"));
		table.setVerb(rs.getString("verb"));
		table.setVersion_Major(rs.getInt("version_Major"));
		table.setVersion_Minor(rs.getInt("version_Minor"));
		table.setProvider_Content_Tier(rs.getString("provider_Content_Tier"));
		table.setMetadata_Spec_Version(rs.getString("metadata_Spec_Version"));
		return table;
	}
}
