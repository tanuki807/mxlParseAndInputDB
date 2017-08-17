package dao;

import java.sql.SQLException;

import table.Package_Table;

public interface JdbcDao {
	int getCount() throws ClassNotFoundException, SQLException;	
	void add(Package_Table package_Table);
	void deleteAll();
}
