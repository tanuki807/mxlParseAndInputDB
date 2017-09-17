package table;

public class Type implements Table {
	int package_ID;
	String type;
	String publication_Right;
	
	public int getPackage_ID() {
		return package_ID;
	}
	public void setPackage_ID(int package_ID) {
		this.package_ID = package_ID;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPublication_Right() {
		return publication_Right;
	}
	public void setPublication_Right(String publication_Right) {
		this.publication_Right = publication_Right;
	}
}
