package ua.kpi.testingsystem.web.commands.tutor;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ua.kpi.testingsystem.beans.Question;
import ua.kpi.testingsystem.beans.Test;
import ua.kpi.testingsystem.beans.User;
import ua.kpi.testingsystem.exceptions.BuildException;
import ua.kpi.testingsystem.logic.QuestionLogic;
import ua.kpi.testingsystem.logic.SessionLogic;
import ua.kpi.testingsystem.logic.TestLogic;
import ua.kpi.testingsystem.managers.PageManager;
import ua.kpi.testingsystem.managers.MessageManager;
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
public class EditTestCommand implements Command {

	public CommandResponse execute(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException, IOException {
		final User tutor = SessionLogic.getUser(request);
		Security.ensure(tutor, AccessList.EDIT_TEST);
		CommandResponse commandResponse;
		Integer currentTestID;
		try {
			currentTestID = getCurrentTest(request);
		} catch (BuildException e) {
			return new RedirectResponse(PageManager.PAGE_URL_TUTOR);
		}
		// check if current Test belong to current user
		final Test test = TestLogic.getTest(currentTestID);
		if ((test != null) && (tutor.getIdUser() == test.getIdUser())) {
			// if belong
			commandResponse = prepareResponce(test, request);
		} else {
			commandResponse = new RedirectResponse(PageManager.PAGE_URL_TUTOR);
		}
		return commandResponse;
	}
	
	protected CommandResponse prepareResponce(final Test test, final HttpServletRequest request) {
		final List<Question> questions = QuestionLogic.getQuestions(test.getIdTest());
		request.setAttribute(Pages.TUTOR_QUESTIONS, questions);
		request.setAttribute(Pages.QUESTIONS_COUNT, questions.size());
		// remember current Test id in session
		request.getSession().setAttribute(Pages.SESSION_CURRENT_TEST_ID, test.getIdTest());
		request.setAttribute(Pages.CURRENT_TEST_NAME, test.getName());
		return new ForwardResponse(PageManager.PAGE_PATH_EDIT_TEST);
	}

	private int getCurrentTest(final HttpServletRequest request) {
		final HttpSession session = request.getSession();
		Integer currentTestID;
		try {
			currentTestID = Integer.parseInt(request
					.getParameter(Pages.SELECTED_TEST_ID));
		} catch (NumberFormatException ex) {
			currentTestID = null;
		}
		if (currentTestID == null) {
			currentTestID = (Integer) session
					.getAttribute(Pages.SESSION_CURRENT_TEST_ID);
			if (currentTestID == null) {
				throw new BuildException(MessageManager.getInstance()
						.getProperty(MessageManager.INVALID_TEST_ID));
			}
		}
		return currentTestID;
	}

}
