package remote;

public class Airport extends locationSuper {
	
	int airportID;

	public Airport(String co, String name, int id, int airID) {
		super(co, name, id);
		airportID=airID;
		// TODO Auto-generated constructor stub
	}

		public int getAirportID() {
		return airportID;
	}




	public void setAirportID(int airportID) {
		this.airportID = airportID;
	}

	public String toString(){
			return " {\"airportid\":" + airportID +",\"gps\":\"" + coordinates + "\", \"locationid\":" + locID + ", \"name\":" + name + 
					 "}";
		}





}
