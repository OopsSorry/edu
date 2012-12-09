package ua.kpi.testingsystem.web.commands.student;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ua.kpi.testingsystem.beans.User;
import ua.kpi.testingsystem.logic.SessionLogic;
import ua.kpi.testingsystem.logic.TestState;
import ua.kpi.testingsystem.managers.PageManager;
import ua.kpi.testingsystem.security.AccessList;
import ua.kpi.testingsystem.security.Security;
import ua.kpi.testingsystem.web.commands.Command;
import ua.kpi.testingsystem.web.commands.helpers.Pages;
import ua.kpi.testingsystem.web.commands.responce.CommandResponse;
import ua.kpi.testingsystem.web.commands.responce.ForwardResponse;
import ua.kpi.testingsystem.web.commands.responce.RedirectResponse;

/**
 * @author Ivanov Yaroslav
 * 
 */
public class DoTestCommand implements Command {

	public CommandResponse execute(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException,
			IOException {
		final User student = SessionLogic.getUser(request);
		Security.ensure(student, AccessList.PASS_TEST);

		CommandResponse commandResponse = null;
		final TestState testState = SessionLogic.getTestState(request);
		if (testState == null) {
			commandResponse = new RedirectResponse(PageManager.PAGE_URL_STUDENT);
		} else {
			final Integer questionId = getQuestionId(request);
			if ((questionId == null)
					|| (!questionId.equals(testState.getQuestionId()))) {
				// if response contain wrong question id show question again
				request.setAttribute(Pages.CURRENT_QUESTION,
						testState.getCurrentQuestion());
				commandResponse = new ForwardResponse(
						PageManager.PAGE_PATH_DO_TEST);
			} else if (testState.hasMoreQuestions()) {
				// show next question
				request.setAttribute(Pages.CURRENT_QUESTION,
						testState.getNextQuestion());
				commandResponse = new ForwardResponse(
						PageManager.PAGE_PATH_DO_TEST);
			} else {
				// else end of test
				request.getSession().removeAttribute(Pages.SESSION_TEST_STATE);
				commandResponse = new ForwardResponse(
						PageManager.PAGE_PATH_FINISH_TEST);
			}
		}
		return commandResponse;
	}

	private Integer getQuestionId(final HttpServletRequest request) {
		Integer questionId;
		final String paramQuestionId = request.getParameter(
				Pages.CURRENT_QUESTION_ID);
		if (paramQuestionId == null) {
			questionId = null;
		} else {
			questionId = Integer.parseInt(paramQuestionId);
		}
		return questionId;
	}
	
}
