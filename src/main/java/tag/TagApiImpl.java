package tag;

import accessToken.AccessTokenApi;
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
	private final AccessTokenApi accessTokenApi;
	private final String coding;
	
	public TagApiImpl(AccessTokenApi accessTokenApi) {
		this.accessTokenApi = accessTokenApi;
		coding=WxConfiguration.getCoding();
	}
	
	public JSONObject createTag(String tagName) {
		HttpPost httpPost = new HttpPost("https://api.weixin.qq.com/cgi-bin/tags/create?access_token=" + accessTokenApi.getAccessToken());
		httpPost.addHeader("Content-Type", "application/json;charset=" + coding);
		JSONObject tag = new JSONObject();
		tag.put("name", tagName);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("tag", tag);
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
	
	public JSONObject deleteTag(int tagId) {
		HttpPost httpPost = new HttpPost("https://api.weixin.qq.com/cgi-bin/tags/delete?access_token=" + accessTokenApi.getAccessToken());
		httpPost.addHeader("Content-Type", "application/json;charset=" + coding);
		JSONObject tag = new JSONObject();
		tag.put("id", tagId);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("tag", tag);
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
	
	public JSONObject getTags() {
		HttpGet httpGet = new HttpGet("https://api.weixin.qq.com/cgi-bin/tags/get?access_token=" + accessTokenApi.getAccessToken());
		String result = HttpRequestBaseResultGet.getHttpRequestBaseResult(httpGet);
		if (result == null) {
			return null;
		}
		return new JSONObject(result);
	}
	
	public JSONObject getTagOpenIds(int tagId) {
		HttpPost httpPost = new HttpPost("https://api.weixin.qq.com/cgi-bin/user/tag/get?access_token=" + accessTokenApi.getAccessToken());
		httpPost.addHeader("Content-Type", "application/json;charset=" + coding);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("tagid", tagId);
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
	
	public JSONObject addTagIntoOpenId(int tagId, JSONArray openIds) {
		HttpPost httpPost = new HttpPost("https://api.weixin.qq.com/cgi-bin/tags/members/batchtagging?access_token=" + accessTokenApi.getAccessToken());
		httpPost.addHeader("Content-Type", "application/json;charset=" + coding);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("tagid", tagId);
		jsonObject.put("openid_list", openIds);
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
	
	public JSONObject deleteTagFromOpenId(int tagId, JSONArray openIds) {
		HttpPost httpPost = new HttpPost("https://api.weixin.qq.com/cgi-bin/tags/members/batchuntagging?access_token=" + accessTokenApi.getAccessToken());
		httpPost.addHeader("Content-Type", "application/json;charset=" + coding);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("tagid", tagId);
		jsonObject.put("openid_list", openIds);
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
	
	public JSONObject getOpenIdTags(String openId) {
		HttpPost httpPost = new HttpPost("https://api.weixin.qq.com/cgi-bin/tags/getidlist?access_token=" + accessTokenApi.getAccessToken());
		httpPost.addHeader("Content-Type", "application/json;charset=" + coding);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("openid", openId);
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
