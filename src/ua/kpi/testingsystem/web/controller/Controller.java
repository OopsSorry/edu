package ua.kpi.testingsystem.web.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import ua.kpi.testingsystem.exceptions.DAOException;
import ua.kpi.testingsystem.managers.PageManager;
import ua.kpi.testingsystem.managers.MessageManager;
import ua.kpi.testingsystem.web.commands.Command;
import ua.kpi.testingsystem.web.commands.helpers.Pages;
import ua.kpi.testingsystem.web.commands.responce.CommandResponse;
import ua.kpi.testingsystem.web.commands.responce.ForwardResponse;
import ua.kpi.testingsystem.web.requesthelper.RequestHelper;

public class Controller extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(Controller.class);

	public Controller() {
		super();
		LOGGER.info(MessageManager.getInstance().getProperty(
				MessageManager.INFO_STARTED));
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		CommandResponse commandResponse = null;
		try {
			final Command command = RequestHelper.getInstance().getCommand(request);
			commandResponse = command.execute(request, response);
		} catch (ServletException ex) {
			LOGGER.error(ex, ex);
			commandResponse = new ForwardResponse(
					PageManager.PAGE_PATH_SERVER_ERROR);
		} catch (IOException ex) {
			LOGGER.error(ex, ex);
			commandResponse = new ForwardResponse(
					PageManager.PAGE_PATH_SERVER_ERROR);
		} catch (DAOException ex) {
			LOGGER.error(ex, ex);
			commandResponse = new ForwardResponse(
					PageManager.PAGE_PATH_SERVER_ERROR);
		} catch (SecurityException ex) {
			LOGGER.warn(ex, ex);
			request.setAttribute(Pages.PARAM_ERROR_MESSAGE, MessageManager
					.getInstance().getProperty(MessageManager.ACCESS_DENIED));
			commandResponse = new ForwardResponse(
					PageManager.PAGE_PATH_ACCESS_DENIED);
		} catch (Exception ex) {
			LOGGER.error(ex, ex);
			commandResponse = new ForwardResponse(
					PageManager.PAGE_PATH_SERVER_ERROR);
		}
		commandResponse.execute(request, response);
	}

}
