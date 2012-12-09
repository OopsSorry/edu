package ua.kpi.testingsystem.dao.connection;

import java.sql.Connection;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import ua.kpi.testingsystem.exceptions.DAOException;
import ua.kpi.testingsystem.managers.ConfigurationManager;

/**
 * @author Ivanov Yaroslav
 * 
 */
public class ConnectionManager {

	private static final Logger LOGGER = Logger.getLogger(
			ConnectionManager.class);
	private static ConnectionManager connManager;

	private ConnectionPool connPool;

	public static synchronized ConnectionManager getInstance() {
		if (connManager == null) {
			connManager = new ConnectionManager();
		}
		return connManager;
	}

	public ConnectionManager() {
		String url = ConfigurationManager.getInstance().getProperty(
				ConfigurationManager.DATABASE_URL);
		String driver = ConfigurationManager.getInstance().getProperty(
				ConfigurationManager.DATABASE_DRIVER_NAME);
		String login = ConfigurationManager.getInstance().getProperty(
				ConfigurationManager.DATABASE_LOGIN);
		String password = ConfigurationManager.getInstance().getProperty(
				ConfigurationManager.DATABASE_PASSWORD);
		connPool = new ConnectionPool(url, driver, login, password);
	}

	public Connection getConnection() {
		try {
			return connPool.getConnection();
		} catch (SQLException e) {
			throw new DAOException(e.getMessage(), e);
		}
	}

	public void freeConnection(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			LOGGER.warn(e.getMessage(), e);
		}
	}

}
