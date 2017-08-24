package daoimpl;

import dao.JdbcDao;
import dbconnection.ConnectionMaker;
import dbconnection.JdbcContext;
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
	public int getFindMaxPK() {
		return this.jdbcContext.queryForInt("select max(actor_Id) from actors_table");
	}
	
	@Override
	public int getCount() {
		return this.jdbcContext.queryForInt("select count(actor_Id) from actors_table");
	}

	@Override
	public void add(Table table) {
		if(table instanceof Actors_Table) {
			actors_Table = (Actors_Table)table;
		}
		this.jdbcContext.insert("insert into actors_table(package_Id, actor) values(?,?)", 
								 actors_Table.getPackage_Id(), actors_Table.getActor());
	}

	@Override
	public void deleteAll() {
		this.jdbcContext.executeSql("delete from actors_table");
	}
}
