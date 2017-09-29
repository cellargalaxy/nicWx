package wx;

import accessToken.AccessTokenApi;
import accessToken.AccessTokenApiThread;
import openId.OpenIdApi;
import openId.OpenIdApiImpl;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import tag.TagApi;
import tag.TagApiImpl;
import template.TemplateApi;
import template.TemplateApiImpl;

/**
 * Created by cellargalaxy on 17-9-23.
 */
public class WxApiImpl implements WxApi {
	private static final Logger LOGGER = Logger.getLogger(WxApiImpl.class.getName());
	
	private final AccessTokenApi accessTokenApi;
	private final OpenIdApi openIdApi;
	private final TagApi tagApi;
	private final TemplateApi templateApi;
	
	private static final AccessTokenApi ACCESS_TOKEN_API = new AccessTokenApiThread();
	private static final OpenIdApi OPEN_ID_API = new OpenIdApiImpl(ACCESS_TOKEN_API);
	private static final TagApi TAG_API = new TagApiImpl(ACCESS_TOKEN_API);
	private static final TemplateApi TEMPLATE_API = new TemplateApiImpl(ACCESS_TOKEN_API);
	public static final WxApiImpl WX_API = new WxApiImpl(ACCESS_TOKEN_API, OPEN_ID_API, TAG_API, TEMPLATE_API);
	
	public WxApiImpl(AccessTokenApi accessTokenApi, OpenIdApi openIdApi, TagApi tagApi, TemplateApi templateApi) {
		this.accessTokenApi = accessTokenApi;
		this.openIdApi = openIdApi;
		this.tagApi = tagApi;
		this.templateApi = templateApi;
	}
	
	public JSONObject getOpenIds() {
		return openIdApi.getOpenIds();
	}
	
	public JSONObject sendTemplate(String openId, String templateId, String url, JSONObject data) {
		return templateApi.sendTemplate(openId,templateId,url,data);
	}
	
	public JSONObject createTag(String tagName) {
		return tagApi.createTag(tagName);
	}
	
	public JSONObject deleteTag(int tagId) {
		return tagApi.deleteTag(tagId);
	}
	
	public JSONObject getTags() {
		return tagApi.getTags();
	}
	
	public JSONObject getTagOpenIds(int tagId) {
		return tagApi.getTagOpenIds(tagId);
	}
	
	public JSONObject addTagIntoOpenId(int tagId, JSONArray openIds) {
		return tagApi.addTagIntoOpenId(tagId,openIds);
	}
	
	public JSONObject deleteTagFromOpenId(int tagId, JSONArray openIds) {
		return tagApi.deleteTagFromOpenId(tagId,openIds);
	}
	
	public JSONObject getOpenIdTags(String openId) {
		return tagApi.getOpenIdTags(openId);
	}
	
	public void init() {
		accessTokenApi.start();
	}
	
	public void destroy() {
		accessTokenApi.interrupt();
	}
}
