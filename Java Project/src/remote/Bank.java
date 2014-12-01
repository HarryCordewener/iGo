package remote;

public class Bank extends locationSuper {

	int bankID;
	public Bank(String co, String name, int id, int bID) {
		super(co, name, id);
		bankID=bID;
		// TODO Auto-generated constructor stub
	}
	public int getBankID() {
		return bankID;
	}
	public void setBankID(int bankID) {
		this.bankID = bankID;
	}

	public String toString(){
		return " {\"bankid\":" + bankID +",\"gps\":\"" + coordinates + "\", \"locationid\":" + locID + ", \"name\":" + name + 
				 "}";
	}
}
