package configuration;

import javax.security.auth.login.Configuration;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Created by cellargalaxy on 17-9-22.
 */
public class WxConfiguration {
	private static String appID;
	private static String appsecret;
	private static String coding;
	private static int socketTimeout;
	private static int connectTimeout;
	private static int accessTokenFlushTime;
	
	static {
		Properties properties = new Properties();
		File confFile = new File(new File(Configuration.class.getResource("").getPath()).getParentFile().getAbsolutePath() + "/nicWx.properties");
		try {
			properties.load(new InputStreamReader(new FileInputStream(confFile)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		appID = properties.getProperty("appID");
		appsecret = properties.getProperty("appsecret");
		coding = properties.getProperty("coding");
		if (coding == null) {
			coding = "utf-8";
		}
		try {
			socketTimeout = new Integer(properties.getProperty("socketTimeout"));
		} catch (Exception e) {
			e.printStackTrace();
			socketTimeout = 1000 * 5;
		}
		try {
			connectTimeout = new Integer(properties.getProperty("connectTimeout"));
		} catch (Exception e) {
			e.printStackTrace();
			connectTimeout = 1000 * 60 * 60;
		}
		try {
			accessTokenFlushTime = new Integer(properties.getProperty("accessTokenFlushTime"));
		} catch (Exception e) {
			e.printStackTrace();
			accessTokenFlushTime = 1000 * 60 * 60;
		}
	}
	
	public static String getCoding() {
		return coding;
	}
	
	public static void setCoding(String coding) {
		WxConfiguration.coding = coding;
	}
	
	public static int getSocketTimeout() {
		return socketTimeout;
	}
	
	public static void setSocketTimeout(int socketTimeout) {
		WxConfiguration.socketTimeout = socketTimeout;
	}
	
	public static int getConnectTimeout() {
		return connectTimeout;
	}
	
	public static void setConnectTimeout(int connectTimeout) {
		WxConfiguration.connectTimeout = connectTimeout;
	}
	
	public static String getAppID() {
		return appID;
	}
	
	public static void setAppID(String appID) {
		WxConfiguration.appID = appID;
	}
	
	public static String getAppsecret() {
		return appsecret;
	}
	
	public static void setAppsecret(String appsecret) {
		WxConfiguration.appsecret = appsecret;
	}
	
	public static int getAccessTokenFlushTime() {
		return accessTokenFlushTime;
	}
	
	public static void setAccessTokenFlushTime(int accessTokenFlushTime) {
		WxConfiguration.accessTokenFlushTime = accessTokenFlushTime;
	}
	
}
