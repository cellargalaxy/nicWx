package controler;

import org.json.JSONArray;
import org.json.JSONObject;
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
		WxApi wxApi = WxApiImpl.WX_API;
		JSONArray tags = wxApi.getTags();
		for (Object tag : tags) {
			JSONObject jsonObject = (JSONObject) tag;
			if (jsonObject.get("name").equals(tagName)) {
				writer.write(wxApi.getTagOpenIds(jsonObject.getInt("id")).toString());
				break;
			}
		}
		writer.close();
	}
}
