package table;

public class Ams implements Table {
	int package_ID;
	String asset_Class;
	String asset_ID;
	String description;
	
	public int getPackage_ID() {
		return package_ID;
	}
	public void setPackage_ID(int package_ID) {
		this.package_ID = package_ID;
	}
	public String getAsset_Class() {
		return asset_Class;
	}
	public void setAsset_Class(String asset_Class) {
		this.asset_Class = asset_Class;
	}
	public String getAsset_ID() {
		return asset_ID;
	}
	public void setAsset_ID(String asset_ID) {
		this.asset_ID = asset_ID;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}