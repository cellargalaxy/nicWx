package wx;

import accessToken.AccessTokenApi;
import accessToken.AccessTokenApiImpl;
import configuration.WxConfiguration;
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
public class WxApiImpl extends Thread implements WxApi {
	private static final Logger LOGGER = Logger.getLogger(WxApiImpl.class.getName());
	
	private final AccessTokenApi accessTokenApi;
	private final OpenIdApi openIdApi;
	private final TagApi tagApi;
	private final TemplateApi templateApi;
	
	private volatile String accessToken;
	private boolean runable;
	
	private static final AccessTokenApi ACCESS_TOKEN_API = new AccessTokenApiImpl();
	private static final OpenIdApi OPEN_ID_API = new OpenIdApiImpl();
	private static final TagApi TAG_API = new TagApiImpl();
	private static final TemplateApi TEMPLATE_API = new TemplateApiImpl();
	public static final WxApiImpl WX_API = new WxApiImpl(ACCESS_TOKEN_API, OPEN_ID_API, TAG_API, TEMPLATE_API);
	
	
	public WxApiImpl(AccessTokenApi accessTokenApi, OpenIdApi openIdApi, TagApi tagApi, TemplateApi templateApi) {
		this.accessTokenApi = accessTokenApi;
		this.openIdApi = openIdApi;
		this.tagApi = tagApi;
		this.templateApi = templateApi;
		runable = true;
		setName("微信服务号线程");
		flushAccessToken();
	}
	
	@Override
	public void interrupt() {
		runable = false;
		super.interrupt();
	}
	
	@Override
	public void run() {
		do {
			try {
				Thread.sleep(WxConfiguration.getFlushTime());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			flushAccessToken();
		} while (runable || isInterrupted());
	}
	
	public String flushAccessToken() {
		String accessToken = accessTokenApi.flushAccessToken();
		if (accessToken != null) {
			this.accessToken = accessToken;
			LOGGER.info("成功刷新accessToken：" + accessToken);
		} else {
			LOGGER.info("失败刷新accessToken");
		}
		return accessToken;
	}
	
	public int createTag(String tagName) {
		return tagApi.createTag(accessToken, tagName);
	}
	
	public boolean deleteTag(int tagId) {
		return tagApi.deleteTag(accessToken, tagId);
	}
	
	public JSONArray getTags() {
		return tagApi.getTags(accessToken);
	}
	
	public JSONArray getTagOpenIds(int tagId) {
		return tagApi.getTagOpenIds(accessToken, tagId);
	}
	
	public boolean addTagIntoOpenId(int tagId, JSONArray openIds) {
		return tagApi.addTagIntoOpenId(accessToken, tagId, openIds);
	}
	
	public boolean addTagIntoOpenId(int tagId, String openId) {
		JSONArray openIds = new JSONArray();
		openIds.put(openId);
		return addTagIntoOpenId(tagId, openIds);
	}
	
	public boolean deleteTagFromOpenId(int tagId, JSONArray openIds) {
		return tagApi.deleteTagFromOpenId(accessToken, tagId, openIds);
	}
	
	public boolean deleteTagFromOpenId(int tagId, String openId) {
		JSONArray openIds = new JSONArray();
		openIds.put(openId);
		return deleteTagFromOpenId(tagId, openIds);
	}
	
	public JSONArray getOpenIdTags(String openId) {
		return tagApi.getOpenIdTags(accessToken, openId);
	}
	
	public JSONArray getOpenIds() {
		return openIdApi.getOpenIds(accessToken);
	}
	
	public boolean sendTemplate(String openId, String templateId, String url, JSONObject data) {
		return templateApi.sendTemplate(accessToken, openId, templateId, url, data);
	}
	
	public JSONArray sendTemplate2OpenIds(JSONArray openIds, String templateId, String url, JSONObject data) {
		JSONArray failOpenIds = new JSONArray();
		for (Object openId : openIds) {
			if (!templateApi.sendTemplate(accessToken, openId.toString(), templateId, url, data)) {
				failOpenIds.put(openId);
			}
		}
		return failOpenIds;
	}
}
