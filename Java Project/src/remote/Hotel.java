package remote;

public class Hotel extends locationSuper {

	int hotelID;
	String type;
	public Hotel(String co, String name, int id, int hid, String t) {
		super(co, name, id);
		hotelID=hid;
		type=t;
		// TODO Auto-generated constructor stub
	}
	public int getHotelID() {
		return hotelID;
	}
	public void setHotelID(int hotelID) {
		this.hotelID = hotelID;
	}


	public String toString(){
		return " {\"hotelid\":" + hotelID +",\"gps\":\"" + coordinates + "\", \"locationid\":" + locID + ", \"name\":" + name + ", \"type\":" + type + 
				 "}";
	}
	
	public String toString2(){
		return " {\"theaterid\":" + hotelID +",\"gps\":\"" + coordinates + "\", \"locationid\":" + locID + ", \"name\":" + name + 
				 "}";
	}
	
	public String toString3(){
		return " {\"storeid\":" + hotelID +",\"gps\":\"" + coordinates + "\", \"locationid\":" + locID + ", \"name\":" + name + ", \"type\":" + type + 
				 "}";
	}
}
