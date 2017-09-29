package controler;

import org.json.JSONArray;
import org.json.JSONObject;
import tag.TagApi;
import wx.WxApi;
import wx.WxApiImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

/**
 * Created by cellargalaxy on 17-9-24.
 */
public class TagOpenIdsServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Writer writer = resp.getWriter();
		String tagName = req.getParameter("tagName");//?tagName=netview
		if (tagName == null) {
			writer.write("[]");
			writer.close();
			return;
		}
		TagApi tagApi = WxApiImpl.WX_API;
		JSONObject jsonObject=tagApi.getTags();
		if (jsonObject==null || !jsonObject.has("tags")) {
			writer.write("[]");
			writer.close();
			return;
		}
		JSONArray tags=jsonObject.getJSONArray("tags");
		for (Object object : tags) {
			JSONObject tag = (JSONObject) object;
			if (tag.get("name").equals(tagName)) {
				writer.write(tagApi.getTagOpenIds(tag.getInt("id")).toString());
				writer.close();
				return;
			}
		}
		writer.write("[]");
		writer.close();
	}
}
