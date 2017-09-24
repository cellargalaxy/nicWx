package openId;

import org.json.JSONArray;

/**
 * Created by cellargalaxy on 17-9-23.
 */
public interface OpenIdApi {
	JSONArray getOpenIds(String accessToken);
}
