package factory;

import dao.XmlDao;
import dbconnection.ConnectionMaker;
import dbconnection.JdbcConnectionMaker;

public class DaoFactory {
	
	public XmlDao xmlDao() {
		return new XmlDao(connectionMaker());
	}
	
	private ConnectionMaker connectionMaker() {
		return new JdbcConnectionMaker();
	}
}
