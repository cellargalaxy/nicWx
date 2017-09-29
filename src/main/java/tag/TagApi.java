package tag;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by cellargalaxy on 17-9-23.
 */
public interface TagApi {
	JSONObject createTag(String tagName);
	
	JSONObject deleteTag(int tagId);
	
	JSONObject getTags();
	
	JSONObject getTagOpenIds(int tagId);
	
	JSONObject addTagIntoOpenId(int tagId, JSONArray openIds);
	
	JSONObject deleteTagFromOpenId(int tagId, JSONArray openIds);
	
	JSONObject getOpenIdTags(String openId);
}
