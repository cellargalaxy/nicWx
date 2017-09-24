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
public class SendTemplateServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Writer writer = resp.getWriter();
		JSONObject result = new JSONObject();
		String openIdsString = req.getParameter("openIds");
		String templateId = req.getParameter("templateId");
		String url = req.getParameter("url");
		String dataString = req.getParameter("data");
		if (openIdsString == null || templateId == null || dataString == null) {
			result.put("result", false);
			writer.write(result.toString());
			writer.close();
			return;
		}
		JSONArray openIds = new JSONArray(openIdsString);
		JSONObject data = new JSONObject(dataString);
		WxApi wxApi = WxApiImpl.WX_API;
		result.put("result", wxApi.sendTemplate2OpenIds(openIds, templateId, url, data));
		writer.write(result.toString());
		writer.close();
	}
}
