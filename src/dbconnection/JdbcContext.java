package dbconnection;

import java.io.StringWriter;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;

import parameter.SqlParameter;
import parameter.SqlParameterValue;
import parameter.SqlTypeValue;
import parameter.SqlValue;

public class JdbcContext {
	private ConnectionMaker connectionMaker;

	public void setConnectionMaker(ConnectionMaker connectionMaker) {
		this.connectionMaker = connectionMaker;
	}

	public int queryForInt(String sql) {
		return doInStatement(sql);
	}

	public void insert(final String query, Object... args) {
		workWithStatementStrategy(new StatementStrategy() {
			public PreparedStatement makePreparedStatement(Connection con) throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(query);
				if (args != null) {
					for (int i = 0; i < args.length; i++) {
						Object arg = args[i];
						doSetValue(pstmt, i + 1, arg);
					}
				}
				return pstmt;
			}
		});
	}

	private void doSetValue(PreparedStatement ps, int parameterPosition, Object argValue) throws SQLException {
		if ((argValue instanceof SqlParameterValue)) {
			SqlParameterValue paramValue = (SqlParameterValue) argValue;
			setParameterValue(ps, parameterPosition, paramValue, paramValue.getValue());
		} else {
			setParameterValue(ps, parameterPosition, Integer.MIN_VALUE, argValue);
		}
	}

	private void setParameterValue(PreparedStatement ps, int paramIndex, SqlParameter param, Object inValue)
			throws SQLException {
		setParameterValueInternal(ps, paramIndex, param.getSqlType(), param.getTypeName(), param.getScale(), inValue);
	}

	private void setParameterValue(PreparedStatement ps, int paramIndex, int sqlType, Object inValue)
			throws SQLException {
		setParameterValueInternal(ps, paramIndex, sqlType, null, null, inValue);
	}

	private void setParameterValueInternal(PreparedStatement ps, int paramIndex, int sqlType, String typeName,
			Integer scale, Object inValue) throws SQLException {
		String typeNameToUse = typeName;
		int sqlTypeToUse = sqlType;
		Object inValueToUse = inValue;

		if ((inValue instanceof SqlParameterValue)) {
			SqlParameterValue parameterValue = (SqlParameterValue) inValue;

			if (parameterValue.getSqlType() != Integer.MIN_VALUE) {
				sqlTypeToUse = parameterValue.getSqlType();
			}
			if (parameterValue.getTypeName() != null) {
				typeNameToUse = parameterValue.getTypeName();
			}
			inValueToUse = parameterValue.getValue();
		}
		if (inValueToUse == null) {
			setNull(ps, paramIndex, sqlTypeToUse, typeNameToUse);
		} else {
			setValue(ps, paramIndex, sqlTypeToUse, typeNameToUse, scale, inValueToUse);
		}
	}

	private static void setNull(PreparedStatement ps, int paramIndex, int sqlType, String typeName)
			throws SQLException {
		if (sqlType == Integer.MIN_VALUE) {
			boolean useSetObject = false;
			sqlType = 0;
			try {
				ParameterMetaData pmd = null;
				try {
					pmd = ps.getParameterMetaData();
				} catch (AbstractMethodError localAbstractMethodError) {
				}

				if (pmd != null) {
					sqlType = pmd.getParameterType(paramIndex);
				} else {
					DatabaseMetaData dbmd = ps.getConnection().getMetaData();
					String databaseProductName = dbmd.getDatabaseProductName();
					String jdbcDriverName = dbmd.getDriverName();
					if ((databaseProductName.startsWith("Informix"))
							|| (jdbcDriverName.startsWith("Microsoft SQL Server"))) {
						useSetObject = true;
					} else if ((databaseProductName.startsWith("DB2")) || (jdbcDriverName.startsWith("jConnect"))
							|| (jdbcDriverName.startsWith("SQLServer"))
							|| (jdbcDriverName.startsWith("Apache Derby"))) {
						sqlType = 12;
					}
				}
			} catch (Throwable ex) {

			}
			if (useSetObject) {
				ps.setObject(paramIndex, null);
			} else {
				ps.setNull(paramIndex, sqlType);
			}
		} else if (typeName != null) {
			ps.setNull(paramIndex, sqlType, typeName);
		} else {
			ps.setNull(paramIndex, sqlType);
		}
	}

	private static void setValue(PreparedStatement ps, int paramIndex, int sqlType, String typeName, Integer scale,
			Object inValue) throws SQLException {
		if ((inValue instanceof SqlTypeValue)) {
			((SqlTypeValue) inValue).setTypeValue(ps, paramIndex, sqlType, typeName);
		} else if ((inValue instanceof SqlValue)) {
			((SqlValue) inValue).setValue(ps, paramIndex);
		} else if ((sqlType == 12) || (sqlType == -1) || ((sqlType == 2005) && (isStringValue(inValue.getClass())))) {
			ps.setString(paramIndex, inValue.toString());
		} else if ((sqlType == 3) || (sqlType == 2)) {
			if ((inValue instanceof BigDecimal)) {
				ps.setBigDecimal(paramIndex, (BigDecimal) inValue);
			} else if (scale != null) {
				ps.setObject(paramIndex, inValue, sqlType, scale.intValue());
			} else {
				ps.setObject(paramIndex, inValue, sqlType);
			}
		} else if (sqlType == 91) {
			if ((inValue instanceof java.util.Date)) {
				if ((inValue instanceof java.sql.Date)) {
					ps.setDate(paramIndex, (java.sql.Date) inValue);
				} else {
					ps.setDate(paramIndex, new java.sql.Date(((java.util.Date) inValue).getTime()));
				}
			} else if ((inValue instanceof Calendar)) {
				Calendar cal = (Calendar) inValue;
				ps.setDate(paramIndex, new java.sql.Date(cal.getTime().getTime()), cal);
			} else {
				ps.setObject(paramIndex, inValue, 91);
			}
		} else if (sqlType == 92) {
			if ((inValue instanceof java.util.Date)) {
				if ((inValue instanceof Time)) {
					ps.setTime(paramIndex, (Time) inValue);
				} else {
					ps.setTime(paramIndex, new Time(((java.util.Date) inValue).getTime()));
				}
			} else if ((inValue instanceof Calendar)) {
				Calendar cal = (Calendar) inValue;
				ps.setTime(paramIndex, new Time(cal.getTime().getTime()), cal);
			} else {
				ps.setObject(paramIndex, inValue, 92);
			}
		} else if (sqlType == 93) {
			if ((inValue instanceof java.util.Date)) {
				if ((inValue instanceof Timestamp)) {
					ps.setTimestamp(paramIndex, (Timestamp) inValue);
				} else {
					ps.setTimestamp(paramIndex, new Timestamp(((java.util.Date) inValue).getTime()));
				}
			} else if ((inValue instanceof Calendar)) {
				Calendar cal = (Calendar) inValue;
				ps.setTimestamp(paramIndex, new Timestamp(cal.getTime().getTime()), cal);
			} else {
				ps.setObject(paramIndex, inValue, 93);
			}
		} else if (sqlType == Integer.MIN_VALUE) {
			if (isStringValue(inValue.getClass())) {
				ps.setString(paramIndex, inValue.toString());
			} else if (isDateValue(inValue.getClass())) {
				ps.setTimestamp(paramIndex, new Timestamp(((java.util.Date) inValue).getTime()));
			} else if ((inValue instanceof Calendar)) {
				Calendar cal = (Calendar) inValue;
				ps.setTimestamp(paramIndex, new Timestamp(cal.getTime().getTime()), cal);
			} else {
				ps.setObject(paramIndex, inValue);
			}

		} else {
			ps.setObject(paramIndex, inValue, sqlType);
		}
	}

	private static boolean isStringValue(Class inValueType) {
		return (CharSequence.class.isAssignableFrom(inValueType)) || (StringWriter.class.isAssignableFrom(inValueType));
	}

	private static boolean isDateValue(Class inValueType) {
		return (java.util.Date.class.isAssignableFrom(inValueType))
				&& (!java.sql.Date.class.isAssignableFrom(inValueType)) && (!Time.class.isAssignableFrom(inValueType))
				&& (!Timestamp.class.isAssignableFrom(inValueType));
	}

	public void executeSql(final String query) {
		workWithStatementStrategy(new StatementStrategy() {
			public PreparedStatement makePreparedStatement(Connection con) throws SQLException {
				return con.prepareStatement(query);
			}
		});
	}

	public int doInStatement(String sql) {

		try (Connection con = connectionMaker.makeConnection();
			 PreparedStatement pstmt = con.prepareStatement(sql);
			 ResultSet result = pstmt.executeQuery()) {

			result.next();
			return result.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQLException!!" + e.getMessage());
			return -1;
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
			System.out.println("ClassNotFoundException!!" + ce.getMessage());
			return -1;
		}
	}

	public void workWithStatementStrategy(StatementStrategy stmt) {

		try (Connection con = connectionMaker.makeConnection();
			 PreparedStatement pstmt = stmt.makePreparedStatement(con)) {

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQLException!!" + e.getMessage());
			System.exit(0);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
			System.out.println("ClassNotFoundException!!" + ce.getMessage());
			System.exit(0);
		}
	}
}
