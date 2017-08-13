package dao;

import java.sql.SQLException;
import readnode.App_Data;

public interface AppDao {
	int getCount() throws ClassNotFoundException, SQLException;	
	void add(App_Data app_Data) throws ClassNotFoundException, SQLException;
	void deleteAll() throws ClassNotFoundException, SQLException;
}
