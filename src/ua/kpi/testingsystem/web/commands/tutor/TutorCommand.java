package ua.kpi.testingsystem.web.commands.tutor;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ua.kpi.testingsystem.beans.Subject;
import ua.kpi.testingsystem.beans.User;
import ua.kpi.testingsystem.logic.SessionLogic;
import ua.kpi.testingsystem.logic.TestLogic;
import ua.kpi.testingsystem.managers.PageManager;
import ua.kpi.testingsystem.security.AccessList;
import ua.kpi.testingsystem.security.Security;
import ua.kpi.testingsystem.web.commands.Command;
import ua.kpi.testingsystem.web.commands.helpers.Pages;
import ua.kpi.testingsystem.web.commands.responce.CommandResponse;
import ua.kpi.testingsystem.web.commands.responce.ForwardResponse;

/**
 * @author Ivanov Yaroslav
 * 
 */
public class TutorCommand implements Command {

	public CommandResponse execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		final User tutor = SessionLogic.getUser(request);
		Security.ensure(tutor, AccessList.TUTOR_PAGE);
		cleareSessionParams(request.getSession(false));
		
		final List<Subject> subjects = TestLogic.getTutorTests(tutor.getIdUser());
		request.setAttribute(Pages.SUBJECTS, subjects);
		setAttributes(request);
		return new ForwardResponse(PageManager.PAGE_PATH_TUTOR);
	}
	
	private void cleareSessionParams(HttpSession session) {
		session.removeAttribute(Pages.SESSION_CURRENT_TEST_ID);
	}
	
	private void setAttributes(HttpServletRequest request) {
		request.setAttribute(Pages.EDIT_TEST_ACTION,
				PageManager.getInstance().getProperty(
						PageManager.PAGE_URL_ETID_TEST));
		request.setAttribute(Pages.CREATE_TEST_ACTION,
				PageManager.getInstance().getProperty(
						PageManager.PAGE_URL_CREATE_TEST));
	}

}
