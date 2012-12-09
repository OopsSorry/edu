package ua.kpi.testingsystem.managers;

import java.util.ResourceBundle;

/**
 * @author Ivanov Yaroslav
 *
 */
public class MessageManager {
	
	private static MessageManager instance = new MessageManager();
	private static final String BUNDLE_NAME = "conf.messages";
	private ResourceBundle resourceBundle;
	
	public static final String INFO_STARTED = "INFO_STARTED";
	public static final String DATASOURCE_INVALID_NAME = "DATASOURCE_INVALID_NAME";
	public static final String INVALID_LANGUAGE = "INVALID_LANGUAGE";
	
	public static final String INVALID_USER = "INVALID_USER";
	public static final String INVALID_PAGE = "INVALID_PAGE";
	public static final String ACCESS_DENIED = "ACCESS_DENIED";
	public static final String UNAVAILABLE_TEST = "UNAVAILABLE_TEST";
	public static final String INVALID_TEST_ID = "INVALID_TEST_ID";
	
	// Test creation
	public static final String TEST_NAME_NULL = "TEST_NAME_NULL";
	public static final String INVALID_SUBJECT_ID = "INVALID_SUBJECT_ID";
	public static final String INVALID_ANSWER_COUNT = "INVALID_ANSWER_COUNT";
	
	protected MessageManager() {
		resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);
	}
	
	public static MessageManager getInstance() {
		return instance;
	}
	
	public String getProperty(String key) {
		return resourceBundle.getString(key);
	}

}
