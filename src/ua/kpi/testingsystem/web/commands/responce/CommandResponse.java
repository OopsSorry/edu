package ua.kpi.testingsystem.web.commands.responce;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Ivanov Yaroslav
 *
 */
public interface CommandResponse {

	public void execute(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException;
	
}
