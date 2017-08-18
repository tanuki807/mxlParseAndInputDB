package table;

public class Advisories_Table implements Table {
	int package_Id;
	String advisories;
	
	public int getPackage_Id() {
		return package_Id;
	}
	public void setPackage_Id(int package_Id) {
		this.package_Id = package_Id;
	}
	public String getAdvisories() {
		return advisories;
	}
	public void setAdvisories(String advisories) {
		this.advisories = advisories;
	}
}
