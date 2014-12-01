package remote;


public class Hospital extends locationSuper{

	int hospitalID;
	String hospitalType;
	String contactNumber;

	public Hospital(String co, String name, int id, int hospID, String hospType, String contact) {
		super(co, name, id);
		hospitalID= hospID;
		hospitalType = hospType;
		contactNumber=contact;
		// TODO Auto-generated constructor stub
	}

	public int getHospitalID() {
		return hospitalID;
	}

	public void setHospitalID(int hospitalID) {
		this.hospitalID = hospitalID;
	}

	public String getHospitalType() {
		return hospitalType;
	}

	public void setHospitalType(String hospitalType) {
		this.hospitalType = hospitalType;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	/*
	 * [{"id": 1,
	 * name= "testHospital"
	 * coordinates = "1,1,1"
	 * hospitalID= 1
	 * hospitalType= "Emergency"
	 * contactNumber = "555-555-5555"},
	 * 
	 *{ id=2
	 * name="testhostpital2"
	 * coordinates= "2,2,2"
	 * hospitalID=2
	 * hospitalType= "Outpatient"
	 * contactNumber="666-666-6666"}]
	 * 
	 * 
	 */

	public String toString(){
		return " {\"hospitalid\":" + hospitalID +",\"gps\":\"" + coordinates + "\", \"locationid\":" + locID + ", \"name\":" + name + ", \"type\":" + hospitalType + 
				", \"contactnumber\":" + contactNumber + "}";
	}
	
	public String toString2(){
		return " {\"policeid\":" + hospitalID +",\"gps\":\"" + coordinates + "\", \"locationid\":" + locID + ", \"name\":" + name +  
				", \"contactnumber\":" + contactNumber + "}";
	}
	
	public String toString3(){
		return " {\"schoolid\":" + hospitalID +",\"gps\":\"" + coordinates + "\", \"locationid\":" + locID + ", \"name\":" + name + ", \"type\":" + hospitalType + 
				 "}";
			}


}


