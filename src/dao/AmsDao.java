package dao;

import java.sql.SQLException;

import domain.Ams;

public interface AmsDao {
	int getCount() throws ClassNotFoundException, SQLException;	
	void add(Ams ams) throws ClassNotFoundException, SQLException;
	void deleteAll() throws ClassNotFoundException, SQLException;
}
