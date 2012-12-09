package ua.kpi.testingsystem.web.requesthelper;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import ua.kpi.testingsystem.managers.PageManager;
import ua.kpi.testingsystem.web.commands.ChangeLanguageCommand;
import ua.kpi.testingsystem.web.commands.Command;
import ua.kpi.testingsystem.web.commands.LoginCommand;
import ua.kpi.testingsystem.web.commands.LogoutCommand;
import ua.kpi.testingsystem.web.commands.NoCommand;
import ua.kpi.testingsystem.web.commands.ServerErrorCommand;
import ua.kpi.testingsystem.web.commands.student.DoTestCommand;
import ua.kpi.testingsystem.web.commands.student.StartTestCommand;
import ua.kpi.testingsystem.web.commands.student.StudentCommand;
import ua.kpi.testingsystem.web.commands.tutor.AddQuestionCommand;
import ua.kpi.testingsystem.web.commands.tutor.CreateQuestionCommand;
import ua.kpi.testingsystem.web.commands.tutor.CreateTestCommand;
import ua.kpi.testingsystem.web.commands.tutor.DeleteQuestionCommand;
import ua.kpi.testingsystem.web.commands.tutor.DeleteTestCommand;
import ua.kpi.testingsystem.web.commands.tutor.DisableTestCommand;
import ua.kpi.testingsystem.web.commands.tutor.EditTestCommand;
import ua.kpi.testingsystem.web.commands.tutor.EnableTestCommand;
import ua.kpi.testingsystem.web.commands.tutor.TutorCommand;

/**
 * @version 1.0 5 трав. 2011
 * @author Ivanov Yaroslav
 * 
 */
public class RequestHelper {
	
	private static final Logger LOGGER = Logger.getLogger(RequestHelper.class);
	private static RequestHelper instance = new RequestHelper();
	private HashMap<String, Command> commands = new HashMap<String, Command>();
	

	protected RequestHelper() {
		addCommand(PageManager.PAGE_URL_LOGIN, new LoginCommand());
		addCommand((PageManager.PAGE_URL_TUTOR), new TutorCommand());
		addCommand((PageManager.PAGE_URL_STUDENT), new StudentCommand());
		addCommand((PageManager.PAGE_URL_LOGOUT), new LogoutCommand());
		addCommand((PageManager.PAGE_URL_CREATE_TEST), new CreateTestCommand());
		addCommand((PageManager.PAGE_URL_ETID_TEST), new EditTestCommand());
		addCommand((PageManager.PAGE_URL_ADD_QUESTION), new AddQuestionCommand());
		addCommand((PageManager.PAGE_URL_CREATE_QUESTION), new CreateQuestionCommand());
		addCommand((PageManager.PAGE_URL_START_TEST), new StartTestCommand());
		addCommand((PageManager.PAGE_URL_DO_TEST), new DoTestCommand());
		addCommand((PageManager.PAGE_URL_DELETE_QUESTION), new DeleteQuestionCommand());
		addCommand((PageManager.PAGE_URL_DELETE_TEST), new DeleteTestCommand());
		addCommand((PageManager.PAGE_URL_CHANGE_LANGUAGE), new ChangeLanguageCommand());
		addCommand((PageManager.PAGE_URL_CHANGE_LANGUAGE), new ChangeLanguageCommand());
		addCommand(PageManager.PAGE_URL_ENABLE_TEST, new EnableTestCommand());
		addCommand(PageManager.PAGE_URL_DISABLE_TEST, new DisableTestCommand());
		addCommand((PageManager.PAGE_URL_NO_COMMAND), new NoCommand());
		addCommand((PageManager.PAGE_URL_SERVER_ERROR), new ServerErrorCommand());
	}
	
	private void addCommand(final String strCommand, final Command command) {
		commands.put("/" + PageManager.getInstance().getProperty(strCommand),
				 command);
	}

	public static RequestHelper getInstance() {
		return instance;
	}

	public Command getCommand(final HttpServletRequest request) {
		final String commandName = request.getServletPath();
		LOGGER.debug("commandName = " + commandName);
		Command command = commands.get(commandName);
		if (command == null) {
			command = new NoCommand();
		}
		LOGGER.debug("Command = " + command);
		return command;
	}

}
