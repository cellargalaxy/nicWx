package util;

import configuration.WxConfiguration;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * Created by cellargalaxy on 17-9-23.
 */
public class HttpRequestBaseResultGet {
	private static final CloseableHttpClient HTTP_CLIENT = HttpClients.createDefault();
	
	public static final String getHttpRequestBaseResult(HttpRequestBase httpRequestBase) {
		try {
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(WxConfiguration.getSocketTimeout()).setConnectTimeout(WxConfiguration.getConnectTimeout()).build();
			httpRequestBase.setConfig(requestConfig);
			HttpResponse httpResponse = HTTP_CLIENT.execute(httpRequestBase);
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity entity = httpResponse.getEntity();
				return EntityUtils.toString(entity, WxConfiguration.getConding());
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
