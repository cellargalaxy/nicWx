package accessToken;

import configuration.WxConfiguration;
import org.apache.http.client.methods.HttpGet;
import org.json.JSONObject;
import util.HttpRequestBaseResultGet;

/**
 * Created by cellargalaxy on 17-9-22.
 */
public class AccessTokenApiImpl implements AccessTokenApi {
	private final HttpGet accessTokenHttpGet;
	
	public AccessTokenApiImpl() {
		accessTokenHttpGet = new HttpGet("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" +
				WxConfiguration.getAppID() + "&secret=" + WxConfiguration.getAppsecret());
	}
	
	public String flushAccessToken() {
		try {
			String result = HttpRequestBaseResultGet.getHttpRequestBaseResult(accessTokenHttpGet);
			if (result == null) {
				return null;
			}
			JSONObject jsonObject = new JSONObject(result);
			if (jsonObject.has("access_token")) {
				return jsonObject.get("access_token").toString();
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
