package remote;



public class LocationToVisit extends locationSuper {

	String type;
	String subtype;


	public LocationToVisit(String co, String name, int id, String type, String subtype) {
		super(co, name, id);
		this.type=type;
		this.subtype=subtype;
		// TODO Auto-generated constructor stub
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getSubtype() {
		return subtype;
	}


	public void setSubtype(String subtype) {
		this.subtype = subtype;
	}

	public String toString(){
		return "Location [coordinates=" + coordinates + ", locationID=" + locID + ", Name=" + name + ", type=" + type + 
				". subtype=" + subtype + "]";
	}


}
