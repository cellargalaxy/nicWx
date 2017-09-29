package template;

import org.json.JSONObject;

/**
 * Created by cellargalaxy on 17-9-23.
 */
public interface TemplateApi {
	JSONObject sendTemplate(String openId, String templateId, String url, JSONObject data);
}
