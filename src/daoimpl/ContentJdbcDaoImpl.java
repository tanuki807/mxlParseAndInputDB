package daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import dao.JdbcDao;
import table.Content;
import table.Table;
import table.Type;


public class ContentJdbcDaoImpl implements JdbcDao {
	private Content content;
	private JdbcTemplate jdbcTemplate;
	private RowMapper<Content> contentMapper = 
			new RowMapper<Content>() {
				@Override
				public Content mapRow(ResultSet rs, int rowNum) throws SQLException {
					Content content = new Content();
					content.setPackage_ID(rs.getInt("package_ID"));
					content.setContent_FileSize(rs.getString("content_FileSize"));
					content.setContent_CheckSum(rs.getString("content_CheckSum"));
					content.setValue(rs.getString("value"));
					content.setAdvisories(rs.getString("advisories"));
					return content;
				}
			};
	
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public List<Content> getContent(int package_ID) {
		return this.jdbcTemplate.query("select package_ID, content_FileSize, content_CheckSum,"
									 + "value, advisories from content where package_ID = ?", 
						new Object[] {package_ID}, contentMapper);
	}
	
	@Override
	public Content get(int package_ID) {
		return this.jdbcTemplate.queryForObject("select package_ID, content_FileSize, content_CheckSum,"
				 							  + "value, advisories from content where package_ID = ?", 
						new Object[] {package_ID}, contentMapper);
	}
	
	@Override
	public int getCount() {
		return this.jdbcTemplate.queryForInt("select count(content_ID) from content");
	}

	@Override
	public int getFindMaxPK() {
		return this.jdbcTemplate.queryForInt("select max(content_ID) from content");
	}

	@Override
	public void add(Table table) throws SQLException {
		if(table instanceof Content) {
			content = (Content)table;
		}
		this.jdbcTemplate.update("insert into content(package_ID, content_FileSize, content_CheckSum, "
												+ "value, advisories) "
												+ "values(?,?,?,?,?)", 
								 content.getPackage_ID(), content.getContent_FileSize(), content.getContent_CheckSum(), 
								 content.getValue(), content.getAdvisories());
	}

	@Override
	public void deleteAll() throws SQLException {
		this.jdbcTemplate.update("delete from content");
	}
}
