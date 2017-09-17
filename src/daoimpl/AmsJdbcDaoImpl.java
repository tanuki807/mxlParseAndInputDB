package daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import dao.JdbcDao;
import table.Ams;
import table.M_Package;
import table.Table;

public class AmsJdbcDaoImpl implements JdbcDao {
	private Ams ams;
	private JdbcTemplate jdbcTemplate;
	private RowMapper<Ams> amsMapper = 
			new RowMapper<Ams>() {
				@Override
				public Ams mapRow(ResultSet rs, int rowNum) throws SQLException {
					Ams ams = new Ams();
					ams.setAsset_Class(rs.getString("asset_Class"));
					ams.setAsset_ID(rs.getString("asset_ID"));
					ams.setDescription(rs.getString("description"));
					return ams;
				}
			};
	
	public List<Ams> getAms(int package_ID) {
		return this.jdbcTemplate.query("select asset_Class, asset_ID, description "
				  + "from ams where package_ID = ?", 
				  new Object[] {package_ID}, amsMapper);
	}
			
	@Override
	public Ams get(int package_ID) {
		return this.jdbcTemplate.queryForObject("select asset_Class, asset_ID, description "
											  + "from ams where package_ID = ?", 
					new Object[] {package_ID}, amsMapper);
	}
			
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public int getCount() {
		return this.jdbcTemplate.queryForInt("select count(ams_ID) from ams");
	}

	@Override
	public int getFindMaxPK() {
		return this.jdbcTemplate.queryForInt("select max(ams_ID) from ams");
	}

	@Override
	public void add(Table table) throws SQLException {
		if(table instanceof Ams) {
			ams = (Ams)table;
		}
		this.jdbcTemplate.update("insert into ams("
										+ "package_ID, asset_Class, asset_ID, description) "
										+ "values(?,?,?,?)", 
										ams.getPackage_ID(), ams.getAsset_Class(), ams.getAsset_ID(), 
										ams.getDescription());
	}

	@Override
	public void deleteAll() throws SQLException {
		this.jdbcTemplate.update("delete from ams");
	}

}
