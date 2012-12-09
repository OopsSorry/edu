package ua.kpi.testingsystem.web.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.kpi.testingsystem.managers.PageManager;
import ua.kpi.testingsystem.managers.MessageManager;
import ua.kpi.testingsystem.web.commands.helpers.Pages;
import ua.kpi.testingsystem.web.commands.responce.CommandResponse;
import ua.kpi.testingsystem.web.commands.responce.ForwardResponse;

/**
 * @version 1.0 5 трав. 2011
 * @author Ivanov Yaroslav
 * 
 */
public class NoCommand implements Command {

	public CommandResponse execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute(Pages.PARAM_ERROR_MESSAGE, MessageManager
				.getInstance().getProperty(MessageManager.INVALID_PAGE));
		return new ForwardResponse(PageManager.PAGE_PATH_INVALID);
	}
}
