package domain;

import java.util.List;

public class Content {
	String title;
	List<String> advisories;
	List<String> content_FileSize;
	List<String> content_CheckSum;
	List<String> value;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<String> getAdvisories() {
		return advisories;
	}
	public void setAdvisories(List<String> advisories) {
		this.advisories = advisories;
	}
	public List<String> getContent_FileSize() {
		return content_FileSize;
	}
	public void setContent_FileSize(List<String> content_FileSize) {
		this.content_FileSize = content_FileSize;
	}
	public List<String> getContent_CheckSum() {
		return content_CheckSum;
	}
	public void setContent_CheckSum(List<String> content_CheckSum) {
		this.content_CheckSum = content_CheckSum;
	}
	public List<String> getValue() {
		return value;
	}
	public void setValue(List<String> value) {
		this.value = value;
	}
}
