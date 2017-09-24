package tag;

import configuration.WxConfiguration;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.json.JSONArray;
import org.json.JSONObject;
import util.HttpRequestBaseResultGet;

/**
 * Created by cellargalaxy on 17-9-23.
 */
public class TagApiImpl implements TagApi {
	
	public int createTag(String accessToken, String tagName) {
		HttpPost httpPost = new HttpPost("https://api.weixin.qq.com/cgi-bin/tags/create?access_token=" + accessToken);
		httpPost.addHeader("Content-Type", "application/json;charset=" + WxConfiguration.getConding());
		JSONObject tag = new JSONObject();
		tag.put("name", tagName);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("tag", tag);
		StringEntity stringEntity = new StringEntity(jsonObject.toString(), WxConfiguration.getConding());
		stringEntity.setContentEncoding(WxConfiguration.getConding());
		stringEntity.setContentType("application/json");
		httpPost.setEntity(stringEntity);
		String result = HttpRequestBaseResultGet.getHttpRequestBaseResult(httpPost);
		if (result == null) {
			return -1;
		}
		JSONObject resultJson = new JSONObject(result);
		if (resultJson.has("tag")) {
			return resultJson.getJSONObject("tag").getInt("id");
		}
		return -1;
	}
	
	public boolean deleteTag(String accessToken, int tagId) {
		HttpPost httpPost = new HttpPost("https://api.weixin.qq.com/cgi-bin/tags/delete?access_token=" + accessToken);
		httpPost.addHeader("Content-Type", "application/json;charset=" + WxConfiguration.getConding());
		JSONObject tag = new JSONObject();
		tag.put("id", tagId);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("tag", tag);
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
	
	public JSONArray getTags(String accessToken) {
		HttpGet httpGet = new HttpGet("https://api.weixin.qq.com/cgi-bin/tags/get?access_token=" + accessToken);
		String result = HttpRequestBaseResultGet.getHttpRequestBaseResult(httpGet);
		if (result == null) {
			return null;
		}
		JSONObject resultJson = new JSONObject(result);
		if (resultJson.has("tags")) {
			return resultJson.getJSONArray("tags");
		}
		return null;
	}
	
	public JSONArray getTagOpenIds(String accessToken, int tagId) {
		HttpPost httpPost = new HttpPost("https://api.weixin.qq.com/cgi-bin/user/tag/get?access_token=" + accessToken);
		httpPost.addHeader("Content-Type", "application/json;charset=" + WxConfiguration.getConding());
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("tagid", tagId);
		StringEntity stringEntity = new StringEntity(jsonObject.toString(), WxConfiguration.getConding());
		stringEntity.setContentEncoding(WxConfiguration.getConding());
		stringEntity.setContentType("application/json");
		httpPost.setEntity(stringEntity);
		String result = HttpRequestBaseResultGet.getHttpRequestBaseResult(httpPost);
		if (result == null) {
			return null;
		}
		JSONObject resultJson = new JSONObject(result);
		if (resultJson.has("data")) {
			return resultJson.getJSONObject("data").getJSONArray("openid");
		}
		return null;
	}
	
	public boolean addTagIntoOpenId(String accessToken, int tagId, JSONArray openIds) {
		HttpPost httpPost = new HttpPost("https://api.weixin.qq.com/cgi-bin/tags/members/batchtagging?access_token=" + accessToken);
		httpPost.addHeader("Content-Type", "application/json;charset=" + WxConfiguration.getConding());
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("tagid", tagId);
		jsonObject.put("openid_list", openIds);
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
	
	public boolean deleteTagFromOpenId(String accessToken, int tagId, JSONArray openIds) {
		HttpPost httpPost = new HttpPost("https://api.weixin.qq.com/cgi-bin/tags/members/batchuntagging?access_token=" + accessToken);
		httpPost.addHeader("Content-Type", "application/json;charset=" + WxConfiguration.getConding());
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("tagid", tagId);
		jsonObject.put("openid_list", openIds);
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
	
	public JSONArray getOpenIdTags(String accessToken, String openId) {
		HttpPost httpPost = new HttpPost("https://api.weixin.qq.com/cgi-bin/tags/getidlist?access_token=" + accessToken);
		httpPost.addHeader("Content-Type", "application/json;charset=" + WxConfiguration.getConding());
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("openid", openId);
		StringEntity stringEntity = new StringEntity(jsonObject.toString(), WxConfiguration.getConding());
		stringEntity.setContentEncoding(WxConfiguration.getConding());
		stringEntity.setContentType("application/json");
		httpPost.setEntity(stringEntity);
		String result = HttpRequestBaseResultGet.getHttpRequestBaseResult(httpPost);
		if (result == null) {
			return null;
		}
		JSONObject resultJson = new JSONObject(result);
		if (resultJson.has("tagid_list")) {
			return resultJson.getJSONArray("tagid_list");
		}
		return null;
	}
}
