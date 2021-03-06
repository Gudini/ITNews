package by.yakimchik.itnews.server.requests;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.apache.http.entity.mime.MultipartEntity;

import by.yakimchik.itnews.server.CookieStorage;
import by.yakimchik.itnews.server.HttpClient;
import by.yakimchik.itnews.server.StatusesConstant;
import by.yakimchik.itnews.server.TestCookie;

/**
 * 
 * @author Sergei Yakimchik
 * @version 1.0
 * Http base request class
 */

public class BaseRequest {
	
	private static final String url = "http://chitki.ru";

	private HttpPost httppost;
	private int _status;
	private String _responseStr;
	private CookieStore cookieStore;
	private HttpContext localContext;
	
	protected static int multipart = 0;
	protected MultipartEntity reqEntity;
	
	public BaseRequest(String server_url)
	{		
		httppost = new HttpPost(url+server_url);
		
		cookieStore = new BasicCookieStore();
		localContext = new BasicHttpContext();
		
		setContext();
	}
	
	public int getStatus()
	{
		return _status;
	}
	
	public String getResponse()
	{
		return _responseStr;
	}
	
	public void sendRequest(List<NameValuePair> params) 
			throws IOException, JSONException
	{
		if(params!=null)
			httppost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
		else{
			httppost.setEntity(reqEntity);
		}
		
		if(CookieStorage.getCookie()!=null)
			httppost.setHeader("Cookie", CookieStorage.getCookie());
		
		HttpResponse responseHttp = HttpClient.getHttpClient().execute(httppost, localContext);
		
		if(CookieStorage.getCookie()==null)
		{
			String cookie = responseHttp.getLastHeader("Set-Cookie").getValue();
			CookieStorage.setCookie(cookie);
		}

		_responseStr = EntityUtils.toString(responseHttp.getEntity());
		
		_status = responseHttp.getStatusLine().getStatusCode();
		
		if(_status>=StatusesConstant.ErrorStatus)
		{
			CookieStorage.resetCookie();
		}
	}
	
	public void setContext()
	{
		localContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
	}
	
	public CookieStore getCookieStore()
	{
		return HttpClient.getHttpClient().getCookieStore();
	}
	
	
}
