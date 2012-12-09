package ua.kpi.testingsystem.dao.connection;

import java.sql.Connection;
import java.sql.SQLException;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

;

/**
 * @author Ivanov Yaroslav
 * 
 */
public class ConnectionPool {

	DataSource dataSource;

	public ConnectionPool(String url, String driverClassName, String login,
			String password) {
		PoolProperties properties = new PoolProperties();
		properties.setUrl(url);
		properties.setDriverClassName(driverClassName);
		properties.setUsername(login);
		properties.setPassword(password);

		properties.setJmxEnabled(true);
		properties.setTestWhileIdle(false);
		properties.setTestOnBorrow(true);
		properties.setValidationQuery("SELECT 1");
		properties.setTestOnReturn(false);
		properties.setValidationInterval(30000);
		properties.setTimeBetweenEvictionRunsMillis(30000);
		properties.setMaxActive(100);
		properties.setInitialSize(10);
		properties.setMaxWait(10000);
		properties.setRemoveAbandonedTimeout(60);
		properties.setMinEvictableIdleTimeMillis(30000);
		properties.setMinIdle(10);
		properties.setLogAbandoned(true);
		properties.setRemoveAbandoned(true);
		properties.setJdbcInterceptors(
				"org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;"
					+ "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");
		dataSource = new DataSource();
		dataSource.setPoolProperties(properties);
	}

	public Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}

}
