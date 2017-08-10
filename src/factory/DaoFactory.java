package factory;

import dao.AmsDao;
import dbconnection.ConnectionMaker;
import dbconnection.JdbcConnectionMaker;
import dbconnection.JdbcContext;

public class DaoFactory {
	private AmsDao dao = new AmsDao();
	
	public AmsDao amsDao() {
		dao.setConnectionMaker(connectionMaker());
		return dao;
	}
	
	private ConnectionMaker connectionMaker() {
		return new JdbcConnectionMaker();
	}
}
