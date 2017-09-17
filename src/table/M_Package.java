package table;

public class M_Package implements Table {
	int package_ID;
	String asset_Name;
	String creation_Date;
	String product;
	String provider;
	String provider_ID;
	String verb;
	int version_Major;
	int version_Minor;
	
	public int getPackage_ID() {
		return package_ID;
	}
	
	public void setPackage_ID(int package_ID) {
		this.package_ID = package_ID;
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
	public String getProvider_ID() {
		return provider_ID;
	}
	public void setProvider_ID(String provider_ID) {
		this.provider_ID = provider_ID;
	}
	public String getVerb() {
		return verb;
	}
	public void setVerb(String verb) {
		this.verb = verb;
	}
	public int getVersion_Major() {
		return version_Major;
	}
	public void setVersion_Major(int version_Major) {
		this.version_Major = version_Major;
	}
	public int getVersion_Minor() {
		return version_Minor;
	}
	public void setVersion_Minor(int version_Minor) {
		this.version_Minor = version_Minor;
	}
}