package daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import dao.JdbcDao;
import table.Actor;
import table.Table;
import table.Type;

public class TypeJdbcDaoImpl implements JdbcDao {
	private Type type;
	private JdbcTemplate jdbcTemplate;
	private RowMapper<Type> typeMapper = 
			new RowMapper<Type>() {
				@Override
				public Type mapRow(ResultSet rs, int rowNum) throws SQLException {
					Type type = new Type();
					type.setPackage_ID(rs.getInt("package_ID"));
					type.setType(rs.getString("type"));
					type.setPublication_Right(rs.getString("publication_Right"));
					return type;
				}
			};
	
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public List<Type> getType(int package_ID) {
		return this.jdbcTemplate.query("select package_ID, type, publication_Right from type where package_ID = ?", 
						new Object[] {package_ID}, typeMapper);
	}
	
	@Override
	public Type get(int package_ID) {
		return this.jdbcTemplate.queryForObject("select package_ID, type, publication_Right from type where package_ID = ?", 
						new Object[] {package_ID}, typeMapper);
	}
	
	@Override
	public int getCount() {
		return this.jdbcTemplate.queryForInt("select count(type_ID) from type");
	}

	@Override
	public int getFindMaxPK() {
		return this.jdbcTemplate.queryForInt("select max(type_ID) from type");
	}

	@Override
	public void add(Table table) throws SQLException {
		if(table instanceof Type) {
			type = (Type)table;
		}
		this.jdbcTemplate.update("insert into type(package_ID, type, publication_Right) values(?,?,?)", 
								 type.getPackage_ID(), type.getType(), type.getPublication_Right());
	}

	@Override
	public void deleteAll() throws SQLException {
		this.jdbcTemplate.update("delete from type");
	}
}
