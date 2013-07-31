package by.yakimchik.itnews.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.util.Log;

/**
 * 
 * @author Sergei Yakimchik
 * @version 1.0
 * This is class check available server or not. 	
 */
public class ConnectionToServer {

	public static final String server_url = "http://jobs4all.rollupme.com/";
	
	private static StringBuffer response= null;
	
	private static int _status;
	
	public static void connect() throws IOException
	{
		URL url = new URL(server_url);
		
		HttpURLConnection _mHttpConn = (HttpURLConnection) url.openConnection();
		_mHttpConn.setReadTimeout(10000);
		_mHttpConn.setConnectTimeout(15000);
		_mHttpConn.setRequestMethod("POST");
		_mHttpConn.setDoInput(true);
		_mHttpConn.setDoOutput(true);
		
		
		_status = _mHttpConn.getResponseCode();
		
		readStream(_mHttpConn.getInputStream());
	}
	
	public static int getStatus()
	{
		return _status;
	}
	
	private static void readStream(InputStream in)
	{
		BufferedReader reader = null;
		response = new StringBuffer();
		  try {
		    reader = new BufferedReader(new InputStreamReader(in));
		    String line = "";
		    while ((line = reader.readLine()) != null) {
		      response.append(line);
		    }
		  } catch (IOException e) {
		    e.printStackTrace();
		  } finally {
		    if (reader != null) {
		      try {
		        reader.close();
		      } catch (IOException e) {
		        e.printStackTrace();
		        }
		    }
		  }
	}
	
	public static String getResponse()
	{
		return response.toString();
	}
}
