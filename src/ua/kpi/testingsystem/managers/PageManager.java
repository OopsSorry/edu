package ua.kpi.testingsystem.managers;

import java.util.ResourceBundle;

/**
 * @version 1.0 25 квіт. 2011
 * @author Ivanov Yaroslav
 * 
 */
public class PageManager {

	private static PageManager instance = new PageManager();
	private final ResourceBundle resourceBundle;
	private static final String BUNDLE_NAME = "conf.pages";

	public static final String IMG_UA = "IMG_UA";
	public static final String IMG_EN = "IMG_EN";

	// JSP pages
	public static final String PAGE_PATH_USER_ERROR = "PAGE_PATH_ERROR";
	public static final String PAGE_PATH_LOGIN = "PAGE_PATH_LOGIN";
	public static final String PAGE_PATH_STUDENT = "PAGE_PATH_STUDENT";
	public static final String PAGE_PATH_TUTOR = "PAGE_PATH_TUTOR";
	public static final String PAGE_PATH_ACCESS_DENIED = "PAGE_PATH_ACCESS_DENIED";
	public static final String PAGE_PATH_INVALID = "PAGE_PATH_INVALID";
	public static final String PAGE_PATH_EDIT_TEST = "PAGE_PATH_EDIT_TEST";
	public static final String PAGE_PATH_CREATE_QUESTION = "PAGE_PATH_CREATE_QUESTION";
	public static final String PAGE_PATH_DO_TEST = "PAGE_PATH_DO_TEST";
	public static final String PAGE_PATH_FINISH_TEST = "PAGE_PATH_FINISH_TEST";
	public static final String PAGE_PATH_SERVER_ERROR = "PAGE_PATH_SERVER_ERROR";

	// pages URL
	public static final String PAGE_URL_MAIN = "PAGE_URL_MAIN";
	public static final String PAGE_URL_LOGIN = "PAGE_URL_LOGIN";
	public static final String PAGE_URL_TUTOR = "PAGE_URL_TUTOR";
	public static final String PAGE_URL_STUDENT = "PAGE_URL_STUDENT";
	public static final String PAGE_URL_LOGOUT = "PAGE_URL_LOGOUT";
	public static final String PAGE_URL_CREATE_TEST = "PAGE_URL_CREATE_TEST";
	public static final String PAGE_URL_ETID_TEST = "PAGE_URL_ETID_TEST";
	public static final String PAGE_URL_ADD_QUESTION = "PAGE_URL_ADD_QUESTION";
	public static final String PAGE_URL_CREATE_QUESTION = "PAGE_URL_CREATE_QUESTION";
	public static final String PAGE_URL_START_TEST = "PAGE_URL_START_TEST";
	public static final String PAGE_URL_DO_TEST = "PAGE_URL_DO_TEST";
	public static final String PAGE_URL_DELETE_QUESTION = "PAGE_URL_DELETE_QUESTION";
	public static final String PAGE_URL_DELETE_TEST = "PAGE_URL_DELETE_TEST";
	public static final String PAGE_URL_CHANGE_LANGUAGE = "PAGE_URL_CHANGE_LANGUAGE";
	public static final String PAGE_URL_NO_COMMAND = "PAGE_URL_NO_COMMAND";
	public static final String PAGE_URL_SERVER_ERROR = "PAGE_URL_SERVER_ERROR";
	public static final String PAGE_URL_ENABLE_TEST = "PAGE_URL_ENABLE_TEST";
	public static final String PAGE_URL_DISABLE_TEST = "PAGE_URL_DISABLE_TEST";

	protected PageManager() {
		resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);
	}

	public static PageManager getInstance() {
		return instance;
	}

	public String getProperty(final String key) {
		return resourceBundle.getString(key);
	}

}
