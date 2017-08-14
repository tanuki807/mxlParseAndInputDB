package dao;

import java.sql.SQLException;

import domain.App;
import readnode.App_DataTag;

public interface AppDao {
	int getCount() throws ClassNotFoundException, SQLException;	
	//void add(App_Data app_Data) throws ClassNotFoundException, SQLException;
	void add(App app);
	void deleteAll();
}
