package parsingtest;


import java.io.IOException;
import java.sql.SQLException;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.xml.sax.SAXException;

import dao.AmsDao;
import dao.PackageDao;
import factory.DaoFactory;
import xmlparse.XmlParse;

public class XmlParsingTest {
	
	@Test
	public void add() throws SQLException, ClassNotFoundException, 
	ParserConfigurationException, SAXException, IOException {
		XmlParse parse = new XmlParse();
		parse.addFile();
		//PackageDao packageDao = new DaoFactory().packageDao();
		
		//packageDao.deleteAll();
		//assertThat(packageDao.getCount(), is(0));
		
		//parse.addFile();
		//assertThat(packageDao.getCount(), is(1));
		
	}
}
