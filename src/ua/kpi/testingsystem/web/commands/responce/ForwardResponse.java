package ua.kpi.testingsystem.web.commands.responce;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.kpi.testingsystem.managers.PageManager;

/**
 * @author Ivanov Yaroslav
 *
 */
public class ForwardResponse implements CommandResponse {
	
	private String page;
	
	public ForwardResponse(String page) {
		this.page = page;
	}

	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String forwardPage = PageManager.getInstance().getProperty(page);
		request.getRequestDispatcher(forwardPage).forward(request, response);
		
	}
	
}
