package table;

public class Content implements Table {
	int package_ID;
	String content_FileSize;
	String content_CheckSum;
	String value;
	String advisories;
	
	public int getPackage_ID() {
		return package_ID;
	}
	public void setPackage_ID(int package_ID) {
		this.package_ID = package_ID;
	}
	public String getContent_FileSize() {
		return content_FileSize;
	}
	public void setContent_FileSize(String content_FileSize) {
		this.content_FileSize = content_FileSize;
	}
	public String getContent_CheckSum() {
		return content_CheckSum;
	}
	public void setContent_CheckSum(String content_CheckSum) {
		this.content_CheckSum = content_CheckSum;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getAdvisories() {
		return advisories;
	}
	public void setAdvisories(String advisories) {
		this.advisories = advisories;
	}
}
