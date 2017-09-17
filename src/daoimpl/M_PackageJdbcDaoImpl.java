package daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import dao.JdbcDao;
import table.M_Package;
import table.Table;

public class M_PackageJdbcDaoImpl implements JdbcDao {
	private M_Package m_Package;
	private JdbcTemplate jdbcTemplate;
	private RowMapper<M_Package> m_PackageMapper = 
		new RowMapper<M_Package>() {
			@Override
			public M_Package mapRow(ResultSet rs, int rowNum) throws SQLException {
				M_Package m_Package = new M_Package();
				m_Package.setPackage_ID(rs.getInt("package_ID"));
				m_Package.setAsset_Name(rs.getString("asset_Name"));
				m_Package.setCreation_Date(rs.getString("creation_Date"));
				m_Package.setProduct(rs.getString("product"));
				m_Package.setProvider(rs.getString("provider"));
				m_Package.setProvider_ID(rs.getString("provider_ID"));
				m_Package.setVerb(rs.getString("verb"));
				m_Package.setVersion_Major(rs.getInt("version_Major"));
				m_Package.setVersion_Minor(rs.getInt("version_Minor"));
				return m_Package;
			}
		};
	
	
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public List<M_Package> getAll() {
		return this.jdbcTemplate.query("select package_ID, asset_Name, creation_Date, product, "
										+ "provider, provider_ID, verb, version_Major, version_Minor "
										+ "from m_package order by package_ID", m_PackageMapper);
	}
	
	public M_Package get(int package_ID) {
		return this.jdbcTemplate.queryForObject("select package_ID, asset_Name, creation_Date, product, "
													 + "provider, provider_ID, verb, version_Major, version_Minor "
											  + "from m_package where package_ID = ?", 
				new Object[] {package_ID}, m_PackageMapper);
	}
	
	@Override
	public int getCount() {
		return this.jdbcTemplate.queryForInt("select count(package_ID) from m_package");
	}

	@Override
	public int getFindMaxPK() {
		return this.jdbcTemplate.queryForInt("select max(package_ID) from m_package");
	}

	@Override
	public void add(Table table) throws SQLException {
		if(table instanceof M_Package) {
			m_Package = (M_Package)table;
		}
		this.jdbcTemplate.update("insert into m_package("
										+ "asset_Name, creation_Date, product, provider, "
										+ "provider_ID, verb, version_Major, version_Minor) "
										+ "values(?,?,?,?,?,?,?,?)", 
										m_Package.getAsset_Name(), m_Package.getCreation_Date(), m_Package.getProduct(), 
										m_Package.getProvider(), m_Package.getProvider_ID(), m_Package.getVerb(), 
										m_Package.getVersion_Major(), m_Package.getVersion_Minor());
	}

	@Override
	public void deleteAll() {
		this.jdbcTemplate.update("delete from m_package");
	}

}
