package ua.kpi.testingsystem.web.commands.tutor;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ua.kpi.testingsystem.beans.Test;
import ua.kpi.testingsystem.beans.User;
import ua.kpi.testingsystem.dao.factories.DAOFactoryManager;
import ua.kpi.testingsystem.dao.interfaces.TestDAO;
import ua.kpi.testingsystem.exceptions.BuildException;
import ua.kpi.testingsystem.logic.SessionLogic;
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
public class CreateTestCommand implements Command {

	public CommandResponse execute(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException, IOException {
		final User tutor = SessionLogic.getUser(request);
		Security.ensure(tutor, AccessList.CREATE_TEST);
		Test test;
		CommandResponse commandResponse = null;;
		try {
			test = buildTest(request);
		} catch (BuildException e) {
			request.setAttribute(Pages.PARAM_ERROR_MESSAGE, e.getMessage());
			commandResponse = new ForwardResponse(PageManager.PAGE_PATH_USER_ERROR);
			test = null;
		}
		if (test != null) {
			final TestDAO testDao = DAOFactoryManager.getFactory().createTestDAO();
			testDao.insert(test);
			commandResponse = new RedirectResponse(PageManager.PAGE_URL_TUTOR);
		}
		return commandResponse;
	}

	private Test buildTest(final HttpServletRequest request) {
		final Test test = new Test();
		int subjectId;
		final User tutor = SessionLogic.getUser(request);
		test.setIdUser(tutor.getIdUser());
		subjectId = Integer.valueOf(request.getParameter(Pages.SELECTED_SUBJECT_ID));
		test.setIdSubject(subjectId);
		final String testName = request.getParameter(Pages.TEST_CREATE_NAME);
		if ((testName == null) || (testName.isEmpty())) {
			throw new BuildException(MessageManager.getInstance().getProperty(
					MessageManager.TEST_NAME_NULL));
		}
			test.setName(testName);
		return test;
	}

}
