package ua.kpi.testingsystem.web.filters;

import java.io.IOException;
import java.util.HashSet;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import ua.kpi.testingsystem.beans.User;
import ua.kpi.testingsystem.managers.PageManager;
import ua.kpi.testingsystem.managers.MessageManager;
import ua.kpi.testingsystem.security.UserType;
import ua.kpi.testingsystem.web.commands.helpers.Pages;

public class LoginFilter implements Filter {
	
	private static final Logger LOGGER = Logger.getLogger(LoginFilter.class);
	private HashSet<String> freeAccess;

	public void init(FilterConfig fConfig) throws ServletException {
		
		freeAccess = new HashSet<String>();
		freeAccess.add("/"	+ PageManager.getInstance().getProperty(
				PageManager.PAGE_URL_LOGIN));
		freeAccess.add("/" + PageManager.getInstance().getProperty(
				PageManager.PAGE_URL_CHANGE_LANGUAGE));
		freeAccess.add(PageManager.getInstance().getProperty(
				PageManager.IMG_UA));
		freeAccess.add(PageManager.getInstance().getProperty(
				PageManager.IMG_EN));
		freeAccess.add(PageManager.getInstance().getProperty(
				PageManager.PAGE_PATH_LOGIN));
		LOGGER.info(MessageManager.getInstance().getProperty(
				MessageManager.INFO_STARTED));
	}

	public LoginFilter() {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession httpSession = httpRequest.getSession();
		User user = (User) httpSession.getAttribute(Pages.SESSION_USER);
		String servletPath = httpRequest.getServletPath();
		if ((freeAccess.contains(servletPath)) || (checkUser(user))) {
			// pass the request along the filter chain
			chain.doFilter(request, response);
		} else {
			httpRequest.getRequestDispatcher(
					PageManager.getInstance().getProperty(
							PageManager.PAGE_PATH_LOGIN)).forward(
					request, response);
		}
	}

	private boolean checkUser(User user) {
		boolean isAuthorized;
		if (user == null) {
			isAuthorized = false;
		} else if (user.getType() == UserType.ANONYMOUS) {
			isAuthorized = false;
		} else {
			isAuthorized = true;
		}
		return isAuthorized;
	}

}
