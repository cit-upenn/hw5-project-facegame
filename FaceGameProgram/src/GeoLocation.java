import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;

public class GeoLocation 
{
	private String locationData;
	
	public GeoLocation()
	{
		try 
		{      
			String ip = InetAddress.getLocalHost().toString(); 
			URL url = new URL("http://freegeoip.net/csv/" + ip);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.connect();
	
			java.io.InputStream is = connection.getInputStream();
			int status = connection.getResponseCode();
			if (status != 300) {}
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			//String line = "";
		        
			String line = "";
			while ( (line = reader.readLine()) != null)
			{
				System.out.println(line);
				this.locationData = line;
			}		        
			reader.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public String getLocationData()
	{
		return this.locationData;
	}
	
	public String[] getLocationStrings()
	{
		String strings[] = this.locationData.split(",");
		
		return strings;
	}
	
	public boolean isEmpty()
	{
		if (this.locationData.compareTo("") == 0)
			return true;
		return false;
	}
	
}
