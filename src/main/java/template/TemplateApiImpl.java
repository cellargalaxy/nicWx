package template;

import configuration.WxConfiguration;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.json.JSONObject;
import util.HttpRequestBaseResultGet;

/**
 * Created by cellargalaxy on 17-9-23.
 */
public class TemplateApiImpl implements TemplateApi {
	private static final String TEMPLATE_Url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=";
	
	public boolean sendTemplate(String accessToken, String openId, String templateId, String url, JSONObject data) {
		HttpPost httpPost = new HttpPost(TEMPLATE_Url + accessToken);
		httpPost.addHeader("Content-Type", "application/json;charset=" + WxConfiguration.getConding());
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("touser", openId);
		jsonObject.put("template_id", templateId);
		jsonObject.put("url", url);
		jsonObject.put("data", data);
		StringEntity stringEntity = new StringEntity(jsonObject.toString(), WxConfiguration.getConding());
		stringEntity.setContentEncoding(WxConfiguration.getConding());
		stringEntity.setContentType("application/json");
		httpPost.setEntity(stringEntity);
		String result = HttpRequestBaseResultGet.getHttpRequestBaseResult(httpPost);
		if (result == null) {
			return false;
		}
		JSONObject resultJson = new JSONObject(result);
		if (resultJson.has("errcode") && resultJson.getInt("errcode") == 0) {
			return true;
		}
		return false;
	}
}