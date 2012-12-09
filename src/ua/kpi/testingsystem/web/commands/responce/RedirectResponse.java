package ua.kpi.testingsystem.web.commands.responce;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.kpi.testingsystem.managers.PageManager;

/**
 * @author Ivanov Yaroslav
 *
 */
public class RedirectResponse implements CommandResponse {
	
	private String page;
	
	public RedirectResponse(String page) {
		this.page = page;
	}

	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String redirectPage = PageManager.getInstance().getProperty(page);
		response.sendRedirect(redirectPage);
	}

}
