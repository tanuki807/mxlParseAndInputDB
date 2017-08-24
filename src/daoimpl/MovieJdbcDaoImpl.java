package daoimpl;

import dao.JdbcDao;
import dbconnection.ConnectionMaker;
import dbconnection.JdbcContext;
import table.Movie_Table;
import table.Table;

public class MovieJdbcDaoImpl implements JdbcDao {
	private ConnectionMaker connectionMaker;
	private JdbcContext jdbcContext;
	private Movie_Table movie_Table;
	
	public void setConnectionMaker(ConnectionMaker connectionMaker) {
		this.jdbcContext = new JdbcContext();
		this.jdbcContext.setConnectionMaker(connectionMaker);
		this.connectionMaker = connectionMaker;
	}
	
	
	@Override
	public int getFindMaxPK() {
		return this.jdbcContext.queryForInt("select max(movie_Id) from movies_table");
	}
	
	@Override
	public int getCount() {
		return this.jdbcContext.queryForInt("select count(movie_Id) from movie_table");
	}
	
	@Override
	public void add(Table table) {
		if(table instanceof Movie_Table) {
			movie_Table = (Movie_Table)table;
		}
		this.jdbcContext.insert("insert into movie_table("
											+ "package_Id, description, asset_Id, asset_Class, type, "
											+ "encryption, audio_Type, screen_Format, languages, "
											+ "subtitle_Languages, dubbed_Languages, copy_Protection, "
											+ "copy_Protection_Verbose, analog_Protection_System, "
											+ "encryption_Mode_Indicator, constrained_Image_Trigger, "
											+ "cgms_A, HDContent, content_FileSize, content_CheckSum, value, "
											+ "publication_Right, advisories) "
											+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", 
											movie_Table.getPackage_Id(), movie_Table.getDescription(), 
											movie_Table.getAsset_Id(), movie_Table.getAsset_Class(), 
											movie_Table.getType(), movie_Table.getEncryption(), 
											movie_Table.getAudio_Type(), movie_Table.getScreen_Format(), 
											movie_Table.getLanguages(), movie_Table.getSubtitle_Languages(), 
											movie_Table.getDubbed_Languages(), movie_Table.getCopy_Protection(), 
											movie_Table.getCopy_Protection_Verbose(), movie_Table.getAnalog_Protection_System(), 
											movie_Table.getEncryption_Mode_Indicator(), movie_Table.getConstrained_Image_Trigger(), 
											movie_Table.getCgms_A(), movie_Table.getHDContent(), movie_Table.getContent_FileSize(), 
											movie_Table.getContent_CheckSum(), movie_Table.getValue(), movie_Table.getPublication_Right(), 
											movie_Table.getAdvisories());
	}
	
	@Override
	public void deleteAll() {
		this.jdbcContext.executeSql("delete from movie_table");
	}
}
