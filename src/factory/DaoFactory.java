package factory;

import dao.JdbcDao;
import daoimpl.ActorsJdbcDaoImpl;
import daoimpl.AdvisoriesJdbcDaoImpl;
import daoimpl.MovieJdbcDaoImpl;
import daoimpl.PackageJdbcDaoImpl;
import daoimpl.PosterJdbcDaoImpl;
import daoimpl.PublicationJdbcDaoImpl;
import daoimpl.TitleJdbcDaoImpl;
import dbconnection.ConnectionMaker;
import dbconnection.JdbcConnectionMaker;


public class DaoFactory {
	private ActorsJdbcDaoImpl actorDao = new ActorsJdbcDaoImpl();
	private PackageJdbcDaoImpl packageJdbcDaoImpl = new PackageJdbcDaoImpl();
	private TitleJdbcDaoImpl titleJdbcDaoImpl = new TitleJdbcDaoImpl();
	private MovieJdbcDaoImpl movieJdbcDaoImpl = new MovieJdbcDaoImpl();
	private PosterJdbcDaoImpl posterJdbcDaoImpl = new PosterJdbcDaoImpl(); 
	private PublicationJdbcDaoImpl publicationDaoImpl = new PublicationJdbcDaoImpl();
	private AdvisoriesJdbcDaoImpl advisoriesDaoImpl = new AdvisoriesJdbcDaoImpl();
	
	public JdbcDao advisoriesDao() {
		this.advisoriesDaoImpl.setConnectionMaker(connectionMaker());
		return this.advisoriesDaoImpl;
	}
	
	public JdbcDao publicationDao() {
		this.publicationDaoImpl.setConnectionMaker(connectionMaker());
		return this.publicationDaoImpl;
	}
	
	public JdbcDao posterJdbcDao() {
		this.posterJdbcDaoImpl.setConnectionMaker(connectionMaker());
		return this.posterJdbcDaoImpl;
	}
	
	public JdbcDao movieJdbcDao() {
		this.movieJdbcDaoImpl.setConnectionMaker(connectionMaker());
		return this.movieJdbcDaoImpl;
	}
	
	public JdbcDao titleJdbcDao() {
		this.titleJdbcDaoImpl.setConnectionMaker(connectionMaker());
		return this.titleJdbcDaoImpl;
	}
	
	public JdbcDao packageJdbcDao() {
		this.packageJdbcDaoImpl.setConnectionMaker(connectionMaker());
		return this.packageJdbcDaoImpl;
	}

	public JdbcDao actorDao() {
		this.actorDao.setConnectionMaker(connectionMaker());
		return this.actorDao;
	}
	
	private ConnectionMaker connectionMaker() {
		return new JdbcConnectionMaker();
	}
}
