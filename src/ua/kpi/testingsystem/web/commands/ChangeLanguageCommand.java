package ua.kpi.testingsystem.web.commands;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ua.kpi.testingsystem.managers.PageManager;
import ua.kpi.testingsystem.web.commands.helpers.Pages;
import ua.kpi.testingsystem.web.commands.responce.CommandResponse;
import ua.kpi.testingsystem.web.commands.responce.DirectRedirect;
import ua.kpi.testingsystem.web.commands.responce.RedirectResponse;

/**
 * @author Ivanov Yaroslav
 *
 */
public class ChangeLanguageCommand implements Command {
	
	private enum AvaliableLanguages{
		UA("ua"), EN("en");
		
		private String language;
		
		private AvaliableLanguages(String language) {
			this.language = language;
		}
		
		public String getLanguage() {
			return language;
		}
	}

	public CommandResponse execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String language = request.getParameter(Pages.PARAM_CHANGE_LANGUAGE);
		if((language != null) && (!language.isEmpty())) {
			for(AvaliableLanguages avaliableLang : AvaliableLanguages.values()) {
				if (language.equalsIgnoreCase(avaliableLang.getLanguage())) {
					request.getSession().setAttribute(Pages.SESSION_LANGUAGE, language);
					break;
				}
			}
		}
		String preferredPage = request.getParameter(Pages.PARAM_PREFERRED_PAGE);
		CommandResponse commandResponse = null;
		if (preferredPage == null) {
			commandResponse = new RedirectResponse(PageManager.PAGE_URL_MAIN);
		} else {
			commandResponse = new DirectRedirect(preferredPage);
		}
		return commandResponse;
	}
	
}
