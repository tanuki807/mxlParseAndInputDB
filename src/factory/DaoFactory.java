package factory;

import dao.ActorsDao;
import dao.ActorsDaoJdbc;
import dao.AmsDao;
import dao.AmsDaoJdbc;
import dao.AppDao;
import dao.AppDaoJdbc;
import dao.ContentDao;
import dao.ContentDaoJdbc;
import dao.Publication_TypeDao;
import dao.Publication_TypeDaoJdbc;
import dbconnection.ConnectionMaker;
import dbconnection.JdbcConnectionMaker;


public class DaoFactory {
	private AppDaoJdbc	appDao = new AppDaoJdbc();
	private AmsDaoJdbc amsDao = new AmsDaoJdbc();
	private ActorsDaoJdbc actorDao = new ActorsDaoJdbc();
	private Publication_TypeDaoJdbc pubDao = new Publication_TypeDaoJdbc();
	private ContentDaoJdbc contentDao = new ContentDaoJdbc();
	
	public ContentDao contentDao() {
		contentDao.setConnectionMaker(connectionMaker());
		return contentDao;
	}
	
	public Publication_TypeDao pubDao() {
		pubDao.setConnectionMaker(connectionMaker());
		return pubDao;
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
