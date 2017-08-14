package dao;

import java.sql.SQLException;

import readnode.App_DataTag;

public interface ActorsDao {
	int getCount() throws ClassNotFoundException, SQLException;	
	void add(String title, String actor);
	void deleteAll();
}