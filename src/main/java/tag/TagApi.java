package tag;

import org.json.JSONArray;

/**
 * Created by cellargalaxy on 17-9-23.
 */
public interface TagApi {
	int createTag(String accessToken, String tagName);
	
	boolean deleteTag(String accessToken, int tagId);
	
	JSONArray getTags(String accessToken);
	
	JSONArray getTagOpenIds(String accessToken, int tagId);
	
	boolean addTagIntoOpenId(String accessToken, int tagId, JSONArray openIds);
	
	boolean deleteTagFromOpenId(String accessToken, int tagId, JSONArray openIds);
	
	JSONArray getOpenIdTags(String accessToken, String openId);
}
