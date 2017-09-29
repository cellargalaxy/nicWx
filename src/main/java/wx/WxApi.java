package wx;

import openId.OpenIdApi;
import org.json.JSONArray;
import org.json.JSONObject;
import tag.TagApi;
import template.TemplateApi;

/**
 * Created by cellargalaxy on 17-9-23.
 */
public interface WxApi extends OpenIdApi, TagApi, TemplateApi{
	void init();
	void destroy();
}
