package template;

import accessToken.AccessTokenApi;
import configuration.WxConfiguration;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.json.JSONObject;
import util.HttpRequestBaseResultGet;

/**
 * Created by cellargalaxy on 17-9-23.
 */
public class TemplateApiImpl implements TemplateApi {
	private static final String TEMPLATE_URL = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=";
	private final AccessTokenApi accessTokenApi;
	private final String coding;
	
	public TemplateApiImpl(AccessTokenApi accessTokenApi) {
		this.accessTokenApi = accessTokenApi;
		coding=WxConfiguration.getCoding();
	}
	
	public JSONObject sendTemplate(String openId, String templateId, String url, JSONObject data) {
		HttpPost httpPost = new HttpPost(TEMPLATE_URL + accessTokenApi.getAccessToken());
		httpPost.addHeader("Content-Type", "application/json;charset=" + coding);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("touser", openId);
		jsonObject.put("template_id", templateId);
		jsonObject.put("url", url);
		jsonObject.put("data", data);
		StringEntity stringEntity = new StringEntity(jsonObject.toString(), coding);
		stringEntity.setContentEncoding(coding);
		stringEntity.setContentType("application/json");
		httpPost.setEntity(stringEntity);
		String result = HttpRequestBaseResultGet.getHttpRequestBaseResult(httpPost);
		if (result == null) {
			return null;
		}
		return new JSONObject(result);
	}
}