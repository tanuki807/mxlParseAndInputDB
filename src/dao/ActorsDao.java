package dao;

import java.sql.SQLException;

import readnode.App_Data;

public interface ActorsDao {
	int getCount() throws ClassNotFoundException, SQLException;	
	void add(String actor, String title) throws ClassNotFoundException, SQLException;
	void deleteAll() throws ClassNotFoundException, SQLException;
}
