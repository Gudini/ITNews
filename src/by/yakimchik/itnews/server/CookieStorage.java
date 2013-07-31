package by.yakimchik.itnews.server;

/**
 * 
 * @author Sergei Yakimchik
 * @version 1.0
 * This class contain cookie
 */
public class CookieStorage {

	private static String cookie = null;
	
	public static boolean isRegistrationOrAuthorization = false;
	
	public static String getCookie()
	{
		return cookie;
	}
	
	public static void setCookie(String cookie)
	{
		if(isRegistrationOrAuthorization==true)
			CookieStorage.cookie = cookie;
	}
	
	public static void resetCookie()
	{
		if(isRegistrationOrAuthorization==true)
			cookie = null;
	}
	
}
