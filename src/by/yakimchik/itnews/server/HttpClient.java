package by.yakimchik.itnews.server;

import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;

public class HttpClient {

	private HttpClient(){}
	
	public static DefaultHttpClient instance;
	
	public static DefaultHttpClient getHttpClient()
	{
		if(instance==null)
		{
			instance =  new DefaultHttpClient();
			instance.getParams().setParameter(CoreProtocolPNames.USER_AGENT, "Android-AEApp,ID=2435743");
			instance.getParams().setParameter(ClientPNames.COOKIE_POLICY, CookiePolicy.RFC_2109);
		}
		
		return instance;
	}
	
	public static void reset(){
		instance = null;
	}
	
}
