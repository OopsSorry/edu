package ua.kpi.testingsystem.web.commands.student;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
public class StudentCommand implements Command {

	public CommandResponse execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		User student = SessionLogic.getUser(request);
		Security.ensure(student, AccessList.PASS_TEST);
		
		List<Subject> subjects = TestLogic.getAvailableTests();
		request.setAttribute(Pages.STUDENT_SUBJECTS, subjects);
		return new ForwardResponse(PageManager.PAGE_PATH_STUDENT);
	}

}
