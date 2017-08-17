package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.JdbcDao;
import dbconnection.ConnectionMaker;
import dbconnection.JdbcContext;
import dbconnection.StatementStrategy;
import table.Package_Table;

public class PackageJdbcDaoImpl implements JdbcDao {
	private ConnectionMaker connectionMaker;
	private JdbcContext jdbcContext;
	
	public void setConnectionMaker(ConnectionMaker connectionMaker) {
		this.jdbcContext = new JdbcContext();
		this.jdbcContext.setConnectionMaker(connectionMaker);
		this.connectionMaker = connectionMaker;
	}
	
	public int getMaxPackage_Id() throws ClassNotFoundException, SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		try {
			con = connectionMaker.makeConnection();
			pstmt = con.prepareStatement("select max(package_Id) from package_table");
			resultSet = pstmt.executeQuery();
			resultSet.next();
			return resultSet.getInt(1);
		} catch(SQLException e) {
			throw e;
		} finally {
			if(resultSet!=null) {
				try {
					resultSet.close();
				} catch(SQLException e) {
					
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch(SQLException e) {
					
				}
			}
			if(con!=null) {
				try {
					con.close();
				} catch(SQLException e) {
					
				}
			}
		}
	}
	
	@Override
	public int getCount() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void add(Package_Table package_Table) {
		this.jdbcContext.workWithStatementStrategy(
				new StatementStrategy() {
					public PreparedStatement makePreparedStatement(Connection con) throws SQLException {
						PreparedStatement pstmt = con.prepareStatement(
				"insert into package_table("
										+ "asset_Class, asset_Id, asset_Name, creation_Date, description, "
										+ "product, provider, provider_ID, verb, version_Major, version_Minor, "
										+ "provider_Content_Tier, metadata_Spec_Version) "
										+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?)") ;
						
						pstmt.setString(1, package_Table.getAsset_Class());
						pstmt.setString(2, package_Table.getAsset_Id());
						pstmt.setString(3, package_Table.getAsset_Name());
						pstmt.setString(4, package_Table.getCreation_Date());
						pstmt.setString(5, package_Table.getDescription());
						pstmt.setString(6, package_Table.getProduct());
						pstmt.setString(7, package_Table.getProvider());
						pstmt.setString(8, package_Table.getProvider_Id());
						pstmt.setString(9, package_Table.getVerb());
						pstmt.setInt(10, package_Table.getVersion_Major());
						pstmt.setInt(11, package_Table.getVersion_Minor());
						pstmt.setString(12, package_Table.getProvider_Content_Tier());
						pstmt.setString(13, package_Table.getMetadata_Spec_Version());
						return pstmt;
				 }
			  }
		   );
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

}
