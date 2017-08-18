package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import dao.JdbcDao;
import dbconnection.ConnectionMaker;
import dbconnection.JdbcContext;
import dbconnection.StatementStrategy;
import table.Actors_Table;
import table.Table;

public class ActorsJdbcDaoImpl implements JdbcDao {
	private ConnectionMaker connectionMaker;
	private JdbcContext jdbcContext;
	private Actors_Table actors_Table;
	
	public void setConnectionMaker(ConnectionMaker connectionMaker) {
		this.jdbcContext = new JdbcContext();
		this.jdbcContext.setConnectionMaker(connectionMaker);
		this.connectionMaker = connectionMaker;
	}
	
	@Override
	public int getFind_PK() {
		
		try (Connection con = connectionMaker.makeConnection();
			 PreparedStatement pstmt = con.prepareStatement("select max(actor_Id) from actors_table");
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
				 PreparedStatement pstmt = con.prepareStatement("select count(actor_Id) from actors_table");
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
		if(table instanceof Actors_Table) {
			actors_Table = (Actors_Table)table;
		}
		this.jdbcContext.workWithStatementStrategy(
				new StatementStrategy() {
					public PreparedStatement makePreparedStatement(Connection con) throws SQLException {
						PreparedStatement pstmt = con.prepareStatement(
						"insert into actors_table("
												+ "package_Id, actor) "
												+ "values(?,?)") ;
						
						pstmt.setInt(1, actors_Table.getPackage_Id());
						pstmt.setString(2, actors_Table.getActor());
						return pstmt;
				 }
			  }
		   );
	}

	@Override
	public void deleteAll() {
		this.jdbcContext.executeSql("delete from actors_table");
	}
}
