package domain;

import java.util.List;

public class Actors {
	int package_Id;
	List<String> actors;

	public int getPackage_Id() {
		return package_Id;
	}

	public void setPackage_Id(int package_Id) {
		this.package_Id = package_Id;
	}

	public List<String> getActors() {
		return actors;
	}

	public void setActors(List<String> actors) {
		this.actors = actors;
	}
}
