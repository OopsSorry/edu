package ua.kpi.testingsystem.managers;

import java.util.ResourceBundle;

/**
 * @version 1.0 25 квіт. 2011
 * @author Ivanov Yaroslav
 * 
 */
public class ConfigurationManager {

	private static ConfigurationManager instance = new ConfigurationManager();
	private ResourceBundle resourceBundle;
	private static final String BUNDLE_NAME = "conf.config";

	public static final String DEFAULT_LANGUAGE = "DEFAULT_LANGUAGE";
	public static final String DATA_SOURCE = "DATA_SOURCE";

	// database
	public static final String DATABASE_DRIVER_NAME = "DATABASE_DRIVER_NAME";
	public static final String DATABASE_URL = "DATABASE_URL";
	public static final String DATABASE_LOGIN = "DATABASE_LOGIN";
	public static final String DATABASE_PASSWORD = "DATABASE_PASSWORD";

	protected ConfigurationManager() {
		resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);
	}

	public static ConfigurationManager getInstance() {
		return instance;
	}

	public String getProperty(String key) {
		return resourceBundle.getString(key);
	}

}
