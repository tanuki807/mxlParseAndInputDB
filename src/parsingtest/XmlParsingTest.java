package parsingtest;


import java.io.IOException;
import java.sql.SQLException;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.xml.sax.SAXException;

import dao.XmlDao;
import factory.DaoFactory;
import xmlparse.XmlParse;

public class XmlParsingTest {
	
	@Test
	public void add() throws SQLException, ClassNotFoundException, 
	ParserConfigurationException, SAXException, IOException {
		XmlParse parse = new XmlParse();
		XmlDao dao = new DaoFactory().xmlDao();
		
		dao.deleteAll();
		assertThat(dao.getCount(), is(0));
		
		parse.addFile();
		assertThat(dao.getCount(), is(44));
	}
	
	
//	public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException  {
//		XmlParse parse = new XmlParse();
//		parse.addFile();
//	}
}
