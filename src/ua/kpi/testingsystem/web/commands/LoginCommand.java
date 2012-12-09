package ua.kpi.testingsystem.web.commands;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ua.kpi.testingsystem.logic.LoginLogic;
import ua.kpi.testingsystem.logic.SessionLogic;
import ua.kpi.testingsystem.managers.PageManager;
import ua.kpi.testingsystem.managers.MessageManager;
import ua.kpi.testingsystem.web.commands.helpers.Pages;
import ua.kpi.testingsystem.web.commands.responce.CommandResponse;
import ua.kpi.testingsystem.web.commands.responce.ForwardResponse;
import ua.kpi.testingsystem.web.commands.responce.RedirectResponse;
import ua.kpi.testingsystem.beans.User;

public class LoginCommand implements Command {

	public CommandResponse execute(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException, IOException {
		CommandResponse commandResponce;
		final HttpSession session = SessionLogic.createNewSession(request);
		// read input login and password
		final String login = (String) request.getParameter(Pages.PARAM_LOGIN);
		final String password = (String) request.getParameter(Pages.PARAM_PASSWORD);
		// check if user exist
		final User user = LoginLogic.getUser(login, password);
		switch (user.getType()) {
		case TUTOR:
			session.setAttribute(Pages.SESSION_USER, user);
			commandResponce = new RedirectResponse(
					PageManager.PAGE_URL_TUTOR);
			break;
		case STUDENT:
			session.setAttribute(Pages.SESSION_USER, user);
			commandResponce = new RedirectResponse(
					PageManager.PAGE_URL_STUDENT);
			break;
		default:
			request.setAttribute(Pages.PARAM_INVALID_USER, MessageManager
					.getInstance().getProperty(MessageManager.INVALID_USER));
			commandResponce = new ForwardResponse(
					PageManager.PAGE_PATH_LOGIN);
		}
		return commandResponce;
	}

}