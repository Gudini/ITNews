package by.yakimchik.itnews.server.requests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;

import by.yakimchik.itnews.server.Tags;

public class AuthorizationRequest extends BaseRequest {

	private final static String server_url = "/api/account/login";
	
	public AuthorizationRequest() {
		super(server_url);
	}
	
	public void sendRequest(String login, String password, String remember, String android_device_token) 
			throws IOException, JSONException
	{		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair(Tags.LOGIN, login));
		params.add(new BasicNameValuePair(Tags.PASSWORD, password));
		params.add(new BasicNameValuePair(Tags.REMEMBER, remember));
		
		this.sendRequest(params);
	}
}
