package dao;

import java.sql.SQLException;

import readnode.AmsTag;

public interface AmsDao {
	int getCount() throws ClassNotFoundException, SQLException;	
	void add(String title, String asset_Id, String asset_Class, String description);
	void deleteAll();
}
