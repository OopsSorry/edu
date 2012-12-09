package ua.kpi.testingsystem.dao.factories;

import java.util.Locale;

import ua.kpi.testingsystem.exceptions.DAOException;
import ua.kpi.testingsystem.managers.ConfigurationManager;
import ua.kpi.testingsystem.managers.MessageManager;

/**
 * @version 1.0 27 квіт. 2011
 * @author Ivanov Yaroslav
 * 
 */
public class DAOFactoryManager {

	private static DAOFactoryManager instance = new DAOFactoryManager();
	protected DAOFactory factory;
	
	protected DAOFactoryManager() {
		final String dataSource = ConfigurationManager.getInstance().getProperty(
				ConfigurationManager.DATA_SOURCE);
		switch (DataSourceType.valueOf(dataSource.toUpperCase(Locale.ENGLISH))) {
		case JDBCDAO:
			factory = new JDBCDAOFactory();
			break;
		default :
			throw new DAOException(MessageManager.getInstance().getProperty(
					MessageManager.DATASOURCE_INVALID_NAME));
		}
	}

	public static DAOFactory getFactory() {
		return instance.factory;
	}
	
	private enum DataSourceType {
		JDBCDAO;
	}

}
