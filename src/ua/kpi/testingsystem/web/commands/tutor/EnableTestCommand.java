package ua.kpi.testingsystem.web.commands.tutor;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.kpi.testingsystem.beans.User;
import ua.kpi.testingsystem.logic.SessionLogic;
import ua.kpi.testingsystem.logic.TestLogic;
import ua.kpi.testingsystem.managers.PageManager;
import ua.kpi.testingsystem.security.AccessList;
import ua.kpi.testingsystem.security.Security;
import ua.kpi.testingsystem.web.commands.Command;
import ua.kpi.testingsystem.web.commands.helpers.Pages;
import ua.kpi.testingsystem.web.commands.responce.CommandResponse;
import ua.kpi.testingsystem.web.commands.responce.RedirectResponse;

public class EnableTestCommand implements Command {

	public CommandResponse execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		final User user = SessionLogic.getUser(request);
		Security.ensure(user, AccessList.EDIT_TEST);
		
		int testId = Integer.parseInt(request.getParameter(Pages.SELECTED_TEST_ID));
		TestLogic.setTestState(testId, user.getIdUser(), true);
		return new RedirectResponse(PageManager.PAGE_URL_TUTOR);
	}

}
