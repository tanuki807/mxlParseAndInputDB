package domain;

import readnode.Xml;

public class Duplicated_Ams implements Xml{
	String title;
	String asset_Name;
	String creation_Date;
	String product;
	String provider;
	String provider_Id;
	int version_Major;
	int Version_Minor;
	String verb;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAsset_Name() {
		return asset_Name;
	}
	public void setAsset_Name(String asset_Name) {
		this.asset_Name = asset_Name;
	}
	public String getCreation_Date() {
		return creation_Date;
	}
	public void setCreation_Date(String creation_Date) {
		this.creation_Date = creation_Date;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	public String getProvider_Id() {
		return provider_Id;
	}
	public void setProvider_Id(String provider_Id) {
		this.provider_Id = provider_Id;
	}
	public int getVersion_Major() {
		return version_Major;
	}
	public void setVersion_Major(int version_Major) {
		this.version_Major = version_Major;
	}
	public int getVersion_Minor() {
		return Version_Minor;
	}
	public void setVersion_Minor(int version_Minor) {
		Version_Minor = version_Minor;
	}
	public String getVerb() {
		return verb;
	}
	public void setVerb(String verb) {
		this.verb = verb;
	}
}
