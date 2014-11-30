package yelp;


import java.io.IOException;
import javax.jws.WebMethod;
import javax.jws.WebService;
import org.json.JSONException;


@WebService
public class ExampleWs extends Yelp{
	@WebMethod
	public String getText(String Term,String Location) throws IOException, JSONException
	{
		
		return getData(Term,Location);
	}
}
