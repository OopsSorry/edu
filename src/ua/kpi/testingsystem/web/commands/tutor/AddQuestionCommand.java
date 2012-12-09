package ua.kpi.testingsystem.web.commands.tutor;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ua.kpi.testingsystem.beans.User;
import ua.kpi.testingsystem.logic.SessionLogic;
import ua.kpi.testingsystem.managers.PageManager;
import ua.kpi.testingsystem.managers.MessageManager;
import ua.kpi.testingsystem.security.AccessList;
import ua.kpi.testingsystem.security.Security;
import ua.kpi.testingsystem.web.commands.Command;
import ua.kpi.testingsystem.web.commands.helpers.Pages;
import ua.kpi.testingsystem.web.commands.responce.CommandResponse;
import ua.kpi.testingsystem.web.commands.responce.ForwardResponse;

/**
 * @version 1.0 10 трав. 2011
 * @author Ivanov Yaroslav
 * 
 */
public class AddQuestionCommand implements Command {

	public CommandResponse execute(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException, IOException {
		final User tutor = SessionLogic.getUser(request);
		Security.ensure(tutor, AccessList.EDIT_TEST);
		CommandResponse commandResponse;
		boolean answerCountError = false;
		int answerCount = -1;
		try {
			answerCount = Integer.parseInt(request
					.getParameter(Pages.ANSWERS_COUNT));
		} catch (NumberFormatException e) {
			answerCountError = true;
		}
		if ((!answerCountError) && (answerCount > 1)) {
			request.setAttribute(Pages.ANSWERS_COUNT, answerCount);
			commandResponse = new ForwardResponse(PageManager.PAGE_PATH_CREATE_QUESTION);
		} else {
			request.setAttribute(Pages.PARAM_ERROR_MESSAGE, MessageManager.getInstance().getProperty(
					MessageManager.INVALID_ANSWER_COUNT));
			commandResponse = new ForwardResponse(PageManager.PAGE_PATH_USER_ERROR);
		}
		return commandResponse;
	}
}
