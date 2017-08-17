package dao;

import java.sql.SQLException;

import readnode.App_DataTag;

public interface ActorsDao {
	int getCount() throws ClassNotFoundException, SQLException;	
	void add(int package_Id, String actor);
	void deleteAll();
}
