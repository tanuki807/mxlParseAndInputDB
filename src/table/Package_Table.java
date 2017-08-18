package table;

import readnode.Xml;

public class Package_Table implements Table {
	int package_Id;
	String asset_Class;
	String asset_Id;
	String asset_Name;
	String creation_Date;
	String description;
	String product;
	String provider;
	String provider_Id;
	String verb;
	int version_Major;
	int version_Minor;
	String provider_Content_Tier;
	String metadata_Spec_Version;
	
	public int getPackage_Id() {
		return package_Id;
	}
	public void setPackage_Id(int package_Id) {
		this.package_Id = package_Id;
	}
	public String getAsset_Class() {
		return asset_Class;
	}
	public void setAsset_Class(String asset_Class) {
		this.asset_Class = asset_Class;
	}
	public String getAsset_Id() {
		return asset_Id;
	}
	public void setAsset_Id(String asset_Id) {
		this.asset_Id = asset_Id;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public String getProvider_Content_Tier() {
		return provider_Content_Tier;
	}
	public void setProvider_Content_Tier(String provider_Content_Tier) {
		this.provider_Content_Tier = provider_Content_Tier;
	}
	public String getMetadata_Spec_Version() {
		return metadata_Spec_Version;
	}
	public void setMetadata_Spec_Version(String metadata_Spec_Version) {
		this.metadata_Spec_Version = metadata_Spec_Version;
	}
}
