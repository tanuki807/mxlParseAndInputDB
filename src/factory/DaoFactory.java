package factory;

import dao.ActorsDao;
import dao.AmsDao;
import dao.AppDao;
import dao.ContentDao;
import dao.JdbcDao;
import daoimpl.ActorsDaoJdbc;
import daoimpl.AmsDaoJdbc;
import daoimpl.AppDaoJdbc;
import daoimpl.ContentDaoJdbc;
import daoimpl.MovieJdbc;
import daoimpl.PackageJdbcDaoImpl;
import daoimpl.PosterJdbc;
import daoimpl.SubTableJdbc;
import daoimpl.TitleJdbc;
import dbconnection.ConnectionMaker;
import dbconnection.JdbcConnectionMaker;


public class DaoFactory {
	private AppDaoJdbc	appDao = new AppDaoJdbc();
	private AmsDaoJdbc amsDao = new AmsDaoJdbc();
	private ActorsDaoJdbc actorDao = new ActorsDaoJdbc();
	private ContentDaoJdbc contentDao = new ContentDaoJdbc();
	private PackageJdbcDaoImpl packageJdbcDao = new PackageJdbcDaoImpl();
	private SubTableJdbc subTableJdbc = new SubTableJdbc();
	private TitleJdbc titleJdbc = new TitleJdbc();
	private MovieJdbc movieJdbc = new MovieJdbc();
	private PosterJdbc posterJdbc = new PosterJdbc(); 
	
	public PosterJdbc posterJdbc() {
		posterJdbc.setConnectionMaker(connectionMaker());
		return posterJdbc;
	}
	
	public MovieJdbc movieJdbc() {
		movieJdbc.setConnectionMaker(connectionMaker());
		return movieJdbc;
	}
	
	public TitleJdbc titleJdbc() {
		titleJdbc.setConnectionMaker(connectionMaker());
		return titleJdbc;
	}
	
	public SubTableJdbc subTableJdbc() {
		subTableJdbc.setConnectionMaker(connectionMaker());
		return subTableJdbc;
	}
	public PackageJdbcDaoImpl packageJdbcDao() {
		packageJdbcDao.setConnectionMaker(connectionMaker());
		return packageJdbcDao;
	}
	
	public ContentDao contentDao() {
		contentDao.setConnectionMaker(connectionMaker());
		return contentDao;
	}
	
	public ActorsDao actorDao() {
		actorDao.setConnectionMaker(connectionMaker());
		return actorDao;
	}
	
	public AmsDao amsDao() {
		amsDao.setConnectionMaker(connectionMaker());
		return amsDao;
	}
	
	public AppDao appDao() {
		appDao.setConnectionMaker(connectionMaker());
		return appDao;
	}
	
	private ConnectionMaker connectionMaker() {
		return new JdbcConnectionMaker();
	}
}
