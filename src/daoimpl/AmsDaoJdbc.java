package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.AmsDao;
import dbconnection.ConnectionMaker;
import dbconnection.JdbcContext;
import dbconnection.StatementStrategy;
import readnode.AmsTag;


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
	public void deleteAll() {
		this.jdbcContext.executeSql("delete from ams");
	}
	
	@Override
	public void add(String title, String asset_Id, String asset_Class, String description) {
		this.jdbcContext.workWithStatementStrategy(
			new StatementStrategy() {
				public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
					PreparedStatement ps = c.prepareStatement(
					"insert into ams("
									+ "title, asset_Id, asset_Class, description) "
									+ "values(?,?,?,?)") ;
					
					ps.setString(1, title);
					ps.setString(2, asset_Id);
					ps.setString(3, asset_Class);
					ps.setString(4, description);
					return ps;
			 }
		  }
	   );
	}
	
	
	public AmsTag get(String asset_Class) throws ClassNotFoundException, SQLException {
		Connection c = connectionMaker.makeConnection();
		PreparedStatement ps = c.prepareStatement("select *from asm where Asset_Class = ?");
		
		ps.setString(1, asset_Class);
		ResultSet rs = ps.executeQuery();
		rs.next();
		
		AmsTag amsTag = new AmsTag();
		amsTag.setAsset_Class(rs.getString("asset_Class"));
		amsTag.setAsset_Id(rs.getString("asset_Id"));
		amsTag.setAsset_Name(rs.getString("asset_Name"));
		amsTag.setCreation_Date(rs.getString("creation_Date"));
		amsTag.setDescription(rs.getString("description"));
		amsTag.setProduct(rs.getString("product"));
		amsTag.setProvider(rs.getString("provider"));
		amsTag.setProvider_ID(rs.getString("provider_Id"));
		amsTag.setVerb(rs.getString("verb"));
		amsTag.setVersion_Major(rs.getInt("version_Major"));
		amsTag.setVersion_Minor(rs.getInt("version_Minor"));
		return amsTag;
	}
}
