package domain;

import java.util.List;

public class Ams {
	String title;
	List<String> asset_Id;
	List<String> asset_Class;
	List<String> description;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<String> getAsset_Id() {
		return asset_Id;
	}
	public void setAsset_Id(List<String> asset_Id) {
		this.asset_Id = asset_Id;
	}
	public List<String> getAsset_Class() {
		return asset_Class;
	}
	public void setAsset_Class(List<String> asset_Class) {
		this.asset_Class = asset_Class;
	}
	public List<String> getDescription() {
		return description;
	}
	public void setDescription(List<String> description) {
		this.description = description;
	}
}
