package domain;

import java.util.List;

public class Publication_Type {
	String title;
	List<String> publication_Right;
	List<String> type;
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<String> getPublication_Right() {
		return publication_Right;
	}
	public void setPublication_Right(List<String> publication_Right) {
		this.publication_Right = publication_Right;
	}
	public List<String> getType() {
		return type;
	}
	public void setType(List<String> type) {
		this.type = type;
	}
}
