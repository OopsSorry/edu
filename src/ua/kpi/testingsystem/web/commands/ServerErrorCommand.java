package ua.kpi.testingsystem.web.commands;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.kpi.testingsystem.managers.PageManager;
import ua.kpi.testingsystem.web.commands.responce.CommandResponse;
import ua.kpi.testingsystem.web.commands.responce.ForwardResponse;

/**
 * @author Ivanov Yaroslav
 *
 */
public class ServerErrorCommand implements Command {

	public CommandResponse execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		return new ForwardResponse(PageManager.PAGE_PATH_SERVER_ERROR);
	}

}
