package dao;

import table.Table;

public interface JdbcDao {
	int getCount();
	int getFind_PK();
	void add(Table table);
	void deleteAll();
}
