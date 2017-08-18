package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.JdbcDao;
import dbconnection.ConnectionMaker;
import dbconnection.JdbcContext;
import dbconnection.StatementStrategy;
import table.Publication_Table;
import table.Table;

public class PublicationJdbcDaoImpl implements JdbcDao {
	private ConnectionMaker connectionMaker;
	private JdbcContext jdbcContext;
	private Publication_Table publication_Table;
	
	public void setConnectionMaker(ConnectionMaker connectionMaker) {
		this.jdbcContext = new JdbcContext();
		this.jdbcContext.setConnectionMaker(connectionMaker);
		this.connectionMaker = connectionMaker;
	}
	
	@Override
	public int getCount() {
		
		try (Connection con = connectionMaker.makeConnection();
				 PreparedStatement pstmt = con.prepareStatement("select count(publication_Id) from publication_table");
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
	public int getFind_PK() {
		
		try (Connection con = connectionMaker.makeConnection();
				 PreparedStatement pstmt = con.prepareStatement("select max(publication_Id) from publication_table");
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
		if(table instanceof Publication_Table) {
			publication_Table = (Publication_Table) table;
		}
		this.jdbcContext.workWithStatementStrategy(
				new StatementStrategy() {
					public PreparedStatement makePreparedStatement(Connection con) throws SQLException {
						PreparedStatement pstmt = con.prepareStatement(
						"insert into publication_table("
													 + "package_Id, publication_Right) "
													 + "values(?,?)") ;
						
						pstmt.setInt(1, publication_Table.getPackage_Id());
						pstmt.setString(2, publication_Table.getPublication_Right());
						return pstmt;
				 }
			  }
		   );

	}

	@Override
	public void deleteAll() {
		this.jdbcContext.executeSql("delete from publication_table");
	}
}
