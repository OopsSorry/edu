package ua.kpi.testingsystem.web.commands.responce;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @version 1.0 22 трав. 2011
 * @author Ivanov Yaroslav
 *
 */
public class DirectRedirect implements CommandResponse {

	
	private String page;
	
	public DirectRedirect(String page) {
		this.page = page;
	}

	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		response.sendRedirect(page);
	}

}
