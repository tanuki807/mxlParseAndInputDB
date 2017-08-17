package parsingtest;


import java.io.IOException;
import java.sql.SQLException;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.xml.sax.SAXException;

import dao.ActorsDao;
import dao.AmsDao;
import dao.AppDao;
import dao.ContentDao;
import factory.DaoFactory;
import xmlparse.XmlParse;

public class XmlParsingTest {
	
	@Test
	public void add() throws SQLException, ClassNotFoundException, ParserConfigurationException {
		XmlParse parse = new XmlParse();
		
		//PackageDao packageDao = new DaoFactory().packageDao();
		AppDao appDao = new DaoFactory().appDao();
		AmsDao amsDao = new DaoFactory().amsDao();
		ActorsDao actorDao = new DaoFactory().actorDao();
		ContentDao contentDao = new DaoFactory().contentDao();
		
		appDao.deleteAll();
		assertThat(appDao.getCount(), is(0));
		amsDao.deleteAll();
		assertThat(amsDao.getCount(), is(0));
		actorDao.deleteAll();
		assertThat(actorDao.getCount(), is(0));
		contentDao.deleteAll();
		assertThat(contentDao.getCount(), is(0));
		
		parse.addFile();
		//assertThat(appDao.getCount(), is(1));
		assertThat(appDao.getCount(), is(21643));
//		assertThat(amsDao.getCount(), is(12));
		
	}
}
