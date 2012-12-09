package ua.kpi.testingsystem.web.commands;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.kpi.testingsystem.managers.PageManager;
import ua.kpi.testingsystem.web.commands.helpers.Pages;
import ua.kpi.testingsystem.web.commands.responce.CommandResponse;
import ua.kpi.testingsystem.web.commands.responce.RedirectResponse;

/**
 * @author Ivanov Yaroslav
 * 
 */
public class LogoutCommand implements Command {

	public CommandResponse execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String language = (String) session.getAttribute(Pages.SESSION_LANGUAGE);
		session.invalidate();
		request.getSession().setAttribute(Pages.SESSION_LANGUAGE, language);
		return new RedirectResponse(PageManager.PAGE_URL_MAIN);
	}

}
