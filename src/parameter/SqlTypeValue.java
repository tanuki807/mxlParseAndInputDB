package parameter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class SqlTypeValue {
	public static final int TYPE_UNKNOWN = Integer.MIN_VALUE;
	  
	public abstract void setTypeValue(PreparedStatement paramPreparedStatement, int paramInt1, int paramInt2, String paramString)
	    throws SQLException;
}
