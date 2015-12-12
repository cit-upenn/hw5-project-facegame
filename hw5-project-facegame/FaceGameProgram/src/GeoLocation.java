import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;

/**
 * Geo location class for getting location from IP address
 * @author Rony Edde
 *
 */
public class GeoLocation 
{
	private String locationData;
	
	/**
	 * constructor that tries to get the ip and extract the location by querying freegeoip
	 */
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
			while ((line = reader.readLine()) != null)
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
	
	/**
	 * gets the latest geo location data as is
	 * @return string that contains all the geo localisation info
	 */
	public String getLocationData()
	{
		return this.locationData;
	}
	
	/**
	 * gets the location strings extracted from the data as a String array
	 * @return string array with location info
	 */
	public String[] getLocationStrings()
	{
		String strings[] = this.locationData.split(",");
		
		return strings;
	}
	
	/**
	 * checks if location string is empty
	 * @return true if empty
	 */
	public boolean isEmpty()
	{
		if (this.locationData.compareTo("") == 0)
			return true;
		return false;
	}
	
}
