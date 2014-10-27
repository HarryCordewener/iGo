package remote;

public abstract class location {

	String coordinates;
	String locID;
	String locName;
	
	public location(String co, String name, String id) {
		coordinates=co;
		locName=name;
		locID=id;
	}

	public String getLocID() {
		return locID;
	}

	public void setLocID(String locID) {
		this.locID = locID;
	}

	public String getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
	}

	public String getLocName() {
		return locName;
	}

	public void setLocName(String locName) {
		this.locName = locName;
	}
	
	

}
