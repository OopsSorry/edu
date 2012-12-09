package ua.kpi.testingsystem.dao.implementations.jdbc;

import java.sql.Connection;

import ua.kpi.testingsystem.dao.implementations.jdbc.queries.Query;

/**
 * @author Ivanov Yaroslav
 *
 */
public abstract class JDBCAbstractDAO {

	protected Query query;
	
	protected JDBCAbstractDAO(Connection connection) {
		this.query = new Query(connection);
	}
	
	public void close() {
		query.close();
	}
	
}
