package ua.kpi.testingsystem.web.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import ua.kpi.testingsystem.managers.ConfigurationManager;
import ua.kpi.testingsystem.managers.MessageManager;
import ua.kpi.testingsystem.web.commands.helpers.Pages;

public class LanguageFilter implements Filter {

	private static final Logger LOGGER = Logger.getLogger(LanguageFilter.class);

	public LanguageFilter() {
		LOGGER.info(MessageManager.getInstance().getProperty(
				MessageManager.INFO_STARTED));
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession();
		String language = (String) session.getAttribute(Pages.SESSION_LANGUAGE);
		if (language == null) {
			session.setAttribute(
					Pages.SESSION_LANGUAGE,
					ConfigurationManager.getInstance().getProperty(
							ConfigurationManager.DEFAULT_LANGUAGE));
		}
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
