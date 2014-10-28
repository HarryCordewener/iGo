package remote;


public class restraunt extends locationSuper{

	int restrauntID;
	String restrauntType;

	public restraunt(String co, String name, int id, int restID, String restType) {
		super(co, name, id);
		restrauntID= restID;
		restrauntType = restType;
		
	}

	public int getrestrauntID() {
		return restrauntID;
	}

	public void setrestrauntID(int restrauntID) {
		this.restrauntID = restrauntID;
	}

	public String getrestrauntType() {
		return restrauntType;
	}

	public void setrestrauntType(String restrauntType) {
		this.restrauntType = restrauntType;
	}


	public String toString(){
		return "{\"restrauntID\":" + restrauntID +  ", \"coordinates\":" + coordinates + ", \"locationID\":" + locID + ", \"Name\":"
				+ name + ", \"type\":" + restrauntType +  "}";
	}


}


