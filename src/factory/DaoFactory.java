package factory;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import dao.JdbcDao;
import daoimpl.ActorJdbcDaoImpl;
import daoimpl.AmsJdbcDaoImpl;
import daoimpl.App_DataJdbcDaoImpl;
import daoimpl.ContentJdbcDaoImpl;
import daoimpl.M_PackageJdbcDaoImpl;
import daoimpl.TypeJdbcDaoImpl;

public class DaoFactory {
	private M_PackageJdbcDaoImpl m_PackageJdbcDao = new M_PackageJdbcDaoImpl();
	private AmsJdbcDaoImpl amsJdbcDao = new AmsJdbcDaoImpl();
	private ActorJdbcDaoImpl actorJdbcDao = new ActorJdbcDaoImpl();
	private TypeJdbcDaoImpl typeJdbcDao = new TypeJdbcDaoImpl();
	private ContentJdbcDaoImpl contentJdbcDao = new ContentJdbcDaoImpl();
	private App_DataJdbcDaoImpl appJdbcDao = new App_DataJdbcDaoImpl();
	
	public JdbcDao appDao() {
		this.appJdbcDao.setDataSource(dataSource());
		return this.appJdbcDao;
	}
	
	public JdbcDao contentDao() {
		this.contentJdbcDao.setDataSource(dataSource());
		return this.contentJdbcDao;
	}
	
	public JdbcDao typeDao() {
		this.typeJdbcDao.setDataSource(dataSource());
		return this.typeJdbcDao;
	}
	
	public JdbcDao actorDao() {
		this.actorJdbcDao.setDataSource(dataSource());
		return this.actorJdbcDao;
	}
	
	public JdbcDao amsDao() {
		this.amsJdbcDao.setDataSource(dataSource());
		return this.amsJdbcDao;
	}
	
	public JdbcDao m_PackageDao() {
		this.m_PackageJdbcDao.setDataSource(dataSource());
		return this.m_PackageJdbcDao;
	}
	
	private DataSource dataSource() {
		SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
		dataSource.setDriverClass(com.mysql.jdbc.Driver.class);
		dataSource.setUrl("jdbc:mysql://localhost/xmlparse?useSSL=false");
		dataSource.setUsername("root");
		dataSource.setPassword("fuckingdba");
		
		return dataSource;
	}
}
