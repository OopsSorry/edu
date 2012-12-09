package ua.kpi.testingsystem.logic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import ua.kpi.testingsystem.beans.User;
import ua.kpi.testingsystem.web.commands.helpers.Pages;

/**
 * @author Ivanov Yaroslav
 *
 */
public final class SessionLogic {
	
	protected SessionLogic() {
	}
	
	public static HttpSession createNewSession(final HttpServletRequest request) {
		final HttpSession session = request.getSession(false);
		if (session != null) {
			String language = (String) session.getAttribute(Pages.SESSION_LANGUAGE);
			session.invalidate();
			request.getSession().setAttribute(Pages.SESSION_LANGUAGE, language);
		}
		return request.getSession();
	}
	
	public static User getUser(final HttpServletRequest request) {
		final HttpSession session = request.getSession(false);
		return (User) session.getAttribute(Pages.SESSION_USER);
	}
	
	public static TestState getTestState(HttpServletRequest request) {
		final HttpSession session = request.getSession(false);
		return (TestState) session.getAttribute(Pages.SESSION_TEST_STATE);
	}

}
