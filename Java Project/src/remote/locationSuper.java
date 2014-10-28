package remote;

public abstract class locationSuper {

	String coordinates;
	int locID;
	String name;
	
	
	public locationSuper(String co, String name, int id) {
		coordinates=co;
		this.name=name;
		locID=id;
	}

	public int getLocID() {
		return locID;
	}

	public void setLocID(int locID) {
		this.locID = locID;
	}

	public String getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
	}

	public String getLocName() {
		return name;
	}

	public void setLocName(String locName) {
		this.name = locName;
	}
	
	

}
