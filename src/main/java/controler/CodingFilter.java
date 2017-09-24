package controler;

import configuration.WxConfiguration;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by cellargalaxy on 17-9-24.
 */
public class CodingFilter implements Filter{
	public void init(FilterConfig filterConfig) throws ServletException {
	
	}
	
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		servletRequest.setCharacterEncoding(WxConfiguration.getConding());           //可以直接设置编码
		servletResponse.setCharacterEncoding(WxConfiguration.getConding());    //也可以读取初始化的参数character
		filterChain.doFilter(servletRequest, servletResponse);
	}
	
	public void destroy() {
	
	}
}
