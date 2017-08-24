package parameter;

import java.util.LinkedList;
import java.util.List;

import org.springframework.util.Assert;

public class SqlParameter {
	private String name;
	private final int sqlType;
	private String typeName;
	private Integer scale;
	   
	   public SqlParameter(int sqlType)
	   {
	     this.sqlType = sqlType;
	   }
 
	   public SqlParameter(int sqlType, String typeName)
	   {
	     this.sqlType = sqlType;
	     this.typeName = typeName;
	   }
	
	   public SqlParameter(int sqlType, int scale)
	   {
	     this.sqlType = sqlType;
	     this.scale = Integer.valueOf(scale);
	   }
 
	   public SqlParameter(String name, int sqlType)
	   {
	     this.name = name;
	     this.sqlType = sqlType;
	   }
 
	   public SqlParameter(String name, int sqlType, String typeName)
	   {
	     this.name = name;
	     this.sqlType = sqlType;
	     this.typeName = typeName;
	   }   
	 
	   public SqlParameter(String name, int sqlType, int scale)
	   {
	     this.name = name;
	     this.sqlType = sqlType;
	     this.scale = Integer.valueOf(scale);
	   }
	 
	   public SqlParameter(SqlParameter otherParam)
	   {
	     Assert.notNull(otherParam, "SqlParameter object must not be null");
	     this.name = otherParam.name;
	     this.sqlType = otherParam.sqlType;
	     this.typeName = otherParam.typeName;
	     this.scale = otherParam.scale;
	   }
	 
	   public String getName()
	   {
	     return this.name;
	   } 
	 
	   public int getSqlType()
	   {
	     return this.sqlType;
	   }

	   public String getTypeName()
	   {
	     return this.typeName;
	   }
	
	   public Integer getScale()
	   {
	     return this.scale;
	   }

	   public boolean isInputValueProvided()
	   {
	     return true;
	   }  

	   public boolean isResultsParameter()
	   {
	     return false;
	   }

	   public static List<SqlParameter> sqlTypesToAnonymousParameterList(int[] types)
	   {
	     List<SqlParameter> result = new LinkedList<SqlParameter>();
	     if (types != null) {
	       int[] arrayOfInt = types;int j = types.length; for (int i = 0; i < j; i++) { int type = arrayOfInt[i];
	         result.add(new SqlParameter(type));
	       }
	     }
	     return result;
	   }
}
