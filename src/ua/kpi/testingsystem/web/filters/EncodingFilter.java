package ua.kpi.testingsystem.web.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.apache.log4j.Logger;
import ua.kpi.testingsystem.managers.MessageManager;

public class EncodingFilter implements Filter {

	private static final Logger LOGGER = Logger.getLogger(EncodingFilter.class);
	private static final String ENCODING_DEFAULT = "UTF-8";
	private static final String ENCODING_INIT_PARAM_NAME = "encoding";
	private static final String CONTENT_TYPE_DEFAULT = "text/html; charset=UTF-8";
	private static final String CONTENT_INIT_PARAM_NAME = "contentType";
	private String encoding;
	private String contentType;

	public EncodingFilter() {
		LOGGER.info(MessageManager.getInstance().getProperty(
				MessageManager.INFO_STARTED));
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding(encoding);
		response.setCharacterEncoding(encoding);
		response.setContentType(contentType);

		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		encoding = fConfig.getInitParameter(ENCODING_INIT_PARAM_NAME);
		if (encoding == null) {
			encoding = ENCODING_DEFAULT;
		}
		contentType = fConfig.getInitParameter(CONTENT_INIT_PARAM_NAME);
		if (contentType == null) {
			contentType = CONTENT_TYPE_DEFAULT;
		}
	}

}
