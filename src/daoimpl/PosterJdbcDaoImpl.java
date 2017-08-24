package daoimpl;

import dao.JdbcDao;
import dbconnection.ConnectionMaker;
import dbconnection.JdbcContext;
import table.Poster_Table;
import table.Table;

public class PosterJdbcDaoImpl implements JdbcDao {
	private ConnectionMaker connectionMaker;
	private JdbcContext jdbcContext;
	private Poster_Table poster_Table;
	
	public void setConnectionMaker(ConnectionMaker connectionMaker) {
		this.jdbcContext = new JdbcContext();
		this.jdbcContext.setConnectionMaker(connectionMaker);
		this.connectionMaker = connectionMaker;
	}
	
	@Override
	public int getFindMaxPK() {
		return this.jdbcContext.queryForInt("select max(poster_Id) from poster_table");
	}
	
	@Override
	public int getCount() {
		return this.jdbcContext.queryForInt("select count(poster_Id) from poster_table");
	}
	
	@Override
	public void add(Table table) {
		if(table instanceof Poster_Table) {
			poster_Table = (Poster_Table)table;
		}
		this.jdbcContext.insert("insert into poster_table("
											+ "package_Id, description, asset_Id, asset_Class, type, "
											+ "content_FileSize, content_CheckSum, value, image_Aspect_Ratio, "
											+ "publication_Right, advisories) "
											+ "values(?,?,?,?,?,?,?,?,?,?,?)", 
											poster_Table.getPackage_Id(), poster_Table.getDescription(), 
											poster_Table.getAsset_Id(), poster_Table.getAsset_Class(), 
											poster_Table.getType(), poster_Table.getContent_FileSize(), 
											poster_Table.getContent_CheckSum(), poster_Table.getValue(), 
											poster_Table.getImage_Aspect_Ratio(), poster_Table.getPublication_Right(), 
											poster_Table.getAdvisories());
	}
	
	@Override
	public void deleteAll() {
		this.jdbcContext.executeSql("delete from poster_table");
	}
}
