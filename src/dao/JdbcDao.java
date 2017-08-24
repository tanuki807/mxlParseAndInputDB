package dao;

import table.Table;

public interface JdbcDao {
	int getCount();
	int getFindMaxPK();
	void add(Table table);
	void deleteAll();
}
