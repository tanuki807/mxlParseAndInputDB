package dao;

import java.sql.SQLException;

public interface ContentDao {
	int getCount() throws ClassNotFoundException, SQLException;	
	void add(String title, String advisories, String content_FileSize, String content_CheckSum, String value);
	void deleteAll();
}
