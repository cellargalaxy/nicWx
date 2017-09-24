package configuration;

/**
 * Created by cellargalaxy on 17-9-22.
 */
public class WxConfiguration {
	private static String conding;
	private static int socketTimeout;
	private static int connectTimeout;
	private static String appID;
	private static String appsecret;
	private static int flushTime;
	private static int templateSendTime;
	
	static {
		conding = "utf-8";
		socketTimeout = 1000 * 5;
		connectTimeout = 1000 * 60 * 60;
		appID = "";
		appsecret = "";
		flushTime = 1000 * 60 * 60;
		templateSendTime = 1000 * 5;
	}
	
	public static String getConding() {
		return conding;
	}
	
	public static void setConding(String conding) {
		WxConfiguration.conding = conding;
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
	
	public static int getFlushTime() {
		return flushTime;
	}
	
	public static void setFlushTime(int flushTime) {
		WxConfiguration.flushTime = flushTime;
	}
	
	public static int getTemplateSendTime() {
		return templateSendTime;
	}
	
	public static void setTemplateSendTime(int templateSendTime) {
		WxConfiguration.templateSendTime = templateSendTime;
	}
}
