package wx;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by cellargalaxy on 17-9-23.
 */
public interface WxApi {
	String flushAccessToken();
	
	JSONArray getOpenIds();
	
	int createTag(String tagName);
	
	boolean deleteTag(int tagId);
	
	JSONArray getTags();
	
	JSONArray getTagOpenIds(int tagId);
	
	boolean addTagIntoOpenId(int tagId, JSONArray openIds);
	
	boolean addTagIntoOpenId(int tagId, String openId);
	
	boolean deleteTagFromOpenId(int tagId, JSONArray openIds);
	
	boolean deleteTagFromOpenId(int tagId, String openId);
	
	JSONArray getOpenIdTags(String openId);
	
	boolean sendTemplate(String openId, String templateId, String url, JSONObject data);
	
	JSONArray sendTemplate2OpenIds(JSONArray openIds, String templateId, String url, JSONObject data);
}
