package ua.kpi.testingsystem.web.commands;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.kpi.testingsystem.web.commands.responce.CommandResponse;

/**
 * @version 1.0 5 трав. 2011
 * @author Ivanov Yaroslav
 * 
 */
public interface Command {

	public CommandResponse execute(HttpServletRequest request,
			HttpServletResponse response) 
		throws ServletException, IOException;

}
