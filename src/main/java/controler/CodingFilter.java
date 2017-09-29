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
		servletRequest.setCharacterEncoding(WxConfiguration.getCoding());
		servletResponse.setCharacterEncoding(WxConfiguration.getCoding());
		filterChain.doFilter(servletRequest, servletResponse);
	}
	
	public void destroy() {
	
	}
}
