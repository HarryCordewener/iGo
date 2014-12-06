package remote;


public class Restaurant extends locationSuper{

	int restaurantID;
	String restaurantType;

	public Restaurant(String co, String name, int id, int restID, String restType) {
		super(co, name, id);
		restaurantID= restID;
		restaurantType = restType;
		
	}

	public int getrestrauntID() {
		return restaurantID;
	}

	public void setrestrauntID(int restrauntID) {
		this.restaurantID = restrauntID;
	}

	public String getrestrauntType() {
		return restaurantType;
	}

	public void setrestrauntType(String restrauntType) {
		this.restaurantType = restrauntType;
	}


	public String toString(){
		return "{\"restaurantid\":" + restaurantID +  ", \"gps\":\"" + coordinates + "\", \"locationid\":" + locID + ", \"name\":\""
				+ name + "\", \"type\":\"" + restaurantType +  "\"}";
	}


}


