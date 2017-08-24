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
import table.Table;

public class PackageJdbcDaoImpl implements JdbcDao {
	private ConnectionMaker connectionMaker;
	private JdbcContext jdbcContext;
	private Package_Table package_Table;
	
	public void setConnectionMaker(ConnectionMaker connectionMaker) {
		this.jdbcContext = new JdbcContext();
		this.jdbcContext.setConnectionMaker(connectionMaker);
		this.connectionMaker = connectionMaker;
	}
	
	@Override
	public int getFindMaxPK() {
		return this.jdbcContext.queryForInt("select max(package_Id) from package_table");
	}
	
	@Override
	public int getCount() {
		return this.jdbcContext.queryForInt("select count(package_Id) from package_table");
	}

	@Override
	public void add(Table table) {
		if(table instanceof Package_Table) {
			package_Table = (Package_Table)table;
		}
		this.jdbcContext.insert("insert into package_table("
										+ "asset_Class, asset_Id, asset_Name, creation_Date, description, "
										+ "product, provider, provider_ID, verb, version_Major, version_Minor, "
										+ "provider_Content_Tier, metadata_Spec_Version, publication_Right) "
										+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)", 
										package_Table.getAsset_Class(), package_Table.getAsset_Id(), package_Table.getAsset_Name(), 
										package_Table.getCreation_Date(), package_Table.getDescription(), package_Table.getProduct(), 
										package_Table.getProvider(), package_Table.getProvider_Id(), package_Table.getVerb(), 
										package_Table.getVersion_Major(), package_Table.getVersion_Minor(), package_Table.getProvider_Content_Tier(), 
										package_Table.getMetadata_Spec_Version(), package_Table.getPublication_Right());
//		this.jdbcContext.workWithStatementStrategy(
//				new StatementStrategy() {
//					public PreparedStatement makePreparedStatement(Connection con) throws SQLException {
//						PreparedStatement pstmt = con.prepareStatement(
//				"insert into package_table("
//										+ "asset_Class, asset_Id, asset_Name, creation_Date, description, "
//										+ "product, provider, provider_ID, verb, version_Major, version_Minor, "
//										+ "provider_Content_Tier, metadata_Spec_Version, publication_Right) "
//										+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)") ;
//						
//						pstmt.setString(1, package_Table.getAsset_Class());
//						pstmt.setString(2, package_Table.getAsset_Id());
//						pstmt.setString(3, package_Table.getAsset_Name());
//						pstmt.setString(4, package_Table.getCreation_Date());
//						pstmt.setString(5, package_Table.getDescription());
//						pstmt.setString(6, package_Table.getProduct());
//						pstmt.setString(7, package_Table.getProvider());
//						pstmt.setString(8, package_Table.getProvider_Id());
//						pstmt.setString(9, package_Table.getVerb());
//						pstmt.setInt(10, package_Table.getVersion_Major());
//						pstmt.setInt(11, package_Table.getVersion_Minor());
//						pstmt.setString(12, package_Table.getProvider_Content_Tier());
//						pstmt.setString(13, package_Table.getMetadata_Spec_Version());
//						pstmt.setString(14, package_Table.getPublication_Right());
//						return pstmt;
//				 }
//			  }
//		   );
	}

	@Override
	public void deleteAll() {
		this.jdbcContext.executeSql("delete from package_table");
	}
}
