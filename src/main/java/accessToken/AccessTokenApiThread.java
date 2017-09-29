package accessToken;

import configuration.WxConfiguration;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import util.HttpRequestBaseResultGet;
import wx.WxApiImpl;

/**
 * Created by cellargalaxy on 17-9-22.
 */
public class AccessTokenApiThread extends AccessTokenApi {
	private static final Logger LOGGER = Logger.getLogger(AccessTokenApiThread.class.getName());
	private volatile String accessToken;
	private final HttpGet accessTokenHttpGet;
	private boolean runable;
	private final int sleepTime;
	
	public AccessTokenApiThread() {
		accessTokenHttpGet = new HttpGet("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" +
				WxConfiguration.getAppID() + "&secret=" + WxConfiguration.getAppsecret());
		runable=true;
		sleepTime=WxConfiguration.getFlushTime();
	}
	
	public void flushAccessToken() {
		try {
			String result = HttpRequestBaseResultGet.getHttpRequestBaseResult(accessTokenHttpGet);
			if (result == null) {
				return;
			}
			JSONObject jsonObject = new JSONObject(result);
			if (jsonObject.has("access_token")) {
				accessToken=jsonObject.getString("access_token");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			LOGGER.info("刷新accessToken："+accessToken);
		}
	}
	
	@Override
	public void run() {
		while (runable) {
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			flushAccessToken();
		}
	}
	
	@Override
	public void interrupt() {
		runable=false;
		super.interrupt();
	}
	
	public String getAccessToken() {
		return accessToken;
	}
}
