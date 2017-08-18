package table;

public class Publication_Table implements Table {
	int package_Id;
	String publication_Right;
	
	public int getPackage_Id() {
		return package_Id;
	}
	public void setPackage_Id(int package_Id) {
		this.package_Id = package_Id;
	}
	public String getPublication_Right() {
		return publication_Right;
	}
	public void setPublication_Right(String publication_Right) {
		this.publication_Right = publication_Right;
	}
}
