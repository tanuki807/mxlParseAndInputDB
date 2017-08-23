package table;

public class Poster_Table implements Table {
	int package_Id;
	String description;
	String asset_Id;
	String asset_Class;
	String type;
	String content_FileSize;
	String content_CheckSum;
	String value;
	String image_Aspect_Ratio;
	String publication_Right;
	String advisories;
	
	public int getPackage_Id() {
		return package_Id;
	}
	public void setPackage_Id(int package_Id) {
		this.package_Id = package_Id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAsset_Id() {
		return asset_Id;
	}
	public void setAsset_Id(String asset_Id) {
		this.asset_Id = asset_Id;
	}
	public String getAsset_Class() {
		return asset_Class;
	}
	public void setAsset_Class(String asset_Class) {
		this.asset_Class = asset_Class;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	public String getImage_Aspect_Ratio() {
		return image_Aspect_Ratio;
	}
	public void setImage_Aspect_Ratio(String image_Aspect_Ratio) {
		this.image_Aspect_Ratio = image_Aspect_Ratio;
	}
	public String getPublication_Right() {
		return publication_Right;
	}
	public void setPublication_Right(String publication_Right) {
		this.publication_Right = publication_Right;
	}
	public String getAdvisories() {
		return advisories;
	}
	public void setAdvisories(String advisories) {
		this.advisories = advisories;
	}
}
