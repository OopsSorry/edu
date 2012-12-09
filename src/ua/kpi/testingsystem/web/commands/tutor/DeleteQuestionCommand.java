package ua.kpi.testingsystem.web.commands.tutor;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ua.kpi.testingsystem.beans.User;
import ua.kpi.testingsystem.logic.QuestionLogic;
import ua.kpi.testingsystem.logic.SessionLogic;
import ua.kpi.testingsystem.managers.PageManager;
import ua.kpi.testingsystem.security.AccessList;
import ua.kpi.testingsystem.security.Security;
import ua.kpi.testingsystem.web.commands.Command;
import ua.kpi.testingsystem.web.commands.helpers.Pages;
import ua.kpi.testingsystem.web.commands.responce.CommandResponse;
import ua.kpi.testingsystem.web.commands.responce.RedirectResponse;

/**
 * @version 1.0 17 трав. 2011
 * @author Ivanov Yaroslav
 * 
 */

public class DeleteQuestionCommand implements Command {

	public CommandResponse execute(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException, IOException {
		final User user = SessionLogic.getUser(request);
		Security.ensure(user, AccessList.EDIT_TEST);
		final HttpSession session = request.getSession(false);
		final int currentTestId = (Integer) session.getAttribute(
				Pages.SESSION_CURRENT_TEST_ID);
		final int idQuestion = Integer.parseInt(request.getParameter(
				Pages.QUESTION_TO_DELETE));
		QuestionLogic.deleteQuestion(idQuestion, currentTestId);
		return new RedirectResponse(PageManager.PAGE_URL_ETID_TEST);
	}

}
