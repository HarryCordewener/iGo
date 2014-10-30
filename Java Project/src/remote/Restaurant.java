package remote;


public class Restaurant extends locationSuper{

	int restauraantID;
	String restaurantType;

	public Restaurant(String co, String name, int id, int restID, String restType) {
		super(co, name, id);
		restauraantID= restID;
		restaurantType = restType;
		
	}

	public int getrestrauntID() {
		return restauraantID;
	}

	public void setrestrauntID(int restrauntID) {
		this.restauraantID = restrauntID;
	}

	public String getrestrauntType() {
		return restaurantType;
	}

	public void setrestrauntType(String restrauntType) {
		this.restaurantType = restrauntType;
	}


	public String toString(){
		return "{\"restaurantid\":" + restauraantID +  ", \"gps\":\"" + coordinates + "\", \"locationid\":" + locID + ", \"name\":"
				+ name + ", \"type\":" + restaurantType +  "}";
	}


}


