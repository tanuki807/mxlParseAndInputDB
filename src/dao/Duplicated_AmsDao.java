package dao;

import java.sql.SQLException;

import domain.Duplicated_Ams;

public interface Duplicated_AmsDao {
	int getCount() throws ClassNotFoundException, SQLException;	
	void add(Duplicated_Ams dupAms); 
	void deleteAll();
}
