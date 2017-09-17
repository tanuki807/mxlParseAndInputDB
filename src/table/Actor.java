package table;

public class Actor implements Table {
	int package_ID;
	String actor;
	
	public int getPackage_ID() {
		return package_ID;
	}
	public void setPackage_ID(int package_ID) {
		this.package_ID = package_ID;
	}
	public String getActor() {
		return actor;
	}
	public void setActor(String actor) {
		this.actor = actor;
	}
}
