package factory;

import dao.AmsDao;
import dao.PackageDao;
import dbconnection.ConnectionMaker;
import dbconnection.JdbcConnectionMaker;
import dbconnection.JdbcContext;

public class DaoFactory {
	private AmsDao dao = new AmsDao();
	private PackageDao packageDao = new PackageDao();
	
	public PackageDao packageDao() {
		packageDao.setConnectionMaker(connectionMaker());
		return packageDao;
	}
	
//	public AmsDao amsDao() {
//		dao.setConnectionMaker(connectionMaker());
//		return dao;
//	}
	
	private ConnectionMaker connectionMaker() {
		return new JdbcConnectionMaker();
	}
}
