package openId;

import org.apache.http.client.methods.HttpGet;
import org.json.JSONArray;
import org.json.JSONObject;
import util.HttpRequestBaseResultGet;

/**
 * Created by cellargalaxy on 17-9-23.
 */
public class OpenIdApiImpl implements OpenIdApi {
	private static final String OPEN_ID_URL = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=";
	
	public JSONArray getOpenIds(String accessToken) {
		HttpGet httpGet = new HttpGet(OPEN_ID_URL + accessToken);
		String result = HttpRequestBaseResultGet.getHttpRequestBaseResult(httpGet);
		if (result == null) {
			return null;
		}
		JSONObject jsonObject = new JSONObject(result);
		if (jsonObject.has("data")) {
			return jsonObject.getJSONObject("data").getJSONArray("openid");
		}
		return null;
	}
}
