package remote;

public class Bar extends locationSuper {

	int barid;
	public Bar(String co, String name, int id, int barid) {
		super(co, name, id);
		this.barid =barid;
		// TODO Auto-generated constructor stub
	}
	public int getBarid() {
		return barid;
	}
	public void setBarid(int barid) {
		this.barid = barid;
	}

	public String toString(){
		return " {\"barid\":" + barid +",\"gps\":\"" + coordinates + "\", \"locationid\":" + locID + ", \"name\":" + name + 
				 "}";
	}
}
