package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbconnection.ConnectionMaker;
import domain.Xml;

public class XmlDao {
	ConnectionMaker connectionMaker;
	
	public XmlDao(ConnectionMaker connectionMaker) {
		this.connectionMaker = connectionMaker;
	}
	
	public int getCount() throws ClassNotFoundException, SQLException {
		Connection c = connectionMaker.makeConnection();
		PreparedStatement ps = c.prepareStatement("select count(*) from ams");
		
		ResultSet rs = ps.executeQuery();
		rs.next();
		int count = rs.getInt(1);
		
		rs.close();
		ps.close();
		c.close();
		
		return count;
	}
	
	public void deleteAll() throws ClassNotFoundException, SQLException {
		Connection c = connectionMaker.makeConnection();
		PreparedStatement ps = c.prepareStatement("delete from ams");
		ps.executeUpdate();
		
		ps.close();
		c.close();
	}
	
	public void add(Xml xml) throws ClassNotFoundException, SQLException {
		Connection c = connectionMaker.makeConnection();
		PreparedStatement ps = c.prepareStatement(
				"insert into ams("
				+ "asset_Class, asset_Id, asset_Name, creation_Date, description, product, "
				+ "provider, provider_Id, verb, version_Major, version_Minor) "
				+ "values(?,?,?,?,?,?,?,?,?,?,?)") ;
		
		ps.setString(1, xml.getAsset_Class());
		ps.setString(2, xml.getAsset_Id());
		ps.setString(3, xml.getAsset_Name());
		ps.setString(4, xml.getCreation_Date());
		ps.setString(5, xml.getDescription());
		ps.setString(6, xml.getProduct());
		ps.setString(7, xml.getProvider());
		ps.setString(8, xml.getProvider_Id());
		ps.setString(9, xml.getVerb());
		ps.setInt(10, xml.getVersion_Major());
		ps.setInt(11, xml.getVersion_Minor());
		
		ps.executeUpdate();
		
		ps.close();
		c.close();
	}
	
	public Xml get(String asset_Class) throws ClassNotFoundException, SQLException {
		Connection c = connectionMaker.makeConnection();
		PreparedStatement ps = c.prepareStatement("select *from asm where Asset_Class = ?");
		
		ps.setString(1, asset_Class);
		ResultSet rs = ps.executeQuery();
		rs.next();
		
		Xml xml = new Xml();
		xml.setAsset_Class(rs.getString("asset_Class"));
		xml.setAsset_Id(rs.getString("asset_Id"));
		xml.setAsset_Name(rs.getString("asset_Name"));
		xml.setCreation_Date(rs.getString("creation_Date"));
		xml.setDescription(rs.getString("description"));
		xml.setProduct(rs.getString("product"));
		xml.setProvider(rs.getString("provider"));
		xml.setProvider_Id(rs.getString("provider_Id"));
		xml.setVerb(rs.getString("verb"));
		xml.setVersion_Major(rs.getInt("version_Major"));
		xml.setVersion_Minor(rs.getInt("version_Minor"));
		return xml;
	}
}
