package controler;

import configuration.WxConfiguration;
import org.json.JSONObject;
import template.TemplateApi;
import wx.WxApi;
import wx.WxApiImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;

/**
 * Created by cellargalaxy on 17-9-24.
 */
public class SendTemplateServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(req.getInputStream(), WxConfiguration.getCoding()));
		StringBuffer stringBuffer = new StringBuffer("");
		String string;
		while ((string = bufferedReader.readLine()) != null) {
			stringBuffer.append(string);
		}
		bufferedReader.close();
		
		JSONObject jsonObject=new JSONObject(stringBuffer.toString());
		String openId=null;
		String templateId=null;
		String url=null;
		JSONObject data=null;
		if (jsonObject.has("touser")) {
			openId=jsonObject.getString("touser");
		}
		if (jsonObject.has("template_id")) {
			templateId=jsonObject.getString("template_id");
		}
		if (jsonObject.has("url")) {
			url=jsonObject.getString("url");
		}
		if (jsonObject.has("data")) {
			data=jsonObject.getJSONObject("data");
		}
		
		Writer writer = resp.getWriter();
		TemplateApi templateApi=WxApiImpl.WX_API;
		writer.write(templateApi.sendTemplate(openId,templateId,url,data).toString());
		writer.close();
	}
}
