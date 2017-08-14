package dao;

import java.sql.SQLException;

public interface Publication_TypeDao {
	int getCount() throws ClassNotFoundException, SQLException;	
	void add(String publication, String type, String title);
	void deleteAll();
}
