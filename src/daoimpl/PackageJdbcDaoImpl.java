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
	public int getFind_PK() {
		
		try (Connection con = connectionMaker.makeConnection();
				 PreparedStatement pstmt = con.prepareStatement("select max(package_Id) from package_table");
				 ResultSet resultSet = pstmt.executeQuery()) {
				
				resultSet.next();
				return resultSet.getInt(1);
			} catch(SQLException sqle) {
				System.out.println("SQLException!!"+sqle.getMessage());
				sqle.printStackTrace();
				return -1;
			} catch(ClassNotFoundException ce) {
				System.out.println("ClassNotFoundException!!"+ce.getMessage());
				ce.printStackTrace();
				return -1;
			}
	}
	
	@Override
	public int getCount() {
		
		try (Connection con = connectionMaker.makeConnection();
				 PreparedStatement pstmt = con.prepareStatement("select count(package_Id) from package_table");
				 ResultSet resultSet = pstmt.executeQuery()) {
				
				resultSet.next();
				return resultSet.getInt(1);
			} catch(SQLException sqle) {
				System.out.println("SQLException!!"+sqle.getMessage());
				sqle.printStackTrace();
				return -1;
			} catch(ClassNotFoundException ce) {
				System.out.println("ClassNotFoundException!!"+ce.getMessage());
				ce.printStackTrace();
				return -1;
			}
	}

	@Override
	public void add(Table table) {
		if(table instanceof Package_Table) {
			package_Table = (Package_Table)table;
		}
		this.jdbcContext.workWithStatementStrategy(
				new StatementStrategy() {
					public PreparedStatement makePreparedStatement(Connection con) throws SQLException {
						PreparedStatement pstmt = con.prepareStatement(
				"insert into package_table("
										+ "asset_Class, asset_Id, asset_Name, creation_Date, description, "
										+ "product, provider, provider_ID, verb, version_Major, version_Minor, "
										+ "provider_Content_Tier, metadata_Spec_Version, publication_Right) "
										+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)") ;
						
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
						pstmt.setString(14, package_Table.getPublication_Right());
						return pstmt;
				 }
			  }
		   );
	}

	@Override
	public void deleteAll() {
		this.jdbcContext.executeSql("delete from package_table");
	}
}
