package daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import dao.JdbcDao;
import table.Actor;
import table.Ams;
import table.Table;

public class ActorJdbcDaoImpl implements JdbcDao {
	private Actor actor;
	private JdbcTemplate jdbcTemplate;
	private RowMapper<Actor> actorMapper = 
			new RowMapper<Actor>() {
				@Override
				public Actor mapRow(ResultSet rs, int rowNum) throws SQLException {
					Actor actor = new Actor();
					actor.setPackage_ID(rs.getInt("package_ID"));
					actor.setActor(rs.getString("actor"));
					return actor;
				}
			};
	
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public List<Actor> getActor(int package_ID) {
		return this.jdbcTemplate.query("select package_ID, actor from actor where package_ID = ?", 
					new Object[] {package_ID}, actorMapper);
	}
	
	@Override
	public Actor get(int package_ID) {
		return this.jdbcTemplate.queryForObject("select package_ID, actor "
												+ "from actor where package_ID = ?", 
									new Object[] {package_ID}, actorMapper);
	}
	
	@Override
	public int getCount() {
		return this.jdbcTemplate.queryForInt("select count(actor_ID) from actor");
	}

	@Override
	public int getFindMaxPK() {
		return this.jdbcTemplate.queryForInt("select max(actor_ID) from actor");
	}

	@Override
	public void add(Table table) throws SQLException {
		if(table instanceof Actor) {
			actor = (Actor)table;
		}
		this.jdbcTemplate.update("insert into actor(package_ID, actor) values(?,?)", 
								 actor.getPackage_ID(), actor.getActor());
	}

	@Override
	public void deleteAll() throws SQLException {
		this.jdbcTemplate.update("delete from actor");
	}

}
