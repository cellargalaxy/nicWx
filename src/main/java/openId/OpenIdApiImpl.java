package openId;

import accessToken.AccessTokenApi;
import org.apache.http.client.methods.HttpGet;
import org.json.JSONArray;
import org.json.JSONObject;
import util.HttpRequestBaseResultGet;

/**
 * Created by cellargalaxy on 17-9-23.
 */
public class OpenIdApiImpl implements OpenIdApi {
	private static final String OPEN_ID_URL = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=";
	private final AccessTokenApi accessTokenApi;
	
	public OpenIdApiImpl(AccessTokenApi accessTokenApi) {
		this.accessTokenApi = accessTokenApi;
	}
	
	public JSONObject getOpenIds() {
		HttpGet httpGet = new HttpGet(OPEN_ID_URL + accessTokenApi.getAccessToken());
		String result = HttpRequestBaseResultGet.getHttpRequestBaseResult(httpGet);
		if (result == null) {
			return null;
		}
		return new JSONObject(result);
	}
}
