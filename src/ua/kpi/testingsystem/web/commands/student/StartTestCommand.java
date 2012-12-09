package ua.kpi.testingsystem.web.commands.student;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ua.kpi.testingsystem.beans.Test;
import ua.kpi.testingsystem.beans.User;
import ua.kpi.testingsystem.logic.SessionLogic;
import ua.kpi.testingsystem.logic.TestLogic;
import ua.kpi.testingsystem.logic.TestState;
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
public class StartTestCommand implements Command {

	public CommandResponse execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		User student = SessionLogic.getUser(request);
		Security.ensure(student, AccessList.PASS_TEST);
		Test test = getTestFromRequest(request);
		CommandResponse commandResponse;
		if((test == null) || (!test.isEnable())) {
			request.setAttribute(
					Pages.PARAM_ERROR_MESSAGE, 
					MessageManager.getInstance().getProperty(
							MessageManager.UNAVAILABLE_TEST));
			commandResponse = new ForwardResponse(PageManager.PAGE_PATH_INVALID);
		} else {
			TestState testState = new TestState(test);
			if (testState.hasQuestions()) {
				request.getSession().setAttribute(Pages.SESSION_TEST_STATE, testState);
				commandResponse = new RedirectResponse(PageManager.PAGE_URL_DO_TEST);
			} else {
				commandResponse = new RedirectResponse(PageManager.PAGE_URL_STUDENT);
			}
		}
		return commandResponse;
	}
	
	private static Test getTestFromRequest(HttpServletRequest request) {
		int selectedTest = Integer.parseInt(request.getParameter(Pages.SELECTED_TEST_ID));
		Test test = TestLogic.getTest(selectedTest);
		return test;
	}
	
}
