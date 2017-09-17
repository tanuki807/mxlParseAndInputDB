package dao;

import java.sql.SQLException;

import table.Table;

public interface JdbcDao {
	Table get(int package_ID);
	int getCount();
	int getFindMaxPK();
	void add(Table table) throws SQLException;
	void deleteAll() throws SQLException;
}
