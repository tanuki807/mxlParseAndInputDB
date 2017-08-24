package parameter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class SqlValue {
	public abstract void setValue(PreparedStatement paramPreparedStatement, int paramInt)
		    throws SQLException;
		  
	public abstract void cleanup();
}
