package controler;

import wx.WxApiImpl;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by cellargalaxy on 17-9-24.
 */
public class WxListener implements ServletContextListener{
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		WxApiImpl.WX_API.init();
	}
	
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		WxApiImpl.WX_API.destroy();
	}
}
